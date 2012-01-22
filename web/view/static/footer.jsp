<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<fmt:bundle basename="resources.config">
    <fmt:message key="HEADER.CSS" var="css"/>
</fmt:bundle>
<fmt:bundle basename="resources.messages">
    <fmt:message key="FOOTER.COPYRIGHT" var="cp"/>
</fmt:bundle>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}${css}" type="text/css" media="screen"/>
</head>
<div id="footer">
    <div>
        &copy; ${cp}
    </div>
</div>