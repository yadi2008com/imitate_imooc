<%@page import="com.etc.bean.CourseBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0021)http://www.imooc.com/ -->
<html><head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>慕课网-国内最大的IT技能学习平台</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="renderer" content="webkit">
<meta property="qc:admins" content="77103107776157736375">
<meta property="wb:webmaster" content="c4f857219bfae3cb">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="Keywords" content="慕课网,慕课,IT在线学习,在线编程,MOOC,IT技能,IT在线教育,it技能培训,免费编程视频,在线编程学习,php开发,IT在线视频教程,css教程,it在线编程,慕课官网">
<meta name="Description" content="慕课网（IMOOC）是国内最大的IT技能学习平台。慕课网提供了丰富的移动端开发、php开发、web前端、android开发以及html5等视频教程资源公开课。并且富有交互性及趣味性，你还可以和朋友一起编程。">

<meta name="360-site-verification" content="efec9ca9c0c2bf49b8f54f8ea4626ea8">
<meta name="baidu-site-verification" content="5150647909fe1d5bfb8d429afd444f80">



<script type="text/javascript" async="" src="Homr/Js/rt.js"></script><script type="text/javascript">
  var OP_CONFIG={"module":"index","page":"index"};
  var isLogin = 0;
  var seajsTimestamp="v=210509221611";
  </script>



<link rel="stylesheet" href="Home/Css/saved_resource" type="text/css">
<script charset="utf-8" async="" src="Home/Js/jquery.js"></script>
<script charset="utf-8" async="" src="Home/Js/seajs-text.js">
</script><script charset="utf-8" async="" src="Home/Js/common.js"></script>
<script charset="utf-8" async="" src="Home/Js/string.js"></script>
<script charset="utf-8" async="" src="Home/Js/suggest.js"></script>
<script charset="utf-8" async="" src="Home/Js/store.js"></script>
<script charset="utf-8" async="" src="Home/Js/json.js"></script>
<script charset="utf-8" async="" src="Home/Js/im.js"></script>
<script charset="utf-8" async="" src="Home/Js/index.js"></script>
<script charset="utf-8" async="" src="Home/Js/socket.io.min.js"></script>
</head>
<body id="index">

<%@ include file="head.jsp"%>

<div class="g-banner">
  <div class="g-banner-content">
    <div class="banner-slide slide-active" style="display: block; background-image: url(http://www.imooc.com/static/img/index/banner2.jpg);">
      <a href="javascript:;" style="cursor: default" class="click-help"></a>
      <div class="inner">
          <h3 class="banner-tit"></h3>
          <a href="javascript:void(0);" id="js-play-video" class="more">了解更多</a>
      </div>
    </div>
      </div>
  <div class="banner-dots"></div>
  <a href="javascript:;" class="banner-anchor prev" style="display: none">上一张</a>
  <a href="javascript:;" class="banner-anchor next" style="display: none">下一张</a>
</div>
<div id="js-index-video" class="video-container">
  <div class="video-wrap" id="js-video-wrap">
    <div id="js-video"></div>
  </div>
  <div class="video-mask"></div>
  <div id="js-video-close" class="video-close"></div>
</div>
<div id="characters" class="characters idx-minwidth">
  <div class="idx-width">
    <div class="characters-wrap">
      <img src="Home/Images/banner3.png">
    </div>
  </div>
</div>
<div id="intro1" class="intro intro1 bg-grey idx-minwidth">
  <div class="intro1-wrap idx-width">
   <img src="Home/Images/banner4.png">
  </div>
</div>
<div id="intro2" class="intro intro2 bg-white idx-minwidth">
  <div class="intro2-wrap idx-width">
    <img src="Home/Images/banner5.png">
  </div>
</div>
<div id="intro3" class="intro intro3 bg-grey idx-minwidth">
  <div class="intro3-wrap idx-width">
    <img src="Home/Images/banner6.png">
  </div>
</div>
<div id="intro4" class="intro intro4 bg-white idx-minwidth">
  <div class="intro4-wrap idx-width">
    <img src="Home/Images/banner7.png">
  </div>
