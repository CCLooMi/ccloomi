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
                var selectTemplate='<div style="display: block; position: absolute; top: 86px; left: 508px; padding: 5px; max-height: 250px;width: 360px;min-height: 50px;" class="dropdown-menu form-select"></div>';
                var selectPicker=$(selectTemplate)
                    .appendTo($('body'));
            }
        }
    })