<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" media="screen" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/bootstrap-theme.min.css" media="screen" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/mystyle.css" media="screen" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath }/js/jquery-2.1.3.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js" type="text/javascript"></script>
<title>gogohome</title>

<nav class="nav navbar-inverse navbar-fixed-top" style="filter: alpha(Opacity =   90); -moz-opacity: 0.9; opacity: 0.9" role="navigation">
	<div class="container">
		<div class="navbar-header">
   	     <a href="#" class="navbar-brand">
   	        <span class="glyphicon glyphicon-road"></span> <strong>gogohome</strong>
   	     </a>
   		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="${pageContext.request.contextPath }/platform">首頁</a></li>
			<li><a href="${pageContext.request.contextPath }/platform/user">用戶管理</a></li>
			<li><a href="${pageContext.request.contextPath }/platform/driver">司機管理</a></li>
			<li><a href="${pageContext.request.contextPath }/platform/customer">客戶管理</a></li>
			<li><a href="${pageContext.request.contextPath }/platform/order">訂單管理</a></li>
		</ul>
		<div class="navbar-form navbar-right">
   			<a href="${pageContext.request.contextPath }/platform/logout" style="color: #D2D2D2;">退 出</a>
   		</div>
	</div>
</nav>
<div style="height: 60px;"></div>