<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.smartwork.enums.EnumJobs"%>
<%@page import="com.smartwork.enums.EnumDepts"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="${pageContext.request.contextPath }/common/_taglib.jsp" />
<script>
	function no_submit() {
		return false;
	}
</script>
</head>

<body>
	<div class="container">
		<div class="panel" data-role="panel" style="width:400px;margin:0 auto;top:120px;">
			<div class="heading">
				<span class="icon mif-home"></span> <span class="title">加 入</span>
			</div>
			<div class="content" style="padding: 8px; text-align: center">
				<form action="${pageContext.request.contextPath }/join" method="post" data-role="validator"
					data-on-before-submit="no_submit" data-hint-easing="easeOutBounce" data-show-required-state="false"
					data-hint-mode="hint" data-hide-error="5000" novalidate="novalidate">
					<c:if test="${not empty phoneMsg}">
						<span class="input-state-error mif-warning" style="right: 8px; color: red; font-size: 14px;">${phoneMsg }</span>
					</c:if>
					<c:if test="${not empty emailMsg}">
						<span class="input-state-error mif-warning" style="right: 8px; color: red; font-size: 14px;">${emailMsg }</span>
					</c:if>
					<div class="input-control select full-size">
						<select name="contacts.dept" data-validate-func="required" data-validate-hint="部门不能为空!"
							data-validate-hint-position="right">
							<option value="">选择部门</option>
							<c:forEach items="<%=EnumDepts.values()%>" var="depts">
								<option value="${depts.code }" <c:if test='${depts.code==contacts.dept }'>selected='selected'</c:if>>
									${depts.name }</option>
							</c:forEach>
						</select> <span class="input-state-error mif-warning" style="right: 8px;"></span> <span
							class="input-state-success mif-checkmark" style="right: 8px;"></span>
					</div>
					<div class="input-control select full-size">
						<select name="contacts.jobs" data-validate-func="required" data-validate-hint="职位不能为空!"
							data-validate-hint-position="right">
							<option value="">选择职位</option>
							<c:forEach items="<%=EnumJobs.values()%>" var="_jobs">
								<option value="${_jobs.code }" <c:if test='${_jobs.code==contacts.jobs }'>selected='selected'</c:if>>
									${_jobs.name }</option>
							</c:forEach>
						</select> <span class="input-state-error mif-warning" style="right: 8px;"></span> <span
							class="input-state-success mif-checkmark" style="right: 8px;"></span>
					</div>
					<div class="input-control text full-size">
						<input type="text" placeholder="中文名" name="contacts.namecn" value="${contacts.namecn }"
							data-validate-func="required" data-validate-hint="中文名不能为空!" data-validate-hint-position="right" /> <span
							class="input-state-error mif-warning" style="right: 8px;"></span> <span class="input-state-success mif-checkmark"
							style="right: 8px;"></span>
					</div>
					<div class="input-control text full-size">
						<input type="text" placeholder="英文名" class="mytxt" name="contacts.nameen" value="${contacts.nameen }"
							data-validate-func="required" data-validate-hint="英文名不能为空!" data-validate-hint-position="right" /> <span
							class="input-state-error mif-warning" style="right: 8px;"></span> <span class="input-state-success mif-checkmark"
							style="right: 8px;"></span>
					</div>
					<div class="input-control text full-size">
						<input type="text" placeholder="手机" name="contacts.phone" value="${contacts.phone }" data-validate-func="required"
							data-validate-hint="手机不能为空!" data-validate-hint-position="right" /> <span class="input-state-error mif-warning"
							style="right: 8px;"></span> <span class="input-state-success mif-checkmark" style="right: 8px;"></span>
					</div>
					<div class="input-control text full-size">
						<input type="password" placeholder="密码" name="contacts.pwd" value="${contacts.pwd }" data-validate-func="required"
							data-validate-hint="密码不能为空!" data-validate-hint-position="right" /> <span class="input-state-error mif-warning"
							style="right: 8px;"></span> <span class="input-state-success mif-checkmark" style="right: 8px;"></span>
					</div>
					<div class="input-control text full-size">
						<input type="text" placeholder="邮件" name="contacts.email" value="${contacts.email }" data-validate-func="required"
							data-validate-hint="邮箱不能为空!" data-validate-hint-position="right" /> <span class="input-state-error mif-warning"
							style="right: 8px;"></span> <span class="input-state-success mif-checkmark" style="right: 8px;"></span>
					</div>
					<div class="form-actions">
						<button type="submit" class="button primary">加 入</button>
						<button type="button" class="button bg-pink bg-active-darkPink fg-white loading-pulse lighten"
							onclick="javascript:window.location.href='${pageContext.request.contextPath }/login.jsp'">登 陆</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>