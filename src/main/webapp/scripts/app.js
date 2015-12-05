'use strict';
/**
 * Created by chenxianjun on 15/10/24.
 */
var app=angular
    .module('ccloomi',[
        'oc.lazyLoad',
        'ui.router',
        'ui.bootstrap',
        'angular-loading-bar',
        'ngSanitize'
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
                                'bower_components/jstree/dist/jstree.min.js',
                                'scripts/services/roleService.js',
                                'scripts/controllers/roleController.js',
                                'bower_components/jstree/dist/themes/default/style.min.css'
                            ]
                        })
                    }
                }
            })
            .state('main.user',{
                url:'/user',
                templateUrl:'views/user/user.html',
                resolve:{
                    loadMyFiles: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'ccloomi',
                            files:[
                                'scripts/controllers/userController.js'
                            ]
                        })
                    }
                }
            })
            .state('main.dd',{
                url:'/dd',
                templateUrl:'views/dataDictionary/dataDictionary.html',
                resolve:{
                    loadMyFile: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'idccapp',
                            files:[
                                'scripts/services/dataDictionaryService.js',
                                'scripts/controllers/dataDictionaryController.js'
                            ]
                        })
                    }
                }
            })
            .state('main.icon',{
                url:'/icon',
                templateUrl:'views/icon/icon.html',
                resolve:{
                    loadMyFile: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'idccapp',
                            files:[
                                'scripts/services/iconService.js',
                                'scripts/controllers/iconController.js'
                            ]
                        })
                    }
                }
            })
            .state('main.maven',{
                url:'/maven',
                templateUrl:'views/mavenSearch/mavenSearch.html',
                resolve:{
                    loadMyFile: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'idccapp',
                            files:[
                                'scripts/controllers/mavenSearchController.js'
                            ]
                        })
                    }
                }
            })
            .state('main.db',{
                url:'/db',
                templateUrl:'views/db/db.html',
                resolve:{
                    loadMyFile: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'idccapp',
                            files:[
                                'scripts/controllers/dbController.js',
                                'styles/db.css'
                            ]
                        })
                    }
                }

            })
            .state('main.dbDesign',{
                url:'/db/:name',
                templateUrl:'views/db/design.html',
                resolve:{
                    loadMyFile: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'idccapp',
                            files:[
                                'scripts/services/visService.js',
                                'scripts/controllers/dbDesignController.js',
                                'bower_components/artDialog/dist/dialog-plus-min.js',
                                'css/ui-dialog.css',
                                'styles/dbDesign.css'
                            ]
                        })
                    }
                }
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
app.filter('highlight', ['$sce',function ($sce) {
    return function (a,b) {
        if(a&&b){
            return $sce.trustAsHtml(highlight(b+'',a+''));
        }else{
            return a;
        }
    }
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