/**
 * Created by chenxianjun on 16/4/17.
 */
angular.module('ccloomi')
    .controller('templateCtrl',['$scope','S_pagination','S_template', function ($scope,S_pagination,S_template) {
        $scope.templates=[];
        $scope.template={};
        $scope.search= function (keyword) {
            var searchData={};
            if(keyword&&keyword!==''){
                $.extend(searchData,{keywords:keyword})
            }
            S_pagination.pagination($('#pagination'),'template/byPage.do',10,searchData,function (data,pagination) {
                $scope.templates=data;
                refreshScope($scope);
            });
        };
        $scope.add= function () {
            S_template.add($scope);
        };
        $scope.update= function (template) {
            S_template.update($scope,template);
        };
        $scope.remove= function (template) {
            S_template.remove($scope,template);
        };
        S_pagination.pagination($('#pagination'),'template/byPage.do',10,{},function (data,pagination) {
            $scope.templates=data;
            refreshScope($scope);
        })
    }]);