<%@page import="com.easytonent.enums.EnumState"%>
<%@page import="com.easytonent.enums.EnumStatus"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../common/_header.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">訂單管理</div>
			<div class="panel-body"></div>
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>訂單編號</th>
						<th>出發地</th>
						<th>目的地</th>
						<th>出價</th>
						<th>客戶方狀態</th>
						<th>司機方狀態</th>
						<th>時長</th>
						<th>司機</th>
						<th>用戶</th>
						<th>創建時間</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty orderPage.list }">
						<c:forEach items="${orderPage.list }" var="order">
							<tr>
								<td style="text-align: left;">${order.orderNo }</td>
								<td style="text-align: left;">${order.fromloc }</td>
								<td style="text-align: left;">${order.toloc }</td>
								<td style="text-align: left;">${order.price }</td>
								<td style="text-align: left;">
									<c:forEach items="<%=EnumStatus.values()%>" var="cus">
									<c:if test="${cus.code==order.status }">${cus.name }</c:if>
									</c:forEach>
								</td>
								<td style="text-align: left;">
									<c:forEach items="<%=EnumState.values()%>" var="dri">
									<c:if test="${dri.code==order.state }">${dri.name }</c:if>
									</c:forEach>
								</td>
								<td style="text-align: left;">${order.times }</td>
								<td style="text-align: left;">${order.driverId }</td>
								<td style="text-align: left;">${order.customerId }</td>
								<td style="text-align: left;"><fmt:formatDate value="${order.created }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty orderPage.list }">
						<tr>
							<td style="text-align: center;" colspan="10">暫無數據記錄！</td>
						</tr>
					</c:if>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="10"><c:set var="currentPage" value="${orderPage.pageNumber }" /> <c:set var="totalPage" value="${orderPage.totalPage }" /> <c:set var="actionUrl" value="${pageContext.request.contextPath }/platform/order/" /> <c:set var="urlParas" value="" /> <%@ include file="../common/_paginate.jsp"%></td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</body>
</html>