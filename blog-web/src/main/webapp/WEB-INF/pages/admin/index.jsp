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

<!DOCTYPE html>
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
    <!-- Common Navigator -->
    <jsp:include page="../includes/index-header.jsp"/>

    <div class="ui grid">
        <div class="two wide column"></div>
        <div class="eight wide column">
            <div>
                <h1>title</h1>
                ContentContentContentContentContentContentContentContentContent
                ContentContentContentContentContentContentContentContentContentContent
            </div>

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
                    action: 'hide'
                })
        ;

    });
</script>

</body>
</html>
