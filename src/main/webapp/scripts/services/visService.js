/**
 * Created by chenxianjun on 15/11/20.
 */
angular.module('ccloomi')
    .factory('S_vis',['$http', function ($http) {
        var keyEvents={};
        var allNodes;
        var highlightActive = false;
        var service={
            network: function (scope,data,container,options) {
                scope.nodes=new vis.DataSet(data.nodes);
                scope.edges=new vis.DataSet(data.edges);
                // get a JSON object
                allNodes = scope.nodes.get({returnType:"Object"});
                if(scope.network){
                    if(!window.global)window.global={};
                    window.global.network=scope.network;
                    scope.network.setData({nodes:scope.nodes,edges:scope.edges});
                    scope.network.redraw();
                }else{
                    context.init({
                        container:$(container),
                        targetClickEvent: function(e){
                            e.preventDefault();
                            if(options.targetContextClickEvent){
                                options.targetContextClickEvent(e);
                            }
                        }
                    });
                    if(window.global&&window.global.network){
                        console.log('destroy network.');
                        window.global.network.destroy();
                    }
                    var option={
                        physics: {
                            stabilization: false,
                            solver: 'forceAtlas2Based',
                            barnesHut: {
                                gravitationalConstant: -80000,
                                springConstant: 0.001,
                                springLength: 0
                            }
                        },
                        manipulation:{
                            enabled:false,
                            addEdge:options.addEdge||function(data, callback){callback(data)},
                            editEdge:options.editEdge||function(data, callback){callback(data)}
                        },
                        interaction: {
                            tooltipDelay: 200,
                            hideEdgesOnDrag: true
                        },
                        groups: {
                            database:{
                                shape:'database'
                            }
                        },
                        nodes:{
                            shape:'dot'
                        },
                        edges:{
                            arrows:'to'
                        }
                    };
                    $.extend(option,options.option);
                    scope.network=new vis.Network(container,{nodes:scope.nodes,edges:scope.edges},option);
                    if(!window.global)window.global={};
                    window.global.network=scope.network;
                    scope.network.on('oncontext', function (params) {
                        params.event.preventDefault();
                        //纪录鼠标右击坐标，以在粘贴中需要用到
                        scope.clipboard.pointer=params.pointer;
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
                    scope.network.on('doubleClick',function(params){
                        if(options.doubleClick){
                            options.doubleClick(params);
                        }
                    });
                    scope.network.on('dragEnd',function(params){
                        if(options.dragEnd){
                            options.dragEnd(params);
                        };
                        //判断Alt键是否hold，如果是则继续addEdgeMode
                        if(keyEvents.altKey){
                            scope.network.addEdgeMode();
                        };
                    });
                    scope.network.on('click', function (params) {
                        // if something is selected:
                        if (params.nodes.length > 0) {
                            highlightActive = true;
                            var i,j;
                            var selectedNode = params.nodes[0];
                            var degrees = 2;

                            // mark all nodes as hard to read.
                            for (var nodeId in allNodes) {
                                allNodes[nodeId].color = 'rgba(200,200,200,0.5)';
                                if (allNodes[nodeId].hiddenLabel === undefined) {
                                    allNodes[nodeId].hiddenLabel = allNodes[nodeId].label;
                                    allNodes[nodeId].label = undefined;
                                }
                            }
                            var connectedNodes = scope.network.getConnectedNodes(selectedNode);
                            var allConnectedNodes = [];

                            // get the second degree nodes
                            for (i = 1; i < degrees; i++) {
                                for (j = 0; j < connectedNodes.length; j++) {
                                    allConnectedNodes = allConnectedNodes.concat(scope.network.getConnectedNodes(connectedNodes[j]));
                                }
                            }

                            // all second degree nodes get a different color and their label back
                            for (i = 0; i < allConnectedNodes.length; i++) {
                                allNodes[allConnectedNodes[i]].color = 'rgba(150,150,150,0.75)';
                                if (allNodes[allConnectedNodes[i]].hiddenLabel !== undefined) {
                                    allNodes[allConnectedNodes[i]].label = allNodes[allConnectedNodes[i]].hiddenLabel;
                                    allNodes[allConnectedNodes[i]].hiddenLabel = undefined;
                                }
                            }

                            // all first degree nodes get their own color and their label back
                            for (i = 0; i < connectedNodes.length; i++) {
                                allNodes[connectedNodes[i]].color = undefined;
                                if (allNodes[connectedNodes[i]].hiddenLabel !== undefined) {
                                    allNodes[connectedNodes[i]].label = allNodes[connectedNodes[i]].hiddenLabel;
                                    allNodes[connectedNodes[i]].hiddenLabel = undefined;
                                }
                            }

                            // the main node gets its own color and its label back.
                            allNodes[selectedNode].color = undefined;
                            if (allNodes[selectedNode].hiddenLabel !== undefined) {
                                allNodes[selectedNode].label = allNodes[selectedNode].hiddenLabel;
                                allNodes[selectedNode].hiddenLabel = undefined;
                            }
                        }
                        else if (highlightActive === true) {
                            // reset all nodes
                            for (var nodeId in allNodes) {
                                allNodes[nodeId].color = undefined;
                                if (allNodes[nodeId].hiddenLabel !== undefined) {
                                    allNodes[nodeId].label = allNodes[nodeId].hiddenLabel;
                                    allNodes[nodeId].hiddenLabel = undefined;
                                }
                            }
                            highlightActive = false
                        }

                        // transform the object into an array
                        var updateArray = [];
                        for (nodeId in allNodes) {
                            if (allNodes.hasOwnProperty(nodeId)) {
                                updateArray.push(allNodes[nodeId]);
                            }
                        }
                        scope.nodes.update(updateArray);
                    });
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

            }
        };
        return service;
    }])