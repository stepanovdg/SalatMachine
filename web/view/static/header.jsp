<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--fmt:bundle basename="${applicationScope.config}"-->
<fmt:bundle basename="resources.config">
    <fmt:message key="HEADER.CSS" var="css"/>
    <fmt:message key="LOCALE" var="locl"/>
    <fmt:message key="SERVLET" var="serv"/>
    <fmt:message key="HEADER.JS1" var="js1"/>
    <fmt:message key="HEADER.JS2" var="js2"/>
    <fmt:message key="HEADER.SLIDE.IMAGE1" var="slim1"/>
    <fmt:message key="HEADER.SLIDE.IMAGE2" var="slim2"/>
    <fmt:message key="HEADER.SLIDE.IMAGE3" var="slim3"/>
    <fmt:message key="HEADER.SLIDE.IMAGE4" var="slim4"/>
</fmt:bundle>
<!--fmt:bundle basename="${applicationScope.message}"-->
<fmt:bundle basename="resources.messages">
    <fmt:message key="HEADER.SLIDE.IMAGE1DESCR" var="slim1descr"/>
    <fmt:message key="HEADER.SLIDE.IMAGE1DESCR" var="slim1descr"/>
    <fmt:message key="HEADER.SLIDE.IMAGE2DESCR" var="slim2descr"/>
    <fmt:message key="HEADER.SLIDE.IMAGE3DESCR" var="slim3descr"/>
    <fmt:message key="HEADER.LOGOUT" var="lgout"/>
    <fmt:message key="HEADER.LOGAS" var="logas"/>
</fmt:bundle>
<head>
    <link rel="stylesheet" href="${css}" type="text/css" media="screen"/>
   <%-- <script type="text/javascript">var _siteRoot = 'index.jsp',_root = 'index.jsp';</script>--%>
    <script type="text/javascript" src="${js1}"></script>
    <script type="text/javascript" src="${js2}"></script>
</head>
<body>

<!--/top-->
<div id="header">
    <div class="wrap">
        <div id="slide-holder">
            <div id="slide-runner">
                <a href=""><img id="slide-img-1" src="${slim1}" class="slide" alt="${slim1descr}"/></a>
                <a href=""><img id="slide-img-2" src="${slim2}" class="slide" alt="${slim2descr}"/></a>
                <a href=""><img id="slide-img-3" src="${slim3}" class="slide" alt="${slim3descr}"/></a>
                <a href=""><img id="slide-img-4" src="${slim4}" class="slide" alt="${slim4descr}"/></a>

                <div id="slide-controls">
                    <p id="slide-client" class="text"><strong>post: </strong><span></span></p>

                    <p id="slide-desc" class="text"></p>

                    <p id="slide-nav"></p>
                </div>
            </div>

            <!--content featured gallery here -->
        </div>
        <script type="text/javascript">
            if (!window.slider) var slider = {};
            slider.data = [
                {"id":"slide-img-1","client":"nature beauty","desc":"${slim1descr}"},
                {"id":"slide-img-2","client":"nature beauty","desc":"${slim2descr}"},
                {"id":"slide-img-3","client":"nature beauty","desc":"${slim3descr}"},
                {"id":"slide-img-4","client":"nature beauty","desc":"${slim4descr}"}
            ];
        </script>
    </div>
</div>
</body>
<jsp:include page="${locl}"/>
<c:if test="${user!=null}">
    <div style="background-color: #000025; padding: 5px">
        <div style="float: right; margin-right: 5px">[ <a
                href="<c:url value="${serv}"/>">${lgout}</a> ]
        </div>
        ${logas} <c:out value="${user.getLogin()}"/> ( <c:out
            value="${user.getType()}"/> )
    </div>
</c:if>