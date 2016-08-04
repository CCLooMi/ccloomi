/**
 * Created by Chenxj on 2016/1/12.
 */
(function ($) {
    var CCFileUpload= function (option,element) {
        //var that=this;
        this.element=$(element);
        this.container=option.container||'body';
        this.concurrentHash=option.concurrentHash||option.concurrentUpload||3;
        this.concurrentUpload=option.concurrentUpload||3;
        this.concurrentHashCurrent=0;
        if(option.wsuri&&option.wsuri.match(/ws:\/\//)){
            this.wsuri=option.wsuri;
        }else{
            this.wsuri='ws'+window.location.href.match(/:\/\/\w+\.\w+\.\w+\.\w+:?\w*\/\w+\/|:\/\/\w+:?\w*\/\w+\//)[0]+option.wsuri;
        }
        this.multiple=option.multiple;
        this.draggable=option.draggable||false;
        UPGlobal.debugMode=option.debugMode||false;
        this.fileFilter=option.fileFilter||function () {return true;};
        this.fileInput=$(UPGlobal.singleFileInput);
        this.fileInput.appendTo(this.container).on({change: $.proxy(this.fileSelect,this)});
        this.filesInput=$(UPGlobal.multipleFileInput);
        this.filesInput.appendTo(this.container).on({change: $.proxy(this.fileSelect,this)});
        //在添加文件事件触发之前调用的方法
        this.beforeAddEvent=option.beforeAddEvent||function (e) {};
        this.addFileButton=option.addFileButton;
        if(this.addFileButton){
            this.addFileButton
                .on({click: $.proxy(function(e){
                    this.beforeAddEvent(e);
                    this.addFileTarget=$(e.target);
                    this.fileInput.trigger('click');
            },this)});
        }
        this.addFilesButton=option.addFilesButton;
        if(this.addFilesButton){
            this.addFilesButton.
            on({click: $.proxy(function(e){
                this.beforeAddEvent(e);
                this.addFileTarget=$(e.target);
                this.filesInput.trigger('click');
            },this)});
        };
        this.startButton=option.startButton;
        if(this.startButton){this.startButton.on({click: $.proxy(function(){this.startUpload()},this)});};
        if(this.draggable){this.attachDragEvents(this.element);}
        this.onProcess=function(f){
            f.progressBar.find('.progress-bar').css({width: f.progress});
            option.onProcess&&option.onProcess(f);
        };
        this.onComplete=option.onComplete||function(filesUploaded){};
        this.beforeAdd=option.beforeAdd||function (addFileTarget,files) {};
        this.onAdd=option.onAdd||function(f){};
        this.onError=option.onError||function(o){};
        this.onHashComplete=option.onHashComplete|| function (f) {};
        this.initWSpool();
    };
    CCFileUpload.prototype={
        initWSpool: function () {
            if('WebSocket' in window){
                for(var i=0;i<this.concurrentUpload;i++){
                    var ws=new WebSocket(this.wsuri);
                    ws.onopen=this.onOpen;
                    ws.onmessage=this.onMessage(this);
                    ws.onclose=this.onClose;
                    UPGlobal.WSpool.push(ws);
                }
            }else if('MozWebSocket' in window){
                for(var i=0;i<this.concurrentUpload;i++){
                    var ws=new MozWebSocket(this.wsuri);
                    ws.onopen=this.onOpen;
                    ws.onmessage=this.onMessage(this);
                    ws.onclose=this.onClose;
                    UPGlobal.WSpool.push(ws);
                }
            }else{
                log("WebSocket create error.");
                this.onError({code:1,info:'WebSocket create error.'});
            }
        },
        onOpen:function(){
            log("WebSocket is opened.");
        },
        onMessage:function(that){
            return function(e){
                var ws=this;
                var command=JSON.parse(e.data);
                var f=UPGlobal.allFiles[command.fileId];
                if(command.type=='uploadCommand'){
                    f.progress=parseInt(command.completePercent*65+35,10)+'%';
                    that.onProcess(f);
                    log('文件完成：'+ f.progress);
                    if(command.completePercent!=1){
                        that.doUploadByCommand(ws,command);
                    }else{
                        f.progress='100%';
                        that.onProcess(f);
                        log('文件上传完成，开始上传下一个文件。');
                        that.uploadNextFile(ws);//该ws完成文件上传，接着继续上传下一个文件
                    }
                }//if end
            }
        },
        onClose: function () {
            log('WebSocket is closed.');
        },
        attachDragEvents:function(element){
            element[0].addEventListener('dragover', $.proxy(this.dragOver,this));
            element[0].addEventListener('drop', $.proxy(this.fileSelect,this));
        },
        dragOver:function(e){
            e.stopPropagation();
            e.preventDefault();
        },
        fileSelect:function(e){
            e.stopPropagation();
            e.preventDefault();
            var files= e.dataTransfer ? e.dataTransfer.files : e.target.files;
            if(e.dataTransfer){
                this.beforeAddEvent(e);
                //表示是拖动事件
                this.addFileTarget=$(e.target);
            }
            this.addFiles(files);
        },
        startHashFile:function(){
            for(var f,worker;this.concurrentHashCurrent<this.concurrentHash;this.concurrentHashCurrent++){
                if(UPGlobal.filesToUpload.length==0)break;
                f=UPGlobal.filesToUpload.shift();
                worker=getWorker();
                worker.addEventListener("message",this.handleHashWorkerEvent(f));
                this.doHashWork(f,worker);
            }
        },
        hashNextFile: function () {
            if(UPGlobal.filesToUpload.length!=0){
                var f=UPGlobal.filesToUpload.shift();
                var worker=getWorker();
                worker.addEventListener('message', this.handleHashWorkerEvent(f));
                this.doHashWork(f,worker);
            }else{
                //Hash计算完毕要恢复此值
                this.concurrentHashCurrent=0;
            }
        },
        doHashWork:function(file,worker){
            file.worker=worker;
            var buffer_size = 512 * 1024 ;//512KB
            worker.addEventListener('message', function (event) {
                var block=event.data.block;
                if(event.data.firstHash){
                    var fileId=getFromDisk(event.data.firstHash);
                    if(fileId){
                        log('文件本地存储的hash值：'+fileId);
                        stopHash(worker,block);
                    }
                }
                if(event.data.result){
                    if(file.firstHash){
                        log('存储文件hash值：'+event.data.result);
                        saveToDisk(file.firstHash,event.data.result);
                    }
                    stopHash(worker,block);
                }else{
                    if (block.end !== file.size) {
                        block.start += buffer_size;
                        block.end += buffer_size;
                        block.step++;
                        if (block.end > file.size) {
                            block.end = file.size;
                        }
                        readFile(file,block);
                    }
                }
            });
            function stopHash(worker,block){
                block.stop=true;
                worker.postMessage({
                    'block': block
                });
            }
            function readFile(file,block){
                var reader  = new FileReader();
                var blob    = file.slice(block.start, block.end);
                reader.onload = function (event) {
                    worker.postMessage({
                        'message': event.target.result,
                        'block': block
                    });
                };
                reader.readAsArrayBuffer(blob);
            };
            var block = {file_size:file.size,start:0,end:(buffer_size>file.size?file.size:buffer_size),step:0};
            readFile(file,block);
        },
        handleHashWorkerEvent:function(f){
            var that=this;
            return function (e) {
                if(e.data.firstHash){
                    f.firstHash=e.data.firstHash;
                    var fileId=getFromDisk(e.data.firstHash);
                    if(fileId){
                        f.fileId=fileId;
                    }
                }
                if (e.data.result||f.fileId) {
                    that.concurrentHashCurrent--;
                    f.fileId= f.fileId||e.data.result;
                    f.progress='35%';
                    UPGlobal.allFiles[f.fileId]=f;
                    that.onProcess(f);
                    that.onHashComplete(f);
                    f.worker.terminate();//关闭worker
                    log('文件已算出hash值，开始上传文件：'+ f.name);
                    that.startUploadFile(f);
                    that.hashNextFile();//hash next file
                } else {
                    f.progress= parseInt(e.data.block.end * 35 / e.data.block.file_size,10)+'%';
                    that.onProcess(f);
                }
            };
        },
        startUploadFile:function(f){
            if(UPGlobal.WSpool.length!=0){
                var ws=UPGlobal.WSpool.shift();//返回第一个元素，pop（）返回最后一个元素。
                this.sendFileInfo(ws,f);
                log('已从ws连接池中取出第一个ws连接，已发送['+ f.name+']文件信息，正在使用的ws连接加1.');
                UPGlobal.filesUploaded.push(f);
            }else{
                UPGlobal.filesHasHashed.push(f);
                log('ws连接池中没有可用连接，已将['+ f.name+']放入已计算Hash文件池中.')
            }
        },
        uploadNextFile:function(ws){
            if(UPGlobal.filesHasHashed.length!=0){
                var f=UPGlobal.filesHasHashed.shift();
                this.sendFileInfo(ws,f);
                log('从已计算Hash文件池中取出文件['+ f.name+']，并发送文件信息.');
                UPGlobal.filesUploaded.push(f);
            }else{//文件上传结束，回收ws连接
                UPGlobal.WSpool.push(ws);
                log('没有已计算出Hash值的文件可上传，回收ws连接到连接池中.');
            }
        },
        sendFileInfo:function(ws,f){
            var fileInfo={};
            fileInfo.fileName= f.name;
            fileInfo.fileSize= f.size;
            fileInfo.fileInfo= f.type;
            fileInfo.fileId= f.fileId;
            ws.send(JSON.stringify(fileInfo));
        },
        doUploadByCommand:function(ws,command){
            var f=UPGlobal.allFiles[command.fileId];
            if(f){
                this.readFileByCommand(f,command,function (data) {
                    ws.send(data);
                });
            }else{
                this.uploadNextFile(ws);
            }
        },
        readFileByCommand: function (file,command,callback) {
            var reader=new FileReader();
            reader.onload=function(e){
                callback(e.target.result);
            };
            reader.readAsArrayBuffer(file.slice(command.indexStart,command.indexEnd));
        },
        formatFileSize:function(fileSize,lenght){
            var r;
            var l=lenght||2;
            for(var k in UPGlobal.fileSizeFormats){
                var v=UPGlobal.fileSizeFormats[k];
                r=fileSize/v;
                if(r>=1&&r<1000){
                    r=parseInt(r*Math.pow(10,l),10)/Math.pow(10,l)+k;
                    break;
                }
            }
            return r;
        },
        addFiles:function(files){
            //移除和当前addFileTarget相关的历史文件
            var fs=[];
            for(var i=0,f;f=UPGlobal.filesToUpload[i];i++){
                if(!f.addFileTarget.is(this.addFileTarget)){
                    fs.push(f);
                }else {
                    UPGlobal.allFilesCount--;
                }
            }
            UPGlobal.filesToUpload=fs;
            var selectedfiles=[];
            for(var i= 0,f;f=files[i];i++){
                if(this.fileFilter(f)){
                    selectedfiles.push(f);
                }
            }
            this.beforeAdd(this.addFileTarget,selectedfiles);
            for(var i= 0,f;f=selectedfiles[i];i++){
                UPGlobal.filesToUpload.push(f);
                f.formatFileSize=this.formatFileSize(f.size);
                f.progressBar=$('<div class="progress"><div class="progress-bar progress-bar-striped active"></div></div>');
                f.addFileTarget=this.addFileTarget;
                f.readSelfAsDataURL=function (callback) {
                    var thisFile=this;
                    if(!f.type.match('image.*')){
                        return;
                    }
                    var reader=new FileReader();
                    reader.onload=function (e) {
                        callback&&callback(e.target.result);
                    };
                    reader.readAsDataURL(thisFile);
                }
                this.onAdd(f);
                UPGlobal.allFilesCount++;
            }
        },
        startUpload:function(){
            if(UPGlobal.filesToUpload.length!=0&&!UPGlobal.interval){
                UPGlobal.onUploading=true;
                this.startHashFile();
                //因为有并发问题，故使用定时器监听是否上传完成
                UPGlobal.interval=setInterval(this.checkComplet(this),250);
            };
        },
        checkComplet: function (that) {
            return function(){
                if(UPGlobal.onUploading&&UPGlobal.WSpool.length==that.concurrentUpload&&UPGlobal.allFilesCount==UPGlobal.filesUploaded.length){
                    clearInterval(UPGlobal.interval);
                    UPGlobal.interval=undefined;
                    that.onComplete(UPGlobal.filesUploaded);
                    UPGlobal.onUploading=false;
                }
            }
        }
    };
    var UPGlobal={
        debugMode:false,
        onUploading:false,
        allFilesCount:0,
        allFiles:{},
        //用于删除中不同状态进行不同的处理
        fileUploadStepMap:{},//0,nothing;1,onHashing;2,onWaiting;3,onUploading;4,onSuccess
        filesToUpload:[],
        filesHasHashed:[],
        filesUploaded:[],
        WSpool:[],
        fileSizeFormats:{
            'Byte':Math.pow(10,0),
            'KB':Math.pow(10,3),
            'MB':Math.pow(10,6),
            'GB':Math.pow(10,9),
            'TB':Math.pow(10,12),
            'PB':Math.pow(10,15),
            'EB':Math.pow(10,18),
            'ZB':Math.pow(10,21),
            'YB':Math.pow(10,24),
            'BB':Math.pow(10,27)
        },
        multipleFileInput:'<input cc-multiple type="file" multiple style="display: none"/>',
        singleFileInput:'<input cc-single type="file" style="display: none"/>'
    };
    var countProperties=function(o){
        var i=0;
        for(var k in o){i++;}
        return i;
    };
    var getWorker=function(){
        return new Worker("js/CCHashFile.worker.js");
    };
    var log=function(message){
        if(UPGlobal.debugMode){
            window.console.log(message);
        }
    };
    var error=function(message){
        if(UPGlobal.debugMode){
            window.console.error(message);
        }
    };
    function saveToDisk(key,value){
        localStorage[key]=value;
    };
    function getFromDisk(key){
        return localStorage[key];
    };
    window.CCFileUpload=CCFileUpload;
    window.CCFileUpload.prototype=CCFileUpload.prototype;
    window.CCFileUpload.UPGlobal=UPGlobal;
})(jQuery);