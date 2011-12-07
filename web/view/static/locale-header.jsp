<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<<!--fmt:bundle basename="${applicationScope.config}"-->
<fmt:bundle basename="resources.config">
    <fmt:message key="GMT.SERVER" var="gmtserv"/>
</fmt:bundle>
<!--fmt:bundle basename="${applicationScope.message}"-->
<fmt:bundle basename="resources.messages">
    <fmt:message key="GMT.LOCALE" var="gmtlocl"/>
    <fmt:message key="LOCALE.NAME.USA" var="usname"/>
    <fmt:message key="LOCALE.NAME.GERMANY" var="dename"/>
    <fmt:message key="LOCALE.NAME.RUSSIA" var="runame"/>
</fmt:bundle>
<html>
<body>
<c:if test="${param['locale'] != null}">
  <fmt:setLocale value="${param['locale']}" scope="session" />
   <c:set scope="session" var="timezone" value="${gmtlocl}"/>
</c:if>
<!--c:choose>
<!c:when test="${param['timezone'] != null}">
  <!c:set scope="session" var="timezone" value="${param['timezone']}"/>
<!/c:when>
<!c:otherwise>
  <!c:set scope="session" var="timezone" value="${gmtserv}"/>
<!/c:otherwise>
<!/c:choose-->
<h3>
<a href="${pageContext.request.requestURL}?locale=en-US&timezone=${gmtlocl}">${usname}</a> -
<a href="${pageContext.request.requestURL}?locale=de-DE&timezone=${gmtlocl}">${dename}</a> -
<a href="${pageContext.request.requestURL}?locale=ru-RU&timezone=${gmtlocl}">${runame}</a>
</h3>
</body>
</html>