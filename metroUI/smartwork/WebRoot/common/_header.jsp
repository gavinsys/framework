<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
	function no_submit() {
		return false;
	}
</script>
<div class="app-bar fixed-top">
	<a class="app-bar-element branding">Smartwork</a> <span class="app-bar-divider"></span>
	<ul class="app-bar-menu small-dropdown">
		<li><a href="${pageContext.request.contextPath }/list"><span class="mif-list2"></span>&nbsp;工作板</a></li>
		<li><a href="${pageContext.request.contextPath }/contacts"><span class="mif-contacts-dialer"></span>&nbsp;通讯录</a></li>
		<li onclick="showDialog('#dialog9')"><a href="#"><span class="mif-pencil prepend-icon"></span>&nbsp;写日报</a></li>
	</ul>
	<div class="app-bar-element place-right">
		<a class="dropdown-toggle fg-white" href="${pageContext.request.contextPath }/logout"><span class="mif-exit"></span>
			退 出</a>
	</div>
	<div class="app-bar-pullbutton automatic" style="display: none;"></div>
	<div class="clearfix" style="width: 0;"></div>
</div>
<div class="main-content" style="margin-top: 65px">
</div>
<div data-role="dialog" id="dialog9" class="padding20 dialog info" data-close-button="true" data-overlay="true"
	data-overlay-color="op-dark" data-overlay-click-close="true" data-width="500" data-height="180" data-place="top-center"
	style="visibility: hidden; left: 340px; top: 200px;">
	<h3>记录每一个工作</h3>

	<form action="${pageContext.request.contextPath }/daily" method="post" data-role="validator"
		data-on-before-submit="no_submit" data-hint-easing="easeOutBounce" data-show-required-state="false"
		data-hint-mode="hint" data-hide-error="5000" novalidate="novalidate">
		<div class="input-control text full-size" data-role="input">
			<span class="mif-pencil prepend-icon"></span>
			<input type="text" name="title" value="" data-validate-func="required" />
			<button type="submit" class="button loading-pulse lighten primary">写好了</button>
		</div>
	</form>
	<span class="dialog-close-button"></span>
</div>

<script>
	function showDialog(id) {
		var dialog = $(id).data('dialog');
		dialog.open();
	}
</script>