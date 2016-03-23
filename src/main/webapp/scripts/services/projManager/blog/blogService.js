/**
 * Created by chenxianjun on 16/3/19.
 */
angular.module('ccloomi')
    .factory('S_blog',['$http','S_dialog', function ($http,S_dialog) {
        var service={
            add: function (scope) {
                scope.blog={};
                S_dialog.dialog('发布博客','views/projManager/blog/publish.html',scope, function () {
                    $http.post('blog/add.do',scope.blog)
                        .success(function (data) {
                            if(data.code==0){
                                scope.blog=data.info;
                                scope.blogs.unshift(scope.blog);
                                refreshScope(scope);
                                S_dialog.alert('发布成功','成功发布了博客','success');
                            }else if(data.code==1){
                                S_dialog.alert('发布失败',data.info,'error');
                            }else{
                                S_dialog.alert('发布失败','网络错误','error');
                            }
                        })
                        .error(function () {S_dialog.alert('操作失败','接口调用失败','error');});
                }, function () {

                },null,850);
            }
        };
        return service;
    }]);