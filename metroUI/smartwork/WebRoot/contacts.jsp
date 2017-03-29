<%@page import="com.smartwork.common.model.Contacts"%>
<%@page import="com.smartwork.enums.EnumJobs"%>
<%@page import="com.smartwork.enums.EnumDepts"%>
<%@page import="com.smartwork.common.model.Daily"%>
<%@page import="com.jfinal.plugin.activerecord.Page"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="${pageContext.request.contextPath }/common/_taglib.jsp" />
</head>
<body>
	<jsp:include page="${pageContext.request.contextPath }/common/_header.jsp" />
	<div class="container">
		<div class="window success">
			<div class="window-caption">
				<span class="window-caption-icon"><span class="mif-contacts-dialer"></span></span> <span
					class="window-caption-title">通讯录</span>
			</div>
			<div class="window-content">
				<button class="button loading-pulse lighten primary"
					onclick="javascript:window.location.href='${pageContext.request.contextPath }/download'">导出Excel</button>
			</div>
		</div>
		<table class="dataTable border bordered hovered" data-role="datatable" data-searching="true">
			<thead>
				<tr>
					<th style="width: 100px;">部门</th>
					<th style="width: 100px;">职位</th>
					<th style="width: 100px;">中文名</th>
					<th style="width: 100px;">英文名</th>
					<th style="width: 150px;">手机</th>
					<th style="width: 150px;">邮箱</th>
					<th style="width: 150px;">创建时间</th>
					<th style="width: 150px;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="currentPage" value="${contactsPage.pageNumber }" />
				<c:set var="totalPage" value="${contactsPage.totalPage }" />
				<c:set var="actionUrl" value="${pageContext.request.contextPath }/list/" />
				<c:set var="urlParas" value="" />
				<c:if test="${!empty contactsPage.list }">
					<%
						Page<Contacts> list = (Page<Contacts>) request.getAttribute("contactsPage");
							for (Contacts contacts : list.getList()) {
					%>
					<tr>
						<td><%=EnumDepts.findByCode(contacts.get("dept").toString())%></td>
						<td><%=EnumJobs.findByCode(Integer.valueOf(contacts.get("jobs").toString()))%></td>
						<td><%=contacts.get("namecn")%></td>
						<td><%=contacts.get("nameen")%></td>
						<td><%=contacts.getPhone()%></td>
						<td><%=contacts.getEmail()%></td>
						<td><fmt:formatDate value="<%=contacts.getCreated()%>" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>
							<%
								Contacts user = (Contacts) session.getAttribute("user");
										if (user.getId().equals(contacts.getId())) {
							%> &nbsp;&nbsp;<a href="${pageContext.request.contextPath }/del/<%=contacts.getId() %>">刪除</a> &nbsp;&nbsp;<a
							href="${pageContext.request.contextPath }/edit/<%=contacts.getId() %>">修改</a> <%
						 	}
						 %>
						</td>
					</tr>
					<%
						}
					%>
				</c:if>
			</tbody>
		</table>
	</div>
</body>
</html>