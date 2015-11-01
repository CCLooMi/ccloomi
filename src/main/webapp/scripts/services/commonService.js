/**
 * Created by chenxianjun on 15/10/24.
 */
angular.module('ccloomi')
    .factory('S_user', ['$http','$location',function ($http,$location) {
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
                login: function (user,from) {
                	from=from||'/main';
                    $http.post('sys/login.xhtml',user).success(function(data){
                        if(data.code==0){
                            service.user=data.info.user;
                            service.views=data.info.views;
                            service.roles=data.info.roles;
                            service.permissions=data.info.permissions;
                            $location.path(from);
                        }else{
                            swal('登录失败',data.info,'error');
                        }
                    });
                }
            };
            return service;
        }])