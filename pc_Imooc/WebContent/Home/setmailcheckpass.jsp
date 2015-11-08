<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<title>æè¯¾ç½</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="renderer" content="webkit">
<meta property="qc:admins" content="77103107776157736375" />
<meta property="wb:webmaster" content="c4f857219bfae3cb" />
<meta http-equiv="Access-Control-Allow-Origin" content="*" />
<meta name="keywords"
	content="æè¯¾ç½ï¼æè¯¾å®ç½ï¼MOOCï¼ç§»å¨å¼åï¼ITæè½å¹è®­ï¼åè´¹ç¼ç¨è§é¢ï¼phpå¼åæç¨ï¼webåç«¯å¼åï¼å¨çº¿ç¼ç¨å­¦ä¹ ï¼html5è§é¢æç¨ï¼cssæç¨ï¼ioså¼åå¹è®­ï¼å®åå¼åæç¨" />
<meta name="description"
	content="æè¯¾ç½ï¼IMOOCï¼æ¯å­¦ä¹ ç¼ç¨æç®åçåè´¹å¹³å°ãæè¯¾ç½æä¾äºä¸°å¯çç§»å¨ç«¯å¼åãphpå¼åãwebåç«¯ãhtml5æç¨ä»¥åcss3è§é¢æç¨ç­è¯¾ç¨èµæºãå®å¯æäº¤äºæ§åè¶£å³æ§ï¼å¹¶ä¸ä½ å¯ä»¥åæåä¸èµ·ç¼ç¨ã" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Home/Css/base.css" type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Home/Css/common-less.css"
	type="text/css" />
