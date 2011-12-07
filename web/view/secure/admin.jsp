<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--fmt:bundle basename="${applicationScope.config}"-->
<fmt:bundle basename="resources.config">
    <fmt:message key="HEADER" var="head"/>
    <fmt:message key="FOOTER" var="foot"/>
    <fmt:message key="SERVLET" var="serv"/>
</fmt:bundle>
<!--fmt:bundle basename="${applicationScope.message}"-->
<fmt:bundle basename="resources.messages">
    <fmt:message key="ADMIN.MAIN" var="main"/>
    <fmt:message key="ADMIN.TITLE" var="title"/>
    <fmt:message key="ADMIN.GREETING" var="greet"/>
</fmt:bundle>
<html>
<head><title>${title}</title></head>
<body>
<jsp:include page="${head}"/>
<jsp:useBean id="now" class="java.util.Date"/>
<h3>${title}</h3>
<hr/>
<!-- //todo -3 21 57 -->
<c:out value="${greet}, ${user.getLogin()}"/>
<hr/>
<fmt:timeZone value="${sessionScope.timezone}">
    <fmt:formatDate value="${now}" type="both"
                    dateStyle="full" timeStyle="full"/><br/>
</fmt:timeZone>
<hr/>
<a href="${serv}">${main}</a>
<jsp:include page="${foot}"/>
</body>
</html>