/**
 * Created by chenxianjun on 15/11/21.
 */
angular.module('ccloomi')
    .controller('dbCtrl',['$scope','$http','$stateParams','S_dialog','S_vis', function ($scope,$http,$stateParams,S_dialog,S_vis) {
        $scope.clipboard={};
        var options={
            addEdge: function (data, callback) {

            },
            editEdge: function (data, callback) {

            },
            doubleClick: function (params) {

            },
            menu:[
                {icon:'glyphicon glyphicon-save-file',text:"保存",action: function (e) {
                    e.preventDefault();

                }},
                {icon:'glyphicon glyphicon-refresh',text:"刷新",action: function (e) {
                    e.preventDefault();
                    window.location.reload();
                }},
                {menu_item_src:"clipboardMenu"},
                {menu_item_src:"operateMenu"}
            ]
        };
        var dbName=$stateParams.name;
        $scope['info_onfocus']=false;
        $scope.infoOnfocus= function () {
            $scope['info_onfocus']=true;
        };
        $scope.infoOnblur= function () {
            $scope['info_onfocus']=false;
        };
        $http.get('db/asVisNetwork.json?name='+dbName).success(function (data) {
            S_vis.network(document.getElementById('network'),options,data,$scope);
            $http.get('db/c2c.json?name='+dbName).success(function (data) {
            	$scope.edges.add(data);
            });
        });
        //动态生成菜单
        function clipboardMenu(){
            var selectNodes=$scope.network.getSelectedNodes();
            var selectEdges=$scope.network.getSelectedEdges();
            var m=[];
            if(selectNodes.length){
                var selectNode=$scope.nodes.get(selectNodes[0]);
                if(selectNode.group.indexOf('column')!=-1){
                    m.push({divider: true});
                    if($scope.clipboard.cute||$scope.clipboard.copy){
                        m.push({icon:'glyphicon glyphicon-paste',text:'粘贴'});
                    };
                    m.push({icon:'glyphicon glyphicon-scissors',text:'剪切'});
                    m.push({icon:'glyphicon glyphicon-duplicate',text:'复制'});
                }
            }
            //如果没有菜单则清空数组
            if(m.length==1){
                m=[];
            }
            return m;
        }
        function operateMenu(){
            var selectNodes=$scope.network.getSelectedNodes();
            var selectEdges=$scope.network.getSelectedEdges();
            var m=[];
            m.push({divider: true});
            m.push({icon:'glyphicon glyphicon-plus',text:'新建',subMenu:[
                {icon:'fa fa-database',text:'数据库',action: function (e) {
                    e.preventDefault();
                    S_dialog.dialog('新建数据库','views/db/createDatabase.html',$scope, function () {

                    }, function () {

                    });
                }},
                {icon:'fa fa-table',text:'表',action: function (e) {
                    e.preventDefault();
                    S_dialog.dialog('新建表','views/db/createTable.html',$scope, function () {

                    }, function () {

                    },null,768);
                }}
            ]});
            if(selectNodes.length){
                var selectNode=$scope.nodes.get(selectNodes[0]);
                m.push({icon:'glyphicon glyphicon-edit',text:"编辑"});
                m.push({icon:'glyphicon glyphicon-blackboard',text:'查看'});
                m.push({icon:'glyphicon glyphicon-trash',text:"删除"});
                if(selectNode.group.indexOf('table')!=-1){
                    m.push({icon: 'glyphicon glyphicon-plus',text:'添加',subMenu:[
                        {icon:'glyphicon glyphicon-list-alt',text:'字段'}
                    ]});
                }

            }else if(selectEdges.length){
                var selectEdge=$scope.edges.get(selectEdges[0]);
                console.log(JSON.stringify(selectEdge));
                m.push({divider: true});
                m.push({icon:'glyphicon glyphicon-trash',text:"删除"});

            }
            return m;
        }
        window.clipboardMenu=clipboardMenu;
        window.operateMenu=operateMenu;
    }])