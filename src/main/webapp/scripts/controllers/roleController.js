/**
 * Created by chenxianjun on 15/11/8.
 */
angular.module('ccloomi')
    .controller('roleCtrl',['$scope','S_pagination','S_dialog', function ($scope,S_pagination,S_dialog) {
        $scope.roles=[];


        $scope.menuAndPermission= function () {
            S_dialog.dialog('菜单和权限配置','views/role/menuAndPermission.html',$scope, function () {
                
            },null, function () {
                $('#jstree').jstree({'plugins':["state","checkbox"], 'core' : {
                    'data' : [
                        {
                        	"id" : "D001",
                            "text" : "系统管理",
                            "icon" : "glyphicon glyphicon-leaf",
                            "children" : [
                                { "text" : "数据字典", "icon" : "fa fa-book","state" : { "selected" : true } },
                                { "text" : "菜单配置", "icon" : "glyphicon glyphicon-th-list" },
                                { "text" : "角色管理", "icon" : "fa fa-user"}
                            ]
                        }
                    ]
                }});
            })
        }
        S_pagination.pagination($('#pagination'),'role/byPage.json',20,{}, function (data,pagination) {
            $scope.roles=data;
            refreshScope($scope);
        })
    }])