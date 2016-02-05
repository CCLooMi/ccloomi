/**
 * Created by chenxianjun on 16/2/5.
 */
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