<%@ page import="sun.focusblog.admin.domain.Comment" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2015/11/7
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <script src="<c:url value="/resources/ueditor/ueditor.parse.min.js"/>"></script>
    <script src="<c:url value="/resources/js/common.js"/>"></script>

    <style type="text/css">
        .item > a:hover {
            color: #000;
        }

        .page-fixed-span {
            padding-right: 10px;
        }

        .article-div {
            padding-top: 50px;
        }

        .auto_increment_div {
            min-height: 40px;
            max-height: 300px;
            _height: 40px;
            margin-left: auto;
            margin-right: auto;
            padding: 10px;
            outline: 0;
            border: 1px solid rgba(34, 36, 38, 0.15);
            font-size: 14px;
            word-wrap: break-word;
            overflow-x: hidden;
            overflow-y: auto;
            _overflow-y: visible;
        }
    </style>
</head>
<body>
<div>
    <!-- Common Navigator -->
    <jsp:include page="../includes/index-header.jsp"/>


    <!-- Page Scope User -->
    <c:set var="user" value="${sessionScope.user}"/>
    <c:if test="${not empty requestScope.user}">
        <c:set var="user" value="${requestScope.user}"/>
    </c:if>


    <div class="ui sixteen grid">
        <div class="two wide column"></div>
        <div class="ten wide column">

            <div class="ui basic segment">
                <div class="ui tiny header" style="color:#666">
                    <i class="newspaper icon"></i> 博文阅览
                </div>
                <div class="ui inverted divider"></div>

                <div class="ui blue segment">
                    <div id="type-div" class="ui ribbon label">${article.type}</div>
                    <p></p>

                    <p></p>

                    <div class="ui item">
                        <div class="content">
                            <div class="ui huge header">${article.title}</div>
                            <div class="meta">
                                <div id="tag-div" class="site-tiny-font">
                                    <span style="padding-right:10px;">标签</span>
                                    <c:set value="${ fn:split(article.tags, ',') }" var="tags"/>
                                    <c:forEach items="${tags}" var="tag">
                                        <a class="ui tiny tag label" style="margin-right:10px;">${tag}</a>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="description" style="margin-top:16px;margin-bottom:16px;">
                                <div class="site-mini-font" style="float:right;margin-right:10px;">
                                        <span style="padding-right:20px;">
                                            <fmt:formatDate value="${article.updateDate}"
                                                            pattern="yyyy-MM-dd HH:mm:ss"/>
                                        </span>
                                    <span class="page-fixed-span"><i
                                            class="unhide icon"></i>阅读(${article.pageView})</span>
                                    <span class="page-fixed-span"><i class="comment outline icon"></i>评论(0)</span>
                                    <span class="page-fixed-span"><i class="remove bookmark icon"></i>收藏(0)</span>
                                    <span class="page-fixed-span"><i class="edit icon"></i><a
                                            href="<c:url value="/manager/article/modify?id=${requestScope.article.id}"/>">编辑</a></span>
                                    <span class="page-fixed-span"><i class="remove icon"></i><a
                                            href="javascript:void(0);">删除</a></span>
                                    <span class="page-fixed-span"><i class="share icon"></i>分享</span>
                                </div>
                            </div>
                            <div class="extra">
                                <div class="article-div">
                                    ${requestScope.article.content}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="ui basic segment">
                <div class="ui comments">
                    <h3 class="ui dividing tiny header" style="color:#666"><i class="mini comments icon"></i>评论</h3>
                    <c:forEach var="comment" items="${requestScope.comments}">
                        <div class="comment">
                            <a class="avatar"><img src="${comment.user.header}"></a>
                            <div class="content">
                                <a class="author">${comment.user.userName}</a>
                                <div class="metadata">
                                    <span class="date">
                                        <fmt:formatDate value="${comment.date}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </span>
                                </div>
                                <div class="text">
                                        ${comment.content}
                                </div>
                                <div class="actions">
                                    <a class="reply" href="javascript:void(0);" onclick="reply(this)">回复</a>
                                    <form class="ui reply form" style="display: none;">
                                        <div class="field">
                                            <div contenteditable="true" class="auto_increment_div"></div>
                                        </div>
                                        <div style="text-align:right;">
                                            <a onclick="cancelReply(this);" class="ui link">
                                                取消
                                            </a>
                                            <div onclick="submitReply(this,'${comment.id}','${comment.user.userId}','${comment.id}', 1);"
                                                 class="ui blue mini submit button">
                                                回复
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <c:if test="${comment.comments.size() != 0}">
                                <div class="comments">
                                    <c:forEach var="innerComment" items="${comment.comments}">
                                        <div class="comment">
                                            <a class="avatar">
                                                <img src="${innerComment.user.header}">
                                            </a>
                                            <div class="content">
                                                <a class="author">${innerComment.user.userName}</a>
                                                <span><i class="forward mail icon"></i></span><a
                                                    class="author">${innerComment.parent.user.userName}</a>
                                                <div class="metadata">
                                                       <span class="date">
                                                            <fmt:formatDate value="${innerComment.date}"
                                                                            pattern="yyyy-MM-dd HH:mm:ss"/>
                                                       </span>
                                                </div>
                                                <div class="text">${innerComment.content}</div>
                                                <div class="actions">
                                                    <a class="reply" href="javascript:void(0);"
                                                       onclick="reply(this)">回复</a>
                                                    <form class="ui reply form" style="display: none;">
                                                        <div class="field">
                                                            <div contenteditable="true"
                                                                 class="auto_increment_div"></div>
                                                        </div>
                                                        <div style="text-align:right;">
                                                            <a onclick="cancelReply(this);" class="ui link">
                                                                取消
                                                            </a>
                                                            <div onclick="submitReply(this,'${innerComment.id}','${innerComment.user.userId}','${comment.id}',2);"
                                                                 class="ui blue mini submit button">
                                                                回复
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </c:if>
                        </div>
                    </c:forEach>

                    <div style="text-align: center;">
                        <p:pagination num="${requestScope.pagination.num}"
                                      size="${requestScope.pagination.size}"
                                      count="${requestScope.pagination.count}"
                                      url="/manager/article/view"
                                      params="id=${requestScope.article.id}&uid=${pageScope.user.userId}"/>
                    </div>
                    <div style="margin-top:20px;">
                        <div class="ui middle aligned relaxed list">
                            <div class="item">
                                <img class="ui right spaced avatar image" src="${sessionScope.user.header}">
                                <div class="content">
                                    <a class="header">${sessionScope.user.userName}</a>
                                </div>
                            </div>
                            <div class="item">
                                <form class="ui reply form" action="<c:url value="/manager/comments/add"/>"
                                      method="post">
                                    <div class="field">
                                        <textarea placeholder="" name="content"></textarea>
                                        <input type="hidden" name="articleId" value="${requestScope.article.id}">
                                    </div>
                                    <a href="javascript:void(0);" onclick="addComment(this);"
                                       class="ui blue labeled submit icon button">
                                        <i class="icon edit"></i>添加评论
                                    </a>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="three wide column">
            <div class="ui cards">
                <div class="card">
                    <div class="content">
                        <img class="right floated tiny ui image" src="${pageScope.user.header}">

                        <div class="header">
                            ${pageScope.user.userName}
                        </div>
                        <div class="meta">
                            Friends of Veronika
                        </div>
                        <div class="description">
                            Elliot requested permission to view your contact details
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${requestScope.relation == null}">
                            <a class="ui bottom attached blue button" href="<c:url value="/manager/article/new"/>">
                                <i class="write icon"></i>
                                写博客
                            </a>
                        </c:when>
                        <c:when test="${requestScope.relation == 'FOLLOWER'}">
                            <div style="text-align: center;">
                                <div class="ui bottom inline tiny blue disabled button" style="width:50%;">
                                    <i class="add icon"></i>
                                    已关注
                                </div>
                                <div class="ui bottom inline tiny green button" style="width:40%;">
                                    <i class="mail icon"></i>
                                    发私信
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${requestScope.relation == 'NOTHING'}">
                            <div style="text-align: center;">
                                <a href="javascript:void(0);" onclick="follow(this)"
                                   class="ui bottom inline tiny blue button" style="width:50%;">
                                    <i class="add icon"></i>
                                    添加关注
                                </a>

                                <div class="ui bottom inline tiny green button" style="width:40%;">
                                    <i class="mail icon"></i>
                                    发私信
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${requestScope.relation == 'BLACKLIST'}">
                            <a class="ui bottom attached blue button" href="<c:url value="/manager/article/new"/>">
                                <i class="write icon"></i>
                                黑名单
                            </a>
                        </c:when>
                    </c:choose>

                    <div class="extra content">
                        <span class="right floated">
                        <fmt:formatDate value="${article.createDate}" pattern="yyyy-MM-dd"/> 加入
                        </span>
                        <span>
                            <i class="users icon"></i>
                            <span id="follows">${requestScope.follows}</span> 关注
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
        <div class="one wide column"></div>
    </div>
