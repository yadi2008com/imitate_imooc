<%@page import="com.etc.bean.UsersBean"%>
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


<script type="text/javascript">
     $(function(){
    	 if(<%=request.getAttribute("message")%>=="111")
			{	
			alert("1111");
			window.location.href="index.jsp";
			}
    	 
     }); 
   </script>
</head>
<form action="<%=request.getContextPath()%>/LanguageAddServlet"
	method="post">
	<table class="table table-bordered table-hover definewidth m10">

		<tr>
			<td width="10%" class="tableleft">语言</td>
			<td><input type="text" name="language_name" />&nbsp;<h7
					style="color:#FF0000;<%if ((request.getAttribute("message1") == null)
					&& (request.getAttribute("message3") == null)) {%>visibility: hidden<%}%>">语言为空或重名！</h7></td>
		</tr>
		<tr>
			<td width="10%" class="tableleft">方向</td>
			<td><input type="text" name="deco_name" />&nbsp;<h7
					style="color:#FF0000;<%if (request.getAttribute("message2") == null) {%>visibility: hidden<%}%>">方向不能为空！</h7></td>
		</tr>

		<tr>
			<td class="tableleft"></td>
			<td>
				<button type="submit" class="btn btn-primary" type="button">保存</button>&nbsp;&nbsp;
				<button type="button" class="btn btn-success" name="backid"
					id="backid">返回列表</button>
			</td>
		</tr>

	</table>

</form>
</body>
</html>
<script>
    $(function () {       
		$('#backid').click(function(){
				window.location.href="<%=request.getContextPath()%>/LanguageFetchAllServlet";
						});

	});
</script>