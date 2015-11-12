/**
 * Created by chenxianjun on 15/10/24.
 */
angular.module('ccloomi')
    .factory('S_user', ['$http','$location','$timeout',function ($http,$location,$timeout) {
        var service={
                //user:{},
                //views:[],
                //roles:[],
                //permissions:[],
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
                login: function (user,callback,from) {
                	from=from||'/main';
                    $http.post('sys/login.xhtml',user).success(function(data){
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
                currentUser: function (scope) {
                    $http.post('sys/currentUser.json').success(function (data) {
                        service.user=data.user;
                        service.views=data.views;
                        service.roles=data.roles;
                        service.permissions=data.permissions;
                        scope.views=data.views;
                    })
                }

            };
            return service;
        }])
    .factory('S_pagination',['$http', function ($http) {
        var service={
            pagination: function (paginationContainer,dataUrl,pageSize,data,callback,beforeSend) {
                paginationContainer.pagination({
                    dataSource: dataUrl,
                    locator: 'data',
                    pageSize: pageSize,
                    ajax: {
                        type:'POST',
                        data:data,
                        contentType:'application/json ;charset=UTF-8',
                        beforeSend: beforeSend|| function () {}
                    },
                    callback: callback
                })
            }
        };
        return service;
    }])
    .factory('S_dialog',['$http','$compile', function ($http,$compile) {
        var service={
            dialog: function (title,templateUrl,scope,ok,cancel,afterShow,width) {
                $http.get(templateUrl,{cache:true}).success(function (data) {
                    var d=dialog({
                        title:title,
                        content:data,
                        okValue:'确定',
                        width:width||500,
                        ok: ok||function(){},
                        cancelValue:'取消',
                        cancel: cancel||function(){}
                    });
                    d.showModal();
                    if(afterShow){
                        afterShow();
                    }
                    $compile('.ui-dialog')(scope);
                }).error(function () {
                    swal('操作异常','网络错误','error');
                });
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
            getChangeData: function (containerId,selectIdsBeforeChange) {
                var jstree=$.jstree.reference(containerId);
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
                        if(pids.indexOf(pid)==-1&&pid!='#'){
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
                data.remove=Array.minus(selectIdsBeforeChange,ids);
                //获取需要添加的ids
                //ids-selectIdsBeforeChange
                data.add=Array.minus(ids,selectIdsBeforeChange);
                return data;
            }
        };
        return service;
    }])