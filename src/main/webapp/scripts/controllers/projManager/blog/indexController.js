/**
 * Created by chenxianjun on 16/3/19.
 */
angular.module('ccloomi')
    .controller('blogIndexCtrl',['$scope','S_pagination','S_blog', function ($scope,S_pagination,S_blog) {
        $scope.blogs=[];
        $scope.add= function () {
            S_blog.add($scope);
        };
        S_pagination.pagination($('#pagination'),'blog/byPage.do',10,{},function (data,pagination) {
            $scope.blogs=data;
            refreshScope($scope);
        })
    }]);