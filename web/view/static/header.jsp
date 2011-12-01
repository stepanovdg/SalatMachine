<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- Обработать параметр сортировки --%>
<c:if test="${param.sort!=null}">
	<c:set var="sort" scope="session" value="${param.sort}" />
</c:if>
<%-- Обработать параметр направления сортировки --%>
<c:if test="${param.dir!=null}">
	<c:set var="dir" scope="session" value="${param.dir}" />
</c:if>
<%-- Общая декоративная "шапка" для всех страниц --%>
<div style="background-color: #a0c8ff; padding: 10px;"><img
	src="/view/images/salat4.jpeg" width="93" height="69" border="1"
	align="left">
<div
	style="font-family: 'Trebuchet'; font-size: 30px; height: 69px; margin-left: 120px;">
EpamWebProject "SalatMachine" v.1.0.0</div>
</div>
<%-- Панель отображается если пользователь аутентифицирован --%>
<c:if test="${user!=null}">
	<div style="background-color: #ccc; padding: 5px">
	<div style="float: right; margin-right: 5px">[ <a
		href="<c:url value="/controller" />">Logout</a> ]</div>
	You login as <c:out value="${user}" /> ( <c:out
		value="${user}" /> )</div>
</c:if>