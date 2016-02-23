
function SHTEAPI(){
	this.results = new Array();
	this.cDOMtype = '';
	this.domain = "http://shooter.cn";
	this.handler =  "/api/qhandler.php";
	this.target = "_blank";
	this.posobj = null;
	this.mouseX = -1;
	this.mouseY = -1;
}


SHTEAPI.prototype = {
	showResults : function(){
		var posleft = 100;
		var postop = 100;
		if(typeof(this.posobj.offsetLeft) != 'undefined'){
			posleft = this.posobj.offsetLeft ;
			postop = this.posobj.offsetTop+this.posobj.offsetHeight+2;
			var obj = this.posobj;
			if (obj.offsetParent){
    			while(obj = obj.offsetParent)
		        {
		            posleft += obj.offsetLeft;
		            postop += obj.offsetTop;
		        }
    		}
		}else {
			
			posleft = this.mouseX-15;
			postop = this.mouseY+15;
		}
		var obt = this.fobj('shte_mainbox');
		if(!obt){
			obt = document.createElement("DIV");
			obt.className = 'shteapimainbox';
			obt.id = "shte_mainbox";
			document.body.appendChild(obt);
		   
		}
		obt.style.top = postop;
		obt.style.left = posleft;
		obt.style.display = "block";
		var buf = "";
		var rgot = 0;
		for(var i = 0; i < this.results.length;i++){
			if(this.results[i]['title']){
				rgot = 1;
				if(this.results[i]['desc']){
					this.results[i]['desc'] = "<br/>"+this.results[i]['desc'];
				}else{
					this.results[i]['desc'] = "";
				}
				buf += "<div class='shteapiitembox'><a class='shteapititlelink' target='"+this.target +"' href=\""+this.domain+this.results[i]['link']+"\">"+this.results[i]['title']+"</a>"+this.results[i]['desc']+"</div>";
			}
		}
		if(!rgot){
			buf = "<div class='shteapiitembox'>很抱歉，没有找到结果。</div>";
		}
		obt.innerHTML = "<table class='shteapishadowtable'>"+
		"<tr><td class='shteapishadowcornertl'></td><td class='shteapishadowtbtop'><a href='javascript:shteAPI.closeUI();' class='shteapiclosebtn' ></a></td><td class='shteapishadowcornertr'></td></tr>"+
		"<tr><td class='shteapishadowtbleft'></td><td class='shteapishadowtbcenter'>"+
		"<div class='shteapishowbox'>"+buf+"</div>"+
		"</td><td class='shteapishadowtbright'></td></tr>"+
		"<tr><td class='shteapishadowcornerbl'></td><td  class='shteapishadowtbbottom'><div class='shteapiq4moresec'>"+
		"共 "+this.results['totalc']+" 个结果 <a  target='"+this.target +"' href='"+this.domain+this.results['q4more']+"'>更多...</a></div></td><td class='shteapishadowcornerbr'></td></tr></table>";
	
		
	},
	closeUI : function( ){
		var obt = this.fobj('shte_mainbox')
		obt.style.display = "none";
	},
	querySub : function( qinput, posobj){

		return this.real_query("sub", qinput, posobj);
	},
	real_query : function(qtype, qinput, posobj){
		var obtx = this.fobj(qinput);
		if(obtx){
			if(obtx.tagName == 'INPUT' && obtx.type == 'text'){
				if(obtx.value){
					qinput = obtx.value;
				}
			}else if(obtx.tagName == 'TEXTAREA'){
				qinput = obtx.innerTEXT;
			}else{
				qinput = obtx.innerHTML;
			}
		}
		
		
			this.posobj  = this.fobj(posobj);
		
			
			if('undefined'!=typeof( this.posobj.pageX))
			{
				this.mouseX = this.posobj.pageX;
				this.mouseY = this.posobj.pageY;				
			}
			else if('undefined'!=typeof( this.posobj.clientX))
			{
				
				this.mouseX = this.posobj.clientX  + document.body.scrollLeft;
				this.mouseY = this.posobj.clientY + document.body.scrollTop;
			}
			var echarset = "";
			if(document.charset){
				echarset = document.charset;
			}else{
				echarset = document.inputEncoding;
			}
			var baseurl = document.baseURI ||  document.URL;
		qstr = "?t="+encodeURIComponent(qtype)+"&e="+encodeURIComponent(echarset)+"&q="+encodeURIComponent(qinput)+"&og="+encodeURIComponent(baseurl)+"&rd="+Math.random();
		
			var udsb=document.getElementsByTagName("head")[0];
			var udsa=document.createElement("script");
			udsa.type="text/javascript";
			udsa.charset="utf-8";
			udsa.src = this.domain+ this.handler +qstr;
			var udsf=function(){
				var udsh=udsa.parentNode;
				udsh.removeChild(udsa);
				delete udsa
			};
			var udse=function(udsh){
				var udsj=(udsh?udsh:window.event).target?(udsh?udsh:window.event).target:(udsh?udsh:window.event).srcElement;
				if(udsj.readyState=="loaded"||udsj.readyState=="complete"){udsf()}
			};
			if(navigator.product=="Gecko"){
				udsa.onload=udsf
			}else{
				udsa.onreadystatechange=udse
			}
			udsb.appendChild(udsa)
			
		return ;
	},
	fobj : function(idname){
		if(typeof(idname) == 'object'){
			return idname;
		}
		if(!this.cDOMtype){
			if (document.getElementById)
			{
				this.cDOMtype = "std";
			}
			else if (document.all)
			{
				this.cDOMtype = "ie4";
			}
			else if (document.layers)
			{
				this.cDOMtype = "ns4";
			}
		}
		switch (this.cDOMtype)
		{
			case "std":
			{
				return document.getElementById(idname);
			}
			break;

			case "ie4":
			{
				return document.all[idname];
			}
			break;

			case "ns4":
			{
				return document.layers[idname];
			}
			break;
		}
	}
};

var shteAPI = new SHTEAPI();