<script type="text/javascript">
	var OP_CONFIG = {
		"module" : "user",
		"page" : "setresetpwd",
		"userInfo" : {
			"uid" : "2385822",
			"nickname" : "1221010520",
			"head" : "http:\/\/img.mukewang.com\/user\/5333a1d100010c2602000200-80-80.jpg",
			"usertype" : "1",
			"roleid" : 0
		}
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
				<a href="/" target="_self" class="hide-text">æè¯¾ç½</a>
			</div>
			<div class="g-menu-mini l">
				<a href="#" class="menu-ctrl"> <i class="icon-menu"></i>
				</a>
				<ul class="nav-item l">
					<li><a href="./courseList.jsp" target="_self">è¯¾ç¨</a></li>
					<li><a href="./decoPlanList.jsp" target="_self">è®¡å</a></li>
					<li><a href="/corp/index" target="_self">åäº«</a></li>
					<li><a href="/wenda" target="_self">ç¤¾åº</a></li>
				</ul>
			</div>
			<div id="login-area">
				<ul class="clearfix logined">
					<li class="my_message"><a href="/message" title="æçæ¶æ¯"
						target="_self"> <i class="msg_icon" style="display: none;"></i>
							<span style="display: none;">æçæ¶æ¯</span>
					</a></li>
					<li class="set_btn user-card-box"><a id="header-avator"
						class="user-card-item" action-type="my_menu" href="/space/index"
						target="_self"><img
							src='http://img.mukewang.com/user/5333a1d100010c2602000200-40-40.jpg'
							width='40' height='40' /> <i class="myspace_remind"
							style="display: none;"></i> <span style="display: none;">å¨ææé</span>
					</a>
						<div class="g-user-card">
							<div class="card-inner">
								<div class="card-top">
									<img
										src="http://img.mukewang.com/5333a1d100010c2602000200-100-100.jpg"
										alt="1221010520" class="l"> <span
										class="name text-ellipsis">1221010520</span>
									<p class="meta">
										<span>ç»éª<b id="js-user-mp">167</b></span> <span
											style="display: none">ç§¯å<b>12331</b></span>
									</p>
								</div>
								<div class="card-links">
									<a href="/space/index" class="my-mooc l">æçæè¯¾<i
										class="dot-update"></i></a> <span class="split l"></span> <a
										href="/myclub/myquestion/t/ques" class="my-sns l">æçç¤¾åº</a>
								</div>
								<div class="card-history">
									<span class="history-item"> <span
										class="tit text-ellipsis">Javaå¥é¨ç¬¬ä¸å­£</span> <span
										class="media-name text-ellipsis">3-2 Javaä¸­çç®æ¯è¿ç®ç¬¦</span> <i
										class="icon-clock"></i> <a href="/video/1279" class="continue">ç»§ç»­</a>
									</span>
								</div>
								<div class="card-sets clearfix">
									<a href="/user/userinfo" class="l">ä¸ªäººè®¾ç½®</a> <a
										href="http://www.imooc.com/user/logout" class="r">éåº</a>
								</div>
							</div>
							<i class="card-arr"></i>
						</div></li>
				</ul>
			</div>
			<div class="app-down-area r">
				<a href="/mobile/app"> <i class="header-app-icon"></i>æè¯¾APP
				</a>
			</div>
			<div class="search-area" data-search="top-banner">
				<input class="search-input" data-suggest-trigger="suggest-trigger"
					placeholder="è¯·è¾å¥æ³æç´¢çåå®¹..." type="text" autocomplete="off">
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
					<li><a href="Home/setprofile.jsp">ä¸ªäººèµæ</a></li>
					<li><a href="<%=request.getContextPath()%>/SetavatorServletIn">å¤´åè®¾ç½®</a></li>
					<li><a href="Home/setverifyemail.jsp">é®ç®±éªè¯</a></li>
					<li><a href="Home/setresetpwd.jsp">ä¿®æ¹å¯ç </a></li>
					<li><a href="Home/setbindsns.jsp">ç»å®å¸å·</a></li>
				</ul>
			</div>
			<div class="setting-right ">
				<div class="setting-right-wrap wrap-boxes settings">

					<div class="pwd-reset-wrap setting-resetpwd">
						<form action="<%=request.getContextPath()%>/SetresetpwdServlet"
							method="post" id="resetpwdform">

							<div class="wlfg-wrap">
								<label class="label-name" for="">å½åå¯ç </label>
								<div class="rlf-group">
									<input type="password" name="oldpwd"
										class="rlf-input rlf-input-pwd" placeholder="è¯·è¾å¥å½åå¯ç " />
									<p class="rlf-tip-wrap"><%=request.getAttribute("oldmsg") != null ? request
					.getAttribute("oldmsg") : ""%></p>
								</div>
							</div>


							<div class="wlfg-wrap">
								<label class="label-name" for="newpass">æ°å¯ç </label>
								<div class="rlf-group">
									<input type="password" data-validate="password" name="newpass"
										id="newpass" class="rlf-input rlf-input-pwd"
										placeholder="è¯·è¾å¥å¯ç " />
									<p class="rlf-tip-wrap"><%=request.getAttribute("newmsg") != null ? request
					.getAttribute("newmsg") : ""%></p>
								</div>
							</div>


							<div class="wlfg-wrap">
								<label class="label-name" for="confirm">ç¡®è®¤å¯ç </label>
								<div class="rlf-group">
									<input type="password" name="confirm" id="confirm"
										class="rlf-input rlf-input-pwd" placeholder="è¯·è¾å¥å¯ç " />
									<p class="rlf-tip-wrap"><%=request.getAttribute("confirmmsg") != null ? request
					.getAttribute("confirmmsg") : ""%></p>
								</div>
							</div>
							<div class="wlfg-wrap">
								<label class="label-name" for=""></label>
								<div class="rlf-group">
									<!--<span id="resetpwd-btn-save"  hidefocus="true"   aria-role="button" class="rlf-btn-green btn-block">ä¿å­</span>-->
									<input id="resetpwd-btn-save"
										class="rlf-btn-green btn-block profile-btn" type="submit"
										value="ä¿å­" />
									<p class="rlf-tip-wrap"><%=request.getAttribute("endmsg") != null ? request
					.getAttribute("endmsg") : ""%></p>
								</div>
							</div>
						</form>
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
						title="å¾®ä¿¡">
						<div class="flw-weixin-box"></div>
					</a> <a class="followus-weibo" href="http://weibo.com/u/3306361973"
						target="_blank" title="æ°æµªå¾®å"></a> <a class="followus-qzone"
						href="http://user.qzone.qq.com/1059809142/" target="_blank"
						title="QQç©ºé´"></a>
				</div>
				<div class="footer_intro l">
					<div class="footer_link">
						<ul>
							<li><a href="http://www.imooc.com/" target="_blank">ç½ç«é¦é¡µ</a></li>
							<li><a href="/about/job" target="_blank">äººææè</a></li>
							<li><a href="/about/contact" target="_blank">èç³»æä»¬</a></li>
							<li><a href="http://daxue.imooc.com/" target="_blank">é«æ ¡èç</a></li>
							<li><a href="/about/us" target="_blank">å³äºæä»¬</a></li>
							<li><a href="/about/recruit" target="_blank">è®²å¸æå</a></li>
							<li><a href="/user/feedback" target="_blank">æè§åé¦</a></li>
							<li><a href="/about/friendly" target="_blank">åæé¾æ¥</a></li>
						</ul>
					</div>
					<p>Copyright Â© 2015 imooc.com All Rights Reserved | äº¬ICPå¤
						13046642å·-2</p>
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
	</div>
</body>
</html>