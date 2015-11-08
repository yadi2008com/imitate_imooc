<%@page import="com.etc.bean.CourseBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/main-min.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="header">
		<div class="dl-title">
			<!--<img src="/chinapost/Public/assets/img/top.png">-->
		</div>
		<div class="dl-log">
			欢迎您，<span class="dl-log-user">root</span><a
				href="/chinapost/index.php?m=Public&a=logout" title="退出系统"
				class="dl-log-quit">[退出]</a>
		</div>
	</div>
	<div class="content">
		<div class="dl-main-nav">
			<div class="dl-inform">
				<div class="dl-inform-title">
					<s class="dl-inform-icon dl-up"></s>
				</div>
			</div>
			<ul id="J_Nav" class="nav-list ks-clear">
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-home">系统管理</div></li>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-order">讲师管理</div></li>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-order">课程管理</div></li>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-order">课程计划管理</div></li>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-order">用户管理</div></li>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-order">评论管理</div></li>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-order">等级管理</div></li>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-order">语言管理</div></li>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-order">方向管理</div></li>
			</ul>
		</div>
		<ul id="J_NavContent" class="dl-tab-conten">
		</ul>
	</div>
	<script type="text/javascript" src="assets/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="assets/js/bui-min.js"></script>
	<script type="text/javascript" src="assets/js/common/main-min.js"></script>
	<script type="text/javascript" src="assets/js/config-min.js"></script>
	<script>
		BUI.use('common/main', function() {
			var config = [ {
				id : '1',
				menu : [ {
					text : '系统管理',
					items : [ {
						id : '12',
						text : '机构管理',
						href : 'Node/index.html'
					}, {
						id : '3',
						text : '角色管理',
						href : 'Role/index.html'
					}, {
						id : '4',
						text : '用户管理',
						href : 'User/index.html'
					}, {
						id : '6',
						text : '菜单管理',
						href : 'Menu/index.html'
					} ]
				} ]
			}, {
				id : '7',
				homePage : '9',
				menu : [ {
					text : '讲师管理',
					items : [ {
						id : '9',
						text : '查询讲师',
						href : 'Node/index.html'
					} ]
				} ]
			}, {
				id : '2',
				homePage : '9',
				menu : [ {
					text : '课程管理',
					items : [ {
						id : '9',
						text : '查询课程',
						href : '<%=request.getContextPath()%>/CourseShowServlet'
					} ]
				} ]
			}, {
				id : '3',
				homePage : '9',
				menu : [ {
					text : '课程计划管理',
					items : [ {
						id : '9',
						text : '查询课程计划',
						href : '<%=request.getContextPath()%>/PlanFecthAllServlet'
					}, {
						id : '10',
						text : '章管理',
						href : '<%=request.getContextPath()%>/ChapterFetchAllServlet'
					}, {
						id : '11',
						text : '节管理',
						href : '<%=request.getContextPath()%>/SectionFetchAllServlet'
					} ]
				} ]
			}, {
				id : '4',
				homePage : '9',
				menu : [ {
					text : '用户管理',
					items : [ {
						id : '9',
						text : '查询用户',
						href : '<%=request.getContextPath()%>/UsersFetchALLServlet'
					} ]
				} ]
			}, {
				id : '5',
				homePage : '9',
				menu : [ {
					text : '评论管理',
					items : [ {
						id : '9',
						text : '查询评论',
						href : '<%=request.getContextPath()%>/MydiscussFetchAllServlet'
					} ]
				} ]
			}, {
				id : '8',
				homePage : '9',
				menu : [ {
					text : '等级管理',
					items : [ {
						id : '9',
						text : '查询等级',
						href : '<%=request.getContextPath()%>/LevelFetchAllServlet'
					} ]
				} ]
			}, 
			 {
				id : '10',
				homePage : '9',
				menu : [ {
					text : '语言管理',
					items : [ {
						id : '9',
						text : '查询等级',
						href : '<%=request.getContextPath()%>/LanguageFetchAllServlet'
											} ]
										} ]
									},
			{
										id : '11',
										homePage : '9',
										menu : [ {
											text : '方向管理',
											items : [ {
												id : '9',
												text : '查询方向',
												href : '<%=request.getContextPath()%>/DecorationFetchAllServlet'
											} ]
										} ]

			}, ];
							new PageUtil.MainPage({
								modulesConfig : config
							});
						});
	</script>
</body>
</html>