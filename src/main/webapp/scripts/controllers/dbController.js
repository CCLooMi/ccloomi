/**
 * Created by chenxianjun on 15/11/21.
 */
angular.module('ccloomi')
    .controller('dbCtrl',['$scope','$http','$stateParams','S_vis', function ($scope,$http,$stateParams,S_vis) {
        var options={};
        var dbName=$stateParams.name;
        $scope['info_onfocus']=false;
        $scope.infoOnfocus= function () {
            $scope['info_onfocus']=true;
        };
        $scope.infoOnblur= function () {
            $scope['info_onfocus']=false;
        }
        $http.get('db/asVisNetwork.json?name='+dbName).success(function (data) {
            S_vis.network(document.getElementById('network'),options,data,$scope);
            $http.get('db/c2c.json?name='+dbName).success(function (data) {
            	$scope.edges.add(data);
            });
        });
    }])