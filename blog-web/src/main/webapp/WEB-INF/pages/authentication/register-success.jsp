<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2015/11/12
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>注册成功</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/semantic/semantic.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/site.css"/>">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/semantic/themes/basic/assets/fonts/icons.ttf"/>">
    <script src="<c:url value="/resources/jquery/jquery-2.1.4.min.js"/>"></script>
    <script src="<c:url value="/resources/semantic/semantic.js"/>"></script>
    <script src="<c:url value="/resources/js/common.js"/>"></script>
</head>
<body>
<!-- import register header page -->
<jsp:include page="../includes/register-header.jsp"/>

<!-- Main Content -->
<div class="ui sixteen grid">
    <div class="four wide column"></div>
    <div class="eight wide column">
        <div class="ui sizer vertical segment">
            <div class="ui huge header">Join FocusBlog</div>
            <p><h4>The best way to think,write blog.</h4></p>
        </div>
        <div>
            <div class="ui three steps">
                <div class="step">
                    <i class="user icon"></i>

                    <div class="content">
                        <div class="title">Step 1:</div>
                        <div class="description site-mini-font">创建个人账户</div>
                    </div>
                </div>
                <div class="step">
                    <i class="email icon"></i>

                    <div class="content">
                        <div class="title">Step 2:</div>
                        <div class="description site-mini-font">邮箱进行验证</div>
                    </div>
                </div>
                <div class="active step">
                    <i class="user icon"></i>

                    <div class="content">
                        <div class="title">Step 3:</div>
                        <div class="description site-mini-font">账户注册成功</div>
                    </div>
                </div>
            </div>
            <h3 class="ui header">注册成功</h3>
            <div class="ui positive icon message">
                <i class="comment icon"></i>
                <div class="content">
                    <div class="header">
                        您的邮箱已经验证成功
                    </div>
                    <p>
                        <span>${user.email}</span>验证通过，是否现在登陆?<br><br>
                        <a href="<c:url value="/authentication/login"/>">立即前去登陆</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <div class="four wide column"></div>
</div>

<script>
    $(function () {
        $('.ui.checkbox').checkbox();

        $('.ui.menu a.item').on('click', function () {
            $(this)
                    .addClass('active')
                    .siblings()
                    .removeClass('active');
        });
    });
</script>

</body>
</html>
