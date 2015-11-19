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
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/semantic/semantic.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/site.css"/>">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/semantic/themes/basic/assets/fonts/icons.ttf"/>">
    <script src="<c:url value="/resources/jquery/jquery-2.1.4.min.js"/>"></script>
    <script src="<c:url value="/resources/semantic/semantic.js"/>"></script>
</head>
<body>
<div>
    <div class="ui blue ten item secondary inverted menu">
        <div class="ui item" style="width:15%;"></div>
        <div class="ui fluid category search item" style="width:35%">
            <div style="color:#fff;font-size:20px;padding-right:20px;font-weight: bolder">FocusBlog</div>
            <div class="ui icon input">
                <input class="prompt" type="text" placeholder="Search ...">
                <i class="search link icon"></i>
            </div>
            <div class="results"></div>
        </div>
        <div class="item" style="width:5%"></div>
        <a class="active item" href="javascript:void(0);" style="width:5%;min-width:60px;">首页</a>

        <a class="item" href="javascript:void(0);" style="width:5%;min-width:60px;">发现</a>
        <a class="item" style="width:5%;min-width:60px;" href="javascript:void(0);">我的博客</a>

        <div class="item" style="width:10%"></div>
        <a class="item" style="max-width:50px;" href="javascript:void(0);"> <i class="alarm icon"></i></a>

        <div class="item" style="min-width:125px;">
            <div class="ui inline dropdown">
                <div class="text">
                    <img class="ui middle aligned mini circular image"
                         src="<c:url value="/resources/images/default-head.png"/>">
                    ${sessionScope.user.userName}
                </div>
                <i class="dropdown icon"></i>

                <div class="menu">
                    <div class="item">
                        <i class="user icon"></i>
                        我的主页
                    </div>
                    <div class="item">
                        <i class="mail icon"></i>
                        私信
                    </div>
                    <div class="item">
                        <i class="setting icon"></i>
                        设置
                    </div>
                    <div class="item">
                        <i class="sign out icon"></i>
                        <a style="color:black" href="<c:url value="/logout" />">登出</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="item" style="width:10%"></div>
    </div>
    <div class="ui grid">
        <div class="two wide column"></div>
        <div class="eight wide column">
            <div>
                <h1>title</h1>
                ContentContentContentContentContentContentContentContentContent
                ContentContentContentContentContentContentContentContentContentContent
            </div>

        </div>
        <div class="four wide column">
            RightContentContentContentContentContent
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
                    // you can use any ui transition
                    transition: 'drop'
                })
        ;

    });
</script>

</body>
</html>
