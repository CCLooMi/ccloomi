/**
 * Created by chenxianjun on 16/1/4.
 */
angular.module('ccloomi')
    .directive('affix', function () {
        return {
            restrict: 'A',
            link: function (scope,element,attrs) {
                element.affix({
                    offset:{
                        top: 20,
                        bottom: function () {
                            return (this.bottom = $('.footer').outerHeight(true))
                        }
                    }
                });
            }
        }
    })
    .directive('ckeditor',function () {
        return {
            restrict: 'A',
            require:'ngModel',
            link: function (scope,element,attrs,ngModel) {
                var editor=CKEDITOR.replace(element[0]);
                editor.on('change', function (e) {
                    ngModel.$setViewValue(this.getData());
                });
            }
        }
    })
    .directive('formDate',function(){
        return {
            restrict: 'A',
            link: function (scope,element,attrs) {
                element.datetimepicker({
                    weekStart:1,
                    autoclose:true,
                    minView:attrs.minview||2,
                    startView:attrs.startview||2,
                    todayBtn:true,
                    todayHighlight:true,
                    language:'zh-CN'
                });
            }
        }
    })
    .directive('formSelect',function(){
        return {
            restrict: 'A',
            link: function (scope,element,attrs) {
                var EL=attrs['form-select'];

                var EL='a@A a.name&a.id/a.age';
                var $scope={};
                $scope.A=[
                    {id:1,name:'AA',age:18},
                    {id:2,name:'AA',age:18},
                    {id:3,name:'AA',age:18},
                    {id:4,name:'AA',age:18},
                    {id:5,name:'AA',age:18},
                    {id:6,name:'BA',age:12},
                    {id:7,name:'BB',age:12},
                    {id:8,name:'BC',age:12}
                ];
                (function (EL) {
                    var selectTemplate='<div style="display: block; position: absolute; top: 86px; left: 508px; padding: 5px;min-height: 100px; max-height: 250px;max-width: 360px;" class="dropdown-menu form-select"></div>';
                    var selectPicker=$(selectTemplate)
                        .appendTo($('body'))
                        .on({
                            click: click
                        });
                    try{
                        var el1=EL.match(/\w+@\w+/g)[0].match(/\w+/g);
                        var el2=EL.match(/\w+\.\w+&\w+\.\w+/g)[0].match(/\w+\.\w+/g);
                        var model=el2.length>1?el2[1]:undefined;
                    }catch(e){
                        console.error(e);
                    };
                    try{
                        var el3=EL.match(/\/\w+\.\w+/g)[0].match(/\w+\.\w+/g);
                    }catch(e){};

                    if(!el3||!el3.length){
                        var d='<div class="panel panel-default"><div class="panel-body">';
                        for(var i=0;i<$scope[el1[1]].length;i++){
                            $scope[el1[0]]=$scope[el1[1]][i];
                            var label=$scope[el1[0]][el2[0].split('\.')[1]];
                            d+='<span data-index="'+i+'">'+label+'</span>';
                        }
                        selectPicker.html(d+'</div></div>');
                    }else{
                        var d='';
                        var group;
                        for(var i=0;i<$scope[el1[1]].length;i++){
                            this[el1[0]]=$scope[el1[1]][i];
                            var label=eval(el2[0]);
                            if(group==undefined){
                                group=eval(el3[0]);
                                d+='<div class="panel panel-default"><div class="panel-heading">'+el3[0].split('\.')[1]+'::'+group+'</div><div class="panel-body">';
                            }
                            var currentGroup=eval(el3[0]);
                            if(currentGroup==group){
                                d+='<span data-index="'+i+'">'+label+'</span>';
                            }else{
                                group=currentGroup;
                                d+='</div></div><div class="panel panel-default"><div class="panel-heading">'+el3[0].split('\.')[1]+'::'+group+'</div><div class="panel-body">'+'<span data-index="'+i+'">'+label+'</span>';
                            }

                        }
                        d+='</div></div>';
                        selectPicker.html(d);
                    }
                    function click (e) {
                        var target=$(e.target);
                        if(model){
                            alert($scope[el1[1]][target.data('index')][model.split('\.')[1]]);
                        }else{
                            alert(JSON.stringify($scope[el1[1]][target.data('index')]));
                        }
                    };
                })(EL);
            }
        }
    })