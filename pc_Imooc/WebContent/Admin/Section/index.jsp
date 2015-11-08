<%@page import="com.etc.bean.SectionBean"%>
<%@page import="com.etc.bean.PageBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title></title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/Admin/Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/Admin/Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/Admin/Css/style.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Admin/Js/jquery.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Admin/Js/jquery.sorted.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Admin/Js/bootstrap.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Admin/Js/ckform.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/Admin/Js/common.js"></script>

<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>
</head>
<body>
	<form class="form-inline definewidth m20"
		action="<%=request.getContextPath()%>/ChapterSearchServlet"
		method="get">
		章计划： <input type="text" name="rolename" id="rolename"
			class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>
		&nbsp;&nbsp;
		<button type="button" class="btn btn-success" id="addnew">新增方向</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>节编号</th>
				<th>节标题</th>
				<th>所属章</th>
				<th>管理操作</th>
			</tr>
		</thead>

		<%=request.getAttribute("message")!=null?request.getAttribute("message"):""%>
		<%
			List<SectionBean> sectionBeanList = (List<SectionBean>) request
					.getAttribute("sectionBeans");
			if (sectionBeanList != null) {
				for (SectionBean sectionBean : sectionBeanList) {
		%>
		<tr>
			<td><%=sectionBean.getSection_id()%></td>
			<td><%=sectionBean.getSect_name()%></td>
			<td><%=sectionBean.getChap_name()%></td>
			<td><a
				href="<%=request.getContextPath()%>/SectionEditServlet?id=<%=sectionBean.getSection_id()%>&sort=update">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
				href="<%=request.getContextPath()%>/SectionDeleteServlet?id=<%=sectionBean.getSection_id()%>">删除</a></td>
		</tr>
		<%
			}
			}
		%>
		<tr>
			<td colspan="4">
				<%
					PageBean pageBean = (PageBean) request.getAttribute("pageBean");
						   if (pageBean != null) {
				%> <a
				href="<%=request.getContextPath()%>/SectionFetchAllServlet?pageno=1">首页</a>
				<strong><a
					href="<%=request.getContextPath()%>/SectionFetchAllServlet?pageno=<%=pageBean.getPageno() - 1%>">上一页</a>
					<a
					href="<%=request.getContextPath()%>/SectionFetchAllServlet?pageno=<%=pageBean.getPageno() + 1%>">下一页</a>
					<a
					href="<%=request.getContextPath()%>/SectionFetchAllServlet?pageno=<%=pageBean.getMaxpage()%>">尾页</a>
					共<%=pageBean.getMaxpage()%>页&nbsp;&nbsp;第<%=pageBean.getPageno()%>页
			</strong> <%
 	}
 %>
			</td>
		</tr>
	</table>
</body>
</html>
<script>
	$(function() {

		$('#addnew').click(function() {

			window.location.href = "<%=request.getContextPath()%>/SectionAddServlet";
						});

	});
</script>