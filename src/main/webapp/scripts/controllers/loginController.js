/**
 * Created by chenxianjun on 15/10/26.
 */
angular.module('ccloomi')
    .controller('loginCtrl',['$scope','S_user','$location', function ($scope,S_user,$location) {
        $scope.user={};
        S_user.user=$scope.user;
        $scope.submit= function () {
            S_user.login($scope.user, function (data) {
                if(data.code==0){
                    S_user.user=data.info.user;
                    S_user.views=data.info.views;
                    S_user.roles=data.info.roles;
                    S_user.permissions=data.info.permissions;
                    $('form').fadeOut(500);
                    $('.wrapper').addClass('form-success');
                    //$location.path();
                    $location.replace('/main');
                }
            });
        }
    }])