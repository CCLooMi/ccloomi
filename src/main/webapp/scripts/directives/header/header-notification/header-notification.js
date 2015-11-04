/**
 * Created by chenxianjun on 15/11/4.
 */
angular.module('ccloomi')
    .directive('headerNotification',function(){
        return {
            templateUrl:'scripts/directives/header/header-notification/header-notification.html',
            restrict: 'E',
            replace: true,
        }
    });