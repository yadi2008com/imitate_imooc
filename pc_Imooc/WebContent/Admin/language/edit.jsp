<%@page import="com.etc.bean.UsersBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Admin/Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/Admin/Css/bootstrap-responsive.css" />
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
	<form action="<%=request.getContextPath()%>/LevelUpdateServlet" method="post" class="definewidth m20">
		<input type="hidden" name="id" value="" />
		
		
		<input type="text" name="level_id" value="<%=request.getParameter("level_id")%>" style="visibility: hidden"/>
		<table class="table table-bordered table-hover ">
		
			<tr>
        <td width="10%" class="tableleft">等级</td>
        <td><input type="text" name="leve_name" value="<%=request.getParameter("leve_name") %>" /></td>
    </tr>
      
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
			window.location.href = "<%=request.getContextPath()%>/LevelFetchAllServlet";
		});

	});
</script>