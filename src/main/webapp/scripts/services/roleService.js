/**
 * Created by Chenxj on 2015/11/12.
 */
angular.module('ccloomi')
    .factory('S_role',['$http','S_jstree','S_dialog', function ($http,S_jstree,S_dialog) {
        var service={
            saveJstree: function (scope,role) {
                var saveData=S_jstree.getChangeData(scope);
                saveData.role=role;
                $http.post('role/saveJstree.do',saveData)
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
            }
        };
        return service;
    }])