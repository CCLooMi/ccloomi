/**
 * Created by chenxianjun on 15/11/21.
 */
angular.module('ccloomi')
    .controller('dbCtrl',['$scope','$http','S_pagination','S_schemata', function ($scope,$http,S_pagination,S_schemata) {
        $scope.schematas=[];
        $scope.schemata={};
        $scope.characters=[];
        $scope.collations=[];
        $scope.remove= function (schemata) {

        };
        $scope.getCharacterSets= function () {
            S_schemata.getCharacterSets(function (data) {
                $scope.characters=data;
                refreshScope($scope);
            });
        };
        $scope.getCollationsByCharacter= function (characterName) {
            S_schemata.getCollationsByCharacter(characterName, function (data) {
                $scope.collations=data;
                refreshScope($scope);
            });
        };
        //查找
        $scope.keyword='';
        $scope.search= function (e) {
            e.preventDefault();
            var searchData={};
            if($scope.keyword&&$scope.keyword!==''){
                $.extend(searchData,{keywords:$scope.keyword})
            }
            dbPagination(searchData);
        };
        $scope.createDb= function () {
            $scope.getCharacterSets();
            S_schemata.createDb($scope);
        };
        function dbPagination(searchData){
            S_pagination.pagination($('#pagination'),'db/byPage.json',20,searchData||{}, function (data,pagination) {
                $scope.schematas=data;
                if(!window.global)window.global={};
                window.global.schematas=data;
                refreshScope($scope);
            });
        };
        dbPagination();
    }]);