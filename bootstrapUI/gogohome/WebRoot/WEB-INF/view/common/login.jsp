<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" media="screen" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/bootstrap-theme.min.css" media="screen" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/mystyle.css" media="screen" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath }/js/jquery-2.1.3.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js" type="text/javascript"></script>
<title>用戶登錄</title>
</head>

<body>
<div class="container">
<div class="row">
	<div class="col-md-4"></div>
	<div class="col-md-4">
		<div class="panel panel-default" style="margin-top:50%;">
		  <div class="panel-heading">用戶登錄</div>
		  <div class="panel-body">
		    <form action="login" method="post" class="form-signin">
				<input type="text" name="name" value="${name }" class="form-control" placeholder="用戶" required="請輸入用戶!" autofocus="">
				<input type="password" name="pass" class="form-control" placeholder="密碼" required="請輸入密碼!">
				<button class="btn btn-lg btn-primary btn-block" type="submit">登錄</button>
			</form>
		  </div>
		</div>
	</div>
	<div class="col-md-4"></div>
</div>
</div>
</body>
</html>