<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:bundle basename="resources.config">
    <fmt:message key="HEADER.CSS" var="css"/>
</fmt:bundle>
<fmt:bundle basename="resources.messages">
    <fmt:message key="ERRORPANEL.DESCRIPTION" var="descr"/>
</fmt:bundle>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}${css}" type="text/css" media="screen"/>
</head>
<body>
<c:if test="${errorMessage != null}">
    <div id="error" >
        ${descr}<c:out value="${errorMessage}"/>
    </div>
    <c:remove var="errorMessage"/>
</c:if>
</body>
</html>