<%@page import="com.etc.bean.LanguageBean"%>
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

	<form class="form-inline definewidth m20" action="<%=request.getContextPath()%>/LanguageSelectByName"
		method="get">
		语言： <input type="text" name="language_name" id="rolename"
			class="abc input-default" placeholder="" value="">&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>
		&nbsp;&nbsp;
		<button type="button" class="btn btn-success" id="addnew">新增语言</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>语言编号</th>
				<th>语言名称</th>
				<th>方向名称</th>
				<th>管理</th>
			</tr>
		</thead>
		<%
		List<LanguageBean> languagelist=null;
		languagelist=(List<LanguageBean>)request.getAttribute("languagelist");
		String[] decorationList =(String[])request.getAttribute("decorationList");
		if(languagelist!=null && !languagelist.isEmpty()){
				for (int x = 0; x < languagelist.size(); x++) {
					LanguageBean languageBean = languagelist.get(x);
					String decoration_name= decorationList[x];
		%>
		<tr>
			<td><%=languageBean.getLanguage_id()%></td>
			<td><%=languageBean.getLang_name() %></td>
			<td><%=decoration_name %></td>
			<td>
			<a href="<%=request.getContextPath()%>/LanguageDelectServlet?language_id=<%=languageBean.getLanguage_id()%>">删除</a></td>
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
			<a <%if(i==2){ %>href="<%=request.getContextPath()%>/LanguageSelectByName?pageno=1"<%}else{ %>href="<%=request.getContextPath()%>/LanguageFetchAllServlet?pageno=1"<%} %>>首页</a>
		    <a <%if(i==2){ %>href=""<%}else{ %>href="<%=request.getContextPath()%>/LanguageFetchAllServlet?pageno=<%=pageBean.getPageno() - 1%>"<%} %>>>上一页</a> 
			<a <%if(i==2){ %>href=""<%}else{ %>href="<%=request.getContextPath()%>/LanguageFetchAllServlet?pageno=<%=pageBean.getPageno() + 1%>"<%} %>>>下一页</a> 
			<a <%if(i==2){ %>href=""<%}else{ %>href="<%=request.getContextPath()%>/LanguageFetchAllServlet?pageno=<%=pageBean.getMaxpage()%>"<%} %>>>尾页</a>
			<%
	
			}	%>
			
	</div>
</body>
</html>

<script>
	$(function() {

		$('#addnew').click(function() {

			window.location.href = "<%=request.getContextPath()%>/Admin/language/add.jsp";
		});

	});

	function del(id) {

		if (confirm("确定要删除吗？")) {

			var url = "index.html";

			window.location.href = url;
		}
	}
</script>