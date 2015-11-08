<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@page import="com.etc.bean.ChapterBean"%>
<%@page import="com.etc.bean.PlanBean"%>
<%@page import="com.etc.bean.SectionBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>C++远征攻略_学习计划_慕课网</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="renderer" content="webkit">
<meta property="qc:admins" content="77103107776157736375">
<meta property="wb:webmaster" content="c4f857219bfae3cb">
<meta http-equiv="Access-Control-Allow-Origin" content="*">

<meta name="Keywords" content="C++基础,面向对象">


<meta name="Description"
	content="C++是在C语言的基础上开发的一种通用编程语言，应用广泛。C++支持多种编程范式 －－面向对象编程、泛型编程和过程化编程。C++语言灵活，运算符的数据结构丰富、具有结构化控制语句、程序执行效率高，而且同时具有高级语言与汇编语言的优点，与其它语言相比 ，可以直接访问物理地址，与汇编语言相比又具有良好的可读性和可移植性。">




<script src="<%=request.getContextPath()%>/Home/Js/rt.js" async=""
	type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/Home/Js/rt.js" async=""
	type="text/javascript"></script>
<script type="text/javascript">
	var OP_CONFIG = {
		"module" : "course",
		"page" : "programdetail"
	};
	var isLogin = 0;
	var seajsTimestamp = "v=210509221611";
</script>


<script type="text/javascript">
	var OP_CONFIG = OP_CONFIG || {};

	OP_CONFIG.isLogin = 0;

	var chartData = [];
</script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Home/Css/a2.css" type="text/css">
<script src="<%=request.getContextPath()%>/Home/Js/share.js"></script>
<script src="<%=request.getContextPath()%>/Home/Js/jquery_002.js"
	async="" charset="utf-8"></script>
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
<script src="<%=request.getContextPath()%>/Home/Js/programdetail.js"
	async="" charset="utf-8"></script>
<link href="<%=request.getContextPath()%>/Home/Css/share_style2_161.css"
	rel="stylesheet">
<script src="<%=request.getContextPath()%>/Home/Js/socket.js" async=""
	charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/Home/Js/layer.js" async=""
	charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/Home/Js/jquery_003.js"
	async="" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/Home/Js/jquery.js" async=""
	charset="utf-8"></script>
<link href="<%=request.getContextPath()%>/Home/Css/jquery1.css"
	rel="stylesheet" charset="utf-8">
<script src="<%=request.getContextPath()%>/Home/Js/raphael.js" async=""
	charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/Home/Js/share_002.js"></script>
<script
	src="<%=request.getContextPath()%>/Home/Js/jquery.js?v=210509221611"
	async="" charset="utf-8"></script>
<script
	src="<%=request.getContextPath()%>/Home/Js/seajs-text.js?v=210509221611"
	async="" charset="utf-8"></script>
<script
	src="<%=request.getContextPath()%>/Home/Js/common.js?v=210509221611"
	async="" charset="utf-8"></script>
<script
	src="<%=request.getContextPath()%>/Home/Js/programdetail.js?v=210509221611"
	async="" charset="utf-8"></script>
<link
	href="<%=request.getContextPath()%>/Home/Css/share_style2_16_0021.css"
	rel="stylesheet">

