<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html;
        charset=utf-8" pageEncoding="utf-8" %>
<!--fmt:bundle basename="${applicationScope.config}"-->
<fmt:bundle basename="resources.config">
    <fmt:message key="HEADER" var="head"/>
    <fmt:message key="TABLE.CSS" var="tcss"/>
    <fmt:message key="FOOTER" var="foot"/>
    <fmt:message key="SERVLET" var="serv"/>
</fmt:bundle>
<!--fmt:bundle basename="${applicationScope.message}"-->
<fmt:bundle basename="resources.messages">
    <fmt:message key="MAIN.MAIN" var="main"/>
    <fmt:message key="MAIN.TITLE" var="title"/>
    <fmt:message key="MAIN.GREETING" var="greet"/>
    <fmt:message key="MAIN.RECEPTS" var="rec"/>
    <fmt:message key="MAIN.MONEY" var="mon"/>
</fmt:bundle>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}${tcss}" type="text/css"/>
</head>
<body>
<jsp:include page="${head}"/>
<jsp:useBean id="now" class="java.util.Date"/>
<h3>${title}</h3>
<hr/>
<c:out value="${greet}, ${user.getLogin()}"/>
<hr/>
<fmt:timeZone value="${sessionScope.timezone}">
    <fmt:formatDate value="${now}" type="both"
                    dateStyle="full"
                    timeStyle="full"/><br/>
</fmt:timeZone>
<hr/>
<form name="receptStorage" method="POST" action=${serv}>
    <input type="hidden" name="command"
           value="receptst"/>
    <input type="submit" value="${rec}">
</form>
<form name="moneyAmount" method="POST" action=${serv}>
    <input type="hidden" name="command"
           value="moneyAmount"/>
    <input type="submit" value="${mon}">
</form>
<hr/>
<a href="${serv}">${main}</a>

<jsp:include page="${foot}"/>
</body>
</html>
