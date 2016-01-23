/**
 * Created by chenxianjun on 16/1/10.
 */
angular.module('ccloomi')
    .controller('demandCtrl',['$scope','$stateParams','S_demand', function ($scope,$stateParams,S_demand) {
        var productId=$stateParams.productId;
        $scope.demand={};
        $scope.add= function () {
            S_demand.add($scope);
        };
    }]);