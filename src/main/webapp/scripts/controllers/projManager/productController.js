/**
 * Created by chenxianjun on 15/12/24.
 */
angular.module('ccloomi')
    .controller('productCtrl',['$scope','S_pagination', function ($scope,S_pagination) {
        $scope.products=[];
        S_pagination.pagination($('#pagination'),'product/byPage.json',20,{}, function (data) {
            $scope.products=data;
            refreshScope($scope);
        });
    }]);