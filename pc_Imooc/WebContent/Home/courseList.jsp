<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.etc.bean.DecorationBean"%>
<%@page import="com.etc.bean.CourseBean"%>
<%@page import="com.etc.bean.PageBean"%>
<%@page import="com.etc.bean.LevelBean"%>
<%@page import="com.etc.bean.LanguageBean"%>
<%@ page language="java" import="java.util.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<%
	String cour_title=(String)request.getAttribute("cour_title");
	//得到方向信息列表
List<DecorationBean> decorationInfoList =(List<DecorationBean>)request.getAttribute("decorationInfoList");
//得到难度信息列表
List<LevelBean> levelBeanInfoList =(List<LevelBean>)request.getAttribute("levelBeanInfoList");
//得到语言信息列表
List<LanguageBean> languageInfoList =(List<LanguageBean>)request.getAttribute("languageInfoList");
//得到全部课程信息表
List<CourseBean> courseBeanInfoList =(List<CourseBean>)request.getAttribute("courseBeanInfoList");
//得到分页信息实例
PageBean pageBean=(PageBean)request.getAttribute("pageBean");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实战开发课程_IT培训精品课程_慕课网</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="renderer" content="webkit">
<meta property="qc:admins" content="77103107776157736375">
<meta property="wb:webmaster" content="c4f857219bfae3cb">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="Keywords" content="慕课网课程,IT培训课程,IT课程学习,开发课程">
<meta name="Description"
	content="慕课网精品课程，为您提供专业的IT实战开发课程，包含前端开发、后端开发、移动端开发、数据处理、图像处理等各方面IT技能，课程全面、制作精良、讲解通俗易懂。">
<script type="text/javascript" async="" src="Home/Js/rt.js"></script>
<script type="text/javascript">
	var OP_CONFIG = {
		"module" : "course",
		"page" : "list"
	};
	var isLogin = 0;
	var seajsTimestamp = "v=210509221611";
</script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Home/Css/saved_resource"
	type="text/css">
<script charset="utf-8" async="" src="./js/jquery.js"></script>
<script charset="utf-8" async="" src="./js/seajs-text.js">
	
</script>
<script charset="utf-8" async="" src="./js/common.js"></script>
<script charset="utf-8" async="" src="./js/string.js"></script>
<script charset="utf-8" async="" src="./js/suggest.js"></script>
<script charset="utf-8" async="" src="./js/store.js"></script>
<script charset="utf-8" async="" src="./js/json.js"></script>
<script charset="utf-8" async="" src="./js/im.js"></script>
<script charset="utf-8" async="" src="./js/index.js"></script>
<script charset="utf-8" async="" src="./js/socket.io.min.js"></script>
</head>
<body id="List_courseId">

	<%@ include file="head.jsp"%>
	<div id="main">

		<div class="container">
			<div class="course-content">
				<div class="course-nav-box">
					<div class="course-nav-hd">
						<span>全部课程</span>

					</div>
					<div class="course-nav-row clearfix">
						<span class="hd l">方向：</span>
						<div class="bd">
							<ul class="">
								<li class="course-nav-item on"><a href="CourseListServlet">全部</a>
								</li>

								<%
									for(DecorationBean decorationBean:decorationInfoList){
								%>

								<li class="course-nav-item"><a
									href="CourseListServlet?id=<%=decorationBean.getDecoration_id()%>&sort=decoration"><%=decorationBean.getDeco_name()%></a>
								</li>
								<%
									}
								%>
							</ul>
						</div>
					</div>
					<div class="course-nav-row clearfix">
						<span class="hd l">分类：</span>
						<div class="bd">
							<ul class="">
								<li class="course-nav-item on"><a href="CourseListServlet">全部</a>
								</li>
								<%
									for(LanguageBean languageBean:languageInfoList){
								%>
								<li class="course-nav-item "><a
									href="CourseListServlet?id=<%=languageBean.getLanguage_id()%>&sort=language"
									data-id="7" data-ct="fe"> <%=languageBean.getLang_name()%>
								</a></li>
								<%
									}
								%>

							</ul>
						</div>
					</div>
					<div class="course-nav-row clearfix">
						<span class="hd l">难度：</span>
						<div class="bd">
							<ul class="">
								<li class="course-nav-item  on"><a href="CourseListServlet">全部</a>
								</li>
								<%
									for(LevelBean levelBean:levelBeanInfoList){
								%>
								<li class="course-nav-item "><a
									href="CourseListServlet?id=<%=levelBean.getLevel_id()%>&sort=level">
										<%=levelBean.getLeve_name()%>
								</a></li>
								<%
									}
								%>

							</ul>
						</div>
					</div>
				</div>
				<div class="titleCourse">
					<div   class="titleCourseL"> 
						<a href="CourseListServlet?id=1&sort=time"
							class="sort-item active">最新</a> <a
							href="CourseListServlet?id=2&sort=hot" class="sort-item">最热</a> 
					</div>
