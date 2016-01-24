/**
 * Created by chenxianjun on 16/1/23.
 */
angular.module('ccloomi')
    .factory('S_demand',['$http','S_dialog', function ($http,S_dialog) {
        var service={
            add: function (scope) {
                S_dialog.dialog('添加需求','views/projManager/demand/add.html',scope, function () {
                    $http.post('demand/add.do',scope.demand)
                        .success(function (data) {
                            if(data.code==0){
                                S_dialog.alert('添加成功','添加需求['+scope.demand.title+']成功','success');
                                if(!scope.demand.id){
                                    scope.demand.id=data.info;
                                    scope.demands.push(scope.demand);
                                    refreshScope(scope);
                                }
                            }else if(data.code==1){
                                S_dialog.alert('添加失败',data.info,'error');
                            }else{
                                S_dialog.alert('添加失败','接口[demand/add]调用失败','error');
                            }
                        })
                        .error(function () {
                            S_dialog.alert('操作异常','网络错误','error');
                        });
                }, function () {

                },null,1024);
            },
            update: function (demand,scope) {

            },
            remove: function (demand,scope) {
                S_dialog.alertRemove('demand/remove.do',demand, function () {
                    scope.demands.splice(scope.demands.indexOf(demand),1);
                    refreshScope(scope);
                });
            }
        };
        return service;
    }]);