'use strict';
/**
 * Created by chenxianjun on 15/10/24.
 */
var app=angular
    .module('ccloomi',[
        'oc.lazyLoad',
        'ui.router',
        'ui.bootstrap',
        'angular-loading-bar',
        'ngSanitize'
    ])
    .config(['$stateProvider','$urlRouterProvider','$ocLazyLoadProvider', function ($stateProvider,$urlRouterProvider,$ocLazyLoadProvider) {
        $ocLazyLoadProvider.config({
            debug:false,
            events:true
        });

        $urlRouterProvider.otherwise('/login');

        $stateProvider
            .state('main',{
                url:'/main',
                templateUrl:'views/pages/main.html',
                resolve:{
                    loadMyDirectives: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'ccloomi',
                            files:[
                            'scripts/directives/header/header.js',
                            'scripts/directives/header/header-notification/header-notification.js',
                            'scripts/directives/sidebar/sidebar.js',
                            'bower_components/paginationjs/dist/pagination.js',
                            'js/CCDialog.js',
                            'css/pagination.css'
                            ]
                        })
                    }
                }
            })
            .state('main.test',{
                url:'/test',
                templateUrl:'views/test/test.html',
                resolve:{
                    loadMyDirectives: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'ccloomi',
                            files:[
                                'scripts/test/testController.js'
                            ]
                        })
                    }
                }
            })
            .state('main.menu',{
                url:'/menu',
                templateUrl:'views/menu/menu.html',
                resolve:{
                    loadMyFiles: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'ccloomi',
                            files:[
                                'bower_components/jstree/dist/jstree.min.js',
                                'scripts/services/visService.js',
                                'scripts/services/menuService.js',
                                'scripts/controllers/menuController.js',
                                'bower_components/jstree/dist/themes/default/style.min.css'
                            ]
                        })
                    }
                }
            })
            .state('main.menu.showType',{
                url:'/:showType',
                views:{
                    'showType':{
                        templateUrl: function (params) {
                            return 'views/menu/'+params.showType+'.html';
                        },
                        controller:'menuCtrl'
                    }
                }
            })
            .state('main.role',{
                url:'/role',
                templateUrl:'views/role/role.html',
                resolve:{
                    loadMyFiles: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'ccloomi',
                            files:[
                                'bower_components/jstree/dist/jstree.min.js',
                                'scripts/services/roleService.js',
                                'scripts/controllers/roleController.js',
                                'bower_components/jstree/dist/themes/default/style.min.css'
                            ]
                        })
                    }
                }
            })
            .state('main.user',{
                url:'/user',
                templateUrl:'views/user/user.html',
                resolve:{
                    loadMyFiles: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'ccloomi',
                            files:[
                                'scripts/controllers/userController.js'
                            ]
                        })
                    }
                }
            })
            .state('main.dd',{
                url:'/dd',
                templateUrl:'views/dataDictionary/dataDictionary.html',
                resolve:{
                    loadMyFile: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'ccloomi',
                            files:[
                                'scripts/services/dataDictionaryService.js',
                                'scripts/controllers/dataDictionaryController.js'
                            ]
                        })
                    }
                }
            })
            .state('main.icon',{
                url:'/icon',
                templateUrl:'views/icon/icon.html',
                resolve:{
                    loadMyFile: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'ccloomi',
                            files:[
                                'scripts/services/iconService.js',
                                'scripts/controllers/iconController.js'
                            ]
                        })
                    }
                }
            })
            .state('main.db',{
                url:'/db',
                templateUrl:'views/db/db.html',
                resolve:{
                    loadMyFile: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'ccloomi',
                            files:[
                                'scripts/services/schemataService.js',
                                'scripts/controllers/dbController.js',
                                'styles/db.css'
                            ]
                        })
                    }
                }

            })
            .state('main.dbDesign',{
                url:'/db/:name',
                templateUrl:'views/db/design.html',
                resolve:{
                    loadMyFile: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'ccloomi',
                            files:[
                                'scripts/services/visService.js',
                                'scripts/services/schemataService.js',
                                'scripts/controllers/dbDesignController.js'
                            ]
                        })
                    }
                }
            })
            .state('main.log',{
                url:'/log',
                templateUrl:'views/log/log.html',
                resolve:{
                    loadMyFiles: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'ccloomi',
                            files:[
                                'scripts/services/projManager/demandService.js',
                                'scripts/controllers/logController.js'
                            ]
                        })
                    }
                }
            })
            .state('login',{
                url:'/login',
                templateUrl:'views/pages/login.html',
                resolve:{
                    loadMyFiles: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'loginCtrl',
                            files:[
                                'scripts/controllers/loginController.js',
                                'styles/login.css'
                            ]
                        })
                    }
                }
            })
            .state('main.stock',{
                url:'/stock',
                templateUrl:'views/stock/stock.html',
                resolve:{
                    loadMyFiles: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'ccloomi',
                            files:[
                                'scripts/services/stockService.js',
                                'scripts/controllers/stockController.js',
                                'styles/stock.css'
                            ]
                        })
                    }
                }
            })
            .state('main.stock.showType',{
                url:'/:showType',
                views:{
                    'showType':{
                        templateUrl: function (params) {
                            return 'views/stock/'+params.showType+'.html';
                        },
                        controller:'stockCtrl'
                    }
                }
            })
            .state('main.template',{
                url:'/template',
                templateUrl:'views/template/template.html',
                resolve:{
                    loadMyFiles: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'ccloomi',
                            files:[
                                'scripts/services/templateService.js',
                                'scripts/controllers/templateController.js'
                            ]
                        })
                    }
                }
            })
            .state('map',{
                url:'/map/:key/:table',
                templateUrl:'views/stock/map.html',
                resolve:{
                    loadMyFiles: function ($ocLazyLoad) {
                        return $ocLazyLoad.load({
                            name:'ccloomi',
                            files:[
                                'http://cache.amap.com/lbs/static/main.css?v=1.0',
                                'http://cache.amap.com/lbs/static/main1119.css',
                                'scripts/controllers/mapController.js'
                            ]
                        })
                    }
                }
            })

    }]);


