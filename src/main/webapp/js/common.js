/**
 * Created by chenxianjun on 15/11/7.
 */
Array.prototype.inOf=function(o){
  for(var i=0;i<this.length;i++){
    if(JSON.stringify(this[i])==JSON.stringify(o))return i;
  }
  return -1;
}
function cloneFrom(a){
    if(!a)return a;
    var b;
    if(a instanceof Array){
        b=[];
        for(var i=0;i< a.length;i++){
            b[i]=a[i];
        }
    }else{
        b={};
        for(var k in a){
            b[k]=a[k];
        }
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
    for(var i=0;i<a.length;i++){
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
        var el2=EL.match(/[^ /@|]+\.[^ /@|]+&[^ /@|]+\.[^ /@|]+/g)[0].split('&');
    }catch(e){
        var el2=EL.match(/^[^ /@|]+\.[^ /@|]+| [^ /@|]+\.[^ /@|]+/g);
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
            c=el2[0].replace(new RegExp(a+'\\.','gm'),'');
            d=el2[1].replace(new RegExp(a+'\\.','gm'),'');
        }else{
            c=el2[0];
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
/*
 var a=[1,2,4,5,9];
 var b=[2,4,5,6,8];
 console.log(JSON.stringify(getDeleteUpdateAdd(a,b)));
 */
function getDeleteUpdateAdd(oo,nn){
    var o=cloneFrom(oo);
    var n=cloneFrom(nn);
    var result={del:[],upd:[],add:[]};
    var on=[];
    for(var i=0;i<o.length;i++){
        var oi=o[i];
        if(n.inOf(oi)>-1){
            on.push(oi);
        }
    };
    for(var i=0;i<on.length;i++){
        var oni=on[i];
        n.splice(n.inOf(oni),1);
        o.splice(o.inOf(oni),1);
    };
    for(var i=0;i<o.length;i++){
        var oi=o[i];
        if(n.length){
            result.upd.push({from:oi,to:n.shift()});
        }else{
            result.del.push(oi);
        }
    }
    result.add=n;
    return result;
};
//倒计时a间隔单位毫秒b时长f回调函数c结束回调函数
function countDown(a,b,f,c){
    var i=b;
    var cd=setInterval(function(){
        f&&f(i);
        i--;
        if(i<0){
            clearInterval(cd);
            c&&c();
        };
    },a);
};