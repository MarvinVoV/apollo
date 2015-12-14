<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2015/11/7
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/focusblogTagLib" prefix="p" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/semantic/semantic.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/site.css"/>">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/semantic/themes/basic/assets/fonts/icons.ttf"/>">
    <script src="<c:url value="/resources/jquery/jquery-2.1.4.min.js"/>"></script>
    <script src="<c:url value="/resources/semantic/semantic.js"/>"></script>
    <style type="text/css">
        .item > a:hover {
            color: #000;
        }

        .page-fixed-span {
            padding-right: 10px;
        }
    </style>
</head>
<body>
<div>
    <!-- Common Navigator -->
    <jsp:include page="../includes/index-header.jsp"/>

    <div class="ui grid">
        <div class="two wide column"></div>
        <div class="ten wide column">

            <div class="ui basic segment">
                <div class="ui tiny header" style="color:#666">
                    <i class="newspaper icon"></i> 全部博文
                </div>
                <div class="ui inverted divider"></div>
                <div class="ui items">
                    <c:forEach items="${requestScope.articles}" var="article">
                        <div class="item">
                            <div class="content">
                                <a class="ui huge header" href="<c:url value="/manager/article/view?id=${article.id}&uid=${article.userId}"/>">${article.title}</a>

                                <div class="meta">
                                    <div class="site-mini-font">
                                        来自 ${article.categoryName}
                                    </div>
                                </div>
                                <div class="description" style="margin-top:16px;margin-bottom:16px;">
                                    <p>${article.digest}</p>
                                </div>
                                <div class="extra">
                                    <div class="site-mini-font" style="float:right;margin-right:10px;">
                                        <span style="padding-right:20px;"><fmt:formatDate value="${article.updateDate}"
                                                                                          pattern="yyyy-MM-dd HH:mm:ss"/></span>
                                        <span class="page-fixed-span"><i class="unhide icon"></i>阅读(${article.pageView})</span>
                                        <span class="page-fixed-span"><i class="comment outline icon"></i>评论(0)</span>
                                        <span class="page-fixed-span"><i class="remove bookmark icon"></i>收藏(0)</span>
                                        <span class="page-fixed-span"><i class="share icon"></i>分享</span>
                                        <a class="page-fixed-span" href="<c:url value="/manager/article/view?id=${article.id}&uid=${article.userId}"/>">查看全文</a>
                                    </div>

                                </div>
                            </div>

                        </div>
                        <div class="ui inverted divider"></div>
                    </c:forEach>
                </div>
                <div style="text-align: center;">
                    <p:pagination num="${requestScope.pagination.num}"
                                  size="${requestScope.pagination.size}"
                                  count="${requestScope.pagination.count}"
                                  url="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/manager/article/list"/>
                </div>
            </div>

        </div>
        <div class="three wide column">
            <div class="ui cards">
                <div class="card">
                    <div class="content">
                        <img class="right floated tiny ui image" src="${sessionScope.user.header}">

                        <div class="header">
                            ${sessionScope.user.userName}
                        </div>
                        <div class="meta">
                            Friends of Veronika
                        </div>
                        <div class="description">
                            Elliot requested permission to view your contact details
                        </div>
                    </div>
                    <%--<div style="text-align: center;">--%>
                    <%--<div class="ui bottom inline blue button" style="width:50%;">--%>
                    <%--<i class="add icon"></i>--%>
                    <%--添加关注--%>
                    <%--</div>--%>
                    <%--<div class="ui bottom inline green button" style="width:40%;">--%>
                    <%--<i class="mail icon"></i>--%>
                    <%--发私信--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <a class="ui bottom attached blue button" href="<c:url value="/manager/article/new"/>">
                        <i class="write icon"></i>

                        写博客
                    </a>

                    <div class="extra content">
                        <span class="right floated">
                        2013-02-25加入
                        </span>
                        <span>
                            <i class="users icon"></i>
                            75 关注
                        </span>
                    </div>
                </div>
            </div>
            <div class="ui segment">

                <h4 class="ui header">
                    博客分类
                </h4>

                <div class="ui list">
                    <c:forEach items="${requestScope.categories}" var="category">
                        <div class="item"><a>${category.name}</a> <span>&nbsp;(${category.articleCount})</span></div>
                    </c:forEach>
                </div>
                <div class="ui inverted divider"></div>

                <h4 class="ui header">
                    阅读排行
                </h4>

                <div class="ui list">
                    <a class="item">Getting Started</a>
                    <a class="item">Introduction</a>
                    <a class="item">Getting Started</a>
                    <a class="item">Introduction</a>
                </div>
                <div class="ui inverted divider"></div>

                <h4 class="ui header">
                    访问统计
                </h4>

                <div class="ui list">
                    <a class="item">Getting Started</a>
                    <a class="item">Introduction</a>
                    <a class="item">Getting Started</a>
                    <a class="item">Introduction</a>
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
