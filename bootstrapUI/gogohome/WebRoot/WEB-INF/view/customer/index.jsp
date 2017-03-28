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
			<div class="panel-heading">
				客戶管理
			</div>
			<div class="panel-body"></div>
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>頭像</th>
						<th>姓名</th>
						<th>手機</th>
						<th>車齡</th>
						<th>排擋</th>
						<th>緊急聯絡電話</th>
						<th>創建時間</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty customerPage.list }">
						<c:forEach items="${customerPage.list }" var="customer">
							<tr>
								<td style="text-align: left;">${customer.img }</td>
								<td style="text-align: left;">${customer.name }</td>
								<td style="text-align: left;">${customer.phone }</td>
								<td style="text-align: left;">${customer.carAge }</td>
								<td style="text-align: left;">${customer.gears }</td>
								<td style="text-align: left;">${customer.urgentContacts }</td>
								<td style="text-align: left;"><fmt:formatDate value="${customer.created }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty customerPage.list }">
						<tr>
							<td style="text-align: center;" colspan="7">暫無數據記錄！</td>
						</tr>
					</c:if>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="7"><c:set var="currentPage" value="${customerPage.pageNumber }" /> <c:set var="totalPage" value="${customerPage.totalPage }" /> <c:set var="actionUrl" value="${pageContext.request.contextPath }/platform/customer/" /> <c:set var="urlParas" value="" /> <%@ include file="../common/_paginate.jsp"%></td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</body>
</html>