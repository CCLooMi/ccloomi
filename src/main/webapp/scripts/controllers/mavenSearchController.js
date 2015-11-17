/**
 * Created by chenxianjun on 15/11/17.
 */
angular.module('ccloomi')
    .controller('mavenSearchCtrl',['$scope','S_pagination', function ($scope,S_pagination) {
        $scope.results=[];
        S_pagination.paginationMavenSearch($('#pagination'),'maven.json',20,{}, function (data) {
            $scope.results=data;
            refreshScope($scope);
        })
    }])