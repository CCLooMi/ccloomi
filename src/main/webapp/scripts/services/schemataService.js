/**
 * Created by chenxianjun on 15/12/6.
 */
angular.module('ccloomi')
    .factory('S_schemata',['$http','S_dialog', function ($http,S_dialog) {
        var service={
            createDb: function (scope) {
                S_dialog.dialog('创建数据库','views/db/createDatabase.html',scope, function () {
                    $http.post('db/createDb.do',scope.schemata)
                        .success(function (data) {
                            if(data.code==0){
                                S_dialog.alert('创建成功','创建数据库成功','success');
                                scope.schematas.push(scope.schemata);
                            }else if(data.code==1){
                                S_dialog.alert('创建数据库失败',data.info,'error');
                            }else{
                                S_dialog.alert('网络错误','网络出现异常','error');
                            }
                        })
                        .error(function () {
                            S_dialog.alert('操作失败','接口调用失败','error');
                        });
                });
            },
            getCharacterSets: function (callback) {
                $http.post('db/allCharacterSets.json')
                    .success(callback);
            },
            getCollationsByCharacter: function (characterName,callback) {
                $http.get('db/collationsByCharacter.json?characterName='+characterName)
                    .success(callback);
            },
            getTableColumnsAsProperties: function (dbName,tableName,scope,container) {
                $http.get('db/convertTableColumns2Properties.json?dbName='+dbName+'&tableName='+tableName).success(function (data) {
                    var plistStr='';
                    for(var i in data){
                        plistStr+=data[i]+'<br>';
                    }
                    S_dialog.dialogHtml(dbName+'::'+tableName,plistStr,scope, null,null,null,768,{container:container});
                });
            }
        };
        return service;
    }]);