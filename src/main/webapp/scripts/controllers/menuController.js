/**
 * Created by chenxianjun on 15/11/5.
 */
angular.module('ccloomi')
    .controller('menuCtrl',['$scope','S_pagination','S_menu', function ($scope,S_pagination,S_menu) {
        $scope.views=[];
        $scope.menu={};
        $scope.add= function (menu) {
            S_menu.add($scope,menu);
        };
        $scope.update= function (menu) {
            S_menu.update($scope,menu);
        };
        $scope.remove= function (menu) {
            S_menu.remove($scope,menu);
        };
    	if($('#pagination').length){
            S_pagination.pagination($('#pagination'),'view/byPage.json',10,{}, function (data,pagination) {
                $scope.views=data;
                refreshScope($scope);
            });
        }
    }])