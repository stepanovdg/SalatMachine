<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cbck" uri="http://controller.salatmachine.bsu.by.tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--fmt:bundle basename="${applicationScope.config}"-->
<fmt:bundle basename="resources.config">
    <fmt:message key="HEADER.CSS" var="css"/>
    <fmt:message key="MENU.JS" var="menujs"/>
    <fmt:message key="MENU.CSS" var="menucss"/>
    <fmt:message key="HEADER" var="head"/>
    <fmt:message key="FOOTER" var="foot"/>
    <fmt:message key="SERVLET" var="serv"/>
</fmt:bundle>
<!--fmt:bundle basename="${applicationScope.message}"-->
<fmt:bundle basename="resources.messages">
    <fmt:message key="ADMIN.MAIN" var="main"/>
    <fmt:message key="ADMIN.TITLE" var="title"/>
    <fmt:message key="ADMIN.GREETING" var="greet"/>
    <fmt:message key="ADMIN.VISIBILBUTTON" var="visibil"/>
    <fmt:message key="ADMIN.LOGMESSAGE" var="logmessage"/>

</fmt:bundle>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}${css}" type="text/css" media="screen"/>
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
                    dateStyle="full" timeStyle="full"/><br/>
</fmt:timeZone>
<hr/>
<form name="receptStorage" method="POST" action=${serv}>
    <input type="hidden" name="command"
           value="receptst"/>
    <input type="submit" value="${visibil}">
</form>
<hr/>
<a href="${serv}">${main}</a>
<cbck:logg level="info" configFile="log4j.properties">
   ${logmessage}
</cbck:logg>
<jsp:include page="${foot}"/>
</body>
</html>