<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <head>
  <title>jquery sliders</title>
  <link rel="stylesheet" href="/css/style1.css" type="text/css" media="screen" />
  <script type="text/javascript">var _siteRoot='index.html',_root='index.html';</script>
  <script type="text/javascript" src="/js/jquery1.js"></script>
  <script type="text/javascript" src="/js/scripts1.js"></script>
 </head>
<body>

  <!--/top-->
  <div id="header"><div class="wrap">
   <div id="slide-holder">
<div id="slide-runner">
    <a href=""><img id="slide-img-1" src="/view/images/colorful_peppers.jpg" class="slide" alt="" /></a>
    <a href=""><img id="slide-img-2" src="/view/images/tomatoes.jpg" class="slide" alt="" /></a>
    <a href=""><img id="slide-img-3" src="/view/images/lemon_slices.jpg" class="slide" alt="" /></a>
    <a href=""><img id="slide-img-4" src="/view/images/food_039.jpg" class="slide" alt="" /></a>
    <div id="slide-controls">
     <p id="slide-client" class="text"><strong>post: </strong><span></span></p>
     <p id="slide-desc" class="text"></p>
     <p id="slide-nav"></p>
    </div>
</div>

	<!--content featured gallery here -->
   </div>
   <script type="text/javascript">
    if(!window.slider) var slider={};slider.data=[{"id":"slide-img-1","client":"nature beauty","desc":"nature beauty photography"},{"id":"slide-img-2","client":"nature beauty","desc":"add your description here"},{"id":"slide-img-3","client":"nature beauty","desc":"add your description here"},{"id":"slide-img-4","client":"nature beauty","desc":"add your description here"}];
   </script>
  </div></div><!--/header-->
 </body>

<%-- Обработать параметр сортировки --%>
<c:if test="${param.sort!=null}">
	<c:set var="sort" scope="session" value="${param.sort}" />
</c:if>
<%-- Обработать параметр направления сортировки --%>
<c:if test="${param.dir!=null}">
	<c:set var="dir" scope="session" value="${param.dir}" />
</c:if>
<%-- Общая декоративная "шапка" для всех страниц --%>
<jsp:include page="/view/static/header1.jsp"/>
<!--div style="background-color: #a0c8ff; padding: 10px;"><img
	src="/view/images/salat4.jpeg" width="93" height="69" border="1"
	align="left">
<div
	style="font-family: 'Trebuchet'; font-size: 30px; height: 69px; margin-left: 120px;">
EpamWebProject "SalatMachine" v.1.0.0</div>
</div-->
<%-- Панель отображается если пользователь аутентифицирован --%>
<c:if test="${user!=null}">
	<div style="background-color: #ccc; padding: 5px">
	<div style="float: right; margin-right: 5px">[ <a
		href="<c:url value="/controller" />">Logout</a> ]</div>
	You login as <c:out value="${user}" /> ( <c:out
		value="${user}" /> )</div>
</c:if>