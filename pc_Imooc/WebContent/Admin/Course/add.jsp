<%@page import="com.etc.bean.PageBean"%>
<%@page import="com.etc.bean.CourseBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<script type="text/javascript" src="Admin/Course/My97DatePicker/WdatePicker.js"></script> 
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
		action="<%=request.getContextPath()%>/CourseShowByNameServlet"
		method="get">
		课程： <input type="text" name="courseName" id="rolename"
			class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>
		&nbsp;&nbsp;
		<button type="button" class="btn btn-success" id="addnew">新增课程</button>
	</form>
	<form action="<%=request.getContextPath()%>/CourseAddServlet" method="post" enctype="multipart/form-data">
	<table style="width: 300px;float: left; margin-left: 30px" class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th width="20%">课程标题</th>
				<td><input type="text" name="cour_title" /></td></tr><tr>

				<th>缩略图</th>
				<td><input type="file" name="cour_image" /></td></tr><tr>

				<th>地址</th>
				<td><input type="text" name="cour_url" /></tr><tr>

				<th>时长</th>
				<td><input type="text" name="cour_duration" /></td></tr><tr>

				<th>点击量</th>
				<td><input type="text" name="cour_hot" /></td></tr><tr>

				<th>发布时间</th>
				<td><input type="text" name="cour_date" onFocus="WdatePicker({isShowClear:true,readOnly:false,dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td></tr><tr>

				<th>资源</th>
				<td><input type="file" name="cour_source" /></td></tr><tr>

				<th>简介</th>
				<td><input type="text" name="cour_content" /></td></tr><tr>

				<th>语言</th>
				<td><input type="text" name="cour_language" /></td></tr><tr>

				<th>教师</th>
				<td><input type="text" name="cour_teacher" /></td></tr><tr>

				<th>类型id</th>
				<td><input type="text" name="language_id" /></td></tr><tr>

				<th>方向id</th>
				<td><input type="text" name="decoration_id" /></td></tr><tr>

				<th>难度id</th>
				<td><input type="text" name="level_name" /></td></tr><tr>

			</tr>
		<tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" type="button">保存</button>&nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
		</thead>
	</table>
	</form>
	<div class="inline pull-right page"></div>
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
	
    $(function () {       
		$('#backid').click(function(){
				window.location.href="Admin/Course/index.jsp";
		 });

    });

</script>