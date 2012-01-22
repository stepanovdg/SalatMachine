<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;
        charset=utf-8" pageEncoding="utf-8"  %>
<fmt:bundle basename="resources.config">
    <fmt:message key="HEADER" var="head"/>
    <fmt:message key="FOOTER" var="foot"/>
    <fmt:message key="SERVLET" var="serv"/>
</fmt:bundle>
<fmt:bundle basename="resources.messages">
    <fmt:message key="INDEX.MAIN" var="main"/>
    <fmt:message key="INDEX.TITLE" var="title"/>
</fmt:bundle>
<html>
<head><title>${title}</title></head>
<body>
<jsp:include page="${head}"/>
<a href="${serv}">${main}</a>
<jsp:include page="${foot}"/>
</body>
</html>
