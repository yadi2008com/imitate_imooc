<%@page import="com.etc.bean.Mydiscuss"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<%
		Mydiscuss mydiscuss = (Mydiscuss) request.getAttribute("mydiscuss");
	%>
	<form action="<%=request.getContextPath()%>/MydiscussUpdateServlet"
		method="post" class="definewidth m20">
		<input type="hidden" name="id" value="" />
		<table class="table table-bordered table-hover ">
			<tr>
				<td width="10%" class="tableleft">评论ID</td>
				<td><input type="text" name="mydiscuss_id"
					value="<%=mydiscuss.getMydiscuss_id()%>" readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="tableleft">用户ID</td>
				<td><input type="text" name="user_id"
					value="<%=mydiscuss.getUser_id()%>" readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="tableleft">课程ID</td>
				<td><input type="text" name="course_id"
					value="<%=mydiscuss.getCourse_id()%>" readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="tableleft">评论内容</td>
				<td><input type="text" name="disc_content"
					value="<%=mydiscuss.getDisc_content()%>" /></td>
			</tr>
			<tr>
				<td class="tableleft">发表时间</td>
				<td><input type="text" name="disc_date"
					value="<%=mydiscuss.getDisc_date()%>" readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="tableleft">点赞数量</td>
				<td><input type="text" name="disc_praise"
					value="<%=mydiscuss.getDisc_praise()%>" /></td>
			</tr>
			<tr>
				<td class="tableleft"></td>
				<td>
					<button type="submit" class="btn btn-primary" type="button">修改</button>
					&nbsp;&nbsp;
					<button type="button" class="btn btn-success" name="backid"
						id="backid" onclick="window.history.back();">返回列表</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
