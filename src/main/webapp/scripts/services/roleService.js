/**
 * Created by Chenxj on 2015/11/12.
 */
angular.module('ccloomi')
    .factory('S_role',['$http','S_jstree','S_dialog', function ($http,S_jstree,S_dialog) {
        var service={
        	saveViewJstree: function (scope,role) {
                var saveData=S_jstree.getChangeData(scope);
                if(saveData){
                    saveData.role=role;
                    $http.post('role/saveViewJstree.do',saveData)
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
                    $http.post('role/savePermissionJstree.do',saveData)
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
            }
        };
        return service;
    }])