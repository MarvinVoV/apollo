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
            <form class="ui form">
                <table class="ui blue table">
                    <tr>
                        <td style="width:10%">
                            <div id="style-dropdown" class="ui teal labeled icon dropdown">
                                <input type="hidden" name="filters">
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
                                <input type="text" name="first-name" placeholder="" style="width:600px;">
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
                                        <%--<span title="单击删除该标签" class="ui tag label">hello</span>--%>
                                    </div>
                                </div>
                                <input id="tag-input" type="text" name="country" placeholder="" style="width:70%"
                                       maxlength="45">

                                <div class="ui flowing popup top left transition hidden">
                                    <div style="width:550px;">
                                        <h4 class="ui header">推荐标签</h4>

                                        <p>
                                            <a class="ui tag label">New</a>
                                            <a class="ui red tag label">Upcoming</a>
                                            <a class="ui teal tag label">Featured</a>
                                        </p>
                                    </div>
                                </div>
                                <div style="float:right;vertical-align: middle;width:30%;padding-top:10px;padding-left:10px;"
                                     class="site-mini-font">最多45个字符,标签以逗号分隔
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="field" style="width:70%">
                                <label>文章分类</label>
                                <table style="width:100%">
                                    <tr>
                                        <td style="width:70%">
                                            <div id="category_select" class="ui fluid multiple search selection dropdown">
                                                <input type="hidden" name="country">
                                                <i class="dropdown icon"></i>

                                                <div class="default text">Select Country</div>
                                                <div class="menu">
                                                    <div class="item" data-value="af"><i class="af flag"></i>Afghanistan</div>
                                                    <div class="item" data-value="ax"><i class="ax flag"></i>Aland Islands</div>
                                                    <div class="item" data-value="zm"><i class="zm flag"></i>Zambia</div>
                                                    <div class="item" data-value="zw"><i class="zw flag"></i>Zimbabwe</div>
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            <div style="float:right;vertical-align: middle;"
                                                 class="site-mini-font">同时最多加入三个类别
                                            </div>
                                        </td>
                                    </tr>
                                </table>

                            </div>

                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="field">
                                <label>参考引用</label>
                                <input type="text" name="first-name" placeholder="" style="width:600px;">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="field">
                                <label>摘要</label>
                                <input type="text" name="first-name" placeholder="" style="width:600px;">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <button class="ui blue button" type="submit">发表文章</button>
                        </td>
                    </tr>
                </table>
            </form>

        </div>

        <div class="two wide column"></div>
    </div>
</div>
<script>

    var tagQueue = [];
    function layoutTag(tag) {
        tag.empty();
        for (var i = 0; i < tagQueue.length; i++) {
            var entity = tagQueue[i];
            var item = $('<span onclick="deleteTag(this)" id="tag-item-' + entity['key'] + '" title="单击删除该标签" class="ui tag label">' + entity['value'] + '</span>');
            tag.append(item);
        }
    }
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
        $('#style-dropdown').dropdown({
            transition: 'drop'
        });

        $('.ui.link')
                .popup({
                    on: 'click',
                    hoverable: true,
                    closable: true,
                    position: 'top left'
                })
        ;
        $('#tag-input').popup({
                    on: 'focus',
                    hoverable: true,
                    closable: true,
                    position: 'bottom center'
                }).on('focus', function (e) {
                        var tagList = $('#tag-list');
                        $(this).css('padding-left', tagList.width() + parseInt(tagList.css('left')));
                    }
        ).blur(function (e) {
            var value = $(this).val();
            if (value.trim() != '') {
                value = value.replace(/，/ig, ',');
                console.log(value);
                var list = value.split(',');
                var tagList = $('#tag-list');
                tagList.empty();
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
        });

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

        $('#category_select')
                .dropdown({
                    maxSelections: 3
                })
        ;

        var ue = UE.getEditor('editor', {
            serverUrl: "<c:url value="/editor/control"/>"
        });

    });
</script>

</body>
</html>
