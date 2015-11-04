/**
 * Created by chenxianjun on 15/11/4.
 */
angular.module('ccloomi')
    .directive('header',function(){
        return {
            templateUrl:'scripts/directives/header/header.html',
            restrict: 'E',
            replace: true,
        }
    });