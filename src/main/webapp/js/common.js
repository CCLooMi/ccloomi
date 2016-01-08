/**
 * Created by chenxianjun on 15/11/7.
 */
function cloneFrom(a){
    var b;
    if(a[0]){b=[];}else{b={};}
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

function placeAunderB(a,b){
    var body=$('body');
    var offset,top,left,containerOffset;
    containerOffset=body.offset();
    offset=b.offset();
    left=offset.left;
    var bodyWidth=document.body.clientWidth||window.innerWidth;
    if(350>=window.innerWidth){
        left=0;
    }else{
        if(left+350>bodyWidth){
            left=bodyWidth-350;
        }
    }
    top=offset.top+b.outerHeight();
    top=top-containerOffset.top;
    left=left-containerOffset.left;
    a.css({
        top:top,
        left:left
    });
}
Array.minus = function(a,b){
    var r=[];
    for(var i in a){
        if(b.indexOf(a[i])==-1){
            r.push(a[i]);
        }
    }
    return r;
};