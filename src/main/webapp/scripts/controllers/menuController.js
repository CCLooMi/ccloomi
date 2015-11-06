/**
 * Created by chenxianjun on 15/11/5.
 */
angular.module('ccloomi')
    .controller('menuCtrl',['$scope','S_pagination', function ($scope,S_pagination) {
        $scope.views=[];
    	S_pagination.pagination($('#pagination'),'view/byPage.json',20,{}, function (data,pagination) {
            $scope.views=data;
        });
    }])