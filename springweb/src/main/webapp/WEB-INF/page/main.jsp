<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="common/resources.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>welcome</title>
</head>
<body class="easyui-layout">
    <div data-options="region:'north',href:'init/north'" style="height:80px;"></div>
    <div data-options="region:'south',href:'init/south'" style="height:30px;"></div>
    <div data-options="region:'west',title:'功能菜单',split:true,href:'init/west'" style="width:250px;"></div>
    <div data-options="region:'center',href:'init/center'" style="background:#eee;"></div>
</body>
</html>