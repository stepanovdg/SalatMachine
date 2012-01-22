<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="csbb" uri="http://controller.salatmachine.bsu.by.tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:bundle basename="resources.config">
    <fmt:message key="HEADER.CSS" var="css"/>
    <fmt:message key="GMT.SERVER" var="gmtserv"/>
    <fmt:message key="HEADER.LANGUAGE.IMAGE1" var="lang1"/>
    <fmt:message key="HEADER.LANGUAGE.IMAGE2" var="lang2"/>
    <fmt:message key="HEADER.LANGUAGE.IMAGE3" var="lang3"/>
    <fmt:message key="HEADER.LANGUAGE.ENGLISH" var="eng"/>
    <fmt:message key="HEADER.LANGUAGE.RUSSIAN" var="rus"/>
    <fmt:message key="HEADER.LANGUAGE.GERMAN" var="ger"/>
    <fmt:message key="SERVLET" var="serv"/>

</fmt:bundle>
<fmt:bundle basename="resources.messages">
    <fmt:message key="GMT.LOCALE" var="gmtlocl"/>
    <fmt:message key="LOCALE.NAME.USA" var="usname"/>
    <fmt:message key="LOCALE.NAME.GERMANY" var="dename"/>
    <fmt:message key="LOCALE.NAME.RUSSIA" var="runame"/>
    <fmt:message key="HEADER.LOGOUT" var="lgout"/>
    <fmt:message key="HEADER.LOGAS" var="logas"/>
</fmt:bundle>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}${css}" type="text/css" media="screen"/>
</head>
<body>

<c:if test="${param['locale'] != null}">
    <fmt:setLocale value="${param['locale']}" scope="session"/>
    <c:set scope="session" var="timezone" value="${gmtlocl}"/>
</c:if>
<div id="language-holder">
    <div id="user">
        <c:if test="${user!=null}">
            <div>
                    ${logas}
                    <a href="${serv}">${user.getLogin()}</a> ( <c:out
                    value="${user.getType()} ${user.getMoney()}"/> )
            </div>
            <div>
                <form action="${serv}" method="POST">
                    <input type="hidden" name="command" value="unlogin"/>
                    <input type="submit" value="${lgout}">
                </form>
            </div>

        </c:if>
    </div>
    <div id="language-image">
        <a href="${pageContext.request.requestURL}${eng}${gmtlocl}">
            <img id="language-img-1" src="${pageContext.request.contextPath}${lang1}" alt="${usname}"/>
        </a>
        <a href="${pageContext.request.requestURL}${ger}${gmtlocl}">
            <img id="language-img-2" src="${pageContext.request.contextPath}${lang2}" alt="${dename}"/>
        </a>
        <a href="${pageContext.request.requestURL}${rus}${gmtlocl}">
            <img id="language-img-3" src="${pageContext.request.contextPath}${lang3}" alt="${runame}"/>
        </a>
    </div>
</div>
</body>
</html>