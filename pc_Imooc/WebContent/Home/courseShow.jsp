<%@page import="com.etc.bean.CourseBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	CourseBean courseBean = (CourseBean) request
			.getAttribute("courseBean");
%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title><%=courseBean.getCour_title()%></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="renderer" content="webkit">
<meta property="qc:admins" content="77103107776157736375">
<meta property="wb:webmaster" content="c4f857219bfae3cb">
<meta http-equiv="Access-Control-Allow-Origin" content="*">

<meta name="Keywords" content="">


<meta name="Description"
	content="多对多关联也是常见的一种关联关系，如项目和员工之间就是典型的多对多关系。本次将与各位小伙伴们分享的就是Hibernate中的关联映射——多对多映射。">




<script src="Home/Js/rt.js" async="" type="text/javascript"></script>
<script type="text/javascript">
  var OP_CONFIG={"module":"course","page":"view"};
  var isLogin = 0;
  var seajsTimestamp="v=210509281800";
  </script>
<script src="<%=request.getContextPath() %>/Admin/Js/jquery.js"></script>

<script>
/*学习页通用配置*/
var GC = {
  course: {
    id: 452,
    name: '<%=courseBean.getCour_title()%>
	',
			pic : 'http://img.mukewang.com/55a5f6c100016bb806000338-240-135.jpg',
			video_url : ''
		},
		classmates : 20
	// 你的同学一页显示数量
	};
</script>

