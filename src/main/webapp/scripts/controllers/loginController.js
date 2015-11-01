/**
 * Created by chenxianjun on 15/10/26.
 */
angular.module('ccloomi')
    .controller('loginCtrl',['$scope','S_user',function ($scope,S_user) {
        $scope.user={};
        S_user.user=$scope.user;
        $scope.submit= function () {
            S_user.login($scope.user);
            $('form').fadeOut(500);
            $('.wrapper').addClass('form-success');
        }
    }])