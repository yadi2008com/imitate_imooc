<%@page import="com.etc.bean.DecorationBean"%>
<%@page import="com.etc.bean.PageBean"%>
<%@page import="com.etc.bean.PlanBean"%>
<%@page import="java.util.List"%>
<%@page import="com.etc.bean.CourseBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

PageBean pageBean=(PageBean)request.getAttribute("pageBean");
List<DecorationBean> decorationInfoList =(List<DecorationBean>)request.getAttribute("decorationInfoList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>慕课网-学习计划</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="renderer" content="webkit">
<meta property="qc:admins" content="77103107776157736375" />
<meta property="wb:webmaster" content="c4f857219bfae3cb" />
<meta http-equiv="Access-Control-Allow-Origin" content="*" />

<meta name="Keywords" content="WEB前端工程师,JAVA工程师,Andorid工程师,PHP工程师" />

<meta name="Description"
	content="慕课网学习计划，精心打造适合你的IT学习计划，包含求职路线、加薪利器，从零起点培养WEB前端工程师、JAVA工程师、Andorid工程师、PHP工程师，并且为有一定基础的工程师提供各级别实战指导，为晋级加薪提供帮助！" />




<script type="text/javascript">
	var OP_CONFIG = {
		"module" : "course",
		"page" : "program",
		"userInfo" : {
			"uid" : "2388034",
			"nickname" : "ww_ww",
			"head" : "http:\/\/img.mukewang.com\/user\/5458633f0001c2a902200220-80-80.jpg",
			"usertype" : "1",
			"roleid" : 0
		}
	};
	var isLogin = 1;
	var seajsTimestamp = "v=210509221611";
</script>

	
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Home/Css/plan.css"
	type="text/css">
</head>
<body id="List_courseId">

	<%@ include file="head.jsp"%>
<%

int getDecoration_id=0;
%>

	<div id="main">

		<div class="program-wrap clearfix">
			<div class="program-main">

				<!--program-category end-->

				<div class="program-category">
					<h2>加薪利器</h2>
				</div>
				<div>
					<div class="course-nav-row clearfix">
						<span class="hd l">方向：</span>
						<div class="bd">
							<ul class="">
								<li class="course-nav-item"><a
									href="<%=request.getContextPath()%>/AllPlanServlet">全部</a></li>
								<%
								    
									for(DecorationBean decorationBean:decorationInfoList){
										
								%>

								<li class="course-nav-item"><a
									href="AllPlanServlet?id=<%=decorationBean.getDecoration_id()%>">
									<%=decorationBean.getDeco_name()%></a>
									
									
								</li>
								<%
									}
								%>
							</ul>
						</div>
					</div>
					<div class="course-tool-bar clearfix">
						
						<div class="tool-right r">



							</span>
						</div>
					</div>
				</div>
				<%
					List<PlanBean> decoPlanList = (List<PlanBean>) request.getAttribute("decoPlanList");
				      
				         
				    
								if (decoPlanList!= null && !decoPlanList.isEmpty()) {
									
									
									 
				%>
				<div class="course-list salary-raise program-medias">

					<ul class="clearfix">
						 <%
						 
						 for(PlanBean planBean:decoPlanList) {
							 getDecoration_id=planBean.getDecoration_id();
						 %>
                                    
						<li class="course-one"><a href="/Imooc/ChapterServlet?plan_id=<%=planBean.getPlan_id() %>"
							title="<%=planBean.getPlan_name()%>">
							
							 
							
								<div class="course-list-img">
									<img width="240" height="180"
										alt="<%=planBean.getPlan_content()%>"
										src="<%=request.getContextPath()%>/Home/Images/<%=planBean.getPlan_img()%>">
								</div>
								<h5>
									<span><%=planBean.getPlan_name()%></span>
								</h5>
								<div class="tags">
									<span>职业必备</span> <span>必备技能</span> <span>实践</span>
								</div>
								<div class="tips">
									<span class="l"></span> <span class="l ml20"></span>
								</div>
						</a></li>


						<%
							}
						%>
					</ul>

					<%
					}
					%>

				</div>


				<ul class="program-list clearfix hide"></ul>
				<!--program end-->
				<div class="bg-loading"></div>
			</div>
			<!--program-main end-->
		</div>
		<!--wrap end-->

	</div>
	<div align="center">
	
	<a href="./AllPlanServlet?pageno=1&id=<%=getDecoration_id%>">首页</a>&nbsp;&nbsp;
	<a href="./AllPlanServlet?pageno=<%=pageBean.getPageno() - 1%>&id=<%=getDecoration_id%>">上一页</a>&nbsp;&nbsp;
	<a href="./AllPlanServlet?pageno=<%=pageBean.getPageno() + 1%>&id=<%=getDecoration_id%>">下一页</a>&nbsp;&nbsp;
	<a href="./AllPlanServlet?pageno=<%=pageBean.getMaxpage()%>&id=<%=getDecoration_id%>"">尾页</a>&nbsp;&nbsp;第<%=pageBean.getPageno()%>页&nbsp;&nbsp;共<%=pageBean.getMaxpage()%>页
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
		src="/static/sea-modules/seajs/seajs/2.1.1/sea.js"></script>
	<script type="text/javascript"
		src="/static/sea_config.js?v=210509221611"></script>
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
