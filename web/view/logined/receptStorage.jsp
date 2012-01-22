<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html;
        charset=utf-8" pageEncoding="utf-8" %>
<fmt:bundle basename="resources.config">
    <fmt:message key="HEADER.CSS" var="css"/>
    <fmt:message key="HEADER" var="head"/>
    <fmt:message key="FOOTER" var="foot"/>
    <fmt:message key="SERVLET" var="serv"/>
    <fmt:message key="TABLE.CSS" var="tcss"/>
    <fmt:message key="RECEPTSTORAGE_PAGE_PATH" var="thisPage"/>
</fmt:bundle>
<fmt:bundle basename="resources.messages">
    <fmt:message key="MAIN.MAIN" var="main"/>
    <fmt:message key="RECEPT.TITLE" var="title"/>
    <fmt:message key="MAIN.GREETING" var="greet"/>
    <fmt:message key="PECEPTST.TABLETITLE.NUMBER" var="number"/>
    <fmt:message key="PECEPTST.TABLETITLE.NAME" var="name"/>
    <fmt:message key="PECEPTST.TABLETITLE.AUTHOR" var="author"/>
    <fmt:message key="RECEPTST.DETAILSBUTTON" var="details"/>
    <fmt:message key="RECEPTST.DELETEBUTTON" var="delete"/>
    <fmt:message key="RECEPTST.ADDBUTTON" var="add"/>
    <fmt:message key="RECEPTST.NEWBUTTON" var="new"/>
    <fmt:message key="RECEPTST.REFRESHBUTTON" var="refresh"/>
</fmt:bundle>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}${css}" type="text/css" media="screen"/>
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
                    dateStyle="full" timeStyle="full"/><br/>
</fmt:timeZone>
<hr/>
<table border="1" cellspacing=3 align="center">
    <TR>
        <TH>${number}</TH>
        <TH>${name}</TH>
        <TH>${author}</TH>
    </TR>
    <c:forEach items="${receptStorage.toArray()}" var="rec">
        <tr>
            <td align="center"><c:out value="${rec.getIdRecept()}"/></td>
            <td align="center">
                <c:out value="${rec.getName()}"/>
            </td>
            <td align="center"><c:out value="${rec.getVisibility()}"/></td>
            <td align="center">
                <form name="recept" method="POST" action=${serv}>
                    <input type="hidden" name="command" value="recept"/>
                    <input type="hidden" name="idRecept" value="${rec.getIdRecept()}">
                    <input type="submit" value="${details}">
                </form>
                <form name="recept" method="POST" action=${serv}>
                    <input type="hidden" name="command" value="deleteReceptSt"/>
                    <input type="hidden" name="idRecept" value="${rec.getIdRecept()}">
                    <input type="hidden" name="page" value="${thisPage}">
                    <input type="submit" value="${delete}">
                </form>
            </td>
        </tr>
    </c:forEach>
        <tr>
            <form name="receptst" method="POST" action="${serv}">
                <td align="center">
                    <input type="text" name="receptStName" value="">
                </td>
                <td align="center">
                    <input type="hidden" name="command" value="newReceptSt">
                    <input type="submit" value="${add}">
                </td>
            </form>
        </tr>
</table>

<table>
    <td>
        <form name="save" method="POST"
              action=${serv}>
            <input type="hidden" name="command" value="receptst"/>
            <input type="submit" value="${refresh}">
        </form>
    </td>
    <td>
        <form name="receptst" method="POST" action=${serv}>
            <input type="hidden" name="command" value="refresh"/>
            <input type="hidden" name="addReceptSt" value="true">
            <input type="hidden" name="page" value="${thisPage}">
            <input type="submit" value="${new}">
        </form>
    </td>
</table>
<hr/>
<a href="${serv}">${main}</a>
<jsp:include page="${foot}"/>
</body>
</html>
