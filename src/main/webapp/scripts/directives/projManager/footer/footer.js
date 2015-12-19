/**
 * Created by chenxianjun on 15/12/19.
 */
angular.module('ccloomi')
    .directive('projFooter', function () {
        return {
            templateUrl:'scripts/directives/projManager/footer/footer.html',
            restrict: 'E',
            replace: true
        }
    });