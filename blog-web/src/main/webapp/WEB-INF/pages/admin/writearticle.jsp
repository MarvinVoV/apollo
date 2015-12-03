<%@ page import="sun.focusblog.admin.domain.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2015/11/7
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <script src="<c:url value="/resources/ueditor/ueditor.config.js"/>"></script>
    <script src="<c:url value="/resources/ueditor/ueditor.all.min.js"/>"></script>
    <script src="<c:url value="/resources/jquery/jquery-2.1.4.min.js"/>"></script>
    <script src="<c:url value="/resources/semantic/semantic.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/ueditor/lang/zh-cn/zh-cn.js"/>"></script>
    <script src="<c:url value="/resources/js/common.js"/>"></script>
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
        <div class="twelve wide column">
            <div class="ui pointing secondary menu">
                <a class="item active" data-tab="first">写新文章</a>
                <a class="item" data-tab="first">文章管理</a>
                <a class="item" href="<c:url value="/manager/category"/> " data-tab="second">类别管理</a>
                <a class="item" data-tab="third">评论管理</a>
                <a class="item " data-tab="four">草稿箱</a>
                <a class="item " data-tab="five">回收站</a>
                <a class="item " data-tab="six">主题配置</a>
            </div>
            <form id="post-form" action="<c:url value="/manager/article/post"/>" class="ui form" method="post">
                <table class="ui blue table">
                    <tr>
                        <td style="width:10%">
                            <div id="style-dropdown" class="ui teal labeled icon dropdown">
                                <input type="hidden" name="type" value="原创">
                                <i class="filter icon"></i>
                                <span class="text">原创</span>

                                <div class="menu">
                                    <div class="item">
                                        原创
                                    </div>
                                    <div class="item">
                                        转载
                                    </div>
                                    <div class="item">
                                        翻译
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td style="width:90%">
                            <div class="inline field">
                                <label>标题</label>
                                <input type="text" name="title" placeholder="" style="width:600px;">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="padding:0;margin:0">
                            <div class="inline field">
                                <script id="editor" type="text/plain"></script>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="field">
                                <label>文章标签</label>

                                <div style="position:relative">
                                    <div id="tag-list"
                                         style="position:absolute;z-index:101;float:left;left:4px;top:5px;">
                                    </div>
                                </div>
                                <input id="tag-input" type="text" name="tags" placeholder="" style="width:70%"
                                       maxlength="45">

                                <%--<div class="ui flowing popup top left transition hidden">--%>
                                <%--<div style="width:550px;">--%>
                                <%--<h4 class="ui header">推荐标签</h4>--%>

                                <%--<p>--%>
                                <%--<a class="ui tag label">New</a>--%>
                                <%--<a class="ui red tag label">Upcoming</a>--%>
                                <%--<a class="ui teal tag label">Featured</a>--%>
                                <%--</p>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <div style="float:right;vertical-align: middle;width:30%;padding-top:10px;padding-left:10px;"
                                     class="site-mini-font">最多45个字符,标签以逗号分隔
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="field">
                                <label>文章分类</label>
                                <table style="width:100%;padding:0;margin:0">
                                    <tr>
                                        <td style="width:70%;padding:0;margin:0">
                                            <div id="category_select"
                                                 class="ui fluid search selection dropdown">
                                                <input type="hidden" name="categoryId">
                                                <i class="dropdown icon"></i>

                                                <div class="default text">请选择</div>
                                                <div id="category_menu" class="menu">
                                                    <c:forEach var="category" items="${requestScope.list}">
                                                        <div class="item"
                                                             data-value="${category.id}">
                                                             ${category.name}
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </td>
                                        <td style="width:30%">
                                            <div style="vertical-align: middle;" class="site-mini-font">最多只能选择一个类别
                                            </div>
                                        </td>
                                    </tr>
                                </table>

                            </div>

                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div id="ref-field" class="field" style="width:70%;display:inline-block;">
                                <label>参考引用</label>
                                <input type="text" name="reference" placeholder="参考引用 URL">
                            </div>
                            <button onclick="return addReference(this);"
                                    style="display:inline-block;vertical-align: middle;margin-top:15px;margin-left:5px;"
                                    class="ui positive button">添加
                            </button>
                            <div id="ref-list" class="ui middle aligned selection list"
                                 style="width:70%;display: none;">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <table style="width:100%;padding:0;margin:0">
                                <tr>
                                    <td style="width:70%;padding:0;margin:0">
                                        <div class="field">
                                            <label style="font-size:13px">摘要</label>
                                            <textarea title="摘要" name="digest" rows="4"></textarea>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="vertical-align: middle;" class="site-mini-font">默认选取文章前200个字符</div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="field" style="width:70%">
                                <label>设置</label>

                                <div class="ui segment">
                                    <div class="ui toggle checkbox checked">
                                        <label>是否对所有人可见？</label>
                                        <input placeholder="" type="checkbox" name="isHide" checked tabindex="0"
                                               class="hidden">
                                    </div>
                                </div>
                                <div class="ui segment">
                                    <div class="ui toggle checkbox checked">
                                        <label>是否允许评论？</label>
                                        <input placeholder="" type="checkbox" name="allowComment" checked tabindex="0"
                                               class="hidden">
                                    </div>
                                </div>
                                <div class="ui segment">
                                    <div class="ui toggle checkbox checked">
                                        <label>是否自动生成目录？</label>
                                        <input placeholder="" type="checkbox" name="autoIndex" checked tabindex="0"
                                               class="hidden">
                                    </div>
                                </div>
                                <div class="ui segment">
                                    <div class="ui toggle checkbox">
                                        <label>是否在博客列表置顶？</label>
                                        <input placeholder="" type="checkbox" name="isTop" tabindex="0" class="hidden">
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <input type="button" id="post" class="ui blue button" value="发表文章">
                            <input type="button" id="draft" class="ui teal button" value="保存草稿">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="two wide column"></div>
    </div>
