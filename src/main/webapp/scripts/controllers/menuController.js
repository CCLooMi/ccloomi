/**
 * Created by chenxianjun on 15/11/5.
 */
angular.module('ccloomi')
    .controller('menuCtrl',['$scope','S_pagination','S_menu','$stateParams', function ($scope,S_pagination,S_menu,$stateParams) {
        $scope.showType=$stateParams.showType;
        $scope.views=[];
        $scope.menu={};
        $scope.add= function (menu) {
            S_menu.add($scope,menu);
        };
        $scope.update= function (menu) {
            S_menu.update($scope,menu);
        };
        $scope.remove= function (menu) {
            S_menu.remove($scope,menu);
        };
        $scope.changeShowType= function (showType) {
            $scope.showType=showType;
        };
    	if($('#pagination').length){
            S_pagination.pagination($('#pagination'),'view/byPage.json',10,{}, function (data,pagination) {
                $scope.views=data;
                refreshScope($scope);
            });
        };
        if($('#network').length){
            S_menu.network($scope);
        };
        if($('#tree').length){
            S_menu.tree('view/tree.json',$scope);
        }
    }])