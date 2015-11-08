<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>慕课网</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="renderer" content="webkit">
<meta property="qc:admins" content="77103107776157736375">
<meta property="wb:webmaster" content="c4f857219bfae3cb">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="keywords"
	content="慕课网，慕课官网，MOOC，移动开发，IT技能培训，免费编程视频，php开发教程，web前端开发，在线编程学习，html5视频教程，css教程，ios开发培训，安卓开发教程">
<meta name="description"
	content="慕课网（IMOOC）是学习编程最简单的免费平台。慕课网提供了丰富的移动端开发、php开发、web前端、html5教程以及css3视频教程等课程资源。它富有交互性及趣味性，并且你可以和朋友一起编程。">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Home/Css/base.css" type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Home/Css/common-less.css"
	type="text/css">
<script src="<%=request.getContextPath()%>/Home/Js/rt.js" async=""
	type="text/javascript"></script>
<script type="text/javascript">
	var OP_CONFIG = {
		"module" : "user",
		"page" : "logout",
		"userInfo" : {
			"uid" : "2388067",
			"nickname" : "qq_MonadLee_0",
			"head" : "http:\/\/img.mukewang.com\/user\/533e4d7c0001828702000200-80-80.jpg",
			"usertype" : "1",
			"roleid" : 0
		}
	};
	var isLogin = 0;
	var seajsTimestamp = "v=210509221611";
</script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Home/Css/settings.css">

<style type="text/css">
.wcontainer {
	min-height: 650px;
}

.logout-wrap {
	padding: 48px 0;
}

.logout-left {
	text-align: center;
	padding-top: 40px;
}

.logout-left i {
	display: inline-block;
	width: 120px;
	height: 120px;
	background: url(/static/images/rl-sprite.png) no-repeat 0 -130px;
}

.logout-left p {
	font-size: 24px;
	color: #656e73;
	padding: 35px 0 30px;
}

.logout-left a:link, .logout-left a:visited {
	color: #39b94e;
}

.logout-left a:hover {
	text-decoration: underline;
}

.logout-left {
	float: left;
	width: 320px;
}

.logout-right {
	position: relative;
	margin-left: 320px;
	border-left: 1px solid #edf0f2;
	padding-left: 60px;
}

.field-wrap {
	width: 520px;
	position: relative;
}

#info, #contact {
	border: 1px solid #d0d6d9;
	width: 260px;
	font-size: 14px;
	transition: border-color 0.2s;
	-webkit-transition: border-color 0.2s;
	-moz-transition: border-color 0.2s;
	-o-transition: border-color 0.2s;
	padding: 7px;
}

#info:focus, #contact:focus {
	border-color: #7bd089;
}

#info {
	width: 500px;
	height: 200px;
	resize: none;
	overflow: hidden;
}

#contact {
	vertical-align: middle;
	width: 500px;
	height: 24px;
	line-height: 24px;
	font-size: 14px;
	padding: 7px;
}

.logout-right button {
	display: inline-block;
	margin-right: 5px;
	vertical-align: middle;
	color: #fff;
	font-size: 14px;
	background-color: #39b94e;
	height: 40px;
	line-height: 40px;
	width: 190px;
	cursor: pointer;
	text-align: center;
	transition: background-color 0.2s;
	-webkit-transition: background-color 0.2s;
	-moz-transition: background-color 0.2s;
	-o-transition: background-color 0.2s;
}

.logout-right button:hover {
	background-color: #33a646;
}

.logout-right h2 {
	margin-bottom: 12px;
}

.logout-right .rlf-tip-wrap {
	font-size: 12px;
	height: 30px;
	line-height: 26px;
}

.logout-right .rlf-tip-error {
	background: url(/static/images/rl-sprite.png) no-repeat -108px -934px;
	padding-left: 15px;
	color: #be3948;
}

.logout-right .error-field {
	border-color: #be3948 !important;
}

.placeholder {
	color: #d0d6d9;
	font-size: 14px;
}

.phd:-moz-placeholder {
	color: #d0d6d9;
	font-size: 14px;
}

.phd::-moz-placeholder {
	color: #d0d6d9;
	font-size: 14px;
}

.phd::-webkit-input-placeholder {
	color: #d0d6d9;
	font-size: 14px;
	z
}

.phd:-ms-input-placeholder {
	color: #d0d6d9;
	font-size: 14px;
}

.us-join-qq {
	display: inline-block;
	width: 160px;
	height: 40px;
	line-height: 40px;
	vertical-align: middle;
	background: #2ea7e0;
	text-align: center;
}

.us-join-qq:link, .us-join-qq:visited {
	color: #fff;
}

.us-join-qq:hover {
	background: #2996c9;
}

.us-join-qq i {
	display: inline-block;
	width: 24px;
	height: 20px;
	margin-right: 4px;
	vertical-align: middle;
	background: url(/static/images/us-sprite.png) no-repeat -76px -110px;
}

.result-wrap {
	display: none;
	text-align: center;
	padding: 100px 60px 100px 0;
}

.result-wrap h2 {
	font-size: 24px;
	color: #656e73;
	line-height: 1.5em;
}

.result-wrap a {
	display: inline-block;
	margin-top: 15px;
}

