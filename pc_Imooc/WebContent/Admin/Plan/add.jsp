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
<form action="<%=request.getContextPath()%>/PlanInsertServlet"
	method="post" enctype="multipart/form-data">
	<table class="table table-bordered table-hover definewidth m10">
		<tr>
			<th width="10%" class="tableleft">课程计划名称</th>
			<td><input type="text" name="plan_name" />
				<p style="color: red; font-size: 12px;"><%=request.getAttribute("message_name")!=null?request.getAttribute("message_name"):""%></p></td>
		</tr>
		<tr>
			<th class="tableleft">课程计划简介</th>
			<td><textarea rows="5" cols="300" name="plan_content"></textarea>
				<p style="color: red; font-size: 12px;"><%=request.getAttribute("message_content")!=null?request.getAttribute("message_content"):""%></p>
			</td>
		</tr>
		<tr>
			<th class="tableleft">课程计划缩略图</th>
			<td><input type="file" name="plan_img">
				<p style="color: red; font-size: 12px;"><%=request.getAttribute("message_image")!=null?request.getAttribute("message_image"):""%></p>
			</td>
		</tr>
		<tr>
			<th class="tableleft">课程计划方向</th>
			<td><select name="decoration_id">
					<%
						List<DecorationBean> decorationBeans = (List<DecorationBean>) request
														.getAttribute("decorationBeans");
												if (decorationBeans != null) {
													for (DecorationBean decorationBean : decorationBeans) {
					%>
					<option value="<%=decorationBean.getDecoration_id()%>"><%=decorationBean.getDeco_name()%></option>
					<%
						}
													}
					%>
			</select>
				<p style="color: red; font-size: 12px;"><%=request.getAttribute("message_decoration")!=null?request.getAttribute("message_decoration"):""%></p>
			</td>
		</tr>
		<tr>
			<td class="tableleft"></td>
			<td><input type="submit" class="btn btn-primary" type="button"
				value="保存">&nbsp;&nbsp;
				<button type="button" class="btn btn-success" name="backid"
					id="backid">返回列表</button></td>
		</tr>
	</table>
</form>
</body>
</html>
<script>
	$(function() {
		$('#backid').click(function() {
			window.location.href = "<%=request.getContextPath()%>/PlanFecthAllServlet";
						});

	});
</script>