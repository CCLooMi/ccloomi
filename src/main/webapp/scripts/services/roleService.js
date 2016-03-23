/**
 * Created by Chenxj on 2015/11/12.
 */
angular.module('ccloomi')
    .factory('S_role',['$http','S_jstree','S_dialog', function ($http,S_jstree,S_dialog) {
        var service={
            add: function (scope,role) {
                scope.role=role;
                S_dialog.dialog('添加菜单','views/role/addRole.html',scope, function () {
                    $http.post('/'+app.name+'/role/add.do',scope.role).success(function (data) {
                        if(data.code==0){
                            S_dialog.alert('添加成功','添加菜单['+scope.role.username+']成功','success');
                            if(!scope.role.id){
                                scope.role.id=data.info;
                                scope.roles.push(scope.role);
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
            update: function (scope,role) {
                var cloneObj=cloneFrom(role);
                scope.role=role;
                S_dialog.dialog('修改菜单','views/role/addRole.html',scope, function () {
                    $http.post('/'+app.name+'/role/update.do',scope.role).success(function (data) {
                        if(data.code==0){
                            S_dialog.alert('修改成功','修改菜单['+scope.role.name+']成功','success');
                        }else if(data.code==1){
                            S_dialog.alert('修改失败',data.info,'error');
                        }else{
                            S_dialog.alert('修改失败','接口[role/add]调用失败','error');
                        }
                    }).error(function () {
                        S_dialog.alert('操作异常','网络错误','error');
                    });
                }, function () {
                    S_dialog.alert('取消修改','已取消修改','error');
                    cloneA2B(cloneObj,scope.role);
                    refreshScope(scope);
                })
            },
            remove: function (scope,role) {
                S_dialog.alertRemove('role/remove.do',role, function () {
                    scope.roles.splice(scope.roles.indexOf(role),1);
                    refreshScope(scope);
                });
            },
        	saveViewJstree: function (scope,role) {
                var saveData=S_jstree.getChangeData(scope);
                if(saveData){
                    saveData.role=role;
                    $http.post('/'+app.name+'/role/saveViewJstree.do',saveData)
                        .success(function (data) {
                            if(data.code==0){
                                S_dialog.alert('保存成功','数据已保存成功','success');
                            }else if(data.code==1){
                                S_dialog.alert('保存失败',data.info,'error');
                            }else{
                                S_dialog.alert('网络错误','网络出现异常','error');
                            }
                        })
                        .error(function () {
                            S_dialog.alert('操作失败','接口调用失败','error');
                        });
                }else{
                    S_dialog.alert('没有更改','数据没有变动,将不会提交','info');
                }
            },
            savePermissionJstree: function (scope,role){
                var saveData=S_jstree.getChangeData(scope);
                if(saveData){
                    saveData.role=role;
                    $http.post('/'+app.name+'/role/savePermissionJstree.do',saveData)
                        .success(function (data) {
                            if(data.code==0){
                                S_dialog.alert('保存成功','数据已保存成功','success');
                            }else if(data.code==1){
                                S_dialog.alert('保存失败',data.info,'error');
                            }else{
                                S_dialog.alert('网络错误','网络出现异常','error');
                            }
                        })
                        .error(function () {
                            S_dialog.alert('操作失败','接口调用失败','error');
                        });
                }else{
                    S_dialog.alert('没有更改','数据没有变动,将不会提交','info');
                }
            },
            addUserToRole: function (scope,user) {
                $http.post('/'+app.name+'/role/addUserToRole.do',{user:user,role:scope.role})
                    .success(function (data) {
                        if(data.code==0){
                            scope.usersInRole.push(user);
                            scope.usersNotInRole.splice(scope.usersNotInRole.indexOf(user),1);
                            refreshScope(scope);
                        }else if(data.code==1){
                            S_dialog.alert('保存失败',data.info,'error');
                        }else{
                            S_dialog.alert('网络错误','网络出现异常','error');
                        }
                    })
                    .error(function () {
                        S_dialog.alert('操作失败','接口调用失败','error');
                    });
            },
            removeUserFromRole: function (scope,user) {
                $http.post('role/removeUserFromRole.do',{user:user,role:scope.role})
                    .success(function (data) {
                        if(data.code==0){
                            scope.usersNotInRole.push(user);
                            scope.usersInRole.splice(scope.usersInRole.indexOf(user),1);
                            refreshScope(scope);
                        }else if(data.code==1){
                            S_dialog.alert('保存失败',data.info,'error');
                        }else{
                            S_dialog.alert('网络错误','网络出现异常','error');
                        }
                    })
                    .error(function () {
                        S_dialog.alert('操作失败','接口调用失败','error');
                    });
            },
            findRolesWithWhiteListASChecked: function (callback,id) {
                $http.get('role/findRolesWithWhiteListASChecked.json?id='+id).success(function (data) {
                    callback(data);
                });
            }
        };
        return service;
    }])