</div>
<div id="mooc-dynamic" class="dynamic bg-grey idx-minwidth">
  <div id="dynamic-wrap" class="dynamic-wrap idx-width">
    <div id="dynamic-left" class="dynamic-left"></div>
    <div id="dynamic-right" class="dynamic-right"></div>

          
      <ul class="dynamic-group clearfix">


      
        <li><a href="http://www.imooc.com/activity/javaerindex" target="_blank"><img src="Home/Images/55682623000179d404000200.jpg" alt="java学习专题"></a></li>
                
        <li><a href="http://www.imooc.com/wenda/detail/288042"><img src="Home/Images/5600b66b0001eb4f04000200.jpg" alt="写书评赢Android群英传 你敢写 我敢送"></a></li>
                
        <li><a href="http://www.ituring.com.cn/book/1605" target="_blank"><img src="Home/Images/556e52720001505804000200.jpg" alt="图灵合作图书推广"></a></li>
            </ul>
          
  </div>
</div>
<div class="icourse" >
  <div class="incourse-wrap idx-width" >
    <h2 class="icourse-title hide-text">课程</h2>
   
    <ul class="icourse-course clearfix">
        
      <%
      List<CourseBean> courseBeans=(List<CourseBean>)request.getAttribute("courseBeans");
      if(courseBeans!=null){
      for(CourseBean courseBean :courseBeans){
      %>
          <li>
                
                <a href="http://www.imooc.com/view/500">
                
          <div class="icourse-img">
            <img src="CourseImages/<%=courseBean.getCour_image()%>" alt="">
          </div>
                  <div class="icourse-intro clearfix">
            <p>本课程通过代码实践实现Android中的短信验证。</p>
            <span class="l icourse-new">
                              更新至4-1
                          </span>
            <span class="r">
              课程时长： 1小时 9分
            </span>
          </div>
          <div class="icourse-tips clearfix">
            <h2><%=courseBean.getCour_title() %></h2>
            <span class="l icourse-new">6小时前更新</span>
            <span class="r">5882人学习</span>
          </div>
        </a>
      </li>
        <%}}else{%>
        	<li>
            
            <a href="http://www.imooc.com/view/500">
            
      <div class="icourse-img">
        <img src="Home/Images/55f29e040001935a06000338-280-160.jpg" alt="">
      </div>
              <div class="icourse-intro clearfix">
        <p>本课程通过代码实践实现Android中的短信验证。</p>
        <span class="l icourse-new">
                          更新至4-1
                      </span>
        <span class="r">
          课程时长： 1小时 9分
        </span>
      </div>
      <div class="icourse-tips clearfix">
        <h2>无信息</h2>
        <span class="l icourse-new">6小时前更新</span>
        <span class="r">5882人学习</span>
      </div>
    </a>
  </li>
     <%    } %>
        </ul>
    <div class="icourse-footer">
      <a href="./courseList.jsp">全部课程</a>
       <ul class="icourse-course clearfix">
        
      <%
     
      if(courseBeans!=null){
      for(CourseBean courseBean :courseBeans){
      %>
          <li>
                
                <a href="http://www.imooc.com/view/500">
                
          <div class="icourse-img">
            <img src="CourseImages/<%=courseBean.getCour_image()%>" alt="">
          </div>
                  <div class="icourse-intro clearfix">
            <p>本课程通过代码实践实现Android中的短信验证。</p>
            <span class="l icourse-new">
                              更新至4-1
                          </span>
            <span class="r">
              课程时长： 1小时 9分
            </span>
          </div>
          <div class="icourse-tips clearfix">
            <h2><%=courseBean.getCour_title() %></h2>
            <span class="l icourse-new">6小时前更新</span>
            <span class="r">5882人学习</span>
          </div>
        </a>
      </li>
        <%}}else{%>
        	<li>
            
            <a href="http://www.imooc.com/view/500">
            
      <div class="icourse-img">
        <img src="Home/Images/55f29e040001935a06000338-280-160.jpg" alt="">
      </div>
              <div class="icourse-intro clearfix">
        <p>本课程通过代码实践实现Android中的短信验证。</p>
        <span class="l icourse-new">
                          更新至4-1
                      </span>
        <span class="r">
          课程时长： 1小时 9分
        </span>
      </div>
      <div class="icourse-tips clearfix">
        <h2>无信息</h2>
        <span class="l icourse-new">6小时前更新</span>
        <span class="r">5882人学习</span>
      </div>
    </a>
  </li>
     <%    } %>
        </ul>
      
       

  <div id="js-family-text" class="family-text hide-text">慕课大家庭</div>
