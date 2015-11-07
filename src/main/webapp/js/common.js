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