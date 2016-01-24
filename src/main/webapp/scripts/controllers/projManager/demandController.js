/**
 * Created by chenxianjun on 16/1/10.
 */
angular.module('ccloomi')
    .controller('demandCtrl',['$scope','$stateParams','S_pagination','S_demand', function ($scope,$stateParams,S_pagination,S_demand) {
        var productId=$stateParams.productId;
        $scope.demands=[];
        $scope.add= function () {
            if(productId){
                $scope.demand={idProduct:productId};
            }
            S_demand.add($scope);
        };
        $scope.update= function (demand) {
            S_demand.update(demand,$scope);
        };
        $scope.remove= function (demand) {
            S_demand.remove(demand,$scope);
        };
        function pagination(){
            var searchData={};
            if(productId){
               searchData.idProduct=productId;
            }
            if($('#pagination').length){
                S_pagination.pagination($('#pagination'),'demand/byPage.json',20,searchData, function (data) {
                    $scope.demands=data;
                    refreshScope($scope);
                })
            }
        };
        pagination();
    }]);