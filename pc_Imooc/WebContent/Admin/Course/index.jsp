<%@page import="com.etc.bean.PageBean"%>
<%@page import="com.etc.bean.CourseBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
PageBean pageBean=(PageBean)request.getAttribute("pageBean");
%>
<%
List<CourseBean> courseBeans=(List<CourseBean>)request.getAttribute("courseBeans");
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title></title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="Admin/Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="Admin/Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="Admin/Css/style.css" />
<script type="text/javascript" src="Admin/Js/jquery.js"></script>
<script type="text/javascript" src="Admin/Js/jquery.sorted.js"></script>
<script type="text/javascript" src="Admin/Js/bootstrap.js"></script>
<script type="text/javascript" src="Admin/Js/ckform.js"></script>
<script type="text/javascript" src="Admin/Js/common.js"></script>

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
	<form class="form-inline definewidth m20" action="<%=request.getContextPath() %>/CourseShowByNameServlet"
		method="get">
		课程： <input type="text" name="courseName" id="rolename"
			class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>
		&nbsp;&nbsp;
		<button type="button" class="btn btn-success" id="addnew">新增课程</button><%=request.getAttribute("message")==null?"":request.getAttribute("message") %>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>课程id</th>
				<th>课程标题</th>
				<th>缩略图</th>
				<th>地址</th>
				<th>时长</th>
				<th>点击量</th>
				<th>发布时间</th>
				<th>资源</th>
				<th>简介</th>
				<th>语言</th>
				<th>教师</th>
				<th>类型id</th>
				<th>方向id</th>
				<th>难度id</th>
				<th>管理操作</th>
			</tr>
		</thead>
		<%
		for(CourseBean courseBean:courseBeans){%>
		<tr>
				<td><%=courseBean.getCourse_id() %></td>
				<td><%=courseBean.getCour_title() %></td>
				<td><%=courseBean.getCour_image() %></td>
				<td><%=courseBean.getCour_url() %></td>
				<td><%=courseBean.getCour_duration() %></td>
				<td><%=courseBean.getCour_hot() %></td>
				<td><%=courseBean.getCour_date() %></td>
				<td><%=courseBean.getCour_source() %></td>
				<td><%=courseBean.getCour_content() %></td>
				<td><%=courseBean.getCour_language() %></td>
				<td><%=courseBean.getCour_teacher() %></td>
				<td><%=courseBean.getLanguage_id() %></td>
				<td><%=courseBean.getDecoration_id() %></td>
				<td><%=courseBean.getLevel_name() %></td>
				
			<td><a href="./CourseDeleteServlet?id=<%=courseBean.getCourse_id() %>">删除</a></td>
			
		</tr>
		<%
		}
		%>
	</table>
	<div class="inline pull-right page">
		总记录数 <a href='<%=request.getContextPath()%>/CourseShowServlet?pageno=<%=pageBean.getPageno()-1 %>'>上一页</a><a href='<%=request.getContextPath()%>/CourseShowServlet?pageno=<%=pageBean.getPageno()+1 %>'>下一页</a>
	</div>
</body>
</html>
<script>
	$(function() {

		$('#addnew').click(function() {

			window.location.href = "Admin/Course/add.jsp";
		});

	});

	function del(id) {

		if (confirm("确定要删除吗？")) {

			var url = "Admin/Course/index.jsp";

			window.location.href = url;

		}

	}
</script>