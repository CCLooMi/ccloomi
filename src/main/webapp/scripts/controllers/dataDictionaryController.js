/**
 * Created by Chenxj on 2015/11/4.
 */
angular.module('ccloomi')
    .controller('dataDictionaryCtrl',['$scope','$http','S_dataDictionary','S_pagination', function ($scope,$http,S_dataDictionary,S_pagination) {
        $scope.dds=[];
        $scope.dd_new={};
        $scope.orderBySelect=function(orderBy){
            $scope.orderBy=orderBy;
        }
        //排序
        $scope.orderBy = {name:'default',label:'默认'};
        $scope.orderBys = [
            {name:'default',label:'默认'},
            {name:'name',label:'名称'},
            {name:'code',label:'编码'},
        ];
        //查找
        $scope.keyword='';
        $scope.search= function (e) {
            e.preventDefault();
            var searchData={};
            if($scope.keyword&&$scope.keyword!==''){
                $.extend(searchData,{keywords:$scope.keyword})
            }
            if($scope.orderBy.name!='default'){
                $.extend(searchData,{orderBy:$scope.orderBy.name});
            }
            S_pagination.pagination($('#pagination'),'dd/byPage.json',5,searchData,function (data,pagination) {
                $scope.dds=data;
                refreshScope($scope);
            });
        };
        $scope.click= function (e) {
            var target=$(e.target);
            if(target.is('legend')){
                S_dataDictionary.add($scope);
            }
        };
        $scope.add= function (dd) {
            S_dataDictionary.add($scope,dd);
        };
        $scope.update= function (dd) {
            S_dataDictionary.update($scope,dd);
        };
        $scope.remove= function (dd,d) {
            S_dataDictionary.remove($scope,dd,d);
        };
        S_pagination.pagination($('#pagination'),'dd/byPage.json',5,{},function (data) {
            $scope.dds=data;
            refreshScope($scope);
        })
    }]);