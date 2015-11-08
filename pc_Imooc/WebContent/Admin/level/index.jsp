<%@page import="com.etc.bean.LevelBean"%>
<%@page import="com.etc.bean.PageBean"%>
<%@page import="com.etc.bean.UsersBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Admin/Css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Admin/Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Admin/Css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/Admin/Js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/Admin/Js/jquery.sorted.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/Admin/Js/bootstrap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/Admin/Js/ckform.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/Admin/Js/common.js"></script>

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

	<form class="form-inline definewidth m20" action="<%=request.getContextPath()%>/LevelSelectByName"
		method="get">
		等级： <input type="text" name="leve_name" id="rolename"
			class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>
		&nbsp;&nbsp;
		<button type="button" class="btn btn-success" id="addnew">新增等级</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>等级编号</th>
				<th>等级</th>
				<th>管理</th>
		
			</tr>
		</thead>
		<%
		List<LevelBean> levellist=null;
		levellist=(List<LevelBean>)request.getAttribute("levellist");
		if(levellist!=null && !levellist.isEmpty()){
			for(LevelBean levelBean:levellist){
		%>
		<tr>
			<td><%=levelBean.getLevel_id()%></td>
			<td><%=levelBean.getLeve_name() %></td>
			<td><a href="<%=request.getContextPath()%>/Admin/level/edit.jsp?level_id=<%=levelBean.getLevel_id() %>&leve_name=<%=levelBean.getLeve_name() %>">编辑</a>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/LevelDeleteServlet?level_id=<%=levelBean.getLevel_id() %>">删除</a></td>
		</tr>
		<%
			}
		}
		%>	
		
		
	</table>
	
	<div class="inline pull-right page">
	<%
	   	PageBean pageBean=(PageBean)request.getAttribute("pageBean");
		int rows=(int)request.getAttribute("rows");
		int i=(int)request.getAttribute("i");
	    
	    if (pageBean != null) {
	    	
	%>
	
		
			共<span class="info-number"><%=rows %></span>条<span
						class="info-number"><%=pageBean.getPageno() %></span>/<span class="info-number"><%=pageBean.getMaxpage() %></span>
			<a <%if(i==2){ %>href=""<%}else{ %>href="<%=request.getContextPath()%>/LevelFetchAllServlet?pageno=1"<%} %>>首页</a>
		    <a <%if(i==2){ %>href=""<%}else{ %>href="<%=request.getContextPath()%>/LevelFetchAllServlet?pageno=<%=pageBean.getPageno() - 1%>"<%} %>>>上一页</a> 
			<a <%if(i==2){ %>href=""<%}else{ %>href="<%=request.getContextPath()%>/LevelFetchAllServlet?pageno=<%=pageBean.getPageno() + 1%>"<%} %>>>下一页</a> 
			<a <%if(i==2){ %>href=""<%}else{ %>href="<%=request.getContextPath()%>/LevelFetchAllServlet?pageno=<%=pageBean.getMaxpage()%>"<%} %>>>尾页</a>
			<%
	
			}	%>
			
	</div>
</body>
</html>

<script>
	$(function() {

		$('#addnew').click(function() {

			window.location.href = "<%=request.getContextPath()%>/Admin/level/add.jsp";
		});

	});

	function del(id) {

		if (confirm("确定要删除吗？")) {

			var url = "index.html";

			window.location.href = url;
		}
	}
</script>