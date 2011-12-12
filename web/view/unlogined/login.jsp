<%@ taglib prefix="cbck" uri="http://controller.salatmachine.bsu.by.tags/loggertag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html;
        charset=utf-8" pageEncoding="utf-8" %>
<!--fmt:bundle basename="${applicationScope.config}"-->
<fmt:bundle basename="resources.config">
    <fmt:message key="HEADER" var="head"/>
    <fmt:message key="FOOTER" var="foot"/>
    <fmt:message key="SERVLET" var="serv"/>
    <fmt:message key="LOGIN.COMMAND" var="comm"/>
</fmt:bundle>
<!--fmt:bundle basename="${applicationScope.message}"-->
<fmt:bundle basename="resources.messages">
    <fmt:message key="LOGIN.TITLE" var="title"/>
    <fmt:message key="LOGIN.LOGIN" var="lgn"/>
    <fmt:message key="LOGIN.PASSWORD" var="pswd"/>
</fmt:bundle>
<html>
<head><title>${title}</title></head>
<body>
<jsp:include page="${head}"/>
<h3>${title}</h3>
<hr/>
<form name="loginForm" method="POST"
      action=${serv}>
    <input type="hidden" name="command" value="login"/>
    ${lgn}<br/>
    <input type="text" name="login" value="">
    <br/>
    ${pswd}
    <br/>
    <input type="password" name="password" value="">
    <br/>
    <input type="submit" value="Enter">
</form>
<hr/>
<cbck:log level="info" configFile="log4j.properties">
Debug message from logger.jsp //todo
</cbck:log>
<jsp:include page="${foot}"/>
</body>
</html>