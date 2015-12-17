/**
 * Created by chenxianjun on 15/12/17.
 */
angular.module('ccloomi')
    .directive('projHeader',function(){
        return {
            templateUrl:'scripts/directives/projManager/header/header.html',
            restrict: 'E',
            replace: true,
        }
    });