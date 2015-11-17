<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2015/11/7
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/semantic/semantic.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/site.css"/>">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/semantic/themes/basic/assets/fonts/icons.ttf"/>">
    <script src="<c:url value="/resources/jquery/jquery-2.1.4.min.js"/>"></script>
    <script src="<c:url value="/resources/semantic/semantic.js"/>"></script>

    <style type="text/css">
        .column {
            max-width: 450px;
        }
    </style>

</head>
<body>

<div class="ui middle aligned center aligned grid">
    <div class="column">
        <h2 class="ui teal header">
            <div>
                <c:if test="${requestScope.msg != null}">
                    <p>
                            ${requestScope.msg}
                    </p>
                </c:if>
            </div>
        </h2>
        <c:url value="/login" var="loginUrl"/>
        <form name="loginForm" action="${loginUrl}" method="post" class="ui large form">
            <div class="ui stacked segment">
                <div class="field">
                    <div class="ui left icon input">
                        <i class="user icon"></i>
                        <input type="text" name="username" placeholder="用户名">
                    </div>
                </div>
                <div class="field">
                    <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <input type="password" name="password" placeholder="密码">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </div>
                </div>
                <div class="ui fluid large blue submit button">登陆</div>
            </div>
            <div class="ui error message"></div>
        </form>
        <div class="ui message mini-font">
            新成员？<a href="<c:url value="/authentication/register"/>">注册</a>
        </div>
    </div>
</div>

<script type="application/javascript">
    $(function () {
        $('.ui.form').form({
            fields: {
                username: {
                    identifier: "username",
                    rules: [
                        {
                            type: "empty",
                            prompt: "Please enter your name"
                        }
                    ]
                },
                password: {
                    identifier: "password",
                    rules: [
                        {
                            type: "empty",
                            prompt: "Please enter your password"
                        }
                    ]
                }
            }
        });
    });
</script>
</body>
</html>
