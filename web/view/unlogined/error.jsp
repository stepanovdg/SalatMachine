<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html;
        charset=utf-8" pageEncoding="utf-8" isErrorPage="true" %>
<!--fmt:bundle basename="${applicationScope.config}"-->
<fmt:bundle basename="resources.config">
    <fmt:message key="HEADER.CSS" var="css"/>
    <fmt:message key="HEADER" var="head"/>
    <fmt:message key="FOOTER" var="foot"/>
    <fmt:message key="SERVLET" var="serv"/>
    <fmt:message key="ERROR.REQUEST_ATTRIBUTE" var="reqatr"/>
</fmt:bundle>
<!--fmt:bundle basename="${applicationScope.message}"-->
<fmt:bundle basename="resources.messages">
    <fmt:message key="ERROR.MAIN" var="main"/>
    <fmt:message key="ERROR.UNKNOWN" var="unknown"/>
    <fmt:message key="ERROR.TITLE" var="title"/>
</fmt:bundle>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}${css}" type="text/css" media="screen"/>
</head>
<body>
<jsp:include page="${head}"/>
<h3>${title}</h3>
<hr/>
${pageContext.errorData.requestURI}+${pageContext.errorData.servletName}+${pageContext.errorData.statusCode}
<hr/>
<jsp:expression>
    (request.getAttribute("errorMessage") != null)
    ? (String) request.getAttribute("errorMessage")
    : ${pageContext.errorData.throwable}
</jsp:expression>
<hr/>
<a href="${serv}">${main}</a>
<jsp:include page="${foot}"/>
</body>
</html>
