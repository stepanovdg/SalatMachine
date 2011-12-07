<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--fmt:bundle basename="${applicationScope.message}"-->
<fmt:bundle basename="resources.messages">
    <fmt:message key="FOOTER.COPYRIGHT" var="cp"/>
</fmt:bundle>
<div style="background-color: #a0c8ff; height: 40px;">
<div
	style="font-family: 'Trebuchet',serif; font-size: 12px; padding-top: 11px; text-align: center;">
&copy; ${cp}
</div>
</div>