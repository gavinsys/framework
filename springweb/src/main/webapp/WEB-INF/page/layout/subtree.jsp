<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String subtree_index = request.getParameter("subtree_index")==null?"":request.getParameter("subtree_index");
String subtree_id = request.getParameter("subtree_id")==null?"":request.getParameter("subtree_id");
%>
<script type="text/javascript">
	$(function() {		
		$('#subTree<%=subtree_index%>').tree({
			url:'${pageContext.request.contextPath}/resource/getTrees?grade=2&username=${SPRING_SECURITY_LAST_USERNAME}&parentId=<%=subtree_id%>',
			parentField : 'pid',
			lines : true,
			onClick: function(node){
				if($('#layout_center_tabs').tabs('exists', node.text)){
					$('#layout_center_tabs').tabs('select', node.text);
				}else{
					$('#layout_center_tabs').tabs('add', {
						border: false,
					    title: node.text,
					    content:'<iframe src="${pageContext.request.contextPath}'+ node.url +'" allowTransparency="true" frameborder="0" style="border:0;width:100%;height:99%;"></iframe>',
					    closable:true
					});
				}
			}
		}); 	
	});
</script>
<ul style="padding:5px;" id="subTree<%=subtree_index%>"></ul>