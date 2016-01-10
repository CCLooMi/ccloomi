/**
 * Created by chenxianjun on 15/10/24.
 */
angular.module('ccloomi')
    .factory('S_user', ['$http','$location','$timeout','S_dialog',function ($http,$location,$timeout,S_dialog) {
        var service={
            setFrom: function (from) {
                service.from=from;
            },
            add: function (scope,user) {
                scope.user=user;
                S_dialog.dialog('添加用户','views/user/addUser.html',scope, function () {
                    $http.post('user/add.do',scope.user).success(function (data) {
                        if(data.code==0){
                            S_dialog.alert('添加成功','添加用户['+scope.user.username+']成功','success');
                            scope.user.id=data.info;
                            scope.users.push(scope.user);
                            refreshScope(scope);
                        }else if(data.code==1){
                            S_dialog.alert('添加失败',data.info,'error');
                        }else{
                            S_dialog.alert('添加失败','接口[user/add]调用失败','error');
                        }
                    }).error(function () {
                        S_dialog.alert('操作异常','网络错误','error');
                    });
                })
            },
            update: function (scope,user) {
                var cloneObj=cloneFrom(user);
                scope.user=user;
                S_dialog.dialog('修改用户','views/user/addUser.html',scope, function () {
                    $http.post('user/update.do',scope.user).success(function (data) {
                        if(data.code==0){
                            S_dialog.alert('修改成功','修改用户['+scope.user.username+']成功','success');
                        }else if(data.code==1){
                            S_dialog.alert('修改失败',data.info,'error');
                        }else{
                            S_dialog.alert('修改失败','接口[user/update]调用失败','error');
                        }
                    }).error(function () {
                        S_dialog.alert('操作异常','网络错误','error');
                    });
                }, function () {
                    S_dialog.alert('取消修改','已取消修改','error');
                    cloneA2B(cloneObj,scope.user);
                    refreshScope(scope);
                })
            },
            remove: function (scope,user) {
                S_dialog.alertRemove('user/remove.do',user, function () {
                    scope.users.splice(scope.users.indexOf(user),1);
                    refreshScope(scope);
                });
            },
            hasRole:function(role){
                return service.roles.indexOf(role)==-1?false:true;
            },
            hasPermission:function(permission){
                return service.permissions.indexOf(permission)==-1?false:true;
            },
            doLogin: function (from) {
                service.setFrom(from);
                $timeout(function () {
                    $location.path('/login');
                },0);
            },
            login: function (user,callback,from) {
                from=from||service.from||'/main';
                $http.post('sys/login.do',user).success(function(data){
                    if(data.code==0){
                        service.user=data.info.user;
                        service.views=data.info.views;
                        service.roles=data.info.roles;
                        service.permissions=data.info.permissions;
                        callback();
                        $timeout(function () {
                            $location.path(from);
                        },1000);
                    }else{
                        swal('登录失败',data.info,'error');
                    }
                }).error(function () {
                    swal('操作异常','网络错误','error');
                });
            },
            loginStatus: function (callback) {
                $http.post('sys/loginStatus.json')
                    .success(function (status) {
                        service.isLogin=status;
                        callback(service.isLogin);
                    });
            },
            currentUser: function (scope,callback) {
                $http.post('sys/currentUser.json').success(function (data) {
                    service.user=data.user;
                    service.views=data.views;
                    service.roles=data.roles;
                    service.permissions=data.permissions;
                    scope.views=data.views;
                    callback&&callback();
                })
            }
        };
        return service;
    }])
    .factory('S_pagination',['$http','$location','S_user', function ($http,$location,S_user) {
        var service={
            pagination: function (paginationContainer,dataUrl,pageSize,data,callback,beforeSend) {
                if(paginationContainer.data('pagination')){
                    paginationContainer.pagination('destroy');
                }
                paginationContainer.pagination({
                    dataSource: dataUrl,
                    locator: 'data',
                    pageSize: pageSize,
                    ajax: {
                        type:'POST',
                        data:data,
                        statusCode:{
                            401: function () {
                                S_user.doLogin($location.path());
                            }
                        },
                        contentType:'application/json ;charset=UTF-8',
                        beforeSend: beforeSend|| function () {}
                    },
                    callback: callback
                })
            },
            paginationMavenSearch: function (paginationContainer,dataUrl,pageSize,data,callback) {
                if(paginationContainer.data('pagination')){
                    paginationContainer.pagination('destroy');
                }
                paginationContainer.pagination({
                    dataSource:dataUrl,
                    locator:'docs',
                    alias:{pageSize:'rows',pageNumber:'start',totalNumber:'numFound'},
                    pageSize: pageSize,
                    ajax: {
                        type:'POST',
                        data:data,
                        contentType:'application/json ;charset=UTF-8',
                    },
                    callback: callback
                })
            }
        };
        return service;
    }])
    .factory('S_dialog',['$http','$compile', function ($http,$compile) {
        var service={
            dialog: function (title,templateUrl,scope,ok,cancel,afterShow,width,options) {
                $http.get(templateUrl,{cache:true}).success(function (data) {
                    var d=CCDialog($.extend({
                        title:title,
                        content:data,
                        okValue:'确定',
                        width:width||600,
                        ok: ok||function(){},
                        cancelValue:'取消',
                        cancel: cancel||function(){}
                    },options));
                    d.showModal();
                    if(afterShow){
                        afterShow();
                    }
                    $compile('#CCModal')(scope);
                }).error(function () {
                    swal('操作异常','网络错误','error');
                });
            },
            dialogHtml: function (title,templateHtml,scope,ok,cancel,afterShow,width,options) {
                var d=CCDialog($.extend({
                    title:title,
                    content:templateHtml,
                    okValue:'确定',
                    width:width||600,
                    ok: ok||function(){},
                    cancelValue:'取消',
                    cancel: cancel||function(){}
                },options));
                d.showModal();
                if(afterShow){
                    afterShow();
                }
                $compile('#CCModal')(scope);
            },
            alert: function (title,text,type) {
                swal(title,text,type||'info');
            },
            alertRemove: function (url,data,callback) {
                swal({
                    title:'你确定要删除'+(data.name?('['+data.name+']'):'')+'吗?',
                    text:'删除之后将无法恢复!',
                    type:'warning',
                    showCancelButton:true,
                    cancelButtonText:'取消',
                    confirmButtonText:'确定',
                    closeOnConfirm:false,
                    closeOnCancel:false,
                    showLoaderOnConfirm:true
                }, function (isConfirm) {
                    if(isConfirm){
                        $http.post(url,data).success(function (dt) {
                            if(dt.code==0){
                                swal('删除成功','数据已删除','success');
                                callback();
                            }else if(dt.code==1){
                                swal('删除失败',dt.info,'error');
                            }else {
                                swal('删除失败','接口['+url+']调用失败','error');
                            }
                        }).error(function () {
                            swal('操作异常','网络错误','error');
                        });
                    }else{
                        swal('已取消','数据将不会删除','error');
                    }
                });
            }
        };
        return service;
    }])
    .factory('S_jstree',['$http', function ($http) {
        var service={
            getChangeData: function (scope) {
                var jstree=$.jstree.reference('#jstree');
                var ids=jstree.get_selected();
                function pushAllA2B(a,b){
                    for(var i in a){
                        b.push(a[i]);
                    }
                }
                function getPids(ids){
                    var pidsToReturn=[];
                    var pids=[];
                    for(var i in ids){
                        var id=ids[i];
                        var pid=jstree.get_parent(id);
                        //pid=='#'表示没有父菜单
                        //ids可能会包含父ID
                        if(pids.indexOf(pid)==-1&&pid!='#'&&ids.indexOf(pid)==-1){
                            pids.push(pid);
                        }
                    }
                    if(pids.length>0){
                        pushAllA2B(pids,pidsToReturn);
                        pushAllA2B(getPids(pids),pidsToReturn);
                    }
                    return pidsToReturn;
                }
                var pids=getPids(ids);
                pushAllA2B(pids,ids);
                var data={};
                //获取需要删除的ids
                //selectIdsBeforeChange-ids
                data.remove=Array.minus(scope.selectedIds,ids);
                //获取需要添加的ids
                //ids-selectIdsBeforeChange
                data.add=Array.minus(ids,scope.selectedIds);
                if(data.add.length>0||data.remove.length>0){
                    return data;
                }
            },
            jstree: function (url,data,scope,dnd) {
                var plugins=["checkbox"];
                dnd&&plugins.push("dnd");
                $http.post(url,data).success(function (dt) {
                    $('#jstree').jstree({'plugins':plugins, 'core' : {'data' :dt.data}});
                    scope.selectedIds=dt.ids;
                });
            }
        };
        return service;
    }])