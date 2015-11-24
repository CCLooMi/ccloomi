/**
 * Created by chenxianjun on 15/11/21.
 */
angular.module('ccloomi')
    .controller('dbCtrl',['$scope','$http','S_vis', function ($scope,$http,S_vis) {
        var options={};
        $scope['info_onfocus']=false;
        $scope.infoOnfocus= function () {
            $scope['info_onfocus']=true;
        };
        $scope.infoOnblur= function () {
            $scope['info_onfocus']=false;
        }
        $http.post('db/asVisNetwork.json').success(function (data) {
            S_vis.network(document.getElementById('network'),options,data,$scope);
            $http.post('db/c2c.json').success(function (data) {
            	$scope.edges.add(data);
            });
        });
    }])