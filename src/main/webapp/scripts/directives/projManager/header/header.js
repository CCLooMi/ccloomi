/**
 * Created by chenxianjun on 15/12/17.
 */
angular.module('ccloomi')
    .directive('projHeader',['S_user','$filter','$location',function(S_user,$filter,$location){
        return {
            templateUrl:'scripts/directives/projManager/header/header.html',
            restrict: 'E',
            replace: true,
            controller: function ($scope) {
                $scope.views=[];
                $scope.toggleNav= function (e) {
                    var target=$(e.target);
                    if(target.is('a')){
                        target.closest('ul').find('li.active').removeClass('active');
                        target.closest('li').addClass('active');
                    }
                };
                S_user.loginStatus(function (status) {
                    if(status){
                        S_user.currentUser($scope);
                    }else{
                        S_user.doLogin($location.path());
                    }
                });
            }
        }
    }]);