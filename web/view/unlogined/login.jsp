<%@ taglib prefix="cbck" uri="http://controller.salatmachine.bsu.by.tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;
        charset=utf-8" pageEncoding="utf-8" %>

<fmt:bundle basename="resources.config">
    <fmt:message key="HEADER.CSS" var="css"/>
    <fmt:message key="HEADER" var="head"/>
    <fmt:message key="FOOTER" var="foot"/>
    <fmt:message key="SERVLET" var="serv"/>
    <fmt:message key="LOGIN.COMMAND" var="comm"/>
</fmt:bundle>
<fmt:bundle basename="resources.messages">
    <fmt:message key="LOGIN.TITLE" var="title"/>
    <fmt:message key="LOGIN.LOGIN" var="lgn"/>
    <fmt:message key="LOGIN.PASSWORD" var="pswd"/>
    <fmt:message key="LOGIN.ENTERBUTTON" var="enter"/>
</fmt:bundle>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}${css}" type="text/css" media="screen"/>
</head>
<body>
<jsp:include page="${head}"/>
<h3>${title}</h3>
<c:out value="${applicationScope.initParam['config']}"/>
<hr/>
<form name="loginForm" method="POST"
      action=${serv}>
    <input type="hidden" name="command" value="login"/>
    ${lgn}<br/>
    <label>
        <input type="text" name="login" value="">
    </label>
    <br/>
    ${pswd}
    <br/>
    <label>
        <input type="password" name="password" value="">
    </label>
    <br/>
    <input type="submit" value="${enter}">
</form>
<hr/>
<jsp:include page="${foot}"/>
</body>
</html>