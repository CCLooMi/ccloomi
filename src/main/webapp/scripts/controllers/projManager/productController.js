/**
 * Created by chenxianjun on 15/12/24.
 */
angular.module('ccloomi')
    .controller('productCtrl',['$scope','S_pagination','S_product', function ($scope,S_pagination,S_product) {
        $scope.products=[];
        $scope.product={};
        $scope.add= function () {
            S_product.add($scope);
        };
        $scope.update= function (product) {
            S_product.update($scope,product);
        };
        $scope.remove= function (product) {
            S_product.remove($scope,product);
        };
        productPagination();
        function productPagination(){
            if($('#pagination').length){
                S_pagination.pagination($('#pagination'),'product/byPage.json',20,{}, function (data) {
                    $scope.products=data;
                    refreshScope($scope);
                });
            }
        }
    }]);