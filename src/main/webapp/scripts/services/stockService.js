/**
 * Created by chenxianjun on 16/4/16.
 */
angular.module('ccloomi')
    .factory('S_stock',['$http','S_dialog', function ($http,S_dialog) {
        var service={
            syncCompanyInfo: function (scope,stock) {
                $http.post('stock/syncCompanyInfo.do',stock)
                    .success(function (data) {
                        if(data.code==0){
                            stock.id=data.info;
                            scope.stocks.push(stock);
                            refreshScope(scope);
                        }else if(data.code==1){
                            S_dialog.alert('添加失败',data.info,'error');
                        }else{
                            S_dialog.alert('添加失败','网络错误','error');
                        }
                    })
                    .error(function () {S_dialog.alert('操作失败','接口调用失败','error');});
            },
            syncAllCompanyInfo: function (scope) {
                
            },
            syncCoordinates: function (scope,stock) {
                
            },
            syncAllCoordinates: function (scope) {
                
            },
            removeStock: function (scope,stock) {
                
            }
        };
        return service;
    }])