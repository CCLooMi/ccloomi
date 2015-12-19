/**
 * Created by chenxianjun on 15/12/17.
 */
angular.module('ccloomi')
    .directive('projHeader',function(){
        return {
            templateUrl:'scripts/directives/projManager/header/header.html',
            restrict: 'E',
            replace: true,
            controller: function ($scope) {
                $scope.toggleNav= function (e) {
                    var target=$(e.target);
                    if(target.is('a')){
                        target.closest('ul').find('li.active').removeClass('active');
                        target.closest('li').addClass('active');
                    }
                }
            }
        }
    });