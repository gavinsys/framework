<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../common/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="user.title"/></title>
</head>
<body>
<form action="create" method="post">
	<table>
		<tr><td>name</td><td><input name="username"/></td></tr>
		<tr><td></td><td><input type="submit"/></td></tr>
	</table>
</form>
</body>
</html>