</div>
<script>

    /**
     * init article type color
     */
    function initArticleTypeDivColor() {
        var articleTypeDiv = $('#type-div');
        var type = articleTypeDiv.html().trim();
        switch (type) {
            case '原创':
                articleTypeDiv.addClass('blue');
                break;
            case '转载':
                articleTypeDiv.addClass('orange ');
                break;
            case '翻译':
                articleTypeDiv.addClass('teal');
                break;
            default:
                articleTypeDiv.addClass('blue');
        }
    }

    function follow(e) {
        var $that = $(e);
        $.ajax({
            type: 'POST',
            url: '<c:url value="/manager/user/follow"/>',
            data: {uid: '${article.userId}'},
            dataType: 'json',
            success: function (json) {
                if (json && json.status == 'ok') {
                    $that.html('<i class="add icon"></i>已关注').addClass('disabled');
                    var follows = $('#follows');
                    follows.html(parseInt(follows.html().trim()) + 1);
                }
            },
            error: function (e) {
                alert(e);
            }
        });
    }

    function reply(e) {
        var a = $(e);
        var form = a.next();
        a.hide();
        form.show('fast');

    }

    function cancelReply(e) {
        var form = $(e).closest('form');
        var a = form.prev();
        a.show();
        form.hide();
    }

    function submitReply(e, commentId, userId, ancestor, level) {
        var form = $(e).closest('form');
        var a = form.prev();
        var content = form.find('div[contenteditable="true"]');
        if ('' == content.html()) return;

        $.ajax({
            type: 'post',
            url: '<c:url value="/manager/comments/reply"/>',
            data: {
                'parent.id': commentId,
                'parent.user.userId': userId,
                'articleId': '${requestScope.article.id}',
                'content': content.html(),
                'ancestor': ancestor
            },
            success: function (e) {
                if (e.status == Constants.responseMsgStatus.OK) {
                    a.show();
                    form.hide();
                    content.html(''); // Reset
                    var template;
                    var comments;
                    var comment = e.data;
                    if (level == 1) {
                        var contentDiv = a.closest('div[class="content"]');
                        if (contentDiv.next().hasClass('comments')) {
                            comments = contentDiv.next();
                            template = commentTemplate(comment, ancestor, 'none');
                            comments.append(template);
                            template.show('normal');
                        } else {
                            template = commentsTemplate(comment, ancestor, 'none');
                            template.insertAfter(contentDiv);
                            template.show('normal');
                        }
                    } else {
                        template = commentTemplate(comment, ancestor, 'none');
                        template.insertAfter(a.closest('div[class="comment"]'));
                        template.show('normal');
                    }
                } else {
                    console.log('false')
                }
            },
            error: function (e) {
                alert(e);
            }
        });
    }
    function commentsTemplate(comment, ancestor, display) {
        var commentDiv = commentTemplate(comment, ancestor, 'normal');
        var comments = $('<div class="comments" style="display:' + display + '"></div>');
        comments.append(commentDiv);
        return comments;
    }
    function commentTemplate(comment, ancestor, display) {
        var author = '<a class="author">${sessionScope.user.userName}</a><span><i class="forward mail icon"></i></span><a class="author">' + comment.parent.user.userName + '</a>';
        return $('<div class="comment" style="display:' + display + '">' +
                '<a class="avatar"><img src="${sessionScope.user.header}"></a>' +
                '<div class="content">' +
                author +
                '<div class="metadata">' +
                '<span class="date">' + comment.date + '</span>' +
                '</div>' +
                '<div class="text">' + comment.content + '</div>' +
                '<div class="actions">' +
                '<a class="reply" href="javascript:void(0);" onclick="reply(this)">回复</a>' +
                '<form class="ui reply form" style="display: none;">' +
                '<div class="field">' +
                '<div contenteditable="true" class="auto_increment_div"></div>' +
                '</div>' +
                '<div style="text-align:right;">' +
                '<a onclick="cancelReply(this);" class="ui link">取消</a>' +
                '<div onclick="submitReply(this,\'' + comment.id + '\',\'' + comment.user.userId + '\',\'' + ancestor + '\');" class="ui blue mini submit button">回复</div>' +
                '</div>' +
                '</form>' +
                '</div>' +
                '</div>' +
                '</div>');
    }

    /**
     * Submit comment form
     */
    function addComment(e) {
        var form = $(e).closest('form');
        var content = form.find('textarea');
        if (content.val().trim() != '') {
            form.submit();
        }
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

        uParse('.article-div', {
            rootPath: '<c:url value="/resources/ueditor"/>'
        });

        /**
         * init tag color
         */
        $('div#tag-div a').each(function () {
            $(this).addClass(randomSiteColor());
        });

        /**
         * init article type color
         */
        initArticleTypeDivColor();
    });
</script>

</body>
</html>
