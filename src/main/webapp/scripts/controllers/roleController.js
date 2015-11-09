/**
 * Created by chenxianjun on 15/11/8.
 */
angular.module('ccloomi')
    .controller('roleCtrl',['$scope','S_pagination','S_dialog', function ($scope,S_pagination,S_dialog) {
        $scope.roles=[];

		$('#jstree').jstree({'plugins':["wholerow","checkbox"], 'core' : {
			'data' : [
				{
					"text" : "Same but with checkboxes",
					"children" : [
						{ "text" : "initially selected", "state" : { "selected" : true } },
						{ "text" : "custom icon URL", "icon" : "http://jstree.com/tree-icon.png" },
						{ "text" : "initially open", "state" : { "opened" : true }, "children" : [ "Another node" ] },
						{ "text" : "custom icon class", "icon" : "glyphicon glyphicon-leaf" }
					]
				},
				"And wholerow selection"
			]
		}});
        $scope.menuAndPermission= function () {
            S_dialog.dialog('菜单和权限配置','views/role/menuAndPermission.html',$scope, function () {
                
            })
        }
        S_pagination.pagination($('#pagination'),'role/byPage.json',20,{}, function (data,pagination) {
            $scope.roles=data;
            refreshScope($scope);
        })
    }])