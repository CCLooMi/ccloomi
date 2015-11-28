/**
 * Created by chenxianjun on 15/11/21.
 */
angular.module('ccloomi')
    .controller('dbCtrl',['$scope','$http','$stateParams','S_vis', function ($scope,$http,$stateParams,S_vis) {
        $scope.clipboard={};
        var options={
            addEdge: function (data, callback) {

            },
            editEdge: function (data, callback) {

            },
            doubleClick: function (params) {

            },
            menu:[
                {icon:'glyphicon-save-file',text:"保存"},
                {icon:'glyphicon-refresh',text:"刷新"},
                {icon:'glyphicon-pushpin',text:'固定'},
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
            var m=[];
            m.push({divider: true});
            if($scope.clipboard.cute||$scope.clipboard.copy){
                m.push({icon:'glyphicon-paste',text:'粘贴'});
            }
            if($scope.network.getSelection().nodes.length){
                m.push({icon:'glyphicon-scissors',text:'剪切'});
                m.push({icon:'glyphicon-duplicate',text:'复制'});
            }
            //如果没有菜单则清空数组
            if(m.length==1){
                m=[];
            }
            return m;
        }
        function operateMenu(){
            var m=[];
            m.push({divider: true});
            m.push({icon:'glyphicon-plus',text:'添加',subMenu:[
                {icon:'glyphicon-eye-open',text:'角色'},
                {icon:'glyphicon-user',text:'用户'},
                {icon:'glyphicon-link',text:'关联'},
                {icon:'glyphicon-grain',text:'菜单'}
            ]});
            if($scope.network.getSelection().nodes.length||$scope.network.getSelection().edges.length){
                m.push({icon:'glyphicon-edit',text:"编辑"});
                m.push({icon:'glyphicon-trash',text:"删除"});
            }
            if($scope.network.getSelection().nodes.length){
                m.push({icon:'glyphicon-blackboard',text:'查看'});
                //m[1].subMenu.push({icon:'glyphicon-leaf',text:'子菜单'});
            }
            return m;
        }
        window.clipboardMenu=clipboardMenu;
        window.operateMenu=operateMenu;
    }])