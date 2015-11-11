/**
 * Created by chenxianjun on 15/11/7.
 */
function cloneFrom(a){
    var b={};
    for(var k in a){
        b[k]=a[k];
    }
    return b;
}
function cloneA2B(a,b){
    for(var k in a){
        b[k]=a[k];
    }
}
function refreshScope(scope){
    if(!scope.$$phase){
        scope.$apply();
    }
}
function highlight(kstr,str,color){
    var keywords=[];
    color=color||"red";
    var pl='<span style="color: '+color+';">';
    var pr='</span>';

    if(typeof kstr=='string'){
        keywords=kstr.match(/[\u4e00-\u9fa5]+|\w+/g);
    }else{
        keywords=kstr;
    }
    for(var i =0;i<keywords.length;i++){
        var keyword=keywords[i];
        if(keyword&&keyword!=''){
            //keyword.replace(/#\(/g,'').replace(/#\)/g,'');
            var reg=new RegExp(keyword,'gi');
            str=str.replace(reg,'#('+keyword+'#)');
        }
    };
    return str.replace(/#\(/g,pl).replace(/#\)/g,pr);
}