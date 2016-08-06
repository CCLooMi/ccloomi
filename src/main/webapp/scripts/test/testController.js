/**
 * Created by Idccapp25 on 2016/1/8.
 */
angular.module('ccloomi')
    .controller('testCtrl',['$scope', function ($scope) {
        $scope.dd={vl:'dev'};
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
        $scope.user=$scope.users[0];

        $scope.books=[
            {id:'BK001',name:'红楼梦',type:'四大名著'},
            {id:'BK002',name:'西游记',type:'四大名著'},
            {id:'BK003',name:'三国演义',type:'四大名著'},
            {id:'BK004',name:'水浒传',type:'四大名著'},
            {id:'BK005',name:'金瓶梅',type:'古典小说'},
            {id:'BK006',name:'玉楼春',type:'古典小说'},
            {id:'BK007',name:'翠碧楼',type:'古典小说'},
            {id:'BK008',name:'红芍药',type:'古典小说'},
            {id:'BK009',name:'杀破狼',type:'现代小说'},
            {id:'BK010',name:'金凤凰',type:'现代小说'}
        ];
        $scope.book=$scope.books[0];
        $scope.submit=function(e){
            $(e.target).next('[cc-upload-start]').trigger('click');
        }
    }]);