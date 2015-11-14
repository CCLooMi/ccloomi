/**
 * Created by Chenxj on 2015/11/5.
 */
angular.module('ccloomi')
    .controller('iconCtrl',['$scope','S_pagination','S_icon', function ($scope,S_pagination,S_icon) {
        $scope.icons=[];
        $scope.icon={};
        $scope.orderBySelect=function(orderBy){
            $scope.orderBy=orderBy;
        }
        //排序
        $scope.orderBy = {name:'default',label:'默认'};
        $scope.orderBys = [
            {name:'default',label:'默认'},
            {name:'groupName',label:'图标组'},
            {name:'className',label:'图标类'},
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
            S_pagination.pagination($('#pagination'),'icon/byPage.do',10,searchData,function (data,pagination) {
                $scope.icons=data;
                refreshScope($scope);
            });
        };
        $scope.add= function () {
            S_icon.add($scope);
        };
        $scope.update= function (icon) {
            S_icon.update($scope,icon);
        };
        $scope.remove= function (icon) {
            S_icon.remove($scope,icon);
        };
        S_pagination.pagination($('#pagination'),'icon/byPage.do',10,{},function (data,pagination) {
            $scope.icons=data;
            refreshScope($scope);
        })
    }]);
