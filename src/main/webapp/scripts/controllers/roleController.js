/**
 * Created by chenxianjun on 15/11/8.
 */
angular.module('ccloomi')
    .controller('roleCtrl',['$scope','$http','S_pagination','S_dialog', function ($scope,$http,S_pagination,S_dialog) {
        $scope.roles=[];


        $scope.menuAndPermission= function (role) {
            S_dialog.dialog('菜单和权限配置','views/role/menuAndPermission.html',$scope, function () {
                var jstree=$.jstree.reference('#jstree');
                var ids=jstree.get_selected();
                var pids=[];
                for(var i in ids){
                    var id=ids[i];
                    var pid=jstree.get_parent(id);
                    if(pids.indexOf(pid)==-1){
                        pids.push(pid);
                    }
                }
                for(var i in pids){
                    ids.push(pids[i]);
                }

                var data={};
                //获取需要删除的ids
                //$scope.selectedIds-ids
                data.remove=Array.minus($scope.selectedIds,ids);
                //获取需要添加的ids
                //ids-$scope.selectedIds
                data.add=Array.minus(ids,$scope.selectedIds);

                alert(JSON.stringify(data));
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