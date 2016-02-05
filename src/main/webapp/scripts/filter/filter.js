/**
 * Created by chenxianjun on 16/2/5.
 */
app.filter('highlight', ['$sce',function ($sce) {
    return function (a,b) {
        if(a&&b){
            return $sce.trustAsHtml(highlight(b+'',a+''));
        }else{
            return a;
        }
    }
}]);