/**
 * Created by Chenxj on 2015/11/10.
 */
angular.module('ccloomi')
    .factory('S_icon',['$http','S_dialog', function ($http,S_dialog) {
        var service={
            add: function (scope) {
                S_dialog.dialog('添加图标','views/icon/addIcon.html',scope, function () {
                    $http.post('icon/add.do',scope.icon)
                        .success(function (data) {
                            if(data.code==0){
                                scope.icon.id=data.info;
                                scope.icons.push(scope.icon);
                                refreshScope(scope);
                                S_dialog.alert('修改成功','成功添加图标','success');
                            }else if(data.code==1){
                                S_dialog.alert('添加失败',data.info,'error');
                            }else{
                                S_dialog.alert('添加失败','网络错误','error');
                            }
                        })
                        .error(function () {S_dialog.alert('操作失败','接口调用失败','error');});
                })
            },
            update: function (scope,icon) {
                var cloneObj=cloneFrom(icon);
                scope.icon=icon;
                S_dialog.dialog('修改图标','views/icon/addIcon.html',scope, function () {
                    $http.post('icon/update.do',scope.icon)
                        .success(function (data) {
                            if(data.code==0){
                                S_dialog.alert('修改成功','成功修改图标','success');
                            }else if(data.code==1){
                                S_dialog.alert('修改失败',data.info,'error');
                            }else{
                                S_dialog.alert('修改失败','网络错误','error');
                            }
                        })
                        .error(function () {S_dialog.alert('操作失败','接口调用失败','error');});
                }, function () {
                    cloneA2B(cloneObj,icon);
                    refreshScope(scope);
                })
            },
            remove: function (scope,icon) {
                S_dialog.alertRemove('icon/remove.do',icon, function () {
                    scope.icons.splice(scope.icons.indexOf(icon),1);
                    refreshScope(scope);
                })
            }
        };
        return service;
    }])
