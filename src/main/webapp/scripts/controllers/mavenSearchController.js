/**
 * Created by chenxianjun on 15/11/17.
 */
angular.module('ccloomi')
    .controller('mavenSearchCtrl',['$scope','S_pagination', function ($scope,S_pagination) {
        $scope.results=[];
        //查找
        $scope.keyword='';
        $scope.search= function (e,obj,core) {
            e.preventDefault();
            e.stopPropagation();
            var searchData={};
            if($scope.keyword&&$scope.keyword!==''){
                if(obj){
                    searchData.q=obj;
                }else{
                    searchData.q=$scope.keyword;
                }
                if(core){
                    searchData.core=core;
                }
                S_pagination.paginationMavenSearch($('#pagination'),'maven.json',20,searchData, function (data) {
                    $scope.results=data;
                    refreshScope($scope);
                });
            }
        };
        $scope.download= function (result,p) {
            var g=result.g;
            var a=result.a;
            var v=result.v||result.latestVersion;
            window.location='maven/download.do?g='+g+'&a='+a+'&v='+v+'&p='+p;
        }
    }])