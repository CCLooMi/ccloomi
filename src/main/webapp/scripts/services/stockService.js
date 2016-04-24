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
                            data.info.company=data.info.name;
                            data.info.companyId=data.info.id;
                            delete data.info.id;
                            delete data.info.name;
                            $.extend(stock,data.info);
                            refreshScope(scope);
                        }else if(data.code==1){
                            S_dialog.alert('同步失败',data.info,'error');
                        }else{
                            S_dialog.alert('同步失败','网络错误','error');
                        }
                    })
                    .error(function () {S_dialog.alert('操作失败','接口调用失败','error');});
            },
            syncAllCompanyInfo: function (scope,callback) {
                $http.get('stock/syncAllCompanyInfo.do')
                    .success(function (data) {
                        if(data.code==0){
                            callback();
                        }else if(data.code==1){
                            S_dialog.alert('同步失败',data.info,'error');
                        }else{
                            S_dialog.alert('同步失败','网络错误','error');
                        }
                    })
                    .error(function () {S_dialog.alert('操作失败','接口调用失败','error');});
            },
            syncCoordinates: function (scope,stock) {
                stock.idListedCompany=stock.companyId;
                $http.post('stock/syncCoordinates.do',stock)
                    .success(function (data) {
                        if(data.code==0){
                            //$.extend(stock,data.info);
                            stock.coordinates=data.info.longitude+','+data.info.latitude;
                            refreshScope(scope);
                        }else if(data.code==1){
                            S_dialog.alert('同步失败',data.info,'error');
                        }else{
                            S_dialog.alert('同步失败','网络错误','error');
                        }
                    })
                    .error(function () {S_dialog.alert('操作失败','接口调用失败','error');});
            },
            syncAllCoordinates: function (scope,callback) {
                $http.get('stock/syncAllCoordinates.do')
                    .success(function (data) {
                        if(data.code==0){
                            callback();
                        }else if(data.code==1){
                            S_dialog.alert('同步失败',data.info,'error');
                        }else{
                            S_dialog.alert('同步失败','网络错误','error');
                        }
                    })
                    .error(function () {S_dialog.alert('操作失败','接口调用失败','error');});
            },
            removeStock: function (scope,stock) {
                
            }
        };
        return service;
    }])