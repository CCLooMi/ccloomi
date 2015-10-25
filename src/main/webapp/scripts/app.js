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

        $urlRouterProvider.otherwise('/main');

        $stateProvider
            .state('main',{
                url:'/main',
                templateUrl:'views/pages/main.jsp',
                resolve:{
                    loadMyFiles: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'ccloomi',
                            files:[
                                'scripts/services/commonService.js'
                            ]
                        })
                    }
                }
            })
            .state('login',{
                url:'/login',
                templateUrl:'views/pages/login.jsp'
            })

    }]);

app.factory('securityInterceptor', ['$q','$location',function($q, $location) {
    return {
        // optional method
        'request': function(config) {
            // do something on success
            return config;
        },
        // optional method
        'requestError': function(rejection) {
            // do something on error
            if (canRecover(rejection)) {
                return responseOrNewPromise
            }
            return $q.reject(rejection);
        },
        // optional method
        'response': function(response) {
            // do something on success
            console.log(response.status);
            return response;
        },
        // optional method
        'responseError': function(rejection) {
            // do something on error
            console.log(rejection.status);
            if (canRecover(rejection)) {
                return responseOrNewPromise
            }
            return $q.reject(rejection);
        }
    };
}]);
app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('securityInterceptor');
}]);