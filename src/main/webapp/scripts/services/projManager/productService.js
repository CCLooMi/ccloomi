/**
 * Created by chenxianjun on 15/12/26.
 */
angular.module('ccloomi')
    .factory('S_product',['$http','S_dialog', function ($http,S_dialog) {
        var service={
            add: function (scope) {
                scope.product={};
                S_dialog.dialog('添加产品','views/projManager/product/add.html',scope, function () {
                    $http.post('product/add.do',scope.product).success(function (data) {
                        if(data.code==0){
                            S_dialog.alert('添加成功','添加产品['+scope.product.name+']成功','success');
                            if(!scope.product.id){
                                scope.product.id=data.info;
                                scope.products.push(scope.product);
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
                }, function () {

                },null,1024);
            },
            update: function (scope,product) {
                var oldProduct=cloneFrom(product);
                scope.product=product;
                S_dialog.dialog('编辑产品','views/projManager/product/add.html',scope, function () {
                    $http.post('product/update.do',scope.product).success(function (data) {
                        if(data.code==0){
                            S_dialog.alert('保存成功','修改产品['+scope.product.name+']成功','success');
                        }else if(data.code==1){
                            S_dialog.alert('保存失败',data.info,'error');
                        }else{
                            S_dialog.alert('保存失败','接口[view/add]调用失败','error');
                        }
                    }).error(function () {
                        S_dialog.alert('操作异常','网络错误','error');
                    });
                }, function () {
                    cloneA2B(oldProduct,product);
                    refreshScope(scope);
                },null,1024);
            },
            remove: function (scope,product) {
                S_dialog.alertRemove('product/remove.do',product, function () {
                    scope.products.splice(scope.products.indexOf(product),1);
                    refreshScope(scope);
                })
            }
        };
        return service;
    }]);