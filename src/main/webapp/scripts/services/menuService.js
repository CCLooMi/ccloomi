/**
 * Created by chenxianjun on 15/11/7.
 */
angular.module('ccloomi')
    .factory('S_menu',['$http','S_dialog', function ($http,S_dialog) {
        var service={
            add: function (menu,scope) {
                S_dialog.dialog('添加菜单','views/menu/addMenu.html',scope, function () {
                    
                })
            },
            update: function (menu) {

            },
            remove: function (menu) {

            }
        };
        return service;
    }])