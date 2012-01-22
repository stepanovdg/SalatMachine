<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="csbb" uri="http://controller.salatmachine.bsu.by.tags" %>
<%@ page language="java" contentType="text/html;
        charset=utf-8" pageEncoding="utf-8" %>
<fmt:bundle basename="resources.config">
    <fmt:message key="HEADER" var="head"/>
    <fmt:message key="FOOTER" var="foot"/>
    <fmt:message key="SERVLET" var="serv"/>
    <fmt:message key="TABLE.CSS" var="tcss"/>
    <fmt:message key="HEADER.CSS" var="css"/>
    <fmt:message key="RECEPT_PAGE_PATH" var="thisPage"/>
</fmt:bundle>
<fmt:bundle basename="resources.messages">
    <fmt:message key="MAIN.MAIN" var="main"/>
    <fmt:message key="RECEPTSTORAGE.TITLE" var="title"/>
    <fmt:message key="MAIN.GREETING" var="greet"/>
    <fmt:message key="RECEPT.PRICEMESSAGE" var="recPrice"/>
    <fmt:message key="RECEPT.DELETEBUTTON" var="delete"/>
    <fmt:message key="RECEPT.NEWBUTTON" var="new"/>
    <fmt:message key="RECEPT.SAVEBUTTON" var="save"/>
    <fmt:message key="RECEPT.CHOOSEBUTTON" var="choose"/>
    <fmt:message key="PECEPT.TABLETITLE.ORDER" var="order"/>
    <fmt:message key="PECEPT.TABLETITLE.NAME" var="name"/>
    <fmt:message key="PECEPT.TABLETITLE.COUNT" var="count"/>
    <fmt:message key="PECEPT.TABLETITLE.PREPARATION" var="preparation"/>
    <fmt:message key="PECEPT.TABLETITLE.TIME" var="time"/>
</fmt:bundle>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}${css}" type="text/css" media="screen"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}${tcss}" type="text/css" media="screen"/>
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
<c:out value="${recPrice}"/>
<csbb:price recept="${recept}"/>
<hr/>
<table border="1" cellspacing=3 align="center">
    <TR>
        <TH>${order}</TH>
        <TH>${name}</TH>
        <TH>${count}</TH>
        <TH>${preparation}</TH>
        <TH>${time}</TH>
    </TR>
    ${recept.setCurrentPosition(0)}
    <c:forEach items="${recept.toArray()}" var="rec">
        <tr>
            <td align="center"><c:out value="${recept.getCurrentPosition()+1}"/></td>
            <td align="center"><c:out value="${rec.getVegetEnum()}"/></td>
            <td align="center"><c:out value="${rec.getCount()}"/></td>
            <td align="center"><c:out value="${rec.getProcess()}"/></td>
            <td align="center"><c:out value="${rec.getTime()}"/></td>
            <td align="center">
                <form name="recept" method="POST" action="${serv}">
                    <input type="hidden" name="command" value="deleteRec">
                    <input type="hidden" name="page" value="${thisPage}">
                    <input type="hidden" name="idOrderToDel" value="${recept.getCurrentPosition()}">
                    <input type="submit" value="${delete}">
                </form>
            </td>
                ${recept.setCurrentPosition(recept.getCurrentPosition()+1)}
        </tr>
    </c:forEach>
    <csbb:newRecept/>
</table>
<table>
    <td>
        <form name="recept" method="POST" action="${serv}">
            <input type="hidden" name="command" value="refresh">
            <input type="hidden" name="addRecept" value="true">
            <input type="hidden" name="page" value="${thisPage}">
            <input type="submit" value="${new}">
        </form>
    </td>
    <td>
        <form name="recept" method="POST" action="${serv}">
            <input type="hidden" name="command" value="save">
            <input type="hidden" name="entity" value="recept"/>
            <input type="submit" value="${save}">
        </form>
    </td>
    <td>
        <form name="choose" method="POST"
              action=${serv}>
            <input type="hidden" name="command" value="cook"/>
            <input type="hidden" name="price" value=<csbb:price recept="${recept}"></csbb:price>/>
            <input type="submit" value="${choose}">
        </form>
    </td>
</table>
<a href="${serv}">${main}</a>
<jsp:include page="${foot}"/>
</body>
</html>