</div>
<script>
    // tags queue
    var tagQueue = [];
    var ue;
    // tag color
    var colors = ['red', 'orange', 'yellow', 'olive', 'green', 'teal', 'blue', 'violet', 'purple', 'pink', 'brown', 'grey', 'black'];

    /**
     * Show tags
     */
    function layoutTag(tag) {
        tag.empty();
        for (var i = 0; i < tagQueue.length; i++) {
            var entity = tagQueue[i];
            var item = $('<span onclick="deleteTag(this)" id="tag-item-' + entity['key'] + '" title="单击删除该标签" class="ui '+ colors[randomNumber(colors.length)]+' tag label">' + entity['value'] + '</span>');
            tag.append(item);
        }
    }
    /**
     * Delete tags
     * @param e
     */
    function deleteTag(e) {
        var id = $(e).attr('id');
        var key = id.substring(id.lastIndexOf('-') + 1);
        for (var i = 0; i < tagQueue.length; i++) {
            var entity = tagQueue[i];
            if (entity['key'] == key) {
                tagQueue.splice(i, 1);
                break;
            }
        }
        layoutTag($('#tag-list'));
    }

    /**
     * concat tags
     */
    function concatTags() {
        var tags = "";
        for (var i = 0; i < tagQueue.length; i++) {
            tags += tagQueue[i].value + ',';
        }
        if (tags.trim().length > 0) {
            tags = tags.substring(0, tags.length - 1);
        }
        return tags;
    }

    /**
     * Add reference url
     */
    function addReference() {
        var ref = $('input[name="reference"]').val();
        if (ref.trim() == '' || !validURL(ref)) {
            $('#ref-field').addClass('error');
            return false;
        }
        $('#ref-list')
                .show()
                .append(
                        $(
                                '<div class="item">' +
                                '<div class="content">' +
                                '<a class="header" style="display:inline-block" href="' + ref + '">' + ref + '</a>' +
                                '<a href="javascript:void(0);" onclick="delReference(this)" style="float:right;display:inline-block">删除</a>' +
                                '</div>' +
                                '</div>'
                        )
                );
        return false;
    }

    /**
     * Delete reference url
     */
    function delReference(e) {
        var item = $(e).parent().parent();
        $('#ref-list')[0].removeChild(item[0]);
    }

    function concatReference() {
        var digests = "";
        $('#ref-list').children().each(function () {
            digests += $(this).find('a[class="header"]').attr('href') + '@';
        });
        if (digests.trim().length > 0) {
            digests = digests.substring(0, digests.length - 1);
        }
        return digests;
    }
    /**
     * Get default digest
     */
    function getDefaultDigest() {
        if (ue) {
            return ue.getContentTxt().trim().substring(0, 200);
        }
        return '';
    }

    /**
     * Page init
     */
    $(function () {

        // Init global menu
        $('.ui.menu a.item')
                .on('click', function () {
                    $(this)
                            .addClass('active')
                            .siblings()
                            .removeClass('active')
                    ;
                })
        ;

        // Init global dropdown
        $('.dropdown')
                .dropdown({
                    action: 'hide'
                })
        ;

        // Exclude this dropdown element
        $('#style-dropdown').dropdown({
            transition: 'drop'
        });

        // Init checkbox
        $('.ui.checkbox')
                .checkbox()
        ;

        // Init global link
        $('.ui.link')
                .popup({
                    on: 'click',
                    hoverable: true,
                    closable: true,
                    position: 'top left'
                })
        ;
        // Init default digest value
        $('textarea[name="digest"]').focus(function () {
            if ($(this).val().trim() == '') {
                if (ue) {
                    $(this).val(getDefaultDigest());
                }
            }
        });

        // eliminate error
        $('input[name="reference"]').focus(function () {
            $('#ref-field').removeClass('error');
        });

        // Add color on category items.
        $('#category_menu').children().each(function(){
            var txt = $(this).html();
            var icon = $('<div class="ui '+colors[randomNumber(colors.length)]+' empty circular label"></div>');
            $(this).empty().append(icon).append(txt);
        });

        // Operate tag popup element
        $('#tag-input').popup({
            on: 'focus',
            hoverable: true,
            closable: true,
            position: 'bottom center'
        }).on('focus',
                function () {
                    var tagList = $('#tag-list');
                    $(this).css('padding-left', tagList.width() + parseInt(tagList.css('left')));
                }
        ).blur(
                function () {
                    var value = $(this).val();
                    if (value.trim() != '') {
                        value = value.replace(/，/ig, ',');
                        var list = value.split(',');
                        var tagList = $('#tag-list').empty();
                        for (var i = 0; i < list.length; i++) {
                            if (list[i].trim() == '') continue;
                            tagQueue.push({key: (tagQueue.length + i), value: list[i]});
                        }
                        layoutTag(tagList);
                        var width = tagList.width() + parseInt(tagList.css('left'));
                        if (width > $(this).width()) {
                            $(this).css({'width': width});
                        }
                        $(this).val('');
                    }
                }
        );

        // Init form element
        $('.ui.form').form({
            fields: {
                title: {
                    identifier: "title",
                    rules: [
                        {
                            type: "empty",
                            prompt: "标题名称不能为空"
                        }
                    ]
                },
                categoryId: {
                    identifier: "categoryId",
                    rules: [
                        {
                            type: "empty",
                            prompt: "请选择文章所属分类"
                        }
                    ]
                }
            }
        });

        // Init category list
        $('#category_select')
                .dropdown({
                    maxSelections: 3
                })
        ;

        // Init global UEditor plugin
        ue = UE.getEditor('editor', {
            serverUrl: "<c:url value="/editor/control"/>",
            textarea: 'content'
        });

        /**
         * Post blog
         */
        $('#post').click(function () {
            if (!ue) {
                return false;
            }
            // Set or filter field's value.
            $('#tag-input').val(concatTags());
            $('input[name="reference"]').val(concatReference());
            $('.ui.checkbox').each(function () {
                var checkbox = $(this).find('input[type="checkbox"]');
                if ($(this).hasClass('checked')) {
                    checkbox.val(1);
                } else {
                    checkbox.val(0);
                }
            });
            // Set default digest
            var digest = $('textarea[name="digest"]').val();
            if (digest.trim() == '') {
                if (ue) {
                    digest.val(getDefaultDigest());
                }
            }
            // Submit
            var myForm = $('#post-form');
            if (myForm.form('validate form')) {
                myForm.submit();
            }
            return false;
        });
    });
</script>

</body>
</html>
