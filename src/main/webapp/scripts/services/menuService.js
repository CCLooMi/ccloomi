/**
 * Created by chenxianjun on 15/11/7.
 */
angular.module('ccloomi')
    .factory('S_menu',['$http','S_dialog', function ($http,S_dialog) {
        var service={
            add: function (scope,menu) {
                if(menu){
                    scope.menu={pid:menu.id,idRoot:menu.idRoot,deepIndex:menu.deepIndex+1};
                }else{
                    scope.menu={};
                }
                S_dialog.dialog('添加菜单','views/menu/addMenu.html',scope, function () {
                    $http.post('view/add.do',scope.menu).success(function (data) {
                        if(data.code==0){
                            S_dialog.alert('添加成功','添加菜单['+scope.menu.name+']成功','success');
                            if(!scope.menu.id){
                                scope.menu.id=data.info;
                                scope.menu.idRoot=data.info;
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
                    alert('取消修改');
                    cloneA2B(cloneObj,scope.menu);
                    refreshScope(scope);
                })
            },
            remove: function (scope,menu) {
                S_dialog.alertRemove('view/remove.do',menu, function () {
                    scope.views.splice(scope.views.indexOf(menu),1);
                    refreshScope(scope);
                });
            }
        };
        return service;
    }])