<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
	$(function(){	
		loadMenuAndTree();		
	});
	function loadMenuAndTree(){
		//载入
		$.getJSON('${pageContext.request.contextPath}/resource/getMenus?grade=1&username=${SPRING_SECURITY_LAST_USERNAME}&parentId=',{random:Math.random()}, function(data) {
			//遍历所有一级菜单节点
			$.each(data,function(entryIndex, entry){							
				$('#topMenu').accordion('add',{
					title:entry['name'],
					iconCls:entry['icon'],
					selected: false,
					id : entry['id'],
					href:'${pageContext.request.contextPath}/init/subtree?subtree_index='+entryIndex+'&subtree_id='+entry['id']
				});
            });
		});	
	}
</script>
<div id="topMenu" class="easyui-accordion" data-options="fit:true,border:false"></div>