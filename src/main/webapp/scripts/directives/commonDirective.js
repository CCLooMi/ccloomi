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
    .directive('ccEditor',function () {
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
    .directive('formSelect',['$filter', function ($filter) {
        return {
            restrict:'A',
            require:'ngModel',
            link: function (scope,element,attrs,ngModel) {
                (function (EL) {
                    element.addClass('form-select');
                    element.attr('readonly',true);
                    element.closest('.form-group').addClass('has-feedback');
                    var dropdown=$('<div class="dropdown-menu"></div>')
                        .insertAfter(element)
                        .on({click:click});
                    var feedback=$('<span class="glyphicon glyphicon-asterisk form-control-feedback"></span>')
                        .insertAfter(element);
                    var r=analyzeEL(EL);
                    var tempObj={};
                    if(r.b&&scope[r.b]){
                        var modelVL=getObjectPropertyValue(scope,attrs['ngModel']);
                        for(var i=0;i<scope[r.b].length;i++){
                            if(scope[r.b][i]==modelVL||scope[r.b][i][r.d]==modelVL){
                                element.val(scope[r.b][i][r.c]);
                                //防止model中的值写入到view中,因为两者不想等
                                ngModel.$render= function () {};
                                break;
                            }
                        }
                        if(!r.e){
                            var d='';
                            for(var i=0;i<scope[r.b].length;i++){
                                tempObj[r.a]=scope[r.b][i];
                                var label=tempObj[r.a][r.c];
                                d+='<li><a href="#" data-index="'+i+'">'+label+'</a></li>';
                                dropdown.html(d);
                            }
                        }else{
                            var d='';
                            var group;
                            var groupBy= r.e
                            //先排序
                            scope[r.b]=$filter('orderBy')(scope[r.b],groupBy);
                            for(var i=0;i<scope[r.b].length;i++){
                                tempObj[r.a]=scope[r.b][i];
                                var label=tempObj[r.a][r.c];
                                if(group==undefined){
                                    group=tempObj[r.a][groupBy];
                                    d+='<li class="dropdown-header">'+groupBy+'::'+group+'</li>';
                                }
                                var currentGroup=tempObj[r.a][groupBy];
                                if(currentGroup==group){
                                    d+='<li><a href="#" data-index="'+i+'">'+label+'</a></li>';
                                }else{
                                    group=currentGroup;
                                    d+='<li class="dropdown-header">'+groupBy+'::'+group+'</li>';
                                }

                            }
                            dropdown.html(d);
                        }

                    }
                    element.click(function () {
                        dropdown.removeAttr('style');
                    });
                    function click(e){
                        var target=$(e.target);
                        if(target.is('a')){
                            e.preventDefault();
                            if(r.d){
                                ngModel.$setViewValue(scope[r.b][target.data('index')][r.d]);
                            }else{
                                ngModel.$setViewValue(scope[r.b][target.data('index')]);
                            }
                            element.val(scope[r.b][target.data('index')][r.c]);
                            dropdown.slideUp(150);
                        }
                    }
                })(attrs['formSelect']);
            }
        }
    }])
    .directive('formSelectPanel',['$filter',function($filter){
        return {
            restrict: 'A',
            require:'ngModel',
            link: function (scope,element,attrs,ngModel) {
                (function (EL) {
                    element.addClass('form-select-panel');
                    element.attr('readonly',true);
                    element.closest('.form-group').addClass('has-feedback');
                    var r=analyzeEL(EL);
                    var selectTemplate='<div class="dropdown-menu"></div>';
                    var selectPicker=$(selectTemplate)
                        .insertAfter(element)
                        .on({click: click});
                    var feedback=$('<span class="glyphicon glyphicon-asterisk form-control-feedback"></span>')
                        .insertAfter(element);
                    var tempObj={};
                    if(r.b&&scope[r.b]){
                        var modelVL=getObjectPropertyValue(scope,attrs['ngModel']);
                        for(var i=0;i<scope[r.b].length;i++){
                            if(scope[r.b][i]==modelVL||scope[r.b][i][r.d]==modelVL){
                                element.val(scope[r.b][i][r.c]);
                                //防止model中的值写入到view中,因为两者不想等
                                ngModel.$render= function () {};
                                break;
                            }
                        }
                        if(!r.e){
                            var d='<div class="panel panel-default"><div class="panel-body">';
                            for(var i=0;i<scope[r.b].length;i++){
                                tempObj[r.a]=scope[r.b][i];
                                var label=tempObj[r.a][r.c];
                                d+='<span data-index="'+i+'">'+label+'</span>';
                            }
                            selectPicker.html(d+'</div></div>');
                        }else{
                            var d='';
                            var group;
                            var groupBy= r.e;
                            //先排序
                            scope[r.b]=$filter('orderBy')(scope[r.b],groupBy);
                            for(var i=0;i<scope[r.b].length;i++){
                                tempObj[r.a]=scope[r.b][i];
                                var label=tempObj[r.a][r.c];
                                if(group==undefined){
                                    group=tempObj[r.a][groupBy];
                                    d+='<div class="panel panel-default"><div class="panel-heading">'+groupBy+'::'+group+'</div><div class="panel-body">';
                                }
                                var currentGroup=tempObj[r.a][groupBy];
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
                        if(target.is('span')){
                            if(r.d){
                                ngModel.$setViewValue(scope[r.b][target.data('index')][r.d]);
                            }else{
                                ngModel.$setViewValue(scope[r.b][target.data('index')]);
                            }
                            element.val(scope[r.b][target.data('index')][r.c]);
                            selectPicker.slideUp(150);
                        }
                    };
                    element.click(function () {
                        selectPicker.removeAttr('style');
                    });
                })(attrs['formSelectPanel']);
            }
        }
    }]).directive('ddSelect',['$http','S_constant', function ($http,S_constant) {
        return {
            restrict: 'A',
            require:'ngModel',
            link: function (scope,element,attrs,ngModel) {
                (function (EL) {
                    element.addClass('dd-select');
                    element.attr('readonly',true);
                    element.closest('.form-group').addClass('has-feedback');
                    var dropdown=$('<div class="dropdown-menu"></div>')
                        .insertAfter(element)
                        .on({click:click});
                    var feedback=$('<span class="glyphicon glyphicon-asterisk form-control-feedback"></span>')
                        .insertAfter(element);
                    var r=analyzeEL(EL);
                    var tempObj={};
                    if(r.b){
                        $http.post(S_constant.dd[0],{code: r.b})
                            .success(function (data) {
                                scope[r.b]=data;

                                var modelVL=getObjectPropertyValue(scope,attrs['ngModel']);
                                if(modelVL){
                                    for(var i=0;i<data.length;i++){
                                        if(data[i]==modelVL||data[i][r.d]==modelVL){
                                            element.val(data[i][r.c]);
                                            break;
                                        }
                                    }
                                }

                                if(!r.e){
                                    var d='';
                                    for(var i=0;i<scope[r.b].length;i++){
                                        tempObj[r.a]=scope[r.b][i];
                                        var label=tempObj[r.a][r.c];
                                        d+='<li><a href="#" data-index="'+i+'">'+label+'</a></li>';
                                        dropdown.html(d);
                                    }
                                }else{
                                    var d='';
                                    var group;
                                    var groupBy= r.e
                                    //先排序
                                    scope[r.b]=$filter('orderBy')(scope[r.b],groupBy);
                                    for(var i=0;i<scope[r.b].length;i++){
                                        tempObj[r.a]=scope[r.b][i];
                                        var label=tempObj[r.a][r.c];
                                        if(group==undefined){
                                            group=tempObj[r.a][groupBy];
                                            d+='<li class="dropdown-header">'+groupBy+'::'+group+'</li>';
                                        }
                                        var currentGroup=tempObj[r.a][groupBy];
                                        if(currentGroup==group){
                                            d+='<li><a href="#" data-index="'+i+'">'+label+'</a></li>';
                                        }else{
                                            group=currentGroup;
                                            d+='<li class="dropdown-header">'+groupBy+'::'+group+'</li>';
                                        }

                                    }
                                    dropdown.html(d);
                                }
                            })
                            .error(function () {
                                console.error('接口调用失败::['+S_constant.dd[0]+']');
                            });
                    }
                    element.click(function () {
                        dropdown.removeAttr('style');
                    });
                    function click(e){
                        var target=$(e.target);
                        if(target.is('a')){
                            e.preventDefault();
                            if(r.d){
                                ngModel.$setViewValue(scope[r.b][target.data('index')][r.d]);
                            }else{
                                ngModel.$setViewValue(scope[r.b][target.data('index')]);
                            }
                            element.val(scope[r.b][target.data('index')][r.c]);
                            dropdown.slideUp(150);
                        }
                    }
                })(attrs['ddSelect']);
            }
        }
    }])