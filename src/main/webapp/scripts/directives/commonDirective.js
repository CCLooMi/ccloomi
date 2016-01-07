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
    .directive('formSelect',['$filter',function($filter){
        return {
            restrict: 'A',
            require:'ngModel',
            link: function (scope,element,attrs,ngModel) {
                var EL=attrs['formSelect'];
                (function (EL) {
                    var selectTemplate='<div style="display: block; position: absolute; top: 86px; left: 508px; padding: 5px;min-height: 100px; max-height: 250px;max-width: 360px;" class="dropdown-menu form-select"></div>';
                    var selectPicker=$(selectTemplate)
                        .appendTo($('body'))
                        .on({click: click});
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
                    if(el1&&el1[1]&&scope[el1[1]]){
                        if(!el3||!el3.length){
                            var d='<div class="panel panel-default"><div class="panel-body">';
                            for(var i=0;i<scope[el1[1]].length;i++){
                                scope[el1[0]]=scope[el1[1]][i];
                                var label=scope[el1[0]][el2[0].split('\.')[1]];
                                d+='<span data-index="'+i+'">'+label+'</span>';
                            }
                            selectPicker.html(d+'</div></div>');
                        }else{
                            var d='';
                            var group;
                            var groupBy=el3[0].split('\.')[1];
                            //先排序
                            scope[el1[1]]=$filter('orderBy')(scope[el1[1]],groupBy);
                            for(var i=0;i<scope[el1[1]].length;i++){
                                scope[el1[0]]=scope[el1[1]][i];
                                var label=scope[el1[0]][el2[0].split('\.')[1]];
                                if(group==undefined){
                                    group=scope[el1[0]][groupBy];
                                    d+='<div class="panel panel-default"><div class="panel-heading">'+groupBy+'::'+group+'</div><div class="panel-body">';
                                }
                                var currentGroup=scope[el1[0]][groupBy];
                                if(currentGroup==group){
                                    d+='<span data-index="'+i+'">'+label+'</span>';
                                }else{
                                    group=currentGroup;
                                    d+='</div></div><div class="panel panel-default"><div class="panel-heading">'+groupBy+'::'+group+'</div><div class="panel-body">'+'<span data-index="'+i+'">'+label+'</span>';
                                }

                            }
                            d+='</div></div>';
                            selectPicker.html(d);
                        }
                    }
                    function click (e) {
                        var target=$(e.target);
                        if(model){
                            ngModel.$setViewValue(scope[el1[1]][target.data('index')][model.split('\.')[1]]);
                        }else{
                            ngModel.$setViewValue(scope[el1[1]][target.data('index')]);
                        }
                    };
                })(EL);
            }
        }
    }])