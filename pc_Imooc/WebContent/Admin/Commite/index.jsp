<%@page import="com.etc.bean.PageBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.etc.bean.Mydiscuss"%>
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
		action="<%=request.getContextPath()%>/MydiscussFetchServlet"
		method="get">
		用户昵称： <input type="text" name="username" id="username"
			class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
		课程名称： <input type="text" name="cour_title" id="cour_title"
			class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>评论ID</th>
				<th>用户昵称</th>
				<th>课程名</th>
				<th>评论内容</th>
				<th>发表时间</th>
				<th>点赞数量</th>
				<th>管理操作</th>
			</tr>
		</thead>

		<%
			String usernamett = (String) request.getAttribute("username");
			String cour_titlett = (String) request.getAttribute("cour_title");
			String select = (String) request.getAttribute("select");
			PageBean pageBean = (PageBean) request.getAttribute("pageBean");
			int rows = (int) request.getAttribute("rows");
			List<Mydiscuss> mydiscusseList = null;
			String[] usernameList = (String[]) request
					.getAttribute("usernameList");
			String[] cour_titleList = (String[]) request
					.getAttribute("cour_titleList");
			mydiscusseList = (List<Mydiscuss>) request
					.getAttribute("mydiscusseList");
			if (mydiscusseList != null && !mydiscusseList.isEmpty()) {
				for (int x = 0; x < mydiscusseList.size(); x++) {
					Mydiscuss mydiscuss = mydiscusseList.get(x);
					String username = usernameList[x];
					String cour_title = cour_titleList[x];
		%>
		<tr>
			<td><%=mydiscuss.getMydiscuss_id()%></td>
			<td><%=username%></td>
			<td><%=cour_title%></td>
			<td><%=mydiscuss.getDisc_content()%></td>
			<td><%=mydiscuss.getDisc_date()%></td>
			<td><%=mydiscuss.getDisc_praise()%></td>
			<td><a
				href="<%=request.getContextPath()%>/MydiscussEditServlet?mydiscuss_id=<%=mydiscuss.getMydiscuss_id()%>">编辑</a>&nbsp;&nbsp;<a
				onclick="del(<%=mydiscuss.getMydiscuss_id()%>)">删除</a></td>
		</tr>
		<%
			}
			}
		%>


	</table>
	<div class="inline pull-right page">
		<%=rows%>
		条记录
		<%=pageBean.getPageno()%>/<%=pageBean.getMaxpage()%>
		页 <a <%if ("select".equals(select)) {%>
			href='<%=request.getContextPath()%>/MydiscussFetchServlet?pageno=<%=pageBean.getPageno() - 1%>&username=<%=usernamett%>&cour_title=<%=cour_titlett%>'
			<%} else {%>
			href='<%=request.getContextPath()%>/MydiscussFetchAllServlet?pageno=<%=pageBean.getPageno() - 1%>'
			<%}%>>上一页</a> <a <%if ("select".equals(select)) {%>
			href='<%=request.getContextPath()%>/MydiscussFetchServlet?pageno=<%=pageBean.getPageno() + 1%>&username=<%=usernamett%>&cour_title=<%=cour_titlett%>'
			<%} else {%>
			href='<%=request.getContextPath()%>/MydiscussFetchAllServlet?pageno=<%=pageBean.getPageno() + 1%>'
			<%}%>>下一页</a> <a <%if ("select".equals(select)) {%>
			href='<%=request.getContextPath()%>/MydiscussFetchServlet?pageno=<%=pageBean.getMaxpage()%>&username=<%=usernamett%>&cour_title=<%=cour_titlett%>'
			<%} else {%>
			href='<%=request.getContextPath()%>/MydiscussFetchAllServlet?pageno=<%=pageBean.getMaxpage()%>'
			<%}%>>最后一页</a>
	</div>
</body>
</html>
<script>
	$(function() {
		$('#addnew').click(function() {
			window.location.href = "add.jsp";
		});
	});
	function del(id) {
		if (confirm("确定要删除吗？")) {
			window.location.href = "./MydiscussDeleteServlet?mydiscuss_id="+id;
		}
	}
</script>