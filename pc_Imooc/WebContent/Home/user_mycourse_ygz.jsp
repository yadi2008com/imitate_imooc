<%@page import="com.etc.bean.PageBean"%>
<%@page import="com.etc.bean.CourseBean"%>
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
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>已关注课程</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="renderer" content="webkit">
<meta property="qc:admins" content="77103107776157736375">
<meta property="wb:webmaster" content="c4f857219bfae3cb">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="keywords"
	content="慕课网，慕课官网，MOOC，移动开发，IT技能培训，免费编程视频，php开发教程，web前端开发，在线编程学习，html5视频教程，css教程，ios开发培训，安卓开发教程">
<meta name="description"
	content="慕课网（IMOOC）是学习编程最简单的免费平台。慕课网提供了丰富的移动端开发、php开发、web前端、html5教程以及css3视频教程等课程资源。它富有交互性及趣味性，并且你可以和朋友一起编程。">



<script src="Home/Js/rt.js" async="" type="text/javascript"></script>
<script type="text/javascript">
	var OP_CONFIG = {
		"module" : "space",
		"page" : "index",
		"userInfo" : {
			"uid" : "2385823",
			"nickname" : "qq_\u68a6\u7199\u7b14\u8c08_0",
			"head" : "http:\/\/img.mukewang.com\/user\/56026f740001411b01000100-80-80.jpg",
			"usertype" : "1",
			"roleid" : 0
		}
	};
	var isLogin = 1;
	var seajsTimestamp = "v=210509221611";
</script>

<link rel="stylesheet" href="Home/Css/layer.css">
<link rel="stylesheet" href="Home/Css/a1.css" type="text/css">
<script src="Home/Js/jquery.js" async="" charset="utf-8"></script>
<script src="Home/Js/seajs-text.js" async="" charset="utf-8"></script>
<script src="Home/Js/common.js" async="" charset="utf-8"></script>
<script src="Home/Js/string.js" async="" charset="utf-8"></script>
<script src="Home/Js/suggest.js" async="" charset="utf-8"></script>
<script src="Home/Js/store.js" async="" charset="utf-8"></script>
<script src="Home/Js/json.js" async="" charset="utf-8"></script>
<script src="Home/Js/im.js" async="" charset="utf-8"></script>
<script src="Home/Js/program.js" async="" charset="utf-8"></script>
<script src="Home/Js/socket.js" async="" charset="utf-8"></script>
<script src="Home/Js/layer.js" async="" charset="utf-8"></script>
<script src="Home/Js/space_common.js" async="" charset="utf-8"></script>
<script src="Home/Js/jquery_002.js" async="" charset="utf-8"></script>
</head>
<body class="my space-course self">
	<%@ include file="headsuccess.jsp"%>
	<div id="main">
		<div class="main-body container clearfix">
			<%@ include file="user_course_left.jsp"%>

			<div class="r space-main">

				<div class="family">
					<h1 class="family-hd">我的课程</h1>
				</div>
				<div class="course-tool-bar clearfix">
					<div class="tool-left l">
						<a href="<%=request.getContextPath()%>/MyCourseServlet"
							class="sort-item active">已关注 </a> <a
							href="<%=request.getContextPath()%>/MyCourseYxServlet"
							class="sort-item ">已学</a>

					</div>
						<%List<CourseBean> courseBeanList=null;
						if(request.getAttribute("courseBeanList")!=null){
							courseBeanList=(List<CourseBean>)request.getAttribute("courseBeanList");
						}
				%>
					<%
						PageBean pageBean=(PageBean)request.getAttribute("pageBean");
					       int rows=(int)request.getAttribute("rows");
					%>

					<div class="tool-right r">
				
						<span class="tool-item total-num">
						</span> <span class="tool-item tool-pager"> <span
							class="pager-num"> <b class="pager-cur"><%=pageBean.getPageno()%></b>/<em
								class="pager-total"><%=pageBean.getMaxpage()%></em>
						</span> <a
							href="<%=request.getContextPath()%>/MyCourseServlet?pageno=<%=pageBean.getPageno() - 1%> ">上一页</a>

							<a
							href="<%=request.getContextPath()%>/MyCourseServlet?pageno=<%=pageBean.getPageno() + 1%> ">下一页</a>
						</span>
					</div>
				</div>
			<%if(courseBeanList!=null){
				
			
			for(CourseBean courseBean:courseBeanList){%>
				<ul class="follow-list">
					<li data-id="509"><a class="btn-del js-btn-del"></a>
						<div class="box-left l">
							<a href="#" title=" <%=courseBean.getCour_title()%>"
								target="_blank">
								<div class="course-list-img">
									<img src="CourseImages/<%=courseBean.getCour_image()%>"
										alt=" <%=courseBean.getCour_title()%>" height="123"
										width="220">
									<div class="pro-bg"></div>
									<em class="dot-progress">0%</em>
									<div class="progress-bar">
										<i class="studyrate bar" value="0" data-finishval="0"
											style="width: 0%"></i>
									</div>
								</div>
							</a>
						</div>
						<div class="box-right">
							<h3 class="box-hd">
								<span> <%=courseBean.getCour_title()%></span> <span
									class="span-new ">22小时前更新至6-4</span>
							</h3>
							<div class="study-points">
								<span class="span-left span-common"> 已学习至：1-1 课程重点</span> <span
									class="span-mid span-common">学习耗时： 0分</span> <span
									class="span-right span-common">最后学习：3分钟前</span>
							</div>
							<div class="study-btm">
								<a href="Home/CourseSource/<%=courseBean.getCour_url()%>" class="beginstudy"
									data-title="1-1 内容简介" target="_blank">开始学习</a>

							</div>

						</div></li>
				</ul>


				<%
					}} 
				%>
			</div>
		</div>

	</div>
	<%@ include file="foot.jsp"%>
	<div id="J_GotoTop" class="elevator">
		<a class="elevator-weixin" href="javascript:;">
			<div class="elevator-weixin-box"></div>
		</a> <a class="elevator-msg" href="#" target="_blank" id="feedBack"></a> <a
			class="elevator-app" href="#">
			<div class="elevator-app-box"></div>
		</a> <a class="elevator-top" href="javascript:;" style="display: none"
			id="backTop"></a>
	</div>
	<!--script-->
	<script type="text/javascript" src="Home/Js/sea.js"></script>
	<script type="text/javascript" src="Home/Js/sea_config.js"></script>
	<script type="text/javascript">
		seajs.use("/static/page/" + OP_CONFIG.module + "/" + OP_CONFIG.page);
	</script>




	<style type="text/css">
.myClassList table td dl dd {
	margin-top: 88px
}

#face_panel {
	z-index: 99999999
}
</style>

	<div id="layer_sendmsg" class="font-colorg" style="display: none">
		<div class="sendadd">
			<h4>
				发送好友申请<span class="tipclose"></span>
			</h4>
			<div class="sendcon">
				<textarea class="chatInput" id="textInput" type="text" name="说点什么吧"
					cols="1"></textarea>
			</div>
			<div class="sendsq">
				<a href="javascript:void(0)" id="sendBtn"
					class="sendInvite btn btn-green">发送申请</a>
			</div>
		</div>
	</div>


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
		<script src="Home/Js/h1.js" type="text/javascript"></script>
		<a href="#" target="_blank"><img src="Home/Images/21.gif"
			border="0" height="20" width="20"></a>
	</div>


</body>
</html>