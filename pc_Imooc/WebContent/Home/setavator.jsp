<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<head>
<meta charset="utf-8">
<title>慕课网</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="renderer" content="webkit">
<meta property="qc:admins" content="77103107776157736375" />
<meta property="wb:webmaster" content="c4f857219bfae3cb" />
<meta http-equiv="Access-Control-Allow-Origin" content="*" />
<meta name="keywords"
	content="慕课网，慕课官网，MOOC，移动开发，IT技能培训，免费编程视频，php开发教程，web前端开发，在线编程学习，html5视频教程，css教程，ios开发培训，安卓开发教程" />
<meta name="description"
	content="慕课网（IMOOC）是学习编程最简单的免费平台。慕课网提供了丰富的移动端开发、php开发、web前端、html5教程以及css3视频教程等课程资源。它富有交互性及趣味性，并且你可以和朋友一起编程。" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Home/Css/base.css" type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Home/Css/common-less.css"
	type="text/css" />
<script type="text/javascript">
	var OP_CONFIG = {
		"module" : "user",
		"page" : "setavator",
		"uid" : "2385822",
		"nickname" : "1221010520",
		"head" : "http:\/\/img.mukewang.com\/user\/5333a1d100010c2602000200-80-80.jpg",
		"usertype" : "1",
		"roleid" : 0
	};
	var isLogin = 1;
	var seajsTimestamp = "v=210509221611";
	var ownName = "283454539@qq.com"
</script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Home/Css/login-regist.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Home/Css/settings.css"
	type="text/css" />

<%@include file="headsuccess.jsp"%>
</head>
<body>

	<!--  
	<div id="header">
		<div class="page-container" id="nav">
			<div id="logo" class="logo">
				<a href="/" target="_self" class="hide-text">慕课网</a>
			</div>
			<div class="g-menu-mini l">
				<a href="#" class="menu-ctrl"> <i class="icon-menu"></i>
				</a>
				<ul class="nav-item l">
					<li><a href="./courseList.jsp" target="_self">课程</a></li>
					<li><a href="./decoPlanList.jsp" target="_self">计划</a></li>
					<li><a href="/corp/index" target="_self">分享</a></li>
					<li><a href="/wenda" target="_self">社区</a></li>
				</ul>
			</div>
			<div id="login-area">
				<ul class="clearfix logined">
					<li class="my_message"><a href="/message" title="我的消息"
						target="_self"> <i class="msg_icon" style="display: none;"></i>
							<span style="display: none;">我的消息</span>
					</a></li>
					<li class="set_btn user-card-box"><a id="header-avator"
						class="user-card-item" action-type="my_menu" href="/space/index"
						target="_self"><img
							src='http://img.mukewang.com/user/5333a1d100010c2602000200-40-40.jpg'
							width='40' height='40' /> <i class="myspace_remind"
							style="display: none;"></i> <span style="display: none;">动态提醒</span>
					</a>
						<div class="g-user-card">
							<div class="card-inner">
								<div class="card-top">
									<img
										src="http://img.mukewang.com/5333a1d100010c2602000200-100-100.jpg"
										alt="1221010520" class="l"> <span
										class="name text-ellipsis">1221010520</span>
									<p class="meta">
										<span>经验<b id="js-user-mp">167</b></span> <span
											style="display: none">积分<b>12331</b></span>
									</p>
								</div>
								<div class="card-links">
									<a href="/space/index" class="my-mooc l">我的慕课<i
										class="dot-update"></i></a> <span class="split l"></span> <a
										href="/myclub/myquestion/t/ques" class="my-sns l">我的社区</a>
								</div>
								<div class="card-history">
									<span class="history-item"> <span
										class="tit text-ellipsis">Java入门第一季</span> <span
										class="media-name text-ellipsis">3-2 Java中的算术运算符</span> <i
										class="icon-clock"></i> <a href="/video/1279" class="continue">继续</a>
									</span>
								</div>
								<div class="card-sets clearfix">
									<a href="/user/userinfo" class="l">个人设置</a> <a
										href="http://www.imooc.com/user/logout" class="r">退出</a>
								</div>
							</div>
							<i class="card-arr"></i>
						</div></li>
				</ul>
			</div>
			<div class="app-down-area r">
				<a href="/mobile/app"> <i class="header-app-icon"></i>慕课APP
				</a>
			</div>
			<div class="search-area" data-search="top-banner">
				<input class="search-input" data-suggest-trigger="suggest-trigger"
					placeholder="请输入想搜索的内容..." type="text" autocomplete="off">
				<ul class="search-area-result" data-suggest-result="suggest-result">
				</ul>
				<input type="button" class="btn_search" data-search-btn="search-btn" />
			</div>
		</div>
	</div>
