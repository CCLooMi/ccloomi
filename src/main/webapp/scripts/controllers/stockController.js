/**
 * Created by chenxianjun on 16/4/16.
 */
angular.module('ccloomi')
    .controller('stockCtrl',['$scope','S_pagination','S_stock','$stateParams', function ($scope,S_pagination,S_stock,$stateParams) {
        $scope.showType=$stateParams.showType;
        $scope.stocks=[];
        $scope.changeShowType= function (showType) {
            $scope.showType=showType;
        };
        $scope.syncCompanyInfo= function (stock) {
            S_stock.syncCompanyInfo($scope,stock);
        };
        $scope.syncAllCompanyInfo= function () {
            S_stock.syncAllCompanyInfo($scope);
        };
        $scope.syncCoordinates= function (stock) {
            S_stock.syncCoordinates($scope,stock);
        };
        $scope.syncAllCoordinates= function () {
            S_stock.syncAllCoordinates($scope);
        };
        $scope.remove= function (stock) {
            S_stock.removeStock($scope,stock);
        };
        if($('#pagination').length){
            S_pagination.pagination($('#pagination'),'stock/byPage.json',10,{}, function (data,pagination) {
                $scope.stocks=data;
                refreshScope($scope);
            });
        };
    }]);