/**
 * Created by chenxianjun on 16/3/20.
 */
self.onmessage = function(event) {
    importScripts('../js/highlight.pack.js');
    var result = self.hljs.highlightAuto(event.data);
    self.postMessage(result.value);
};