/**
 * Created by chenxianjun on 15/11/8.
 */
angular.module('ccloomi')
    .controller('roleCtrl',['$scope','S_pagination', function ($scope,S_pagination) {
        $scope.roles=[];
        S_pagination.pagination($('#pagination'),'role/byPage.json',20,{}, function (data,pagination) {
            $scope.roles=data;
            refreshScope($scope);
        })
    }])