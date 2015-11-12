/**
 * Created by chenxianjun on 15/11/8.
 */
angular.module('ccloomi')
    .controller('roleCtrl',['$scope','S_pagination','S_dialog','S_jstree','S_role', function ($scope,S_pagination,S_dialog,S_jstree,S_role) {
        $scope.roles=[];


        $scope.menuAndPermission= function (role) {
            S_dialog.dialog('菜单和权限配置','views/role/menuAndPermission.html',$scope, function () {
                S_role.saveJstree($scope);
            },null, function () {
                S_jstree.jstree('view/jstree.json',role,$scope);
            })
        }
        S_pagination.pagination($('#pagination'),'role/byPage.json',20,{}, function (data,pagination) {
            $scope.roles=data;
            refreshScope($scope);
        })
    }])