.result-wrap a:link, .result-wrap a:visited {
	color: #39b94e;
}

.qqGroup {
	position: absolute;
	right: -220px;
	top: 20px;
	padding-top: 10px;
	line-height: 2.5em;
}

.qqGroup a {
	padding-left: 10px;
	color: #008000;
}

.qqGroup span {
	padding-left: 10px;
}
</style>



<script src="<%=request.getContextPath()%>/Home/Js/jquery.js" async=""
	charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/Home/Js/seajs-text.js"
	async="" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/Home/Js/common.js" async=""
	charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/Home/Js/string.js" async=""
	charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/Home/Js/suggest.js" async=""
	charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/Home/Js/store.js" async=""
	charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/Home/Js/json.js" async=""
	charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/Home/Js/im.js" async=""
	charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/Home/Js/logout.js" async=""
	charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/Home/Js/socket.js" async=""
	charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/Home/Js/placeholder.js"
	async="" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/Home/Js/login-regist.js"
	async="" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/Home/Js/modal.js" async=""
	charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/Home/Js/validate.js" async=""
	charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/Home/Js/autocomplete.js"
	async="" charset="utf-8"></script>
<link href="<%=request.getContextPath()%>/Home/Css/poplogin-less.css"
	rel="stylesheet" charset="utf-8">
</head>
<body>

	<!--script-->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/Home/Js/sea.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/Home/Js/sea_config.js"></script>
	<script type="text/javascript">
		seajs.use("/static/page/" + OP_CONFIG.module + "/" + OP_CONFIG.page);
	</script>




	<div style="display: none">
		<script type="text/javascript">
			var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://"
					: " http://");
			document
					.write(unescape("%3Cscript src='"
							+ _bdhmProtocol
							+ "hm.baidu.com/h.js%3Ff0cfcccd7b1393990c78efdeebff3968' type='text/javascript'%3E%3C/script%3E"));
			(function(d) {
				window.bd_cpro_rtid = "rHT4P1c";
				var s = d.createElement("script");
				s.type = "text/javascript";
				s.async = true;
				s.src = location.protocol + "//cpro.baidu.com/cpro/ui/rt.js";
				var s0 = d.getElementsByTagName("script")[0];
				s0.parentNode.insertBefore(s, s0);
			})(document);
		</script>
		<script src="<%=request.getContextPath()%>/Home/Js/h.js"
			type="text/javascript"></script>
		<a
			href="http://tongji.baidu.com/hm-web/welcome/ico?s=f0cfcccd7b1393990c78efdeebff3968"
			target="_blank"><img
			src="<%=request.getContextPath()%>/Home/Images/21.gif" border="0"
			height="20" width="20"></a>
	</div>
	<div aria-hidden="false" id="signin" class="rl-modal in">
		<div class="rl-modal-header">
			<h1>
				<span class="active-title">登录</span> <span><a
					data-fromto="signin:signup"
					href="<%=request.getContextPath()%>/Home/user_register.jsp">注册</a></span>
					<a href="<%=request.getContextPath()%>/CourseShowNewServlet" style="float: right">返回</a>
			</h1>
			<button type="button" class="rl-close" data-dismiss="modal"
				hidefocus="true" aria-hidden="true"></button>
		</div>
		<div class="rl-modal-body">
			<div class="clearfix">
				<div class="l-left-wrap l">
					<form id="signup-form" autocomplete="off"
						action="<%=request.getContextPath()%>/UserLoginServlet"
						method="post">
						<p class="rlf-tip-globle " id="signin-globle-error"></p>
						<div class="rlf-group">
							<input name="email" data-validate="email" autocomplete="off"
								class="ipt ipt-email js-own-name" placeholder="请输入登录邮箱"
								type="text">
							<%=request.getAttribute("message1") != null ? request
					.getAttribute("message1") : ""%>
							<p class="rlf-tip-wrap"></p>
						</div>
						<!-- fake fields are a workaround for chrome autofill getting the wrong fields -->
						<input style="display: none" name="fakeusernameremembered"
							type="text"> <input style="display: none"
							name="fakepasswordremembered" type="password">
						<div class="rlf-group">
							<input name="password" autocomplete="off" class="ipt ipt-pwd"
								placeholder="请输入密码" type="password">
							<%=request.getAttribute("message2") != null ? request
					.getAttribute("message2") : ""%>
							<p class="rlf-tip-wrap"></p>
						</div>
						<div class="rlf-group js-verify-row clearfix"
							style="display: none">
							<input name="verify" class="ipt ipt-verify l"
								placeholder="请输入验证码" type="text"> <a
								href="javascript:void(0)"
								class="verify-img-wrap js-verify-refresh"><img
								src="<%=request.getContextPath()%>/Home/Images/getlogincode.png"
								class="verify-img"></a> <a href="javascript:void(0)"
								class="icon-refresh js-verify-refresh"></a>
							<p class="rlf-tip-wrap"></p>
						</div>
						<div class="rlf-group rlf-appendix clearfix">
						</div>
						<div class="rlf-group clearfix">
							<input id="signin-btn" value="登录" hidefocus="true"
								class="btn-red btn-full" type="submit" />
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="rl-model-footer">
		</div>
	</div>
	<div class="modal-backdrop  in"></div>
</body>
</html>