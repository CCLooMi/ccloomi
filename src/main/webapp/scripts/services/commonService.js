/**
 * Created by chenxianjun on 15/10/24.
 */
angular.module('ccloomi')
    .factory('S_user', function ($http) {
        var service={
            user:{},
            roles:['admin','user'],
            permissions:['add','update','delete','select'],
            setName: function (newName) {
                service.user['name']=newName;
            },
            save:function(){},
            hasRole:function(role){
                return service.roles.indexOf(role)==-1?false:true;
            },
            hasPermission:function(permission){
                return service.permissions.indexOf(permission)==-1?false:true;
            }
        };
        return service;
    })