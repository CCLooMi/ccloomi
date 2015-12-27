/**
 * Created by chenxianjun on 15/12/24.
 */
angular.module('ccloomi')
    .controller('productCtrl',['$scope','S_pagination', function ($scope,S_pagination) {
        $scope.products=[];
        $scope.product={};
        $scope.addProduct= function () {
            alert($scope.product.desc);
            alert(CKEDITOR.instances.editor1.getData());
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