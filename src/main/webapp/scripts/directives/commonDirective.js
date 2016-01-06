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
                var selectTemplate='<div style="display: block; position: absolute; top: 86px; left: 508px; padding: 5px; max-height: 250px;width: 360px;min-height: 50px;" class="dropdown-menu form-select">' +
                    '<div class="panel panel-default"><div class="panel-heading">User</div><div class="panel-body"><span>CCLooMi</span><span>cjx</span><span>ttm</span><span>Angular</span><span>boos</span><span>AnyAng</span><span>wowo</span></div></div>' +
                    '</div>';
                var selectPicker=$(selectTemplate)
                    .appendTo($('body'));
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
                    try{
                        var el1=EL.match(/\w+@\w+/g)[0].match(/\w+/g);
                        var el2=EL.match(/\w+\.\w+&\w+\.\w+/g)[0].match(/\w+\.\w+/g);
                    }catch(e){
                        console.error(e);
                    };
                    try{
                        var el3=EL.match(/\/\w+\.\w+/g)[0].match(/\w+\.\w+/g);
                    }catch(e){};

                    for(var i=0;i<$scope[el1[1]].length;i++){
                        this[el1[0]]=$scope[el1[1]][i];
                        var label=eval(el2[0]);
                        var d='';
                        d+='<span data-index="'+i+'">'+label+'</span>';
                        console.log(d);
                    }
                })(EL);
                alert(eval('(function(){return 123456})()'));
            }
        }
    })