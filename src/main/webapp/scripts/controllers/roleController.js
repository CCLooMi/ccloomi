/**
 * Created by chenxianjun on 15/11/8.
 */
angular.module('ccloomi')
    .controller('roleCtrl',['$scope','S_pagination','S_dialog','S_jstree','S_role', function ($scope,S_pagination,S_dialog,S_jstree,S_role) {
        $scope.roles=[];


        $scope.menu= function (role) {
            S_dialog.dialog('菜单配置','views/role/menu.html',$scope, function () {
                S_role.saveViewJstree($scope,role);
            },null, function () {
                S_jstree.jstree('view/jstree.json',role,$scope);
            })
        }
        $scope.permission= function (role) {
            S_dialog.dialog('权限配置','views/role/permission.html',$scope, function () {
                S_role.savePermissionJstree($scope,role);
            },null, function () {
                S_jstree.jstree('view/jstree.json',role,$scope);
            })
        }
        S_pagination.pagination($('#pagination'),'role/byPage.json',20,{}, function (data,pagination) {
            $scope.roles=data;
            refreshScope($scope);
        })
    }])