-->

	<div id="main">

		<div class="wcontainer set-space-cont clearfix">
			<div class="setting-left l">
				<ul class="wrap-boxes">
					<li><a href="Home/setprofile.jsp">个人资料</a></li>
					<li><a href="<%=request.getContextPath()%>/SetavatorServletIn">头像设置</a></li>
					<li><a href="Home/setverifyemail.jsp">邮箱验证</a></li>
					<li><a href="Home/setresetpwd.jsp">修改密码</a></li>
					<li><a href="Home/setbindsns.jsp">绑定帐号</a></li>
				</ul>
			</div>
			<div class="setting-right ">
				<div class="setting-right-wrap wrap-boxes settings">


					<div id="setting-avator"
						class="setting-wrap setting-avator clearfix">
						<div class="avator-img l">
							<div>
								<img
									<%String fileField = (String) request.getAttribute("fileField");
			String src1 = request.getContextPath()
					+ "/AvatorImages/ic_launcher-web.png";
			if (fileField != null) {
				src1 = request.getContextPath() + "/AvatorImages/" + fileField;
			}%>
									src="<%=src1%>" width='220' height='220' />
								<div class="js-avator-try avator-try">
									<p><%=request.getAttribute("msg") != null ? request
					.getAttribute("msg") : ""%></p>
								</div>
							</div>


						</div>
						<div class="avator-btn-group">
							<div id="avator-btns" class="avator-btn-inner">

								<div>
									<form
										action="<%=request.getContextPath()%>/SetavatorServletOut"
										method="post" enctype="multipart/form-data">

										<input value="上传图片" type="file" name="fileField" /> <input
											type="hidden" name="type" value="1" /> <input type="submit"
											value="提交" />
									</form>
									<iframe src="about:blank" id="uploadtarget" name="uploadtarget"
										frameborder="0" style="display: none;"></iframe>
								</div>
							</div>
							<div class="avator-upload-wrap" style="display: none;">
								<span hidefocus="true" id="avator-btn-save" aria-role="button"
									class="rlf-btn-green btn-block">保存</span>
							</div>
						</div>

					</div>

				</div>
			</div>
		</div>

	</div>

	<div id="footer">
		<div class="waper">
			<div class="footerwaper clearfix">
				<div class="followus r">
					<a class="followus-weixin" href="javascript:;" target="_blank"
						title="微信">
						<div class="flw-weixin-box"></div>
					</a> <a class="followus-weibo" href="http://weibo.com/u/3306361973"
						target="_blank" title="新浪微博"></a> <a class="followus-qzone"
						href="http://user.qzone.qq.com/1059809142/" target="_blank"
						title="QQ空间"></a>
				</div>
				<div class="footer_intro l">
					<div class="footer_link">
						<ul>
							<li><a href="http://www.imooc.com/" target="_blank">网站首页</a></li>
							<li><a href="/about/job" target="_blank">人才招聘</a></li>
							<li><a href="/about/contact" target="_blank">联系我们</a></li>
							<li><a href="http://daxue.imooc.com/" target="_blank">高校联盟</a></li>
							<li><a href="/about/us" target="_blank">关于我们</a></li>
							<li><a href="/about/recruit" target="_blank">讲师招募</a></li>
							<li><a href="/user/feedback" target="_blank">意见反馈</a></li>
							<li><a href="/about/friendly" target="_blank">友情链接</a></li>
						</ul>
					</div>
					<p>Copyright ? 2015 imooc.com All Rights Reserved | 京ICP备
						13046642号-2</p>
				</div>
			</div>
		</div>
	</div>
	<div id="J_GotoTop" class="elevator">
		<a class="elevator-weixin" href="javascript:;">
			<div class="elevator-weixin-box"></div>
		</a> <a class="elevator-msg" href="/user/feedback" target="_blank"
			id="feedBack"></a> <a class="elevator-app"
			href="http://www.imooc.com/mobile/app">
			<div class="elevator-app-box"></div>
		</a> <a class="elevator-top" href="javascript:;" style="display: none"
			id="backTop"></a>
	</div>



	<!--script-->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/Home/Js/sea.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/Home/Js/sea_config.js?v=210509221611"></script>
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
							+ "Js/h.js%3Ff0cfcccd7b1393990c78efdeebff3968' type='text/javascript'%3E%3C/script%3E"));
			(function(d) {
				window.bd_cpro_rtid = "rHT4P1c";
				var s = d.createElement("script");
				s.type = "text/javascript";
				s.async = true;
				s.src = location.protocol + "//Js/rt.js";
				var s0 = d.getElementsByTagName("script")[0];
				s0.parentNode.insertBefore(s, s0);
			})(document);
		</script>
	</div>
</body>
</html>
