/**
 * Created by chenxianjun on 15/11/8.
 */
angular.module('ccloomi')
    .controller('roleCtrl',['$scope','S_pagination','S_dialog','S_jstree','S_role', function ($scope,S_pagination,S_dialog,S_jstree,S_role) {
        $scope.roles=[];
        $scope.usersInRole=[];
        $scope.usersNotInRole=[];
        $scope.showRightPanel=false;
        $scope.onSearching=false;
        $scope.searchOnFocus= function (e) {
            var target=$(e.target);
            $scope.onSearching=true;
        }
        $scope.closeSearchPlate= function () {
            $scope.onSearching=false;
        }
        $scope.closeRightPanel= function () {
            $scope.showRightPanel=false;
        }
        $scope.add= function () {
            S_role.add($scope);
        };
        $scope.update= function (role) {
            S_role.update($scope,role);
        };
        $scope.remove= function (role) {
            S_role.remove($scope,role);
        };
        $scope.user= function (role) {
            S_pagination.pagination($('#pagination-usersInRole'),'role/usersInRoleByPage.json',10,{role:role}, function (data,pagination) {
                $scope.usersInRole=data;
                refreshScope($scope);
            })
            S_pagination.pagination($('#pagination-usersNotInRole'),'role/usersNotInRoleByPage.json',10,{role:role}, function (data,pagination) {
                $scope.usersNotInRole=data;
                refreshScope($scope);
            })
            $scope.showRightPanel=true;
        }
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
                S_jstree.jstree('permission/jstree.json',role,$scope);
            })
        }
        S_pagination.pagination($('#pagination'),'role/byPage.json',20,{}, function (data,pagination) {
            $scope.roles=data;
            refreshScope($scope);
        })
    }])