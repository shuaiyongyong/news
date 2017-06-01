<%@page import="com.yc.news.util.DbHelper"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="/News/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新闻中国</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="header">
  <div id="top_login">
		<a href='login.jsp' >后台管理 </a>
    <img src="images/friend_logo.gif" alt="Google" id="friend_logo" /> </div>
  <div id="nav">
    <div id="logo"> <img src="images/logo.jpg" alt="新闻中国" /> </div>
    <div id="a_b01"> <img src="images/a_b01.gif" alt="" /> </div>
    <!--mainnav end--> 
  </div>
</div>
<div id="container">
  <div class="sidebar">
    <h1> <img src="images/title_1.gif" alt="国内新闻" /> </h1>
    <div class="side_list">
      <ul id="china">
      </ul>
    </div>
    <h1> <img src="images/title_2.gif" alt="国际新闻" /> </h1>
    <div class="side_list">
      <ul id="inter">
      </ul>
    </div>
    <h1> <img src="images/title_3.gif" alt="娱乐新闻" /> </h1>
    <div class="side_list">
      <ul id="happy">
      </ul>
    </div>
  </div>
  <div class="main">
    <div class="class_type"> <img src="images/class_type.gif" alt="新闻中心" /> </div>
    <div class="content">
      <ul class="class_date">
        <li id="class_month" ></li>
      </ul>
      <ul class="classlist" id="newsList">
      </ul>
      <p align="right" id="pagation"></p>
    </div>
    <div class="picnews">
      <ul>
        <li><a href="#"><img src="images/Picture1.jpg" width="249"
							alt="" /> </a><a href="#">幻想中穿越时空</a></li>
        <li><a href="#"><img src="images/Picture2.jpg" width="249"
							alt="" /> </a><a href="#">国庆多变的发型</a></li>
        <li><a href="#"><img src="images/Picture3.jpg" width="249"
							alt="" /> </a><a href="#">新技术照亮都市</a></li>
        <li><a href="#"><img src="images/Picture4.jpg" width="249"
							alt="" /> </a><a href="#">群星闪耀红地毯</a></li>
      </ul>
    </div>
  </div>
</div>
<div id="friend">
  <h1 class="friend_t"> <img src="images/friend_ico.gif" alt="合作伙伴" /> </h1>
  <div class="friend_list">
    <ul>
      <li><a href="#">中国政府网</a></li>
      <li><a href="#">中国政府网</a></li>
      <li><a href="#">中国政府网</a></li>
      <li><a href="#">中国政府网</a></li>
      <li><a href="#">中国政府网</a></li>
      <li><a href="#">中国政府网</a></li>
      <li><a href="#">中国政府网</a></li>
    </ul>
  </div>
</div>
<div id="footer">
  <p class=""> 24小时客户服务热线：010-68988888 &#160;&#160;&#160;&#160; <a href="#">常见问题解答</a> &#160;&#160;&#160;&#160; 新闻热线：010-627488888 <br />
    文明办网文明上网举报电话：010-627488888 &#160;&#160;&#160;&#160; 举报邮箱： <a href="#">jubao@jb-aptech.com.cn</a> </p>
  <p class="copyright"> Copyright &copy; 1999-2009 News China gov, All Right Reserver <br />
    新闻中国 版权所有 </p>
</div>
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/index.js">	</script>
</body>
</html>