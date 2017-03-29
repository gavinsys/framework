<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="common/resources.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="login.title"/></title>
</head>
<body>
<div id="loginFrm">
	<form:form action="j_spring_security_check" method="POST" >
		<table align="center">
				<tr><td></td><td></td></tr>
				<tr>
				<tr>
				<td></td>
				<td>
				${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
				</td>
				</tr>
				<tr>
					<th><spring:message code="login.name"/></th>
					<td><input name="j_username" value="${SPRING_SECURITY_LAST_USERNAME}"></td>
				</tr>
				<tr>
					<th><spring:message code="login.pass"/></th>
					<td><input name="j_password" type="password" /></td>
				</tr>
				<tr>  
			      <th><spring:message code="validateCode.name"/></th>  
			      <td>
			      <input type="text" maxlength="4" name="code" id="code" style="width: 80px;" onkeypress="javascript:pressKey(event);" />
			      <a href="javascript:;"
         			 onclick="document.getElementById('validateCode').src='init/authImage?'+new Date()">
			       <img id="validateCode" src="init/authImage" width="80" height="35" style="padding:0px" />
			      </a>
			      </td>  
			     </tr>
			     <tr>  
			      <th></th>  
			      <td>
			      </td>  
			     </tr> 
				<tr><td></td><td><input type="checkbox" name="_remember_me" value="true" /><spring:message code="remember.name"/></td></tr>
				<tr>
				<td></td>
				<td>
					<input type="submit" value="<spring:message code="login.submit"/>"/>
				 	<input type="reset" value="<spring:message code="login.reset"/>"/>
				 </td></tr>
			</table>
	</form:form>
</div>

</body>
<script type="text/javascript">

	$(function(){
		$('#loginFrm').window({
			title:'<spring:message code="login.label"/><div style="float:right;padding-right:5px;"><a href="init/global?langType=zh"><spring:message code="login.zh"/></a> | <a href="init/global?langType=en"><spring:message code="login.en"/></a></div>',
			closable:false,
			maximizable:false,
			minimizable:false,
			draggable:false,
			collapsible:false,
			resizable:false,
			width:350,
			height:260
		});
		
		$(window).resize(function(){
			$('#loginFrm').window('center');
		});
	});
</script>
</html>