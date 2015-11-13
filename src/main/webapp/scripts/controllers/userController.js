/**
 * Created by chenxianjun on 15/11/13.
 */
angular.module('ccloomi')
    .controller('userCtrl',['$scope','S_pagination','S_user', function ($scope,S_pagination,S_user) {
        $scope.users=[];
        $scope.repeatPassword="";
        $scope.add= function () {
            S_user.add($scope);
        };
        $scope.update= function (user) {
            S_user.update($scope,user);
        },
            $scope.remove= function (user) {
                S_user.remove($scope,user);
            }
        $scope
        S_pagination.pagination($('#pagination'),'user/byPage.json',20,{}, function (data) {
            $scope.users=data;
            refreshScope($scope);
        })
    }])