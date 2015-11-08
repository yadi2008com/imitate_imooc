<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="header">
	<div class="page-container" id="nav">
		<div id="logo" class="logo">
			<a href="/" target="_self" class="hide-text">慕课网</a>
		</div>
		<div class="g-menu-mini l">
			<a href="#" class="menu-ctrl"> <i class="icon-menu"></i>
			</a>
			<ul class="nav-item l">
				<li><a href="<%=request.getContextPath()%>/CourseListServlet" target="_self">课程</a></li>
				<li><a href="<%=request.getContextPath()%>/AllPlanServlet" target="_self">计划</a></li>
				<li><a href="/corp/index" target="_self">分享</a></li>
				<li><a href="/wenda" target="_self">社区</a></li>
			</ul>
		</div>
		<div id="login-area">
			<ul class="clearfix logined">
				<li class="my_message"><a href="/message" title="我的消息"
					target="_self"> <i class="msg_icon" style="display: none;"></i>
						<span style="display: none;">我的消息</span>
				</a></li>
				<li class="set_btn user-card-box"><a id="header-avator"
					class="user-card-item" action-type="my_menu"
					href="<%=request.getContextPath()%>/MyCourseServlet" target="_self">
						<%
							if (request.getSession().getAttribute("user_image") != null) {
						%> <img
						src="Home/Images/<%=request.getSession().getAttribute("user_image")%>"
						width='40' height='40' /> <%
 	} else {
 %> <img src="Home/Images/dog.jpg" alt="1221010520" class="l"> <%
 	}
 %><i class="myspace_remind" style="display: none;"></i> <span
						style="display: none;">动态提醒</span>
				</a>
					<div class="g-user-card">
						<div class="card-inner">
							<div class="card-top">
								<%
									if (request.getSession().getAttribute("user_image") != null) {
								%>
								<img
									src="Home/Images/<%=request.getSession().getAttribute("user_image")%>"
									alt="1221010520" class="l">
								<%
									} else {
								%>
								<img src="Home/Images/dog.jpg" alt="1221010520" class="l">
								<%
									}
								%>
								<span class="name text-ellipsis"></span>
								<p class="meta">
									<!--  <span>经验<b id="js-user-mp">167</b></span> <span
											style="display: none">积分<b>12331</b></span>-->
								</p>
							</div>
							<div class="card-links">
								<a href="<%=request.getContextPath()%>/MyCourseServlet"
									class="my-mooc l">我的慕课<i class="dot-update"></i></a> <span
									class="split l"></span> <a href="/myclub/myquestion/t/ques"
									class="my-sns l">我的社区</a>
							</div>
							<div class="card-history">
								<!-- <span class="history-item"> <span
										class="tit text-ellipsis">Java入门第一季</span> <span
										class="media-name text-ellipsis">3-2 Java中的算术运算符</span> <i
										class="icon-clock"></i> <a href="/video/1279" class="continue">继续</a>
									</span> -->
							</div>
							<div class="card-sets clearfix">
								<a href="<%=request.getContextPath()%>/SetprofileServlet"
									class="l">个人设置</a> <a
									href="<%=request.getContextPath()%>/CourseShowNewServlet"
									class="r">退出</a>
							</div>
						</div>
						<i class="card-arr"></i>
					</div></li>
			</ul>
		</div>
		<div class="app-down-area r">
			<a href="/mobile/app"> <i class="header-app-icon"></i>慕课APP
			</a>
		</div>
		<div class="search-area" data-search="top-banner">
			<input class="search-input" data-suggest-trigger="suggest-trigger"
				placeholder="请输入想搜索的内容..." type="text" autocomplete="off">
			<ul class="search-area-result" data-suggest-result="suggest-result">
			</ul>
			<input type="button" class="btn_search" data-search-btn="search-btn" />
		</div>
	</div>
</div>