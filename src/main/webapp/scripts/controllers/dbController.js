/**
 * Created by chenxianjun on 15/11/21.
 */
angular.module('ccloomi')
    .controller('dbCtrl',['$scope','$http','S_pagination', function ($scope,$http,S_pagination) {
        $scope.schematas=[];
        $scope.remove= function (schemata) {

        };
        //查找
        $scope.keyword='';
        $scope.search= function (e) {
            e.preventDefault();
            var searchData={};
            if($scope.keyword&&$scope.keyword!==''){
                $.extend(searchData,{keywords:$scope.keyword})
            }
            S_pagination.pagination($('#pagination'),'db/byPage.json',20,{}, function (data,pagination) {
                $scope.schematas=data;
                refreshScope($scope);
            });
        };
        S_pagination.pagination($('#pagination'),'db/byPage.json',20,{}, function (data,pagination) {
            $scope.schematas=data;
            refreshScope($scope);
        });
    }]);