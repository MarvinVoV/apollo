<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2015/11/7
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body onload='document.loginForm.username.focus();'>
<div>
    <c:url value="/login" var="loginUrl"/>
    <form name="loginForm" action="${loginUrl}" method="post">
        <c:if test="${requestScope.msg != null}">
            <p>
                ${requestScope.msg}
            </p>
        </c:if>
        <p>
            <label for="username">Username</label>
            <input type="text" id="username" name="username"/>
        </p>

        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
        </p>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <button type="submit" class="btn">Log in</button>
    </form>
</div>

</body>
</html>
