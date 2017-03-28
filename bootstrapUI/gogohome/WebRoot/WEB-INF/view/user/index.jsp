<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../common/_header.jsp" />
<script>
	$(function() {
		function create() {
			$('#myModal').modal({
				keyboard : true
			});
		}
	});
	function update(id) {
		$.get("${pageContext.request.contextPath }/platform/user/edit/" + id, function(
				data, status) {
			$("#userid").attr("value", data.id);
			$("#username").attr("value", data.name);
		});
		$('#myModal2').modal();

	}
</script>
</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">用戶管理</div>
			<div class="panel-body">
				<!-- 按钮触发模态框 -->
				<button class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="create()">創建用戶</button>
			</div>
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>名稱</th>
						<th>創建時間</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty userPage.list }">
						<c:forEach items="${userPage.list }" var="user">
							<tr>
								<td style="text-align: left;">${user.name }</td>
								<td style="text-align: left;"><fmt:formatDate value="${user.created }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td style="text-align: left;"><c:if test="${user.name!='admin' }">
										<a href="${pageContext.request.contextPath }/platform/user/delete/${user.id }">刪除</a>
										<span style="color: #D2D2D2; padding-left: 5px;">|</span>
									</c:if><a onclick="update('${user.id }')">修改</a></td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty userPage.list }">
						<tr>
							<td style="text-align: center;" colspan="3">暫無數據記錄！</td>
						</tr>
					</c:if>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="3"><c:set var="currentPage" value="${userPage.pageNumber }" /> <c:set var="totalPage" value="${userPage.totalPage }" /> <c:set var="actionUrl" value="${pageContext.request.contextPath }/platform/user/" /> <c:set var="urlParas" value="" /> <%@ include file="../common/_paginate.jsp"%>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">創建用戶</h4>
				</div>
				<div class="modal-body">
					<form action="${pageContext.request.contextPath }/platform/user/save" method="post" class="form-horizontal" role="form">
						<div class="form-group">
							<label for="user.name" class="col-sm-2 control-label">用戶</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="user.name" name="user.name" required="" placeholder="请输入用戶">
							</div>
						</div>
						<div class="form-group">
							<label for="user.pass" class="col-sm-2 control-label">密碼</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="user.pass" name="user.pass" required="" value="" placeholder="请输入密碼">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-default">提 交</button>
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

	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">修改用戶</h4>
				</div>
				<div class="modal-body">
					<form action="${pageContext.request.contextPath }/platform/user/update" method="post" class="form-horizontal" role="form">
						<input type="hidden" id="userid" name="user.id" value="${user.id}" />
						<div class="form-group">
							<label for="username" class="col-sm-2 control-label">用戶</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="username" name="user.name" required="" placeholder="请输入用戶">
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-2 control-label">密碼</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="password" name="user.pass" required="" value="" placeholder="请输入密碼">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-default">提 交</button>
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