app.config(['$stateProvider', function ($stateProvider) {
    $stateProvider.state('main.maven',{
        url:'/maven',
        templateUrl:'views/mavenSearch/mavenSearch.html',
        resolve:{
            loadMyFile: function ($ocLazyLoad) {
                return $ocLazyLoad.load({
                    name:'ccloomi',
                    files:[
                        'scripts/controllers/mavenSearchController.js'
                    ]
                })
            }
        }
    }).state('main.shooter',{
        url:'/shooter',
        templateUrl:'views/shooter/shooter.html',
        resolve:{
            loadMyFile: function ($ocLazyLoad) {
                return $ocLazyLoad.load({
                    name:'ccloomi',
                    files:[
                        'scripts/controllers/shooterController.js'
                    ]
                })
            }
        }
    })
}]);

app.config(['$stateProvider', function ($stateProvider) {
    $stateProvider
        .state('proj',{
            url:'/proj/main',
            templateUrl:'views/projManager/main.html',
            resolve:{
                loadMyFiles: function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name:'loginCtrl',
                        files:[
                            'scripts/directives/projManager/header/header.js',
                            'scripts/directives/projManager/footer/footer.js',
                            'bower_components/paginationjs/dist/pagination.js',
                            'bower_components/highlight/src/styles/monokai-sublime.css',
                            'js/CCDialog.js',
                            'styles/pjm.css',
                            'css/pagination.css'
                        ]
                    })
                }
            }
        })
        .state('proj.product',{
            url:'/product/:operation',
            views:{
                '':{//如果在ui-view中没有指定,这里只需一个空字符串即可
                    templateUrl: function (params) {
                        return 'views/projManager/product/'+params.operation+'.html';
                    },
                    controller:'productCtrl'
                }
            },
            resolve:{
                loadMyFiles: function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name:'product',
                        files:[
                            'scripts/services/roleService.js',
                            'scripts/services/projManager/productService.js',
                            'scripts/controllers/projManager/productController.js'
                        ]
                    })
                }
            }
        })
        .state('proj.demand',{
            url:'/demand/:option',
            templateUrl:'views/projManager/demand/list.html',
            resolve:{
                loadMyFiles: function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name:'demand',
                        files:[
                            'scripts/services/projManager/demandService.js',
                            'scripts/controllers/projManager/demandController.js'
                        ]
                    })
                }
            }
        })
        .state('proj.project',{
            url:'/project/:operation',
            views:{
                '':{//如果在ui-view中没有指定,这里只需一个空字符串即可
                    templateUrl: function (params) {
                        return 'views/projManager/project/'+params.operation+'.html';
                    }
                }
            }
        })
        .state('proj.test',{
            url:'/test/:operation',
            views:{
                '':{//如果在ui-view中没有指定,这里只需一个空字符串即可
                    templateUrl: function (params) {
                        return 'views/projManager/test/'+params.operation+'.html';
                    }
                }
            }
        })
        .state('proj.count',{
            url:'/count/:operation',
            views:{
                '':{//如果在ui-view中没有指定,这里只需一个空字符串即可
                    templateUrl: function (params) {
                        return 'views/projManager/count/'+params.operation+'.html';
                    }
                }
            }
        })
        .state('proj.document',{
            url:'/document/:operation',
            views:{
                '':{//如果在ui-view中没有指定,这里只需一个空字符串即可
                    templateUrl: function (params) {
                        return 'views/projManager/document/'+params.operation+'.html';
                    }
                }
            }
        })
        .state('proj.blog',{
            url:'/blog/:operation',
            views:{
                '':{//如果在ui-view中没有指定,这里只需一个空字符串即可
                    templateUrl: function (params) {
                        return 'views/projManager/blog/'+params.operation+'.html';
                    }
                }
            },
            resolve:{
                loadMyFiles: function ($ocLazyLoad) {
                    return $ocLazyLoad.load({
                        name:'blog',
                        files:[
                            'styles/blog.css',
                            'scripts/services/projManager/blog/blogService.js',
                            'scripts/controllers/projManager/blog/indexController.js'
                        ]
                    })
                }
            }
        })
        .state('proj.tieba',{
            url:'/tieba/:operation',
            views:{
                '':{//如果在ui-view中没有指定,这里只需一个空字符串即可
                    templateUrl: function (params) {
                        return 'views/projManager/tieba/'+params.operation+'.html';
                    }
                }
            }
        })
        .state('proj.workspace',{
            url:'/workspace/:operation',
            views:{
                '':{//如果在ui-view中没有指定,这里只需一个空字符串即可
                    templateUrl: function (params) {
                        return 'views/projManager/workspace/'+params.operation+'.html';
                    }
                }
            }
        })
}]);