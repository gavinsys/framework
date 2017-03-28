<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" media="screen" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/bootstrap-theme.min.css" media="screen" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/mystyle.css" media="screen" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath }/js/jquery-2.1.3.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js" type="text/javascript"></script>
<style>
	.inputStyle{
		font-size:14; color:#DDDDDD; font-family:Fixedsys; width:100%; height:1000px; 
		border:0; background-color:#000000; padding: 10; overflow:auto
	}
</style>
<script type="text/javascript">
		
$(document).ready(function() {
	var url = '${pageContext.request.contextPath }/se/log4j';
	$('#comet-frame')[0].src = url;
});

function update(data) {
	var resultArea = $('#result')[0];
	resultArea.value = resultArea.value + data + '\n';
}

</script>
</head>
<body>
<div style="height: 30px;"></div>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-body">
					<textarea id="result" readonly="true" class="inputStyle"></textarea>
					<iframe id="comet-frame" style="display: none;"></iframe>
			</div>
		</div>
	</div>
</body>
</html>