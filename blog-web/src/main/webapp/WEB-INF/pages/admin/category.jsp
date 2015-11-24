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
    <title>类别管理</title>
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
    <div class="ui grid">
        <div class="two wide column"></div>
        <div class="twelve wide column">
            <div class="ui basic segment">
                <h1 class="ui header">
                    <img src="${sessionScope.user.header}" class="ui cube image">

                    <div class="content">
                        ${sessionScope.user.userName}
                        <div class="sub header">Manage your preferences</div>
                    </div>
                </h1>
            </div>
        </div>
        <div class="two wide column"></div>

    </div>

    <div class="ui grid">
        <div class="two wide column"></div>
        <div class="ten wide column">

            <div class="ui pointing secondary menu">
                <a class="item" data-tab="first">文章管理</a>
                <a class="item active" href="<c:url value="/manager/category"/> " data-tab="second">类别管理</a>
                <a class="item" data-tab="third">评论管理</a>
                <a class="item " data-tab="four">草稿箱</a>
                <a class="item " data-tab="five">回收站</a>
                <a class="item " data-tab="six">主题配置</a>
            </div>

                <table class="ui blue selectable table">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Status</th>
                        <th class="right aligned">Notes</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>John</td>
                        <td>Approved</td>
                        <td class="right aligned">None</td>
                    </tr>
                    <tr>
                        <td>Jamie</td>
                        <td>Approved</td>
                        <td class="right aligned">Requires call</td>
                    </tr>
                    <tr>
                        <td>Jill</td>
                        <td>Denied</td>
                        <td class="right aligned">None</td>
                    </tr>
                    </tbody>
                    <tfoot>
                    <tr><th colspan="3">
                        <div class="ui right floated pagination menu">
                            <a class="icon item">
                                <i class="left chevron icon"></i>
                            </a>
                            <a class="item">1</a>
                            <a class="item">2</a>
                            <a class="item">3</a>
                            <a class="item">4</a>
                            <a class="icon item">
                                <i class="right chevron icon"></i>
                            </a>
                        </div>
                    </th>
                    </tr></tfoot>
                </table>

        </div>
        <div class="three wide column">
            <h4 class="ui header">添加分类</h4>

            <form class="ui form" action="<c:url value="/manager/category/save"/> " method="post">
                <div class="field">
                    <input type="text" name="name" placeholder="分类名称">
                </div>
                <button class="ui compact labeled icon positive button" type="submit">
                    <i class="add icon"></i>
                    添加
                </button>
                <div class="ui error message"></div>
            </form>
            <h4 class="ui header">
                分类状态
            </h4>
            <p><span class="site-mini-font">最近活动时间 2015-10-22 10:30:33</span></p>
        </div>
        <div class="one wide column"></div>
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

        $('.ui.form').form({
            fields: {
                name: {
                    identifier: "name",
                    rules: [
                        {
                            type: "empty",
                            prompt: "类别名称不能为空"
                        }
                    ]
                }
            }
        });

    });
</script>

</body>
</html>
