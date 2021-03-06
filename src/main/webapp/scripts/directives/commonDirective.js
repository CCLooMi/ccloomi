/**
 * Created by chenxianjun on 16/1/4.
 */
var searchBarTemplate='<li class="search-bar"> <div class="input-group"><input type="text" class="form-control"><span class="input-group-btn"><button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button></span></div></li>';

app.directive('affix', function () {
    return {
        restrict: 'A',
        link: function (scope,element,attrs) {
            element.affix({
                offset:{
                    top: 20,
                    bottom: function () {
                        return (this.bottom = $('.footer').outerHeight(true))
                    }
                }
            });
        }
    }
})
app.directive('ccEditor',function () {
    return {
        restrict: 'A',
        require:'ngModel',
        link: function (scope,element,attrs,ngModel) {
            var editor=CKEDITOR.replace(element[0]);
            editor.on('change', function (e) {
                ngModel.$setViewValue(this.getData());
            });
        }
    }
})
app.directive('formDate',function(){
    return {
        restrict: 'A',
        link: function (scope,element,attrs) {
            element.datetimepicker({
                weekStart:1,
                autoclose:true,
                minView:attrs.minview||2,
                startView:attrs.startview||2,
                todayBtn:true,
                todayHighlight:true,
                language:'zh-CN'
            });
        }
    }
})
app.directive('formSelect',['$filter','$parse', function ($filter,$parse) {
    return {
        restrict:'A',
        require:'ngModel',
        link: function (scope,element,attrs,ngModel) {
            (function (EL) {
                element.addClass('form-select');
                element.attr('readonly',true);
                element.closest('.form-group').addClass('has-feedback');
                var dropdown=$('<div class="dropdown-menu"></div>')
                    .insertAfter(element)
                    .on({click:click});
                var searchBar=$(searchBarTemplate);
                var feedback=$('<span class="glyphicon glyphicon-asterisk form-control-feedback"></span>')
                    .insertAfter(element);
                var r=analyzeEL(EL);
                if(r.b){
                    scope.$watchCollection(r.b, function () {
                        ngModel.$setViewValue(null);
                        element.val(null);
                        dropdownHtml(r);
                    });
                }
                element.click(function () {
                    dropdown.removeAttr('style');
                });
                var  bs=[];
                function dropdownHtml(r){
                    dropdown.html('');
                    bs=$parse(r.b)(scope);
                    if(bs){
                        //var modelVL=getObjectPropertyValue(scope,attrs['ngModel']);
                        var modelVL=$parse(attrs['ngModel'])(scope);
                        for(var i=0;i<bs.length;i++){
                            if(bs[i]==modelVL||$parse(r.d)(bs[i])==modelVL){
                                element.val($parse(r.c)(bs[i]));
                                //防止model中的值写入到view中,因为两者不想等
                                ngModel.$render= function () {};
                                break;
                            }
                        }
                        if(!r.e){
                            var d='';
                            for(var i=0;i<bs.length;i++){
                                var label=$parse(r.c)(bs[i]);
                                d+='<li><a href="#" data-index="'+i+'">'+label+'</a></li>';
                                dropdown.html(d);
                            }
                        }else{
                            var d='';
                            var group;
                            var groupBy= r.e
                            //先排序
                            bs=$filter('orderBy')(bs,groupBy);
                            for(var i=0;i<scope[r.b].length;i++){
                                var label=$parse(r.c)(bs[i]);
                                if(group==undefined){
                                    group=$parse(groupBy)(bs[i]);
                                    d+='<li class="dropdown-header">'+groupBy+'::'+group+'</li>';
                                }
                                var currentGroup=$parse(groupBy)(bs[i]);
                                if(currentGroup==group){
                                    d+='<li><a href="#" data-index="'+i+'">'+label+'</a></li>';
                                }else{
                                    group=currentGroup;
                                    d+='<li class="dropdown-header">'+groupBy+'::'+group+'</li>';
                                }

                            }
                            dropdown.html(d);
                        }
                        if(attrs['searchAble'])
                        searchBar.prependTo(dropdown);
                    }
                }
                function click(e){
                    var target=$(e.target);
                    //var bs=$parse(r.b)(scope);
                    if(target.is('a')){
                        e.preventDefault();
                        if(r.d){
                            ngModel.$setViewValue($parse(r.d)(bs[target.data('index')]));
                        }else{
                            ngModel.$setViewValue(bs[target.data('index')]);
                        }
                        element.val($parse(r.c)(bs[target.data('index')]));
                        dropdown.slideUp(150);
                    }
                }
            })(attrs['formSelect']);
        }
    }
}])
app.directive('formSelectPanel',['$filter','$parse',function($filter,$parse){
    return {
        restrict: 'A',
        require:'ngModel',
        link: function (scope,element,attrs,ngModel) {
            (function (EL) {
                element.addClass('form-select-panel');
                element.attr('readonly',true);
                element.closest('.form-group').addClass('has-feedback');
                var r=analyzeEL(EL);
                var selectTemplate='<div class="dropdown-menu"></div>';
                var selectPicker=$(selectTemplate)
                    .insertAfter(element)
                    .on({click: click});
                var searchBar=$(searchBarTemplate);
                var feedback=$('<span class="glyphicon glyphicon-asterisk form-control-feedback"></span>')
                    .insertAfter(element);
                if(r.b){
                    scope.$watchCollection(r.b, function () {
                        ngModel.$setViewValue(null);
                        element.val(null);
                       dropdownHtml(r);
                    });
                }
                var bs=[];
                function dropdownHtml(r){
                    bs=$parse(r.b)(scope);
                    if(bs){
                        //var modelVL=getObjectPropertyValue(scope,attrs['ngModel']);
                        var modelVL=$parse(attrs['ngModel'])(scope);
                        for(var i=0;i<bs.length;i++){
                            if(bs[i]==modelVL||$parse(r.d)(bs[i])==modelVL){
                                element.val($parse(r.c)(bs[i]));
                                //防止model中的值写入到view中,因为两者不想等
                                ngModel.$render= function () {};
                                break;
                            }
                        }
                        if(!r.e){
                            var d='<div class="panel panel-default"><div class="panel-body">';
                            for(var i=0;i<bs.length;i++){
                                var label=$parse(r.c)(bs[i]);
                                d+='<span data-index="'+i+'">'+label+'</span>';
                            }
                            selectPicker.html(d+'</div></div>');
                        }else{
                            var d='';
                            var group;
                            var groupBy= r.e;
                            //先排序
                            bs=$filter('orderBy')(bs,groupBy);
                            for(var i=0;i<scope[r.b].length;i++){
                                var label=$parse(r.c)(bs[i]);
                                if(group==undefined){
                                    group=$parse(groupBy)(bs[i]);
                                    d+='<div class="panel panel-default"><div class="panel-heading">'+groupBy+'::'+group+'</div><div class="panel-body">';
                                }
                                var currentGroup=$parse(groupBy)(bs[i]);
                                if(currentGroup==group){
                                    d+='<span data-index="'+i+'">'+label+'</span>';
                                }else{
                                    group=currentGroup;
                                    d+='</div></div><div class="panel panel-default"><div class="panel-heading">'+groupBy+'::'+group+'</div><div class="panel-body">'+'<span data-index="'+i+'">'+label+'</span>';
                                }

                            }
                            d+='</div></div>';
                            selectPicker.html(d);
                        }
                        if(attrs['searchAble'])
                        searchBar.prependTo(selectPicker);
                    }
                }
                function click (e) {
                    var target=$(e.target);
                    //使用$parse取的值都是复制出来的故前面的排序对这里是失效的,故bs需要在外定义
                    //var bs=$parse(r.b)(scope);
                    if(target.is('span')){
                        if(r.d){
                            ngModel.$setViewValue($parse(r.d)(bs[target.data('index')]));
                        }else{
                            ngModel.$setViewValue(bs[target.data('index')]);
                        }
                        element.val($parse(r.c)(bs[target.data('index')]));
                        selectPicker.slideUp(150);
                    }
                };
                element.click(function () {
                    selectPicker.removeAttr('style');
                });
            })(attrs['formSelectPanel']);
        }
    }
}])
app.directive('ddSelect',['$http','$parse','S_constant', function ($http,$parse,S_constant) {
    return {
        restrict: 'A',
        require:'ngModel',
        link: function (scope,element,attrs,ngModel) {
            (function (EL) {
                element.addClass('dd-select');
                element.attr('readonly',true);
                element.closest('.form-group').addClass('has-feedback');
                var dropdown=$('<div class="dropdown-menu"></div>')
                    .insertAfter(element)
                    .on({click:click});
                var searchBar=$(searchBarTemplate);
                var feedback=$('<span class="glyphicon glyphicon-asterisk form-control-feedback"></span>')
                    .insertAfter(element);
                var r=analyzeEL(EL);
                var bs=[];
                if(r.b){
                    $http.post(S_constant.dd[0],{code: r.b})
                        .success(function (data) {
                            //设置data到scope中对应的属性值
                            //此处注意和ngModel绑定的对象冲突,故去掉点前面的字符(例如a.b-->b)
                            $parse(r.b.split('\\.')[0]).assign(scope,data);
                            bs=data;
                            //var modelVL=getObjectPropertyValue(scope,attrs['ngModel']);
                            var modelVL=$parse(attrs['ngModel'])(scope);
                            if(modelVL){
                                for(var i=0;i<bs.length;i++){
                                    if(bs[i]==modelVL||$parse(r.d)(bs[i])==modelVL){
                                        element.val($parse(r.c)(bs[i]));
                                        break;
                                    }
                                }
                            }

                            if(!r.e){
                                var d='';
                                for(var i=0;i<bs.length;i++){
                                    var label=$parse(r.c)(bs[i]);
                                    d+='<li><a href="#" data-index="'+i+'">'+label+'</a></li>';
                                    dropdown.html(d);
                                }
                            }else{
                                var d='';
                                var group;
                                var groupBy= r.e
                                //先排序
                                bs=$filter('orderBy')(bs,groupBy);
                                for(var i=0;i<bs.length;i++){
                                    var label=$parse(r.c)(bs[i]);
                                    if(group==undefined){
                                        group=$parse(groupBy)(bs[i]);
                                        d+='<li class="dropdown-header">'+groupBy+'::'+group+'</li>';
                                    }
                                    var currentGroup=$parse(groupBy)(bs[i]);
                                    if(currentGroup==group){
                                        d+='<li><a href="#" data-index="'+i+'">'+label+'</a></li>';
                                    }else{
                                        group=currentGroup;
                                        d+='<li class="dropdown-header">'+groupBy+'::'+group+'</li>';
                                    }

                                }
                                dropdown.html(d);
                            }
                        })
                        .error(function () {
                            console.error('接口调用失败::['+S_constant.dd[0]+']');
                        });
                }
                element.click(function () {
                    dropdown.removeAttr('style');
                });
                function click(e){
                    var target=$(e.target);
                    if(target.is('a')){
                        e.preventDefault();
                        if(r.d){
                            ngModel.$setViewValue($parse(r.d)(bs[target.data('index')]));
                        }else{
                            ngModel.$setViewValue(bs[target.data('index')]);
                        }
                        element.val($parse(r.c)(bs[target.data('index')]));
                        dropdown.slideUp(150);
                    }
                }
            })(attrs['ddSelect']);
        }
    }
}])
app.directive('code', function () {
    return {
        restrict: 'E',
        link: function (scope,element,attrs) {
            var worker=new Worker('js/Highlight.worker.js');
            worker.onmessage= function (event) {
                element.html(event.data);
            };
            worker.postMessage({text:element.text(),language:attrs.class});
        }
    }
})
//用于博客中html代码中的code语法高亮
app.directive('ngBindHtmlCompile',['$compile',function ($compile) {
    return {
        restrict: 'A',
        link: function (scope,element,attrs) {
            scope.$watch(function (scope) {
                return scope.$eval(attrs.ngBindHtmlCompile);
            }, function (value) {
                var v=$(value);
                element.html(v);
                v.find('code').each(function(i, block) {
                    hljs.highlightBlock(block);
                });
                v.find('code').removeClass('hljs');
                //$compile(element.contents())(scope);
            })
        }
    }
}])
app.directive('ccUploadField',['$parse',function ($parse) {
    return {
        restrict:'A',
        link:function (scope,element,attrs) {
            var fileAcceptPatten;
            var cf=new CCFileUpload({
                // wsuri:'ws://192.168.248.129:8080/CCFileUploadServer/springSocket/fileup',
                //wsuri:'ws://192.168.1.122:8080/CCFileUploadServer/springSocket/fileup',
                wsuri:'ws://192.168.31.249:8080/CCFileUploadServer/springSocket/fileup',
                // wsuri:'ws://localhost:8080/CCFileUploadServer/springSocket/fileup',
                addFileButton:element.find('[cc-file-add]'),
                addFilesButton:element.find('[cc-files-add]'),
                startButton:$('[cc-upload-start]'),
                beforeAddEvent:function (e) {
                    var target=$(e.target);
                    var ccFile=$(e.target).closest('[cc-file]');
                    var fileAccept;
                    if(target.is('[cc-file-add]')){
                        fileAccept=ccFile.attr('cc-file-accept');
                        if(fileAccept&&fileAccept!=''){
                            $('[cc-single]').attr('accept',fileAccept);
                        }
                    }else if(target.is('[cc-files-add]')){
                        fileAccept=ccFile.attr('cc-file-accept');
                        if(fileAccept&&fileAccept!=''){
                            $('[cc-multiple]').attr('accept',fileAccept);
                        }
                    }
                    if(fileAccept&&fileAccept!=''){
                        var fa=fileAccept.split(',');
                        var fap='';
                        for(var i=0,fp;fp=fa[i];i++){
                            if(i!=fa.length-1){
                                fap+='\\'+fp+'$|';
                            }else{
                                fap+='\\'+fp+'$';
                            }
                        }
                        fileAcceptPatten=new RegExp(fap);
                    }
                },
                onComplete:function (files) {
                    for(var i=0,f;f=files[i];i++){
                        var ccModel=f.addFileTarget.find('[cc-model]').attr('cc-model');
                        if(f.addFileTarget.find('[cc-files-add]').length){
                            var modelValue=$parse(ccModel)(scope)||[];
                            if(!modelValue.push)modelValue=[];
                            modelValue.push({id:f.fileId,name:f.name,size:f.size,type:f.type});
                            $parse(ccModel).assign(scope,modelValue);
                        }else if(f.addFileTarget.find('[cc-file-add]').length){
                            $parse(ccModel).assign(scope,{id:f.fileId,name:f.name,size:f.size,type:f.type});
                        }
                    }
                    refreshScope(scope);
                    scope.onComplete&&scope.onComplete(files);
                },
                beforeAdd:function (addFileTarget,files) {
                    addFileTarget.find('.progress').remove();
                    addFileTarget.find('[cc-file-preview]').html('');
                    var ccFile=addFileTarget.attr('cc-file');
                    if(files.length>1){
                        $parse(ccFile).assign(scope,files);
                        refreshScope(scope);
                    }else if(files.length){
                        $parse(ccFile).assign(scope,files[0]);
                        refreshScope(scope);
                    }
                },
                fileFilter:function (f) {
                    if(fileAcceptPatten){
                        return fileAcceptPatten.test(f.name);
                    }
                    return true;
                },
                onAdd:function (f) {
                    var progessInside=false;
                    var closeButton=$('<button type="button">╳</button>');
                    var operation=$('<div class="operation"></div>').append(closeButton);
                    var a=$('<a></a>').append(operation);
                    var caption=$('<div class="caption"></div>');
                    caption.append(f.progressBar);
                    var thumbnail=$('<div class="thumbnail"></div>');
                    thumbnail.append(a).append(caption);
                    closeButton.click(function(e){
                        e.stopPropagation();
                        f.removeSelf();
                        thumbnail.remove();
                    });
                    f.addFileTarget.find('[cc-file-preview]').append(thumbnail);
                    f.readSelfAsDataURL(function (dataUrl) {
                        a.css({background:'url('+dataUrl+') no-repeat center center','background-size':'cover'});
                        progessInside=true;
                    });
                    if(!progessInside){
                        a.append('<span>'+(f.name+'---'+ f.formatFileSize)+'</span>');
                    }
                },
                onProcess:function (f) {
                    refreshScope(scope);
                },
                onError:function (o) {

                },
                onHashComplete:function (f) {

                }
            });
        }
    }
}]);
app.directive('ccForm',['$parse',function ($parse) {
    return {
        restrict:'A',
        link:function (scope,element,attrs) {
            var first=true;
            var checkInterval;
            var checkTimeout;
            function showError(input,errorIndex) {
                if(first){
                    return;
                }
                var formGroup=input.closest('.form-group');
                input.addClass('hasError');
                formGroup.addClass('hasError');
                formGroup.find('.help-block').find('.cc-show').removeClass('cc-show');
                formGroup.find('.help-block').find('[cc-error-'+errorIndex+']').addClass('cc-show');
                formGroup.next('.help-block').find('.cc-show').removeClass('cc-show');
                formGroup.next('.help-block').find('[cc-error-'+errorIndex+']').addClass('cc-show');
            }
            function  hidError(input,errorIndex) {
                var formGroup=input.closest('.form-group');
                input.removeClass('hasError');
                formGroup.removeClass('hasError');
                formGroup.find('.help-block').find('[cc-error-'+errorIndex+']').removeClass('cc-show');
                formGroup.next('.help-block').find('[cc-error-'+errorIndex+']').removeClass('cc-show');
            }
            function checkInput(input) {
                var that=$(input);
                // $('[cc-email]')[0].attributes[0].name
                var attrs=input.attributes;
                var value=that.val();
                for(var i=0,attr;attr=attrs[i];i++){
                    if(attr.name=='cc-email'){
                        if(/^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/.test(value)){
                            hidError(that,attr.value)
                        }else{
                            showError(that,attr.value);
                            break;
                        }
                    }else  if(attr.name=='cc-phone'){
                        if(/\d{11}/.test(value)){
                            hidError(that,attr.value);
                        }else {
                            showError(that,attr.value);
                            break;
                        }
                    }else if(attr.name=='cc-max'){
                        if(value.length>attr.value){
                            showError(that,that.attr(attr.name+'-error'));
                            break;
                        }else {
                            hidError(that,that.attr(attr.name+'-error'));
                        }
                    }else if(attr.name=='cc-min'){
                        if(value.length<attr.value){
                            showError(that,that.attr(attr.name+'-error'));
                            break;
                        }else {
                            hidError(that,that.attr(attr.name+'-error'));
                        }
                    }else if(attr.name=='cc-regex'){
                        var patten=new RegExp(attr.value);
                        if(patten.test(value)){
                            hidError(that,that.attr(attr.name+'-error'));
                        }else {
                            showError(that,that.attr(attr.name+'-error'));
                            break;
                        }
                    }else if(attr.name=='cc-require'){
                        if(!value||value.trim()==''){
                            showError(that,attr.value);
                            break;
                        }else {
                            hidError(that,attr.value);
                        }
                    }else if(attr.name=='cc-require-condition'){
                        if($parse(attr.value)(scope)){
                            if(!value||value.trim()==''){
                                showError(that,that.attr(attr.name+'-error'));
                                break;
                            }else {
                                hidError(that,that.attr(attr.name+'-error'));
                            }
                        }
                    }
                }
            }
            function checkForm(form) {
                form.find('input,textarea,select').each(function () {
                    checkInput(this);
                });
            }
            element.find('input,textarea,select').focus(function (e) {
                var that=this;
                var value=$(this).val();
                first=false;
                clearTimeout(checkTimeout);
                checkInterval=setInterval(function () {
                    if($(that).val()!=value){
                        checkInput(that);
                    }
                },100);
            });
            element.find('input,textarea,select').blur(function (e) {
                first=false;
                checkInput(this);
                //对于某些慢些的下拉选择列表需要延迟清除检测
                checkTimeout=setTimeout(function(){
                    clearInterval(checkInterval);
                },500);
            });

            var ccSubmit=$parse(element.find('[cc-submit]').attr('cc-submit'));
            element.find('[cc-submit]').click(function (e) {
                first=false;
                clearInterval(checkInterval);
                checkForm(element);
                if(!element.find('.cc-show').length){
                    scope.$apply(function(){
                        ccSubmit(scope,{$event:e});
                    });
                }
            });
            //预检测时不显示错误提示
            checkForm(element);
        }
    }
}]);