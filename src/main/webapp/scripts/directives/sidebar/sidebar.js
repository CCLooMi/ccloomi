/**
 * Created by chenxianjun on 15/11/4.
 */
angular.module('ccloomi')
    .directive('sidebar',['$location', function ($location) {
        return {
            templateUrl:'scripts/directives/sidebar/sidebar.html',
            restrict: 'E',
            replace: true,
            controller:function($scope){

            }
        }
    }])