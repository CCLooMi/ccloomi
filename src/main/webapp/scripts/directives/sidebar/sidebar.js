/**
 * Created by chenxianjun on 15/11/4.
 */
angular.module('ccloomi')
    .directive('sidebar',['$location','S_user', function ($location,S_user) {
        return {
            templateUrl:'scripts/directives/sidebar/sidebar.html',
            restrict: 'E',
            replace: true,
            controller:function($scope){
                if(!S_user.views){
                    S_user.currentUser($scope);
                }
                $scope.views=S_user.views;
            }
        }
    }])