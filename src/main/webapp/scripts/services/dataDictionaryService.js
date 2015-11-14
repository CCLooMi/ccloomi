/**
 * Created by Chenxj on 2015/11/5.
 */

var app=angular.module('ccloomi');

app.factory('S_dataDictionary',['$http','S_dialog', function ($http,S_dialog) {
    var service={
        add: function (scope,dd) {
            if(dd){
                scope.dd_new={deepIndex: dd.deepIndex+1,pid: dd.id,rootId: dd.rootId};
                S_dialog.dialog('添加键值','views/dataDictionary/addDictionaryValue.html',scope, function () {
                    $http.post('dd/add.do',scope.dd_new).success(function (data) {
                        if(data.code==0){
                            S_dialog.alert('添加成功','成功添加键值','success');
                            scope.dd_new.id=data.info;
                            dd.VS.push(scope.dd_new);
                            dd.VSS+=1;
                        }else{
                            S_dialog.alert('添加失败',data.info,'error');
                        }
                    });
                })
            }else{
                scope.dd_new={};
                S_dialog.dialog('添加数据','views/dataDictionary/addDictionaryKey.html', scope, function () {
                    $http.post('dd/add.do',scope.dd_new).success(function (data) {
                        if(data.code==0){
                            S_dialog.alert('添加成功','成功添加字典数据','success');
                            scope.dd_new.id=data.info;
                            scope.dd_new.rootId=data.info;
                            scope.dd_new.deepIndex=0;
                            scope.dd_new.VSS=0;
                            scope.dd_new.VS=[];
                            scope.dds.push(scope.dd_new);
                        }else{
                            S_dialog.alert('添加失败',data.info,'error');
                        }
                    }).error(function () {
                        S_dialog.alert('操作失败','接口调用失败','error');
                    });
                })
            }
        },
        update: function (scope,dd) {
            var ddClone=clone(dd);
            scope.dd_new=dd;
            if(dd.deepIndex==0){
                S_dialog.dialog('修改数据','views/dataDictionary/addDictionaryKey.html',scope, function () {
                    $http.post('dd/update.do',scope.dd_new).success(function (data) {
                        if(data.code==0){
                            S_dialog.alert('修改成功','成功修改数据','success');
                        }else{
                            S_dialog.alert('修改失败',data.info,'error');
                        }
                    }).error(function () {
                        S_dialog.alert('操作失败','接口调用失败','error');
                    });
                }, function () {
                    cloneA2B(ddClone,dd);
                    refreshScope(scope);
                })
            }else{
                S_dialog.dialog('修改键值','views/dataDictionary/addDictionaryValue.html',scope, function () {
                    $http.post('dd/update.do',scope.dd_new).success(function (data) {
                        if(data.code==0){
                            S_dialog.alert('修改成功','成功修改键值','success');
                        }else{
                            S_dialog.alert('修改失败',data.info,'error');
                        }
                    }).error(function () {
                        S_dialog.alert('操作失败','接口调用失败','error');
                    });
                }, function () {
                    cloneA2B(ddClone,dd);
                    refreshScope(scope);
                })
            }
        },
        remove: function (scope,dd,d) {
            S_dialog.alertRemove('dd/remove.do',dd, function () {
                if(dd.deepIndex==0){
                    scope.dds.splice(scope.dds.indexOf(dd),1);
                }else{
                    d.VS.splice(d.VS.indexOf(dd),1);
                    d.VSS-=1;
                }
                refreshScope(scope);
            })
        }
    };
    return service;
}])