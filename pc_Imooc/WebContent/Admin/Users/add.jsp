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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Admin/Css/style.css" />
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
<form action="<%=request.getContextPath()%>/AddUsersServlet"
	method="post">
	<table class="table table-bordered table-hover definewidth m10">
	<%
		UsersBean usersBean=new UsersBean();
		usersBean=(UsersBean)request.getAttribute("usersBean");
		
		
		%>
		<tr>
			<td width="10%" class="tableleft">用户名</td>
			<td><input type="text" name="username" <%if(usersBean!=null){%>value="<%=usersBean.getUsername()%>"<%}%>/>&nbsp;<h7
					style="color:#FF0000;<%if ((request.getAttribute("message1") == null) && (request.getAttribute("message7") == null) ) {%>visibility: hidden<%}%>">昵称为空或重名！</h7></td>
		</tr>
		<tr>
			<td class="tableleft">性别</td>
			<td><input type="text" name="user_sex"  <%if(usersBean!=null){%>value="<%=usersBean.getUser_sex()%>"<%}%>/>&nbsp;<h7
					style="color:#FF0000;<%if (request.getAttribute("message2") == null) {%>visibility: hidden<%}%>">性别不能为空！</h7></td>
		</tr>
		<tr>
			<td class="tableleft">邮箱</td>
			<td><input type="text" name="user_email" <%if(usersBean!=null){%>value="<%=usersBean.getUser_email()%>"<%}%>/>&nbsp;<h7
					style="color:#FF0000;<%if ((request.getAttribute("message3") == null) && (request.getAttribute("message6") == null)) {%>visibility: hidden<%}%>">邮箱为空或重名！</h7></td>
		</tr>
		<tr>
			<td class="tableleft">密码</td>
			<td><input type="text" name="userpwd" <%if(usersBean!=null){%>value="<%=usersBean.getUserpwd()%>"<%}%>/>&nbsp;<h7
					style="color:#FF0000;<%if (request.getAttribute("message4") == null) {%>visibility: hidden<%}%>">密码不能为空！</h7></td>
		</tr>
		<tr>
			<td class="tableleft">确认密码</td>
			<td><input type="text" name="userpwd1" <%if(usersBean!=null){%>value="<%=usersBean.getUserpwd()%>"<%}%>/>&nbsp;<h7
					style="color:#FF0000;<%if (request.getAttribute("message5") == null) {%>visibility: hidden<%}%>">两次密码不一致！</h7></td>
		</tr>
		<tr>
			<td class="tableleft">职位</td>
			<td><input type="text" name="user_job" <%if(usersBean!=null){%>value="<%=usersBean.getUser_job()%>"<%}%>/></td>
		</tr>
		<tr>
			<td class="tableleft">城市</td>
			<td><input type="text" name="user_city" <%if(usersBean!=null){%>value="<%=usersBean.getUser_city()%>"<%}%>/></td>
		</tr>
		<tr>
			<td class="tableleft">头像</td>
			<td><input type="text" name="user_image" <%if(usersBean!=null){%>value="<%=usersBean.getUser_image()%>"<%}%>/></td>
		</tr>
		<tr>
			<td class="tableleft">个性签名</td>
			<td><input type="text" name="user_sign" <%if(usersBean!=null){%>value="<%=usersBean.getUser_sign()%>"<%}%>/></td>
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
				window.location.href="<%=request.getContextPath()%>/UsersFetchALLServlet";
						});

	});
</script>