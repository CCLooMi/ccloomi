/**
 * Created by chenxianjun on 16/4/17.
 */
angular.module('ccloomi')
    .factory('S_template',['$http','S_dialog', function ($http,S_dialog) {
        var service={
            add: function (scope) {
                S_dialog.dialog('添加模版','views/template/add.html',scope, function () {
                    $http.post('template/add.do',scope.template)
                        .success(function (data) {
                            if(data.code==0){
                                scope.templates.push(data.info);
                                refreshScope(scope);
                                S_dialog.alert('修改成功','成功添加模版','success');
                            }else if(data.code==1){
                                S_dialog.alert('添加失败',data.info,'error');
                            }else{
                                S_dialog.alert('添加失败','网络错误','error');
                            }
                        })
                        .error(function () {S_dialog.alert('操作失败','接口调用失败','error');});
                })
            },
            update: function (scope,template) {
                var cloneObj=cloneFrom(template);
                scope.template=template;
                S_dialog.dialog('修改模版','views/template/add.html',scope, function () {
                    $http.post('template/update.do',scope.template)
                        .success(function (data) {
                            if(data.code==0){
                                S_dialog.alert('修改成功','成功修改模版','success');
                            }else if(data.code==1){
                                S_dialog.alert('修改失败',data.info,'error');
                            }else{
                                S_dialog.alert('修改失败','网络错误','error');
                            }
                        })
                        .error(function () {S_dialog.alert('操作失败','接口调用失败','error');});
                }, function () {
                    cloneA2B(cloneObj,template);
                    refreshScope(scope);
                })
            },
            remove: function (scope,template) {
                S_dialog.alertRemove('template/remove.do',template, function () {
                    scope.templates.splice(scope.templates.indexOf(template),1);
                    refreshScope(scope);
                })
            }
        };
        return service;
    }]);