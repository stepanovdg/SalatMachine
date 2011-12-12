<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--fmt:bundle basename="${applicationScope.config}"-->
<fmt:bundle basename="resources.config">
    <fmt:message key="HEADER.CSS" var="css"/>
    <fmt:message key="GMT.SERVER" var="gmtserv"/>
    <fmt:message key="HEADER.LANGUAGE.IMAGE1" var="lang1"/>
        <fmt:message key="HEADER.LANGUAGE.IMAGE2" var="lang2"/>
        <fmt:message key="HEADER.LANGUAGE.IMAGE3" var="lang3"/>

</fmt:bundle>
<!--fmt:bundle basename="${applicationScope.message}"-->
<fmt:bundle basename="resources.messages">
    <fmt:message key="GMT.LOCALE" var="gmtlocl"/>
    <fmt:message key="LOCALE.NAME.USA" var="usname"/>
    <fmt:message key="LOCALE.NAME.GERMANY" var="dename"/>
    <fmt:message key="LOCALE.NAME.RUSSIA" var="runame"/>
</fmt:bundle>
<html>
<head>
    <link rel="stylesheet" href="${css}" type="text/css" media="screen"/>
</head>
<body>
<c:if test="${param['locale'] != null}">
    <fmt:setLocale value="${param['locale']}" scope="session"/>
    <c:set scope="session" var="timezone" value="${gmtlocl}"/>
</c:if>
<div id="language-holder">
    <div id ="language-image">
    <a href="${pageContext.request.requestURL}?locale=en-US&timezone=${gmtlocl}">
        <img id="language-img-1" src="${lang1}" alt="${usname}"/>
    </a>

    <a href="${pageContext.request.requestURL}?locale=de-DE&timezone=${gmtlocl}">
        <img id="language-img-2" src="${lang2}" alt="${dename}"/>
    </a>
    <a href="${pageContext.request.requestURL}?locale=ru-RU&timezone=${gmtlocl}">
        <img id="language-img-3" src="${lang3}" alt="${runame}"/>
    </a>
    </div>
</div>

<!--c:choose>
<!c:when test="${param['timezone'] != null}">
  <!c:set scope="session" var="timezone" value="${param['timezone']}"/>
<!/c:when>
<!c:otherwise>
  <!c:set scope="session" var="timezone" value="${gmtserv}"/>
<!/c:otherwise>
<!/c:choose-->
</body>
</html>