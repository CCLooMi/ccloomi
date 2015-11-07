/**
 * Created by chenxianjun on 15/11/7.
 */
angular.module('ccloomi')
    .factory('S_menu',['$http','S_dialog', function ($http,S_dialog) {
        var service={
            add: function (scope,menu) {
                if(menu){
                    scope.menu={pid:menu.id,rootId:menu.rootId,deepIndex:menu.deepIndex+1};
                }else{
                    scope.menu={};
                }
                S_dialog.dialog('添加菜单','views/menu/addMenu.html',scope, function () {
                    alert(JSON.stringify(scope.menu));
                })
            },
            update: function (scope,menu) {
                var cloneObj=cloneFrom(menu);
            	scope.menu=menu;
                S_dialog.dialog('修改菜单','views/menu/addMenu.html',scope, function () {
                    alert('修改成功');
                }, function () {
                    alert('取消修改');
                    cloneA2B(cloneObj,scope.menu);
                })
            },
            remove: function (menu) {

            }
        };
        return service;
    }])