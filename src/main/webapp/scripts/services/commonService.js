/**
 * Created by chenxianjun on 15/10/24.
 */
angular.module('ccloomi')
    .factory('S_user', function ($http) {
        var service={
            user:{},
            views:[],
            roles:[],
            permissions:[],
            setName: function (newName) {
                service.user['name']=newName;
            },
            save:function(){},
            hasRole:function(role){
                return service.roles.indexOf(role)==-1?false:true;
            },
            hasPermission:function(permission){
                return service.permissions.indexOf(permission)==-1?false:true;
            },
            login: function (user,callback) {
                $http.post('sys/login.xhtml',user).success(callback);
            }
        };
        return service;
    })