/**
 * Created by chenxianjun on 16/3/20.
 */
self.onmessage = function(event) {
    importScripts('../js/highlight.pack.js');
    var language;
    try{
        language=hljs.getLanguage(event.data.language.split('-')[1]).aliases;
    }catch(e){
        language=undefined;
    }
    var result = self.hljs.highlightAuto(event.data.text,language);
    self.postMessage(result.value);
};