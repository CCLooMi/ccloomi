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
                $scope.angleClick= function (e) {
                    var target=$(e.target);
                    if(target.is('.sidebar ul li > span')){
                        target.closest('li').find('ul').slideToggle(250);
                        target.find('span').last().toggleClass('fa-rotate-180');
                    }
                };
                $scope.click= function (e) {
                    var target=$(e.target);
                    if(target.is('.sidebar ul li a')){
                        $('#side-menu').find('.active').removeClass('active');
                        target.addClass('active');
                    }
                }
            }
        }
    }])