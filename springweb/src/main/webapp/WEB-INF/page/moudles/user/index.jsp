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
<form action="search" method="post">
	<table border="1" width="500">
		<tr><td>用户名称</td><td><input name="name" /></td><td>状态</td><td><input name="status" /></td></tr>
		<tr><td></td><td></td><td colspan="2"><input type="submit"/><input type="reset"/></td></tr>
	</table>
</form>
<table border="1" width="500">
	<tr><td colspan="2"><a href="editNew">create</a></td></tr>
	<tr><td>用户名称</td><td>操作</td></tr>
	<c:forEach var="user" items="${page.data }">
	<tr><td>${user.username }</td><td><a href="show?id=${user.id }">show</a> <a href="delete?id=${user.id }">delete</a> <a href="update?id=${user.id }">update</a></td></tr>
	</c:forEach>
</table>
</body>
</html>