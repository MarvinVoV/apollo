<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2015/11/7
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>设置</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/semantic/semantic.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/site.css"/>">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/semantic/themes/basic/assets/fonts/icons.ttf"/>">
    <script src="<c:url value="/resources/jquery/jquery-2.1.4.min.js"/>"></script>
    <script src="<c:url value="/resources/semantic/semantic.js"/>"></script>
</head>
<body>
<div>
    <!-- Common Navigator -->
    <jsp:include page="../includes/index-header.jsp"/>

    <div class="ui sixteen grid">
        <div class="two wide column"></div>
        <div class="three wide column">
            <div class="ui blue vertical pointing menu">
                <div class="header item" style="color:#555;font-size:13px;">
                    个人设置
                </div>
                <a class="item" href="settings.html" style="color:#4078c0;font-size:13px;">
                    基本资料
                </a>
                <a class="item" style="color:#4078c0;font-size:13px;">
                    账户设置
                </a>
                <a class="item" style="color:#4078c0;font-size:13px;">
                    消息和邮件
                </a>
            </div>
        </div>
        <div id="panel" class="nine wide column">
            <div id="basicProfile" class="ui vertical menu" style="width:100%">
                <div class="header item" style="color:#555;font-size:13px;">
                    个人资料
                </div>
                <div class="item">
                    <form class="ui form">
                        <div class="field">
                            <h4 class="ui header">Profile picture</h4>
                            <table>
                                <tr>
                                    <td>
                                        <div>
                                            <img class="ui middle aligned tiny image"
                                                 src="<c:url value="/resources/images/default-head.png"/>">
                                        </div>
                                    </td>
                                    <td>
                                        <div style="margin-left:20px;">
                                            <a href="javascript:void(0);" class="ui positive button">Upload new
                                                picture</a>
                                            <!--<input type="file" name="file"/>-->
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="field">
                            <label style="color:#666">用户名</label>
                            <input type="text" name="first-name" placeholder="First Name">
                        </div>
                        <div class="field">
                            <label style="color:#666">公开邮箱</label>
                            <input type="text" name="last-name" placeholder="Last Name">
                        </div>
                        <div class="field">
                            <div class="ui checkbox">
                                <input type="checkbox" tabindex="0" class="hidden">
                                <label>I agree to the Terms and Conditions</label>
                            </div>
                        </div>
                        <button class="ui primary button" type="submit">Submit</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="two wide column"></div>

    </div>
</div>
<script>
    $(function () {
        $('.ui.menu a.item')
                .on('click', function () {
                    $(this)
                            .addClass('active')
                            .siblings()
                            .removeClass('active')
                    ;
                })
        ;
        $('.dropdown')
                .dropdown({
                    action: 'hide'
                })
        ;

    });
</script>

</body>
</html>
