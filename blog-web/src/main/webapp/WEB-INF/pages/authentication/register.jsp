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
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/semantic/semantic.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/site.css"/>">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/semantic/themes/basic/assets/fonts/icons.ttf"/>">
    <script src="<c:url value="/resources/jquery/jquery-2.1.4.min.js"/>"></script>
    <script src="<c:url value="/resources/semantic/semantic.js"/>"></script>
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
                <div class="active step">
                    <i class="user icon"></i>

                    <div class="content">
                        <div class="title">Step 1:</div>
                        <div class="description site-mini-font">创建个人账户</div>
                    </div>
                </div>
                <div class="step">
                    <i class="mail icon"></i>

                    <div class="content">
                        <div class="title">Step 2:</div>
                        <div class="description site-mini-font">邮箱进行验证</div>
                    </div>
                </div>
                <div class="step">
                    <i class="user icon"></i>

                    <div class="content">
                        <div class="title">Step 3:</div>
                        <div class="description site-mini-font">账户注册成功</div>
                    </div>
                </div>
            </div>
            <h3 class="ui header">创建个人账户</h3>

            <div class="ui grid">
                <div class="ten wide column">
                    <form action="<c:url value="/authentication/registerAction"/>" method="post" class="ui form">
                        <div class="required field">
                            <label>用户名</label>
                            <input type="text" name="userName" placeholder="用户名">

                            <p class="site-tiny-font">登陆时的唯一凭证</p>
                        </div>
                        <div class="required field">
                            <label>邮箱</label>
                            <input type="email" name="email" placeholder="邮箱">

                            <p class="site-tiny-font">用于账户信息变更和通知</p>
                        </div>
                        <div class="required field">
                            <label>密码</label>
                            <input type="password" name="password" placeholder="密码">

                            <p class="site-tiny-font">至少一个大写字母、数字组成的最大长度20个字符</p>
                        </div>
                        <div class="field">
                            <div class="ui checkbox">
                                <input type="checkbox" name="checkbox" tabindex="0" class="hidden">
                                <label class="site-mini-font">同意Focusblog的各项条款</label>
                            </div>
                        </div>
                        <div class="ui error message"></div>
                        <button class="ui primary button" type="submit">创建账户</button>
                    </form>
                </div>
                <div class="six wide column">
                    <div class="ui piled segments">
                        <div class="ui segment">
                            <p><strong>Why FocusBlog</strong></p>
                        </div>
                        <div class="ui segment">
                            <i class="pin icon"></i>
                            Focus
                        </div>
                        <div class="ui segment">
                            <i class="pin icon"></i>
                            Share
                        </div>
                    </div>
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

        $('.ui.form')
                .form({
                    fields: {
                        username: {
                            identifier: 'username',
                            rules: [
                                {
                                    type: 'empty',
                                    prompt: '用户名不能为空'
                                }
                            ]
                        },
                        email: {
                            identifier: 'email',
                            rules: [
                                {
                                    type: 'empty',
                                    prompt: '邮箱不能为空'
                                },
                                {
                                    type: 'email',
                                    prompt: '请输入有效的邮箱地址'
                                }
                            ]
                        },
                        password: {
                            identifier: 'password',
                            rules: [
                                {
                                    type: 'empty',
                                    prompt: '密码不能为空'
                                },
                                {
                                    type: 'length[6]',
                                    prompt: '密码长度至少为六位'
                                }
                            ]
                        },
                        checkbox: {
                            identifier: 'checkbox',
                            rules: [
                                {
                                    type: 'checked',
                                    prompt: '如要注册,必须同意Focusblog的各项条款'
                                }
                            ]
                        }
                    },
                    onSuccess:function(event, fields){
                        console.log("hello")
                    }
                });
    });
</script>

</body>
</html>
