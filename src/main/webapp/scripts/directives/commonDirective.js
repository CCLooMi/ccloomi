/**
 * Created by chenxianjun on 16/1/4.
 */
var searchBarTemplate='<li class="search-bar"> <div class="input-group"><input type="text" class="form-control"><span class="input-group-btn"><button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button></span></div></li>';

app.directive('affix', function () {
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
app.directive('ccEditor',function () {
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
app.directive('formDate',function(){
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
app.directive('formSelect',['$filter','$parse', function ($filter,$parse) {
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
                var searchBar=$(searchBarTemplate);
                var feedback=$('<span class="glyphicon glyphicon-asterisk form-control-feedback"></span>')
                    .insertAfter(element);
                var r=analyzeEL(EL);
                if(r.b){
                    scope.$watchCollection(r.b, function () {
                        ngModel.$setViewValue(null);
                        element.val(null);
                        dropdownHtml(r);
                    });
                }
                element.click(function () {
                    dropdown.removeAttr('style');
                });
                function dropdownHtml(r){
                    dropdown.html('');
                    var bs=$parse(r.b)(scope);
                    if(bs){
                        //var modelVL=getObjectPropertyValue(scope,attrs['ngModel']);
                        var modelVL=$parse(attrs['ngModel'])(scope);
                        for(var i=0;i<bs.length;i++){
                            if(bs[i]==modelVL||$parse(r.d)(bs[i])==modelVL){
                                element.val($parse(r.c)(bs[i]));
                                //防止model中的值写入到view中,因为两者不想等
                                ngModel.$render= function () {};
                                break;
                            }
                        }
                        if(!r.e){
                            var d='';
                            for(var i=0;i<bs.length;i++){
                                var label=$parse(r.c)(bs[i]);
                                d+='<li><a href="#" data-index="'+i+'">'+label+'</a></li>';
                                dropdown.html(d);
                            }
                        }else{
                            var d='';
                            var group;
                            var groupBy= r.e
                            //先排序
                            bs=$filter('orderBy')(bs,groupBy);
                            for(var i=0;i<scope[r.b].length;i++){
                                var label=$parse(r.c)(bs[i]);
                                if(group==undefined){
                                    group=$parse(groupBy)(bs[i]);
                                    d+='<li class="dropdown-header">'+groupBy+'::'+group+'</li>';
                                }
                                var currentGroup=$parse(groupBy)(bs[i]);
                                if(currentGroup==group){
                                    d+='<li><a href="#" data-index="'+i+'">'+label+'</a></li>';
                                }else{
                                    group=currentGroup;
                                    d+='<li class="dropdown-header">'+groupBy+'::'+group+'</li>';
                                }

                            }
                            dropdown.html(d);
                        }
                        if(attrs['searchAble'])
                        searchBar.prependTo(dropdown);
                    }
                }
                function click(e){
                    var target=$(e.target);
                    var bs=$parse(r.b)(scope);
                    if(target.is('a')){
                        e.preventDefault();
                        if(r.d){
                            ngModel.$setViewValue($parse(r.d)(bs[target.data('index')]));
                        }else{
                            ngModel.$setViewValue(bs[target.data('index')]);
                        }
                        element.val($parse(r.c)(bs[target.data('index')]));
                        dropdown.slideUp(150);
                    }
                }
            })(attrs['formSelect']);
        }
    }
}])
app.directive('formSelectPanel',['$filter','$parse',function($filter,$parse){
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
                var searchBar=$(searchBarTemplate);
                var feedback=$('<span class="glyphicon glyphicon-asterisk form-control-feedback"></span>')
                    .insertAfter(element);
                if(r.b){
                    scope.$watchCollection(r.b, function () {
                        ngModel.$setViewValue(null);
                        element.val(null);
                       dropdownHtml(r);
                    });
                }
                function dropdownHtml(r){
                    var bs=$parse(r.b)(scope);
                    if(bs){
                        //var modelVL=getObjectPropertyValue(scope,attrs['ngModel']);
                        var modelVL=$parse(attrs['ngModel'])(scope);
                        for(var i=0;i<bs.length;i++){
                            if(bs[i]==modelVL||$parse(r.d)(bs[i])==modelVL){
                                element.val($parse(r.c)(bs[i]));
                                //防止model中的值写入到view中,因为两者不想等
                                ngModel.$render= function () {};
                                break;
                            }
                        }
                        if(!r.e){
                            var d='<div class="panel panel-default"><div class="panel-body">';
                            for(var i=0;i<bs.length;i++){
                                var label=$parse(r.c)(bs[i]);
                                d+='<span data-index="'+i+'">'+label+'</span>';
                            }
                            selectPicker.html(d+'</div></div>');
                        }else{
                            var d='';
                            var group;
                            var groupBy= r.e;
                            //先排序
                            bs=$filter('orderBy')(bs,groupBy);
                            for(var i=0;i<scope[r.b].length;i++){
                                var label=$parse(r.c)(bs[i]);
                                if(group==undefined){
                                    group=$parse(groupBy)(bs[i]);
                                    d+='<div class="panel panel-default"><div class="panel-heading">'+groupBy+'::'+group+'</div><div class="panel-body">';
                                }
                                var currentGroup=$parse(groupBy)(bs[i]);
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
                        if(attrs['searchAble'])
                        searchBar.prependTo(selectPicker);
                    }
                }
                function click (e) {
                    var target=$(e.target);
                    var bs=$parse(r.b)(scope);
                    if(target.is('span')){
                        if(r.d){
                            ngModel.$setViewValue($parse(r.d)(bs[target.data('index')]));
                        }else{
                            ngModel.$setViewValue(bs[target.data('index')]);
                        }
                        element.val($parse(r.c)(bs[target.data('index')]));
                        selectPicker.slideUp(150);
                    }
                };
                element.click(function () {
                    selectPicker.removeAttr('style');
                });
            })(attrs['formSelectPanel']);
        }
    }
}])
app.directive('ddSelect',['$http','$parse','S_constant', function ($http,$parse,S_constant) {
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
                var searchBar=$(searchBarTemplate);
                var feedback=$('<span class="glyphicon glyphicon-asterisk form-control-feedback"></span>')
                    .insertAfter(element);
                var r=analyzeEL(EL);
                var bs=[];
                if(r.b){
                    $http.post(S_constant.dd[0],{code: r.b})
                        .success(function (data) {
                            //设置data到scope中对应的属性值
                            //此处注意和ngModel绑定的对象冲突,故去掉点前面的字符(例如a.b-->b)
                            $parse(r.b.split('\\.')[0]).assign(scope,data);
                            bs=data;
                            //var modelVL=getObjectPropertyValue(scope,attrs['ngModel']);
                            var modelVL=$parse(attrs['ngModel'])(scope);
                            if(modelVL){
                                for(var i=0;i<bs.length;i++){
                                    if(bs[i]==modelVL||$parse(r.d)(bs[i])==modelVL){
                                        element.val($parse(r.c)(bs[i]));
                                        break;
                                    }
                                }
                            }

                            if(!r.e){
                                var d='';
                                for(var i=0;i<bs.length;i++){
                                    var label=$parse(r.c)(bs[i]);
                                    d+='<li><a href="#" data-index="'+i+'">'+label+'</a></li>';
                                    dropdown.html(d);
                                }
                            }else{
                                var d='';
                                var group;
                                var groupBy= r.e
                                //先排序
                                bs=$filter('orderBy')(bs,groupBy);
                                for(var i=0;i<bs.length;i++){
                                    var label=$parse(r.c)(bs[i]);
                                    if(group==undefined){
                                        group=$parse(groupBy)(bs[i]);
                                        d+='<li class="dropdown-header">'+groupBy+'::'+group+'</li>';
                                    }
                                    var currentGroup=$parse(groupBy)(bs[i]);
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
                            ngModel.$setViewValue($parse(r.d)(bs[target.data('index')]));
                        }else{
                            ngModel.$setViewValue(bs[target.data('index')]);
                        }
                        element.val($parse(r.c)(bs[target.data('index')]));
                        dropdown.slideUp(150);
                    }
                }
            })(attrs['ddSelect']);
        }
    }
}])
app.directive('code', function () {
    return {
        restrict: 'E',
        link: function (scope,element,attrs) {
            var worker=new Worker('js/Highlight.worker.js');
            worker.onmessage= function (event) {
                element.html(event.data);
            };
            worker.postMessage({text:element.text(),language:attrs.class});
        }
    }
})
app.directive('ngBindHtmlCompile',['$compile',function ($compile) {
    return {
        restrict: 'A',
        link: function (scope,element,attrs) {
            scope.$watch(function (scope) {
                return scope.$eval(attrs.ngBindHtmlCompile);
            }, function (value) {
                var v=$(value);
                element.html(v);
                v.find('code').each(function(i, block) {
                    hljs.highlightBlock(block);
                });
                v.find('code').removeClass('hljs');
                //$compile(element.contents())(scope);
            })
        }
    }
}])