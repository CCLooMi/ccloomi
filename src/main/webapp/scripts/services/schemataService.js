/**
 * Created by chenxianjun on 15/12/6.
 */
angular.module('ccloomi')
    .factory('S_schemata',['$http','S_dialog', function ($http,S_dialog) {
        var service={
            createDb: function (scope) {
                S_dialog.dialog('创建数据库','views/db/createDatabase.html',scope, function () {
                    $http.post('')
                });
            }
        };
        return service;
    }]);