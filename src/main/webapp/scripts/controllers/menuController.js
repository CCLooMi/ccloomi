/**
 * Created by chenxianjun on 15/11/5.
 */
angular.module('ccloomi')
    .controller('menuCtrl',['$scope','S_pagination','S_menu', function ($scope,S_pagination,S_menu) {
        $scope.views=[];
        $scope.menu={};
        $scope.click= function (e) {
            var target=$(e.target);
            if(target.is('legend')){
                S_menu.add($scope);
            }
        }
        $scope.add= function (menu) {
            S_menu.add($scope,menu);
        };
        $scope.update= function (menu) {
            S_menu.update($scope,menu);
        };
        $scope.remove= function (menu) {
            S_menu.remove($scope,menu);
        };
    	S_pagination.pagination($('#pagination'),'view/byPage.json',20,{}, function (data,pagination) {
            $scope.views=data;
            refreshScope($scope);
        });
    }])