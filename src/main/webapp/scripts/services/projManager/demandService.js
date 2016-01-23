/**
 * Created by chenxianjun on 16/1/23.
 */
angular.module('ccloomi')
    .factory('S_demand',['$http','S_dialog', function ($http,S_dialog) {
        var service={
            add: function (scope) {
                S_dialog.dialog('添加需求','views/projManager/demand/add.html',scope, function () {

                }, function () {

                },null,1024);
            }
        };
        return service;
    }]);