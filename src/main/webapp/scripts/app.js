'use strict';
/**
 * Created by chenxianjun on 15/10/24.
 */
var app=angular
    .module('ccloomi',[
        'oc.lazyLoad',
        'ui.router',
        'ui.bootstrap',
        'angular-loading-bar'
    ])
    .config(['$stateProvider','$urlRouterProvider','$ocLazyLoadProvider', function ($stateProvider,$urlRouterProvider,$ocLazyLoadProvider) {
        $ocLazyLoadProvider.config({
            debug:false,
            events:true
        });

        $urlRouterProvider.otherwise('/login');

        $stateProvider
            .state('main',{
                url:'/main',
                templateUrl:'views/pages/main.jsp',
                resolve:{
                    loadMyDirectives: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'ccloomi',
                            files:[
                            'scripts/directives/header/header.js',
                            'scripts/directives/header/header-notification/header-notification.js',
                            'scripts/directives/sidebar/sidebar.js',
                            'bower_components/paginationjs/dist/pagination.js',
                            'bower_components/artDialog/dist/dialog-plus-min.js',
                            'css/ui-dialog.css',
                            'css/pagination.css'
                            ]
                        })
                    }
                }
            })
            .state('main.menu',{
                url:'/menu',
                templateUrl:'views/menu/menu.html',
                resolve:{
                    loadMyFiles: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'ccloomi',
                            files:[
                                'scripts/services/menuService.js',
                                'scripts/controllers/menuController.js'
                            ]
                        })
                    }
                }
            })
            .state('main.role',{
                url:'/role',
                templateUrl:'views/role/role.html',
                resolve:{
                    loadMyFiles: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'ccloomi',
                            files:[
                                'scripts/controllers/roleController.js'
                            ]
                        })
                    }
                }
            })
            .state('main.dd',{
                url:'/dd',
                templateUrl:'views/dd/dataDictionary.html'
            })
            .state('login',{
                url:'/login',
                templateUrl:'views/pages/login.jsp',
                resolve:{
                    loadMyFiles: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'loginCtrl',
                            files:[
                                'scripts/controllers/loginController.js',
                                'styles/login.css'
                            ]
                        })
                    }
                }
            })

    }]);

app.factory('securityInterceptor', ['$q','$location','$timeout',function($q,$location,$timeout) {
    return {
        // optional method
        'request': function(config) {
            return config;
        },
        // optional method
        'requestError': function(rejection) {
            return $q.reject(rejection);
        },
        // optional method
        'response': function(response) {
            return response;
        },
        // optional method
        'responseError': function(rejection) {
            if(rejection.status==401){
            	$timeout(function () {
                    $location.path('/login');
                },0);
            }
            return $q.reject(rejection);
        }
    };
}]);
app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('securityInterceptor');
}]);