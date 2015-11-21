/**
 * Created by chenxianjun on 15/11/21.
 */
angular.module('ccloomi')
    .controller('dbCtrl',['$scope','S_vis', function ($scope,S_vis) {
        S_vis.network(document.getElementById('network'),{},{},$scope);
    }])