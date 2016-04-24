/**
 * Created by chenxianjun on 16/4/24.
 */
angular.module('ccloomi')
    .controller('mapCtrl',['$scope','$stateParams','$ocLazyLoad','$http', function ($scope,$stateParams,$ocLazyLoad,$http) {
        $scope.key=$stateParams.key;
        $scope.table=$stateParams.table;
        $scope.drawMap= function (table) {
            //初始化地图对象，加载地图
            var map = new AMap.Map("mapContainer", {
                resizeEnable: true,
                center: [116.397428, 39.90923],//地图中心点
                zoom: 5 //地图显示的缩放级别
            });
            //加载云图层插件
            map.plugin('AMap.CloudDataLayer', function () {
                var layerOptions = {
                    clickable: true
                };
                var cloudDataLayer = new AMap.CloudDataLayer(table, layerOptions); //实例化云图层类
                cloudDataLayer.setMap(map); //叠加云图层到地图

                AMap.event.addListener(cloudDataLayer, 'click', function (result) {
                    var clouddata = result.data;
                    var photo = [];
                    if (clouddata._image[0]) {//如果有上传的图片
                        photo = ['<img width=240 height=100 src="' + clouddata._image[0]._preurl + '"><br>'];
                    }
                    var infoWindow = new AMap.InfoWindow({
                        content: "<font class='title'>" + clouddata.brand_name + "</font>" +
                        "<hr/>" + photo.join("") + "公司：" + clouddata._name + "<br />"+
                        "地址:"+clouddata._address+"<br/>"+
                        "主营:"+clouddata.intro+"<br/>"+
                        "发行价:"+clouddata.price+"元<br/>",
                        size: new AMap.Size(0, 0),
                        autoMove: true,
                        offset: new AMap.Pixel(0, -25)
                    });
                    infoWindow.open(map, clouddata._location);
                });
            });
            // 添加Google图层
            var googleLayer = null;
            googleLayer = new AMap.TileLayer({
                // 图块取图地址
                tileUrl: 'http://mt{1,2,3,0}.google.cn/vt/lyrs=m@142&hl=zh-CN&gl=cn&x=[x]&y=[y]&z=[z]&s=Galil'
            });
            googleLayer.setMap(map);
        };
        if(!$scope.key||!$scope.table){
            $http.get('stock/gaodeMap.json')
                .success(function (data) {
                    $scope.key=data['amap-js-key'];
                    $scope.table=data['amap-stock-table'];
                    $ocLazyLoad
                        .load([{type:'js',path:'http://webapi.amap.com/maps?v=1.3&key='+$scope.key}])
                        .then(function () {
                            $scope.drawMap($scope.table);
                        });
                });
        }else{
            $ocLazyLoad
                .load([{type:'js',path:'http://webapi.amap.com/maps?v=1.3&key='+$scope.key}])
                .then(function () {
                    $scope.drawMap($scope.table);
                });
        };
    }])