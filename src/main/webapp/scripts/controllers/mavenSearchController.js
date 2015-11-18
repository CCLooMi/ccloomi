/**
 * Created by chenxianjun on 15/11/17.
 */
angular.module('ccloomi')
    .controller('mavenSearchCtrl',['$scope','S_pagination', function ($scope,S_pagination) {
        $scope.results=[];
        //查找
        $scope.keyword='';
        $scope.search= function (e,obj) {
            e.preventDefault();
            e.stopPropagation();
            var searchData={};
            if($scope.keyword&&$scope.keyword!==''){
                if(obj){
                    searchData.q=obj;
                }else{
                    searchData.q=$scope.keyword;
                }
                S_pagination.paginationMavenSearch($('#pagination'),'maven.json',20,searchData, function (data) {
                    $scope.results=data;
                    refreshScope($scope);
                });
            }
        };
    }])