</head>
<body id="List_courseId">

	<%@ include file="head.jsp"%>
	<div id="main">


		<div class="g-layer g-layer-left clearfix ">
			<div class="g-crumb">

				<a href="http://www.imooc.com/course/program">学习计划</a>

				<!--
        <a href="/space/program/uid/">的学习计划</a>
         -->
				<span class="split">\</span>
				<%
				 String message=(String) request.getAttribute("message");
				if(message==null){
					message="参加该计划";
				}
					List<PlanBean> planBeanList = (List<PlanBean>) request.getAttribute("planBeanList");
				           if(planBeanList !=null&& !planBeanList.isEmpty()){
				        	   for(PlanBean planBean:planBeanList){
				%>
				<span class="cur"><%=planBean.getPlan_name()%></span>
			</div>
			<div class="layer-aside">
				<div class="plan-logo">
					<img
						src="<%=request.getContextPath()%>/Home/Images/<%=planBean.getPlan_img()%>"
						alt="C++远征攻略">
				</div>
				<div class="plan-actions mb14">

					<a href="<%=request.getContextPath()%>/MyselfPlanServlet?id=<%=planBean.getPlan_id()%>&sort=<%=message %>"><%=message %></a> 
					
				</div>
				<div class="mb30">
					<h3 class="g-tit mb14">计划介绍</h3>
					<p class="txt autowrap" id="js-plan-desc" data-max-height="192">
						<span class="desc-long"> <%=planBean.getPlan_content()%>
						</span> <a href="javascript:void();" class="txt-more">更多</a>
					</p>
				</div>
			</div>
			<div class="layer-container">
				<div class="plan-main">
					<div class="plan-top">
						<h2><%=planBean.getPlan_name()%></h2>
						<div class="plan-meta">
							<div class="r">
								<div class="small-share r">
									<ul class="share-wrap">
										<li><span class="share-txt">分享</span></li>
										<li class="weichat-posi">
											<div data-bd-bind="1443082502649"
												class="bdsharebuttonbox weichat-style bdshare-button-style2-16">
												<a href="#" class="bds_weixin icon-nav icon-share-weichat"
													data-cmd="weixin" title="分享到微信"><img
													src="<%=request.getContextPath()%>/Home/Images/1.jpg" /></a> <a
													href="#" class="bds_qzone icon-nav icon-share-qq"
													data-cmd="qzone" title="分享到QQ空间"><img
													src="<%=request.getContextPath()%>/Home/Images/2.jpg" /></a> <a
													href="#" class="bds_tsina icon-nav icon-share-weibo"
													data-cmd="tsina" title="分享到新浪微博"><img
													src="<%=request.getContextPath()%>/Home/Images/3.jpg" /></a>
											</div>
										</li>
									</ul>
								</div>
							</div>

						</div>
					</div>
					<%
						}
					        	   }else{
						out.print("对不起");
					}
					%>
					<%
						Map<Integer,List> map = (Map<Integer,List>) request.getAttribute("map");
					               if(map.get(0) != null && !map.get(0).isEmpty()){
					            		List<ChapterBean> list = map.get(0);
					            	   	for(ChapterBean chapterBean: list){
					%>


					<div class="plan-step-wrap js-raise-panel" id="js-raise-panel">

						<ul class="plan-step salary-raise-step clearfix">
							<li class="step-item clearfix step-first"><i class="line"></i>
								<i class="dot"></i>
								<div class="step-content">
									<h4 class="">
										<%=chapterBean.getChap_name()%>
									</h4>
									<p class="autowrap"><%=chapterBean.getChap_content()%></p>

								</div>


								<div class="step-medias-wrap" style="display: none">
									<div class="step-medias course-list">
										<ul class="clearfix">
											<%
												int id = chapterBean.getChapter_id();
														if(map.get(1) != null && !map.get(1).isEmpty()){
															List<SectionBean> list1 = (List<SectionBean>)map.get(1);
															List<SectionBean> subList = new ArrayList<SectionBean>();
															for(SectionBean s: list1){
																if(id == s.getChap_id()){
																	subList.add(s);
																}
															}
															for(SectionBean s: subList){
											%>

											<li class="course-one"><a
												href="http://www.imooc.com/view/249" target="_blank">
													<div class="course-list-img">
														<img width="240" height="135" style="display: block"
															alt="<%=s.getSect_name()%>"
															src="./CourseImages/<%=s.getSection_img()%>">
													</div>

													<h5>
														<span><%=s.getSect_name()%></span>
													</h5>
													<div class="tips">
														<p class="text-ellipsis"><%=s.getSection_content()%></p>
														<span class="l ">更新完毕</span> <span class="l ml20">
															108583 人学习</span>
													</div> <span class="time-label"> 4小时50分钟 | 初级 </span> <b
													class="follow-label">跟我学</b>

											</a></li>


											<%
												}
															}else{
																out.print("SORRY!");
															}
											%>
										</ul>
									</div>
								</div></li>

						</ul>
					</div>



					<%
						}  	 
					               }else{
					            	   out.print("抱歉");
					            	   
					               }
					%>


					<!-- 学习报告 -->

					<!-- 学习报告 E-->
				</div>
			</div>
		</div>
		<div class="anchor-pop">
			<div class="t"></div>
			<div class="m">
				<div class="anchor-pop-main"></div>
			</div>
			<div class="b"></div>
		</div>
		<script type="text/javascript">
			var surl = url = 'http://www.mukewang.com'
					+ window.location.pathname;
			window._bd_share_config = {
				"common" : {
					"bdUrl" : surl,
					"bdSnsKey" : {
						'tsina' : '2788596354'
					},
					"bdText" : "我正在参加@慕课网 的一个学习计划【C++远征攻略】，很不错哦！快来一起学习吧！",
					"bdMini" : "2",
					"bdMiniList" : false,
					"bdPic" : "",
					"bdStyle" : "2",
					"bdSize" : "16"
				},
				"share" : {}
			};
			with (document)
				0[(getElementsByTagName('head')[0] || body)
						.appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='
						+ ~(-new Date() / 36e5)];
		</script>


	</div>

	<div id="footer">
		<div class="waper">
			<div class="footerwaper clearfix">
				<div class="followus r">
					<a class="followus-weixin" href="javascript:;" target="_blank"
						title="微信"><div class="flw-weixin-box"></div></a> <a
						class="followus-weibo" href="http://weibo.com/u/3306361973"
						target="_blank" title="新浪微博"></a> <a class="followus-qzone"
						href="http://user.qzone.qq.com/1059809142/" target="_blank"
						title="QQ空间"></a>
				</div>
				<div class="footer_intro l">
					<div class="footer_link">
						<ul>
							<li><a href="http://www.imooc.com/" target="_blank">网站首页</a></li>
							<li><a href="http://www.imooc.com/about/job" target="_blank">人才招聘</a></li>
							<li><a href="http://www.imooc.com/about/contact"
								target="_blank">联系我们</a></li>
							<li><a href="http://daxue.imooc.com/" target="_blank">高校联盟</a></li>
							<li><a href="http://www.imooc.com/about/us" target="_blank">关于我们</a></li>
							<li><a href="http://www.imooc.com/about/recruit"
								target="_blank">讲师招募</a></li>
							<li><a href="http://www.imooc.com/user/feedback"
								target="_blank">意见反馈</a></li>
							<li><a href="http://www.imooc.com/about/friendly"
								target="_blank">友情链接</a></li>
						</ul>
					</div>
					<p>Copyright © 2015 imooc.com All Rights Reserved | 京ICP备
						13046642号-2</p>
				</div>
			</div>
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
		<script src="<%=request.getContextPath()%>/Home/Images/h.txt"
			type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/Home/Js/h.js"
			type="text/javascript"></script>
		<a
			href="http://tongji.baidu.com/hm-web/welcome/ico?s=f0cfcccd7b1393990c78efdeebff3968"
			target="_blank"> <img
			src="<%=request.getContextPath()%>/Home/Images/21_002.gif" border="0"
			height="20" width="20"></a> <a
			href="http://tongji.baidu.com/hm-web/welcome/ico?s=f0cfcccd7b1393990c78efdeebff3968"
			target="_blank"> <img
			src="<%=request.getContextPath()%>/Home/Images/21.gif" border="0"
			height="20" width="20"></a>
	</div>


</body>
</html>
