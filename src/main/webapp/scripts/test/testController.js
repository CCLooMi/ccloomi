/**
 * Created by Idccapp25 on 2016/1/8.
 */
angular.module('ccloomi')
    .controller('testCtrl',['$scope', function ($scope) {
        $scope.users=[
            {id:'U889',name:'Tomcat',age:15},
            {id:'U665',name:'GlassFIsh',age:15},
            {id:'U752',name:'Oracle',age:15},
            {id:'U985',name:'SqlServer',age:18},
            {id:'U325',name:'Jack',age:18},
            {id:'U110',name:'Smith',age:18},
            {id:'U145',name:'HaiL',age:18},
            {id:'U665',name:'Kaiselin',age:16},
            {id:'U323',name:'Aideson',age:16}
        ];
    }]);