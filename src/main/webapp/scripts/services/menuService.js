/**
 * Created by chenxianjun on 15/11/7.
 */
angular.module('ccloomi')
    .factory('S_menu',['$http','S_dialog','S_vis','S_jstree', function ($http,S_dialog,S_vis,S_jstree) {
        var service={
            add: function (scope,menu) {
                if(menu){
                    scope.menu={pid:menu.id,idRoot:menu.idRoot,deepIndex:menu.deepIndex+1};
                }else{
                    scope.menu={deepIndex:0};
                }
                S_dialog.dialog('添加菜单','views/menu/addMenu.html',scope, function () {
                    $http.post('view/add.do',scope.menu).success(function (data) {
                        if(data.code==0){
                            S_dialog.alert('添加成功','添加菜单['+scope.menu.name+']成功','success');
                            if(!scope.menu.id){
                                scope.menu.id=data.info;
                                scope.menu.idRoot=scope.menu.idRoot||data.info;
                                scope.views.push(scope.menu);
                                refreshScope(scope);
                            }
                        }else if(data.code==1){
                            S_dialog.alert('添加失败',data.info,'error');
                        }else{
                            S_dialog.alert('添加失败','接口[view/add]调用失败','error');
                        }
                    }).error(function () {
                        S_dialog.alert('操作异常','网络错误','error');
                    });
                })
            },
            update: function (scope,menu) {
                var cloneObj=cloneFrom(menu);
            	scope.menu=menu;
                S_dialog.dialog('修改菜单','views/menu/addMenu.html',scope, function () {
                    $http.post('view/update.do',scope.menu).success(function (data) {
                        if(data.code==0){
                            S_dialog.alert('修改成功','修改菜单['+scope.menu.name+']成功','success');
                        }else if(data.code==1){
                            S_dialog.alert('修改失败',data.info,'error');
                        }else{
                            S_dialog.alert('修改失败','接口[view/add]调用失败','error');
                        }
                    }).error(function () {
                        S_dialog.alert('操作异常','网络错误','error');
                    });
                }, function () {
                    cloneA2B(cloneObj,scope.menu);
                    refreshScope(scope);
                })
            },
            remove: function (scope,menu) {
                S_dialog.alertRemove('view/remove.do',menu, function () {
                    scope.views.splice(scope.views.indexOf(menu),1);
                    refreshScope(scope);
                });
            },
            network: function (scope) {
                scope.clipboard={};
                var options={
                    addEdge: function (data, callback) {

                    },
                    editEdge: function (data, callback) {

                    },
                    doubleClick: function (params) {

                    },
                    menu:[
                        {icon:'glyphicon glyphicon-save-file',text:"保存",action: function (e) {
                            e.preventDefault();

                        }},
                        {icon:'glyphicon glyphicon-refresh',text:"刷新",action: function (e) {
                            e.preventDefault();
                            window.location.reload();
                        }},
                        {menu_item_src:"clipboardMenu"},
                        {menu_item_src:"operateMenu"}
                    ],
                    searchable:true,
                    fullscreen:true
                };
                $http.get('view/vis.json').success(function (data) {
                    S_vis.network(scope,data,document.getElementById('network'),options);
                });

                //动态生成菜单
                function clipboardMenu(){
                    var selectNodes=scope.network.getSelectedNodes();
                    var selectEdges=scope.network.getSelectedEdges();
                    var m=[];
                    if(selectNodes.length){
                        var selectNode=scope.nodes.get(selectNodes[0]);
                        if(selectNode.group.indexOf('column')!=-1){
                            m.push({divider: true});
                            if(scope.clipboard.cute||scope.clipboard.copy){
                                m.push({icon:'glyphicon glyphicon-paste',text:'粘贴'});
                            };
                            m.push({icon:'glyphicon glyphicon-scissors',text:'剪切'});
                            m.push({icon:'glyphicon glyphicon-duplicate',text:'复制'});
                        }
                    }
                    //如果没有菜单则清空数组
                    if(m.length==1){
                        m=[];
                    }
                    return m;
                }
                function operateMenu(){
                    var selectNodes=scope.network.getSelectedNodes();
                    var selectEdges=scope.network.getSelectedEdges();
                    var m=[];
                    m.push({divider: true});
                    return m;
                }
                window.clipboardMenu=clipboardMenu;
                window.operateMenu=operateMenu;
            },
            tree: function (url,scope,data) {
                S_jstree.tree(url,data||{},scope);
            }
        };
        return service;
    }])