<style> 
.divcss5-right{float:right;width:250px;height:50px;border:0px solid #F00} 
</style>
					<div class="divcss5-right" class="titleCourseR">
						<span class="tool-item total-num"> 共<b><%=request.getAttribute("result")!=null? request.getAttribute("result"):0%></b>个课程
						</span> <a href="CourseListServlet?pageno=1"><b>1</b></a> /<em
							class="pager-total"><%=pageBean.getMaxpage()%></em> 第<%=pageBean.getPageno()%>页
						<a <%if(request.getAttribute("select")!=null){%>
							href="CourseSelectServlet?pageno=<%=(pageBean.getPageno()-1)%>&cour_title=<%=cour_title%>"
							<%}else{%>
							href="CourseListServlet?pageno=<%=(pageBean.getPageno()-1)%>"
							<%}%> class="pager-action pager-prev hide-text disabled"> 上一页</a>
					  <a <%if(request.getAttribute("select")!=null){%>
							href="CourseSelectServlet?pageno=<%=(pageBean.getPageno()+1)%>&cour_title=<%=cour_title%>"
							<%}else{%>
							href="CourseListServlet?pageno=<%=(pageBean.getPageno()+1)%>"
							<%}%> class="pager-action pager-next hide-text"> 下一页 </a>

					</div>
				</div>
				<div class="course-list">

					<ul>


						<%
							if(courseBeanInfoList!=null && !courseBeanInfoList.isEmpty()){
								for(CourseBean courseBean:courseBeanInfoList){
						%>
						<li class="course-one"><a
							href="CourseShowByIdHomeServlet?course_id=<%=courseBean.getCourse_id()%>" target="_self">
								<div class="course-list-img">
									<img width="240" height="135" style="display: block;"
										alt="<%=courseBean.getCour_content()%>"
										src="CourseImages/<%=courseBean. getCour_image()%>">
								</div>
								<h5>
									<span><%=courseBean.getCour_title()%></span>
								</h5>
								<div class="tips">
									<p class="text-ellipsis"><%=courseBean.getCour_content()%></p>
									<span class="l update-latest">讲师：<%=courseBean.getCour_teacher()%></span>
									<span class="l ml20"><%=courseBean.getCour_hot()%>人学习</span>
								</div> <span class="time-label"> <%=courseBean.getCour_duration()%>
									| <%=courseBean.getLevel_name()%>
							</span>
						</a></li>
						<!-- <blockquote>&nbsp;</blockquote> -->
						<%
							}
						}
						%>
					</ul>

				</div>
			</div>
		</div>

	</div>




	<%@ include file="foot.jsp"%>
	<div id="J_GotoTop" class="elevator">
		<a class="elevator-weixin" href="javascript:;">
			<div class="elevator-weixin-box"></div>
		</a> <a class="elevator-msg" href="http://www.imooc.com/user/feedback"
			target="_blank" id="feedBack"></a> <a class="elevator-app"
			href="http://www.imooc.com/mobile/app">
			<div class="elevator-app-box"></div>
		</a> <a class="elevator-top" href="javascript:;" style="display: none;"
			id="backTop"></a>
	</div>



	<!--script-->
	<script type="text/javascript" src="./实战开发课程_IT培训精品课程_慕课网_files/sea.js"></script>
	<script type="text/javascript"
		src="./实战开发课程_IT培训精品课程_慕课网_files/sea_config.js"></script>
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
		<script src="./实战开发课程_IT培训精品课程_慕课网_files/h.js" type="text/javascript"></script>
		<a
			href="http://tongji.baidu.com/hm-web/welcome/ico?s=f0cfcccd7b1393990c78efdeebff3968"
			target="_blank"><img border="0"
			src="./实战开发课程_IT培训精品课程_慕课网_files/21.gif" width="20" height="20"></a>
	</div>


</body>
</html>