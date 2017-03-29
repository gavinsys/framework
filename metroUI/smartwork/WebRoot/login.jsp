<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="${pageContext.request.contextPath }/common/_taglib.jsp"/>
<script>
	$(function() {
		var form = $(".login-form");

		form.css({
			opacity : 1,
			"-webkit-transform" : "scale(1)",
			"transform" : "scale(1)",
			"-webkit-transition" : ".5s",
			"transition" : ".5s"
		});
	});
</script>
<script>
	function no_submit() {
		return false;
	}
</script>
<style>
.login-form {
	width: 25rem;
	height: 16.85rem;
	position: fixed;
	top: 50%;
	margin-top: -9.375rem;
	left: 50%;
	margin-left: -12.5rem;
	background-color: #ffffff;
	opacity: 0;
	-webkit-transform: scale(.8);
	transform: scale(.8);
}
</style>
</head>
<body>
<div class="container">
	<div class="login-form padding20 block-shadow">
		<form action="${pageContext.request.contextPath }/login" method="post" data-role="validator" data-on-before-submit="no_submit"
			data-hint-easing="easeOutBounce" data-show-required-state="false" data-hint-mode="hint" data-hide-error="5000" novalidate="novalidate">
			<h3 class="text-light">用户登录</h3>
			<hr class="thin" />
			<c:if test="${not empty loginError}">
				<span class="input-state-error mif-warning" style="right: 8px;color:red;font-size:14px;">${loginError }</span>
			</c:if>
			<br/>
			<div class="input-control text full-size">
				<span class="mif-user prepend-icon"></span>
				<input placeholder="请输入手机" type="text" data-validate-func="required" data-validate-hint="手机不能为空!" data-validate-hint-position="right" name="phone" value="${phone }"> 
				<span class="input-state-error mif-warning" style="right: 8px;"></span>
				<span class="input-state-success mif-checkmark" style="right: 8px;"></span>
			</div>
			<br /> <br />
			<div class="input-control password full-size">
				<span class="mif-lock prepend-icon"></span>
				<input placeholder="请输入密码" data-validate-func="required" data-validate-hint="密码不能为空!" data-validate-hint-position="right" type="password" name="pwd">
				<span class="input-state-error mif-warning" style="right: 8px;"></span>
				<span class="input-state-success mif-checkmark" style="right: 8px;"></span>
			</div>
			<br /> <br />
			<div class="form-actions">
				<button type="submit" class="button primary">登 录</button>
				<button type="button" class="button bg-pink bg-active-darkPink fg-white loading-pulse lighten" onclick="javascript:window.location.href='${pageContext.request.contextPath }/join.jsp'">加   入</button>
			</div>
		</form>
	</div>
</div>
	<!-- hit.ua -->
	<a href='http://hit.ua/?x=136046' target='_blank'> <script language="javascript" type="text/javascript">
	<!--
		Cd = document;
		Cr = "&" + Math.random();
		Cp = "&s=1";
		Cd.cookie = "b=b";
		if (Cd.cookie)
			Cp += "&c=1";
		Cp += "&t=" + (new Date()).getTimezoneOffset();
		if (self != top)
			Cp += "&f=1";
	//-->
	</script> <script language="javascript1.1" type="text/javascript">
	<!--
		if (navigator.javaEnabled())
			Cp += "&j=1";
	//-->
	</script> <script language="javascript1.2" type="text/javascript">
	<!--
		if (typeof (screen) != 'undefined')
			Cp += "&w="
					+ screen.width
					+ "&h="
					+ screen.height
					+ "&d="
					+ (screen.colorDepth ? screen.colorDepth
							: screen.pixelDepth);
	//-->
	</script> <script language="javascript" type="text/javascript">
	<!--
		Cd.write("<img src='http://c.hit.ua/hit?i=136046&g=0&x=2" + Cp + Cr
				+ "&r=" + escape(Cd.referrer) + "&u="
				+ escape(window.location.href) + "' border='0' wi"
				+ "dth='1' he" + "ight='1'/>");
	//-->
	</script></a>
	<!-- / hit.ua -->


</body>
</html>