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
	<script src="bower_components/jquery/dist/jquery.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>Welcome!</h1>
		<div id="page"></div>
	</div>
	<script>
		var site="http://mvnrepository.com";
		$("#page").load("site/agent.xhtml?site=http://mvnrepository.com/search?q=spring #maincontent", function () {});
	</script>
</body>
</html>