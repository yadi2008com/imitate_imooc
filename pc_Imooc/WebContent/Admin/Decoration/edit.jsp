<%@page import="com.etc.bean.DecorationBean"%>
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
	<form action="<%=request.getContextPath()%>/DecorationUpdateServlet"
		method="post" class="definewidth 

m20">
		<table class="table table-bordered table-hover ">
			<%=request.getAttribute("message") != null ? request
					.getAttribute("message") : ""%>
			<%
				DecorationBean decorationBean = null;
				decorationBean = (DecorationBean) request
						.getAttribute("decorationBean");
				if (decorationBean != null) {
			%>
			<tr>
				<td class="tableleft">方向id</td>
				<td><input type="text" name="decoration_id"
					value="<%=decorationBean.getDecoration_id()%>" readonly /></td>
			</tr>
			<tr>
				<td class="tableleft">方向名称</td>
				<td><input type="text" name="deco_name"
					value="<%=decorationBean.getDeco_name()%>" />
					<p style="color: red; font-size: 12px;"><%=request.getAttribute("message_name") != null
						? request.getAttribute("message_name")
						: ""%></p></td>
			</tr>
			<%
				}
			%>
			<tr>
				<td class="tableleft"></td>
				<td>
					<button type="submit" class="btn btn-primary" type="button">保存</button>
					&nbsp;&nbsp;
					<button type="button" class="btn btn-success" name="backid"
						id="backid">返回列表</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
<script>
	$(function() {
		$('#backid').click(function() {

			window.location.href = "<%=request.getContextPath()%>/DecorationFetchAllServlet";
						});

	});
</script>