</div>
<div class="mobile idx-minwidth">
  <div class="mobile-wrap idx-width">
    <h2 class="mobile-title hide-text">手机慕课，随时随地学习！</h2>
    <div class="mobile-entry">
      <a href="http://www.imooc.com/mobile/app" target="_blank"><!-- Android版 -->手机版</a>
      <a href="http://www.imooc.com/mobile/app?curr=ipad" target="_blank"><!-- IOS版 -->iPad版</a>
      <span id="js-mobile-trigger" class="mobile-trigger"></span>
    </div>
    <div class="mk-mobile-bg" id="J-mk-mobile-bg"></div>
    <div class="mk-down-qrcode" id="J-mk-down-qrcode"></div>
  </div>
</div>

<div class="footer bg-white idx-minwidth">
  <div class="container">
    <div class="footer-wrap idx-width">
      <div class="footer-sns">
        <a href="http://weibo.com/u/3306361973" class="footer-sns-weibo hide-text" target="_blank" title="新浪微博">新浪微博</a>
        <a href="javascript:void(0);" class="footer-sns-weixin" target="_blank" title="微信">
          <i class="footer-sns-weixin-expand"></i>
        </a>
        <a href="http://t.qq.com/mukewang999" class="footer-sns-qqweibo hide-text" target="_blank" title="腾讯微博">腾讯微博</a>
        <a href="http://user.qzone.qq.com/1059809142/" class="footer-sns-qzone hide-text" target="_blank" title="QQ空间">QQ空间</a>
      </div>
    </div>
    <div class="footer-link">
      <a href="http://www.imooc.com/about/us" title="关于我们">关于我们</a>
      <a href="http://www.imooc.com/about/job" title="人才招聘">人才招聘</a>
      <a href="http://www.imooc.com/about/recruit" title="讲师招募">讲师招募</a>
      <a href="http://www.imooc.com/about/contact" title="联系我们">联系我们</a>
      <a href="http://www.imooc.com/user/feedback" title="意见反馈">意见反馈</a>
    </div>
        <div class="friendly-link">
      <span>友情链接：</span>
            <a href="http://hao.360.cn/" target="_blank" title="360导航">360导航</a>
            <a href="http://www.php-z.com/" target="_blank" title="PHP站中文网">PHP站中文网</a>
            <a href="http://www.hao123.com/" target="_blank" title="hao123">hao123</a>
            <a href="http://www.lagou.com/" target="_blank" title="拉勾网">拉勾网</a>
            <a href="http://www.admin10000.com/" target="_blank" title="web开发者">web开发者</a>
            <a href="http://www.imooc.com/about/friendly" title="友情链接">更多...</a>
    </div>
        <div class="footer-copyright">
      Copyright © 2015 imooc.com All Rights Reserved | 京ICP备 13046642号-2
    </div>
    <div style="text-align:center;margin-top:-10px;padding-bottom:10px;">
      <!--可信网站图片LOGO安装开始-->
      <script src="Home/Js/seallogo.dll"></script><a href="https://ss.knet.cn/verifyseal.dll?sn=e15030311010657818aeau000000&ct=df&a=1&pa=0.42705348413437605" target="_blank" kx_type="缩放式" style="display:inline-block;position:relative;width:89px;height:32px;"><img src="Home/Images/cnnic.png" height="32" width="89" h="32" w="89" onmouseover="showFull(this)" onmouseout="showMin(this)" style="left:0;position:absolute;top:0;border:none;"></a>
      <!--可信网站图片LOGO安装结束-->
    </div>
  </div>
</div>






<!--script-->
<script type="text/javascript" src="Home/Js/sea.js"></script>
<script type="text/javascript" src="Home/Js/sea_config.js"></script>
<script type="text/javascript">seajs.use("/static/page/"+OP_CONFIG.module+"/"+OP_CONFIG.page);</script>





<div style="display: none">
<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Ff0cfcccd7b1393990c78efdeebff3968' type='text/javascript'%3E%3C/script%3E"));
(function (d) {
window.bd_cpro_rtid="rHT4P1c";
var s = d.createElement("script");s.type = "text/javascript";s.async = true;s.src = location.protocol + "//cpro.baidu.com/cpro/ui/rt.js";
var s0 = d.getElementsByTagName("script")[0];s0.parentNode.insertBefore(s, s0);
})(document);
</script><script src="Home/Js/h.js" type="text/javascript"></script><a href="http://tongji.baidu.com/hm-web/welcome/ico?s=f0cfcccd7b1393990c78efdeebff3968" target="_blank"><img border="0" src="Home/Js/21.gif" width="20" height="20"></a>
</div>


</body></html>
<script   language='javascript'>   
      function   test(){   
          window.open(<%=request.getContextPath()%>/CourseShowNewServlet);  
                                                    
      } 
      </script>