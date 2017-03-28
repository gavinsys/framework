<%@page import="com.easytonent.enums.EnumOnOff"%>
<%@page import="com.easytonent.enums.EnumPass"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../common/_header.jsp"></jsp:include>
<script>

	function pass(id) {
		$.get("${pageContext.request.contextPath }/platform/driver/view/" + id, function(data, status) {
			$("#driverId").attr("value", data.id);
			$("#img").html(data.img);
			$("#name").html(data.name);
			$("#idNumber").html(data.idNumber);
			$("#phone").html(data.phone);
			$("#dateLicense").html(data.dateLicense);
			$("#imgLicense").html(data.imgLicense);
			$("#imgIdNumber").html(data.imgIdNumber);
			$("#state").html(data.state);
			$("#status").html(data.status);
			$("#created").html(data.created);
		});
		$('#myModal2').modal();
	}
	function ispass() {
		var id = $("#driverId").val();
		window.location.href="${pageContext.request.contextPath }/platform/driver/ispass?id="+id;
	}
	function nopass() {
		var id = $("#driverId").val();
		window.location.href="${pageContext.request.contextPath }/platform/driver/nopass?id="+id;
	}
</script>
</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">司機管理</div>
			<div class="panel-body"></div>
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>頭像</th>
						<th>姓名</th>
						<th>身份證號</th>
						<th>手機</th>
						<th>領證日期</th>
						<th>駕駛證正面</th>
						<th>手持身份證</th>
						<th>在線狀態</th>
						<th>審核狀態</th>
						<th>創建時間</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty driverPage.list }">
						<c:forEach items="${driverPage.list }" var="driver">
							<tr>
								<td style="text-align: left;">${driver.img }</td>
								<td style="text-align: left;">${driver.name }</td>
								<td style="text-align: left;">${driver.idNumber }</td>
								<td style="text-align: left;">${driver.phone }</td>
								<td style="text-align: left;">${driver.dateLicense }</td>
								<td style="text-align: left;">${driver.imgLicense }</td>
								<td style="text-align: left;">${driver.imgIdNumber }</td>
								<td style="text-align: left;">
									<c:forEach items="<%=EnumOnOff.values()%>" var="of">
									<c:if test="${of.code==driver.state }">${of.name }</c:if>
									</c:forEach>
								</td>
								<td style="text-align: left;">
									<c:forEach items="<%=EnumPass.values()%>" var="pass">
									<c:if test="${pass.code==driver.status }">${pass.name }</c:if>
									</c:forEach>
								</td>
								<td style="text-align: left;"><fmt:formatDate value="${driver.created }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td style="text-align: left;">&nbsp;&nbsp;<a onclick="pass('${driver.id}')">審核</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty driverPage.list }">
						<tr>
							<td style="text-align: center;" colspan="10">暫無數據記錄！</td>
						</tr>
					</c:if>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="11"><c:set var="currentPage" value="${driverPage.pageNumber }" /> <c:set var="totalPage" value="${driverPage.totalPage }" /> <c:set var="actionUrl" value="${pageContext.request.contextPath }/platform/driver/" /> <c:set var="urlParas" value="" /> <%@ include file="../common/_paginate.jsp"%></td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
	
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">審核司機</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">
						<input type="hidden" id="driverId" name="id"/>
						<div class="form-group">
							<label for="username" class="col-sm-3 control-label">頭像</label>
							<div class="col-sm-9" id="img">
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">姓名</label>
							<div class="col-sm-9" id="name">
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">身份證號</label>
							<div class="col-sm-9" id="idNumber">
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">手機</label>
							<div class="col-sm-9" id="phone">
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">領證日期</label>
							<div class="col-sm-9" id="dateLicense">
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">駕駛證正面</label>
							<div class="col-sm-9" id="imgLicense">
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">手持身份證</label>
							<div class="col-sm-9" id="imgIdNumber">
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">狀態</label>
							<div class="col-sm-9" id="state">
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">狀態</label>
							<div class="col-sm-9" id="status">
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">創建時間</label>
							<div class="col-sm-9" id="created">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-9">
								<button type="button" class="btn btn-default" onclick="ispass()">通過</button>
								<button type="button" class="btn btn-default" onclick="nopass()">不通過</button>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer"></div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</body>
</html>