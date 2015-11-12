/**
 * Created by chenxianjun on 15/11/8.
 */
angular.module('ccloomi')
    .controller('roleCtrl',['$scope','$http','S_pagination','S_dialog','S_jstree', function ($scope,$http,S_pagination,S_dialog,S_jstree) {
        $scope.roles=[];


        $scope.menuAndPermission= function (role) {
            S_dialog.dialog('菜单和权限配置','views/role/menuAndPermission.html',$scope, function () {
                alert(JSON.stringify(S_jstree.getChangeData('#jstree',$scope.selectedIds)));
            },null, function () {
                $http.post('view/jstree.json',role).success(function (data) {
                    $('#jstree').jstree({'plugins':["checkbox"], 'core' : {'data' :data.data}});
                    $scope.selectedIds=data.ids;
                });
            })
        }
        S_pagination.pagination($('#pagination'),'role/byPage.json',20,{}, function (data,pagination) {
            $scope.roles=data;
            refreshScope($scope);
        })
    }])