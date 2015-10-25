<%@ page session="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta content="IE=edge" http-equiv="X-UA-Compatible">
	<meta content="width=device-width, initial-scale=1" name="viewport">
	<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
	<title>CCLooMIi</title>
	<script src="/bower_components/jquery/dist/jquery.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>Welcome!</h1>
		<div id="page"></div>
	</div>
	<script>
		var site="http://mvnrepository.com";
		var historySite;
		loadSite("/search?q=spring");
		$("#page").click(function (e) {
			var target=$(e.target);
			if(target.is($("a"))){
				var url=target.attr("href");
				if(/\/\//g.test(url)){

				}else if(/^#/g.test(url)){
					$(url).addClass("active");
				}else{
					e.preventDefault();
					loadSite(url);
				}
			}
		});


		function loadSite(url){
			if(startWithSlash(url)){
				url="site/agent.xhtml?site=http://mvnrepository.com"+url;
			}else{
				if(historySite){
					url=historySite.match(/.+\//g)+url;
				}else{
					url="site/agent.xhtml?site=http://mvnrepository.com/"+url;
				}
			}
			$("#page").load(url+" #maincontent", function () {
				historySite=url;
			});
		};
		function startWithSlash(str){
			return str.indexOf("/")==0?true:false;
		}
	</script>
</body>
</html>