<%@page import="com.etc.bean.ChapterBean"%>
<%@page import="com.etc.bean.PlanBean"%>
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
	<form action="<%=request.getContextPath()%>/ChapterUpdateServlet"
		method="post" class="definewidth 

m20">
		<input type="hidden" name="id" value="" />
		<table class="table table-bordered table-hover ">
			<%=request.getAttribute("message") != null ? request
					.getAttribute("message") : ""%>
			<%
				ChapterBean chapterBean = null;
				chapterBean = (ChapterBean) request.getAttribute("chapterBean");
				if (chapterBean != null) {
			%>
			<tr>
				<td width="10%" class="tableleft">章计划编号</td>
				<td><input type="text" name="chapter_id" readonly
					value="<%=chapterBean.getChapter_id()%>" /></td>
			</tr>
			<tr>
				<td class="tableleft">章计划标题</td>
				<td><input type="text" name="chap_name"
					value="<%=chapterBean.getChap_name()%>" />
					<p style="color:red;font-size:12px;"><%=request.getAttribute("message_name")!=null?request.getAttribute("message_name"):"" %></p></td>
			</tr>
			<tr>
				<td class="tableleft">章计划简介</td>
				<td><textarea  name="chap_content" rows="5" cols="300"><%=chapterBean.getChap_content()%>
					</textarea>
					<p style="color:red;font-size:12px;"><%=request.getAttribute("message_content")!=null?request.getAttribute("message_content"):"" %></p></td>
			</tr>
				<tr>
			<th class="tableleft">所属课程计划</th>
			<td><select name="plan_id">
					<%
						List<PlanBean> planBeanList = (List<PlanBean>) request
											.getAttribute("planBeans");
									if (planBeanList != null) {
										for (PlanBean planBean : planBeanList) {
											if(planBean.getPlan_id()==chapterBean.getPlan_id()){
					%>
					<option value="<%=planBean.getPlan_id()%>" selected><%=planBean.getPlan_name()%></option>
					<%}else{ %>
					<option value="<%=planBean.getPlan_id()%>"><%=planBean.getPlan_name()%></option>
					<%}
						}
										}
					%>
			</select></td>
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

			window.location.href = "<%=request.getContextPath()%>/ChapterFetchAllServlet";
						});

	});
</script>