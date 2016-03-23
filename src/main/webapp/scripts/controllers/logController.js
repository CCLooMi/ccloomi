/**
 * Created by chenxianjun on 16/1/23.
 */
angular.module('ccloomi')
    .controller('logCtrl',['$scope', function ($scope) {
        $scope.log= function (msg) {
            $('.log .log').append('<p>'+msg+'</p>');
        };
        $scope.cleanLog= function () {
            $('.log .log').html('');
        };
        if(!$scope.logSocket){
            var logSocket=new WebSocket('ws'+window.location.href.match(/:\/\/\w+\.\w+\.\w+\.\w+:?\w*\/\w+\/|:\/\/\w+:?\w*\/\w+\/|:\/\/\w+\/|:\/\/\w+\.\w+\.\w+\.\w+\//)[0]+'socket/log');
            logSocket.onopen= function () {$scope.log('Socket log opened.')};
            logSocket.onmessage= function (e) {$scope.log(e.data)};
            logSocket.onclose= function () {$scope.log('Socket log closed.')};
            logSocket.onerror=function (e){$scope.log('Socket log error.'+e)}
        }
    }]);