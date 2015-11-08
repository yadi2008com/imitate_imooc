<%@page import="com.etc.bean.UsersBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="l">
		<div class="sider">
			<%
				List<UsersBean> usersBeanList = (List<UsersBean>) request
						.getAttribute("usersBeanList");
				for (UsersBean usersBean : usersBeanList) {
			%>
			<div class="user-info">
				<span class="user-pic"> <%
 	if (usersBean.getUser_image() != null) {
 %><img
					src="Home/Images/<%=usersBean.getUser_image()%>" title=""> <%
 	} else {
 %>
					<img src="Home/Images/dog.jpg" title=""> <%
 	}
 %>
				</span>
				<ul class="user-lay">
					<li class="mynick-name"><span class="user-name"><%=usersBean.getUsername()%></span>
					</li>
					<li><span class="user-site"> 学生 </span></li>
					<li><a href="Home/setprofile.jsp" class="user-setup">设置</a></li>
				</ul>
			</div>

			<div class="user-desc">
				<div class="sign-wrap">
					<p id="signed" class="signed">
						<strong><%=usersBean.getUser_sign()%></strong> <em
							class="publish-sign" id="publishsign"></em>
					</p>
					<textarea class="sign-editor" id="js-sign-editor"><%=usersBean.getUser_sign()%></textarea>
					<p id="rlf-tip-wrap" class="rlf-tip-wrap"></p>
				</div>
			</div>
			<ul class="mp clearfix">
				<li class="l mp-item"><span class="mp-atag">
						<p class="mp-num">0分</p>
						<p class="mp-title">学习</p>
				</span></li>
				<li class="mp-item"><a class="mp-atag" href="#"> <span
						class="mp-num mp-hover"> 0 </span> <span class="mp-title mp-hover">经验</span>
				</a></li>
			</ul>
			<%
				}
			%>
			<ul class="nav">
				<li><a class="js-count-course "
					href="<%=request.getContextPath()%>/MyCourseServlet">我的课程<em
						class="got-num"></em></a></li>
				<li><a class="js-count-plan "
					href="<%=request.getContextPath()%>/MyPlanServlet">我的计划<em
						class="got-num"></em></a></li>
				<li><a class="js-count-note "
					href="<%=request.getContextPath()%>/MyNoteServlet">我的笔记<em
						class="got-num"></em></a></li>
			</ul>
		</div>
		<!--sider end-->
	</div>
</body>
</html>