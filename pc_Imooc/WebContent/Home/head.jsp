<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="Home/Css/saved_resource" type="text/css">
<div id="header">
	<div class="page-container" id="nav">
		<div id="logo" class="logo">
			<a href="index.jsp" target="_self" class="hide-text">慕课网</a>
		</div>
		<div class="g-menu-mini l">
			<a href="http://www.imooc.com/course/list#" class="menu-ctrl"> <i
				class="icon-menu"></i>
			</a>
			<ul class="nav-item l">
				<li><a href="<%=request.getContextPath()%>/CourseListServlet"
					target="_self">课程</a></li>
				<li><a href="<%=request.getContextPath()%>/AllPlanServlet"
					target="_self">计划</a></li>
				<li><a href="http://www.imooc.com/corp/index" target="_self">分享</a></li>
				<li><a href="http://www.imooc.com/wenda" target="_self">社区</a></li>
			</ul>
		</div>
		<div id="login-area">
			<ul class="header-unlogin clearfix">
				<li class="header-signin"><a href="Home/user_login.jsp"
					id="js-signin-btn">登录</a></li>
				<li class="header-signup"><a href="Home/user_register.jsp"
					id="js-signup-btn">注册</a></li>
					<li class="header-signup"><a href="Admin/admin_login.jsp"
					id="js-signup-btn">后台登陆</a></li>
			</ul>
		</div>
		<div class="app-down-area r">
			<a href="http://www.imooc.com/mobile/app"> <i
				class="header-app-icon"></i>慕课APP
			</a>
		</div>
		<div class="search-area" data-search="top-banner">
			<input id="search_input" class="search-input"
				data-suggest-trigger="suggest-trigger" placeholder="请输入想搜索的内容..."
				type="text" autocomplete="off" onfocus="search_focus">
			<ul class="search-area-result" data-suggest-result="suggest-result">
			</ul>
			<a type="button" class="btn_search" data-search-btn="search-btn"
				onclick="select()"></a>
		</div>
	</div>
</div>
<script type="text/javascript">
	function search_focus() {
		var input = document.getElementById("search_input");
		input.style.backgroundColor = "#FF0000";
	}
	function select() {
		var cour_title = document.getElementById("search_input").value;
		window.location.href = "./CourseSelectServlet?cour_title="
				+ cour_title;
	}
</script>