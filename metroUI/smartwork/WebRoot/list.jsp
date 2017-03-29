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
		<div class="window">
			<div class="window-caption">
				<span class="window-caption-icon"><span class="mif-list2"></span></span> <span class="window-caption-title">工作板</span>
			</div>
			<div class="window-content">
				<button class="button loading-pulse lighten primary"
					onclick="javascript:window.location.href='${pageContext.request.contextPath }/export'">导出Excel</button>
			</div>
		</div>
		<table class="dataTable border bordered hovered" data-role="datatable" data-searching="true">
			<thead>
				<tr role="row">
					<th style="width: 100px;">部门</th>
					<th style="width: 100px;">职位</th>
					<th style="width: 100px;">所属</th>
					<th style="width: 300px;">标题</th>
					<th style="width: 150px;">开始时间</th>
					<th style="width: 150px;">结束时间</th>
					<th style="width: 150px;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${!empty dailyPage.list }">
					<%
						Page<Daily> list = (Page<Daily>) request.getAttribute("dailyPage");
																	for (Daily daily : list.getList()) {
					%>
					<tr ondblclick="showDialog('#dialogrow','<%=daily.getId()%>','<%=daily.getTitle()%>')">
						<td><%=EnumDepts.findByCode(daily.get("dept").toString())%></td>
						<td><%=EnumJobs.findByCode(Integer.valueOf(daily.get("jobs").toString()))%></td>
						<td><%=daily.get("nameen")%></td>
						<td><%=daily.getTitle()%></td>
						<td><fmt:formatDate value="<%=daily.getBegin()%>" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><fmt:formatDate value="<%=daily.getEnd()%>" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>&nbsp;&nbsp;<a
							href="${pageContext.request.contextPath }/delete?id=<%=daily.getId() %>&pageNumber=${currentPage }">刪除</a>
							&nbsp;&nbsp;<a
							href="${pageContext.request.contextPath }/finished?id=<%=daily.getId() %>&pageNumber=${currentPage }">完成</a>
						</td>
					</tr>
					<%
						}
					%>
				</c:if>
			</tbody>
		</table>
	</div>
	<div data-role="dialog" id="dialogrow" class="padding20 dialog" data-close-button="true" data-overlay="true"
		data-overlay-color="op-dark" data-overlay-click-close="true" data-width="500" data-height="180" data-place="top-center"
		style="visibility: hidden; left: 340px; top: 200px;">
		<h3>记录每一个工作</h3>

		<form action="${pageContext.request.contextPath }/updateDaily" method="post" data-role="validator"
			data-on-before-submit="no_submit" data-hint-easing="easeOutBounce" data-show-required-state="false"
			data-hint-mode="hint" data-hide-error="5000" novalidate="novalidate">
			<input type="hidden" name="did" id="did" value="" />
			<div class="input-control text full-size" data-role="input">
				<span class="mif-pencil prepend-icon"></span> <input type="text" id="title" name="title" value="${title }"
					data-validate-func="required" />
				<button type="submit" class="button loading-pulse lighten primary">写好了</button>
			</div>
		</form>
		<span class="dialog-close-button"></span>
	</div>

	<script>
		function showDialog(id, did, title) {
			$("#did").val(did);
			$("#title").val(title);
			var dialog = $(id).data('dialog');
			dialog.open();
		}
	</script>
</body>
</html>