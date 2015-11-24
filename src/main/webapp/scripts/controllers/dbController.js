/**
 * Created by chenxianjun on 15/11/21.
 */
angular.module('ccloomi')
    .controller('dbCtrl',['$scope','$http','S_vis', function ($scope,$http,S_vis) {
        var options={
        physics: {
            stabilization: false,
            solver: 'forceAtlas2Based',
            barnesHut: {
              gravitationalConstant: -80000,
              springConstant: 0.001,
              springLength: 0
            }
          },
        interaction: {
            tooltipDelay: 200,
            hideEdgesOnDrag: true
          },
        groups: {
            database:{
              shape:'database'
            },
            table:{
              shape:'box'
            },
            column:{
              shape:'text'
            }
          }
        };
        $http.post('db/asVisNetwork.json').success(function (data) {
            S_vis.network(document.getElementById('network'),options,data,$scope);
            $http.post('db/c2c.json').success(function (data) {
            	$scope.edges.add(data);
            });
        });
    }])