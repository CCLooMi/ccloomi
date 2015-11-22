/**
 * Created by chenxianjun on 15/11/21.
 */
angular.module('ccloomi')
    .controller('dbCtrl',['$scope','S_vis', function ($scope,S_vis) {
        var options={};
        var data={};
        data.nodes=[{label:'Test-1'},{label:'Test-2'}];
        S_vis.network(document.getElementById('network'),options,data,$scope);
    }])