<link rel="stylesheet" href="Home/Css/a.css" type="text/css">
<script src="Home/Js/jquery_002.js" async="" charset="utf-8"></script>
<script src="Home/Js/seajs-text.js" async="" charset="utf-8"></script>
<script src="Home/Js/common.js" async="" charset="utf-8"></script>
<script src="Home/Js/share.js"></script>
<script src="Home/Js/string.js" async="" charset="utf-8"></script>
<script src="Home/Js/suggest.js" async="" charset="utf-8"></script>
<script src="Home/Js/store.js" async="" charset="utf-8"></script>
<script src="Home/Js/json.js" async="" charset="utf-8"></script>
<script src="Home/Js/im.js" async="" charset="utf-8"></script>
<script src="Home/Js/view.js" async="" charset="utf-8"></script>
<script src="Home/Js/socket.js" async="" charset="utf-8"></script>
<script src="Home/Js/course_common.js" async="" charset="utf-8"></script>
<script src="Home/Js/course_collection.js" async="" charset="utf-8"></script>
<link href="Home/Css/share_style0_16.css" rel="stylesheet">
<script src="Home/Js/layer.js" async="" charset="utf-8"></script>
<link href="Home/Css/layer.css" rel="stylesheet" charset="utf-8">
<script src="Home/Js/jquery.js" async="" charset="utf-8"></script>
</head>
<body>
	<%@ include file="head.jsp"%>
	<div id="main1">

		<div class="course-infos">
			<div class="w pr">
				<div class="path">
					<a href="http://www.imooc.com/course/list">课程</a> <i
						class="path-split">\</i><a
						href="http://www.imooc.com/course/list?c=be">后端开发</a> <i
						class="path-split">\</i><a
						href="http://www.imooc.com/course/list?c=java">JAVA</a> <i
						class="path-split">\</i><span><%=courseBean.getCour_title()%></span>
				</div>
				<div class="hd">
					<h2 class="l"><%=courseBean.getCour_title()%></h2>
				</div>
				<div class="statics clearfix">
					<div class="static-item ">
						<span class="meta-value"><strong><%=courseBean.getLevel_name()%></strong></span>
						<span class="meta">难度</span> <em></em>
					</div>
					<div class="static-item static-time">
						<span class="meta-value"><strong><%=courseBean.getCour_duration()%>
						</strong></span> <span class="meta">时长</span> <em></em>
					</div>
					<div class="static-item">
						<span class="meta-value"><strong><%=courseBean.getCour_hot()%></strong></span>
						<span class="meta">学习人数</span> <em></em>
					</div>
					<!-- <div class="static-item">
        <span class="meta-value"><strong>4.8分</strong></span>
        <span class="meta">
          <i class="meta-star"></i>
          <i class="meta-star"></i>
          <i class="meta-star"></i>
        </span>
        <em></em>
      </div> -->
				</div>
				<div class="extra">
					<div data-bd-bind="1443494319097"
						class="share-action r bdsharebuttonbox bdshare-button-style0-16">
						分享 <a title="分享到微信" href="javascript:;" class="share wx js-share"
							data-cmd="weixin"></a> <a title="分享到QQ空间" href="javascript:;"
							class="share qq js-share" data-cmd="qzone"></a> <a
							title="分享到新浪微博" href="javascript:;" class="share sina js-share"
							data-cmd="tsina"></a>
					</div>
					<i class="split-line r"></i> <a href="javascript:;"
						data-cmd="follow" class="follow-action r js-follow-action"
						data-cid="452"> 关注 </a>
				</div>
			</div>
			<div class="info-bg" id="js-info-bg">
				<div class="cover-img-wrap">
					<img
						data-src="http://img.mukewang.com/55af703900018f1206000338.jpg"
						alt="" style="display: none" id="js-cover-img">
				</div>
				<div class="cover-mask"></div>
				<canvas id="js-cover-canvas" class="cover-canvas" height="240"
					width="1583"></canvas>
			</div>
		</div>
		<div class="course-info-main clearfix w">
			<div class="info-bar clearfix">



				<a href="http://www.imooc.com/learn/452"
					class="btn-red start-study-btn r">体验学习</a>
			</div>
			<div class="content-wrap">
				<div class="content">
					<div class="course-brief">
						<h3 class="ctit">课程介绍</h3>
						<p class="auto-wrap"><%=courseBean.getCour_content()%></p>
					</div>
					<div class="course-outline">
						<div class="bar clearfix">
							<h3 class="ctit l">视频播放</h3>
							<ul class="tools l">
								<li class="l">6<i class="icon-video"></i><span>视频</span>
								</li>
								<li class="l">0<i class="icon-test"></i><span>练习题</span>
								</li>
								<li class="l">0<i class="icon-code"></i><span>编程练习</span>
								</li>
							</ul>
						</div>
						<div class="outline-list">
							<ul>
								<li class="chapter clearfix "><i class="chapter-icon"></i>
									<div class="chapter-bd l">
										<!--视频播放开始  -->
										<div id="a1"  name="<%=courseBean.getCour_source() %>"></div>
										<script type="text/javascript" src="ckplayer/ckplayer.js"
											charset="utf-8"></script>
										<script type="text/javascript">
										 var Cour_source=$("#a1").attr("name");
											var flashvars = {
												f : 'http://192.168.207.81:8080/Imooc/CourseSource/'+Cour_source,
												c : 0,
												loaded : 'loadedHandler'
											};
											var video = [ 'http://movie.ks.js.cn/flv/other/1_0.mp4->video/mp4' ];
											CKobject.embed(
													'ckplayer/ckplayer.swf',
													'a1', 'ckplayer_a1', '600',
													'400', false, flashvars,
													video);
										
										</script>
										<!--视频播放结束  -->
										<h5 class="name">第1章 多对多关联关系的应用场景</h5>
										<p class="desc">本章节主要讲解多对多关联关系的应用场景，通过案例分析，理解多对多映射的概念和应用场景</p>
									</div></li>
								<li class="chapter clearfix "><i class="chapter-icon"></i>
									<div class="chapter-bd l">
										<h5 class="name">第2章 多对多映射的配置案例</h5>
										<p class="desc">本章节通过案例的方式，讲解多对多映射的配置</p>
									</div></li>
								<li class="chapter clearfix "><i class="chapter-icon"></i>
									<div class="chapter-bd l">
										<h5 class="name">第3章 课程总结</h5>
										<p class="desc">课程总结</p>
									</div></li>
							</ul>
							<p class="update-tip">后续章节持续更新中</p>
						</div>
					</div>
				</div>
				<div class="aside r">
					<div class="bd">
						<div class="box mb40">
							<h4>讲师提示</h4>
							<div class="teacher-info">
								<a href="http://www.imooc.com/space/teacher/id/112258"
									target="_blank"> <img
									src="Home/Images/535f03950001915501400140-80-80.jpg"
									height="80" width="80">
								</a> <span class="tit"> <a
									href="http://www.imooc.com/space/teacher/id/112258"
									target="_blank"><%=courseBean.getCour_teacher()%></a><i
									class="icon-imooc"></i>
								</span> <span class="job">JAVA开发工程师</span>

							</div>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<!-- 视频介绍 -->
				<div id="js-video-wrap" class="video pop-video"
					style="display: none">
					<div class="video_box" id="js-video"></div>
					<a href="javascript:;" class="pop-close icon-close"></a>
				</div>

			</div>


			<div id="J_GotoTop" class="elevator">
				<a class="elevator-weixin" href="javascript:;">
					<div class="elevator-weixin-box"></div>
				</a> <a class="elevator-msg" href="http://www.imooc.com/user/feedback"
					target="_blank" id="feedBack"></a> <a class="elevator-app"
					href="http://www.imooc.com/mobile/app">
					<div class="elevator-app-box"></div>
				</a> <a class="elevator-top" href="javascript:;" style="display: none"
					id="backTop"></a>
			</div>



			<!--script-->
			<script type="text/javascript" src="Home/Js/sea.js"></script>
			<script type="text/javascript" src="Home/Js/sea_config.js"></script>
			<script type="text/javascript">
				seajs.use("/static/page/" + OP_CONFIG.module + "/"
						+ OP_CONFIG.page);
			</script>


			<script type="text/javascript">
				(function() {
					var imgPic = GC.course.pic
							|| 'http://img.mukewang.com/static/img/common/logo.png', text = '我正在参加@慕课网 的一门课程【'
							+ GC.course.name + '】，很不错哦！快来一起学习吧！', //节名称
					url = 'http://www.mukewang.com' + window.location.pathname;

					window._bd_share_config = {
						"common" : {
							"bdUrl" : url,
							"bdSnsKey" : {
								'tsina' : '2788596354'
							},
							"bdText" : text,
							"bdMini" : "2",
							"bdMiniList" : false,
							"bdPic" : imgPic,
							"bdStyle" : "0",
							"bdSize" : "16"
						},
						"share" : {}
					};
					with (document)
						0[(getElementsByTagName('head')[0] || body)
								.appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='
								+ ~(-new Date() / 36e5)];
				})();
			</script>

			<div class="mask"></div>


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
						s.src = location.protocol
								+ "//cpro.baidu.com/cpro/ui/rt.js";
						var s0 = d.getElementsByTagName("script")[0];
						s0.parentNode.insertBefore(s, s0);
					})(document);
				</script>
				<script src="Home/Js/h.js" type="text/javascript"></script>
				<a
					href="http://tongji.baidu.com/hm-web/welcome/ico?s=f0cfcccd7b1393990c78efdeebff3968"
					target="_blank"><img src="Home/Images/21.gif" border="0"
					height="20" width="20"></a>
			</div>
		</div>
		<%@ include file="foot.jsp"%>
</body>
</html>