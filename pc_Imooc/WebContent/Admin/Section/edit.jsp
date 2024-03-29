<%@page import="com.etc.bean.ChapterBean"%>
<%@page import="com.etc.bean.SectionBean"%>
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
	<form action="<%=request.getContextPath()%>/SectionUpdateServlet"
		method="post" class="definewidth 

m20">
		<input type="hidden" name="id" value="" />
		<table class="table table-bordered table-hover ">
			<%=request.getAttribute("message") != null ? request
					.getAttribute("message") : ""%>
			<%
			SectionBean sectionBean = null;
			sectionBean = (SectionBean) request.getAttribute("sectionBean");
				if (sectionBean != null) {
			%>
			<tr>
				<td width="10%" class="tableleft">节编号</td>
				<td><input type="text" name="section_id" readonly
					value="<%=sectionBean.getSection_id()%>" /></td>
			</tr>
			<tr>
				<td class="tableleft">节标题</td>
				<td><input type="text" name="sect_name"
					value="<%=sectionBean.getSect_name()%>" />
					<p style="color:red;font-size:12px;"><%=request.getAttribute("message_name")!=null?request.getAttribute("message_name"):"" %></p></td>
			</tr>
				<tr>
			<th class="tableleft">所属章</th>
			<td><select name="chap_id">
					<%
						List<ChapterBean> chapterBeanList = (List<ChapterBean>) request
											.getAttribute("chapterBeans");
									if (chapterBeanList != null) {
										for (ChapterBean chapterBean : chapterBeanList) {
											if(chapterBean.getChapter_id()==sectionBean.getChap_id()){
					%>
					<option value="<%=chapterBean.getChapter_id()%>" selected><%=chapterBean.getChap_name()%></option>
					<%}else{ %>
					<option value="<%=chapterBean.getChapter_id()%>"><%=chapterBean.getChap_name()%></option>
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

			window.location.href = "<%=request.getContextPath()%>/SectionFetchAllServlet";
						});

	});
</script>