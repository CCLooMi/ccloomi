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
function analyzeEL(EL){
    var a, b, c, d, e;
    try{
        var el1=EL.match(/\w+@[^ ]+/g)[0].split('@');
    }catch(e){
        console.error(e);
    };
    try{
        var el2=EL.match(/\w+\.\w+&\w+\.\w+/g)[0].match(/\w+\.\w+/g);
    }catch(e){
        var el2=EL.match(/^\w+\.\w+| \w+\.\w+/g);
    };
    try{
        var el3=EL.match(/\/\w+\.\w+/g)[0].match(/\w+\.\w+/g);
    } catch (e) {
    }
    if(el1&&el1[1]){
        a=el1[0];
        b=el1[1];
    }
    if(el2){
        if(el2[1]){
            c=el2[0].split('\.')[1];
            d=el2[1].split('\.')[1];
        }else{
            c=el2[0].split('\.')[1];
        }
    }
    if(el3){
        e=el3[0].split('\.')[1];
    }
    return {a:a,b:b,c:c,d:d,e:e};
}
/*
* 用法demo
* var user={u:{name:'user',sex:{a:'M',b:'F'}}};
 console.log(getObjectPropertyValue(user,'u.sex.b'));
 */
function getObjectPropertyValue(obj,p){
    var t,ps= p.split('\.');
    if(ps.length){
        t=obj;
        for(var i=0;i<ps.length;i++){
            t=t[ps[i]];
            if(!t){
                return t;
            }
        }
    }
    return t;
}