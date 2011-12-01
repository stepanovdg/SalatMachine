<%@ page language="java" contentType="text/html;
        charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!--%@ taglib prefix="cbck" uri="controller.salatmachine.bsu.by.tags" %-->
<html>
<head><title>Login</title></head>
<body>
<jsp:include page="/static/header.jsp"/>
<h3>Login</h3>
<hr/>
<form name="loginForm" method="POST"
      action="controller">
    <input type="hidden" name="command" value="login"/>
    Login:<br/>
    <input type="text" name="login" value=""><br/>
    Password:<br/>
    <input type="password" name="password" value="">
    <br/>
    <input type="submit" value="Enter">
</form>
<hr/>
<!--cbck:log level="debug">
Debug message from logger.jsp
<!/cbck:log-->
<jsp:include page="/static/footer.jsp"/>
</body>
</html>