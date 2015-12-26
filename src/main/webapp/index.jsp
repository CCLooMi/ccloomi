<%@ page session="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>CCLooMIi</title>
	<base href="<%=basePath%>">
	<meta content="IE=edge" http-equiv="X-UA-Compatible">
	<meta content="width=device-width, initial-scale=1" name="viewport">
	<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
	<link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="bower_components/angular-loading-bar/build/loading-bar.min.css">
	<link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="bower_components/sweetalert/dist/sweetalert.css">
	<link rel="stylesheet" href="bower_components/vis/dist/vis.min.css">
	<link rel="stylesheet" href="bower_components/Context.js/context.standalone.css">

	<link rel="stylesheet" href="styles/common.css">
	
	<script src="bower_components/jquery/dist/jquery.min.js"></script>
	<script src="bower_components/angular/angular.js"></script>
	<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="bower_components/angular-ui-router/release/angular-ui-router.js"></script>
	<script src="bower_components/oclazyload/dist/ocLazyLoad.min.js"></script>
	<%--<script src="js/ocLazyLoad.js"></script>--%>
	<script src="bower_components/angular-loading-bar/build/loading-bar.min.js"></script>
	<script src="bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>
	<script src="bower_components/angular-sanitize/angular-sanitize.min.js"></script>
	<script src="bower_components/Chart.js/Chart.min.js"></script>
	<script src="bower_components/sweetalert/dist/sweetalert.min.js"></script>
	<script src="bower_components/vis/dist/vis.min.js"></script>
	<script src="bower_components/Context.js/context.js"></script>

	<script src="js/common.js"></script>
	<script src="scripts/app.js"></script>
	<script src="scripts/services/commonService.js"></script>
</head>
<body ng-app="ccloomi" ui-view>
</body>
</html>