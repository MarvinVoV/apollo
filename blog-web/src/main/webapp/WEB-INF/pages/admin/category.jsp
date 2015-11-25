<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2015/11/7
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

            <table class="ui blue striped selectable table">
                <thead>
                <tr>
                    <th>类别</th>
                    <th>文章</th>
                    <th>编辑</th>
                    <th>删除</th>
                    <th>显/隐</th>
                    <th>排序</th>
                    <th>创建日期</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="category" items="${requestScope.list}">
                    <tr data-value="${category.order}">
                        <td style="width:40%">${category.name}</td>
                        <td>${category.articleCount}</td>
                        <td>
                            <a href="javascript:void(0);" style="color:#333333" class="ui link">
                                <i class="edit icon"></i>
                            </a>

                            <div class="ui flowing popup top left transition hidden">
                                <form class="ui form">
                                    <div class="field">
                                        <input type="text" name="name" value="${category.name}" placeholder="类别名称">
                                        <input type="hidden" name="id" value="${category.id}">
                                    </div>
                                    <button class="ui positive mini button" type="submit"
                                            onclick="return update(this);">确定
                                    </button>
                                </form>
                            </div>
                        </td>
                        <td>
                            <a href="javascript:void(0);" style="color:#333333" class="ui link">
                                <i class="trash icon"></i>
                            </a>

                            <div class="ui flowing popup top left transition hidden">
                                <span class="site-tiny-font">确认删除</span>
                                <a class="ui red mini button"
                                   href="<c:url value="/manager/category/delete?id=${category.id}"/>"
                                   type="submit">删除</a>
                            </div>
                        </td>
                        <td>
                            <a href="javascript:void(0)" onclick="showOrHide(this,'${category.id}')"
                               style="color:black">
                                <c:set var="stat" value="hide"/>
                                <c:if test="${category.status == 1}">
                                    <c:set var="stat" value="unhide"/>
                                </c:if>
                                <i class="${pageScope.stat} icon"></i>
                            </a>
                        </td>
                        <td>
                            <a href="javascript:void(0);" style="color:#333333">
                                <i class="arrow down icon"></i>
                            </a>
                            <a href="javascript:void(0);" style="color:#333333">
                                <i class="arrow up icon"></i>
                            </a>
                        </td>
                        <td>
                            <fmt:formatDate value="${category.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
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

    /**
     * update category name
     */
    function update(e) {
        var form = $(e).parent();
        var td = $(e).parents('tr').children().first();
        $.ajax({
            type: 'post',
            url: '<c:url value="/manager/category/update"/>',
            data: form.serialize(),
            success: function (e) {
                td.html(form.serialize().split('&')[0].split('=')[1])
            },
            error: function (e) {
                alert(e);
            }
        });
        return false;
    }

    /**
     * update category show or hide field
     */
    function showOrHide(e, id) {
        var icon = $(e).children().first();
        var status = 2; // 1 show , 2 hide
        if (icon.hasClass('hide')) {
            icon.attr('class', 'unhide icon');
            status = 1;
        } else {
            icon.attr('class', 'hide icon');
        }

        $.ajax({
            type: 'post',
            url: '<c:url value="/manager/category/update"/>',
            data: {'status': status, 'id': id},
            success: function (e) {
                console.log(e);
            },
            error: function (e) {
                alert(e);
            }
        });
    }

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

        $('.ui.link')
                .popup({
                    on: 'click',
                    hoverable: true,
                    closable: true,
                    position: 'top left'
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
