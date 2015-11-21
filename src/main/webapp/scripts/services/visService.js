/**
 * Created by chenxianjun on 15/11/20.
 */
angular.module('ccloomi')
    .factory('S_vis',['$http', function ($http) {
        var keyEvents={};
        var clipboard={};
        var service={
            network: function (container,options,data,scope) {
                context.init({
                    targetClickEvent: options.targetClickEvent
                })
                scope.nodes=new vis.DataSet(data.nodes);
                scope.edges=new vis.DataSet(data.edges);
                var option={
                    manipulation:{
                        enabled:false,
                        addEdge:options.addEdge||function(data, callback){callback(data)},
                        editEdge:options.editEdge||function(data, callback){callback(data)}
                    },
                    edges:{
                        color:"green",
                        arrows:'to'
                    }
                };
                scope.network=new vis.Network(container,{nodes:scope.nodes,edges:scope.edges},option);
                scope.network.on('oncontext', function (params) {
                    params.event.preventDefault();
                    //纪录鼠标右击坐标，以在粘贴中需要用到
                    clipboard.pointer=params.pointer;
                    var dom=params.pointer.DOM;
                    var nodeid=scope.network.getNodeAt(params.pointer.DOM);
                    if(nodeid){
                        scope.network.selectNodes([nodeid],true);
                    }else{
                        var edgeid=scope.network.getEdgeAt(params.pointer.DOM);
                        if(edgeid)scope.network.selectEdges([edgeid]);
                    }
                    context.show(params,options.menu||[{icon:'fa fa-exclamation',text:'您还没有设置右键菜单'},{icon:'fa fa-hand-o-right',text:'请您在menu中进行设置'}]);
                });
                scope.network.on('doubleClick',options.doubleClick);
                scope.network.on('dragEnd',options.dragEnd);
                //监听键盘事件
                $(document).keydown(function (e) {
                    if(e.keyCode==46){//Delete

                    }else if(e.keyCode==18){//Alt
                        if(!keyEvents.altKey){
                            keyEvents.altKey= e.altKey;
                            scope.network.addEdgeMode();
                        }
                    };
                });
                $(document).keyup(function (e) {
                    if(e.keyCode==18){//Alt
                        keyEvents.altKey= e.altKey;
                        scope.network.disableEditMode();
                    }
                })
            }
        };
        return service;
    }])