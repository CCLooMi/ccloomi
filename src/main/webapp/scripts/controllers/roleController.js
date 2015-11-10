/**
 * Created by chenxianjun on 15/11/8.
 */
angular.module('ccloomi')
    .controller('roleCtrl',['$scope','$http','S_pagination','S_dialog', function ($scope,$http,S_pagination,S_dialog) {
        $scope.roles=[];


        $scope.menuAndPermission= function (role) {
            S_dialog.dialog('菜单和权限配置','views/role/menuAndPermission.html',$scope, function () {
                
            },null, function () {
                $http.post('view/jstree.json',role).success(function (data) {
                    $('#jstree').jstree({'plugins':["checkbox"], 'core' : {'data' :data}});
                });
            })
        }
        S_pagination.pagination($('#pagination'),'role/byPage.json',20,{}, function (data,pagination) {
            $scope.roles=data;
            refreshScope($scope);
        })
    }])