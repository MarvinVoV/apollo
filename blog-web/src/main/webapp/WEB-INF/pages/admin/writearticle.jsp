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
    <script src="<c:url value="/resources/jquery/jquery-2.1.4.min.js"/>"></script>
    <script src="<c:url value="/resources/semantic/semantic.js"/>"></script>
    <script src="<c:url value="/resources/tinymce/tinymce.js"/>"></script>
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
                                <textarea id="editor"></textarea>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="field">
                                <label>文章标签</label>
                                <input type="text" name="first-name" placeholder="" style="width:600px;">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="field">
                                <label>文章分类</label>
                                <input type="text" name="first-name" placeholder="" style="width:600px;">
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


        tinymce.init({
            selector: '#editor',
            theme: "modern",
            width: 1024,
            height: 300,
            max_height: 600,
            max_width: 1024,
            min_height: 300,
            min_width: 500,
            resize: 'both',
            auto_focus: "editor",
            browser_spellcheck: true,
            skin: "lightgray",
            contextmenu: false,
            language: "zh_CN",
            cache_suffix: "?v=4.1.6",
            allow_script_urls: true,
            convert_urls: true,
            custom_undo_redo_levels: 10,
            image_caption: true,
            media_live_embeds: true,
            paste_auto_cleanup_on_paste: false,
            plugin_preview_height: 500,
            plugin_preview_width: 650,
            nonbreaking_force_tab: true, //tab key
            autoresize_bottom_margin: 50,
            autoresize_max_height: 500,
            autoresize_min_height: 350,
            autoresize_on_init: false,
            autoresize_overflow_padding: 2,
            autosave_ask_before_unload: true,
            autosave_interval: "20s",
            autosave_restore_when_empty: true,
            autosave_retention: "30m",
            pagebreak_separator: "<!-- my page break -->",
            pagebreak_split_block: true,
            paste_data_images: true,
            code_dialog_height: 500,
            code_dialog_width: 750,
            insertdatetime_formats: ["%H:%M:%S", "%Y-%m-%d", "%I:%M:%S %p", "%D"],
//        automatic_uploads: false,
//        object_resizing : true,  // img
//        end_container_on_empty_block: true,
//        nowrap : true,
            toolbar: [
                "fullpage, code | undo, redo | styleselect | bold, italic, underline, strikethrough | alignleft, aligncenter, alignright, alignjustify | bullist, numlist, outdent, indent | table | link, image, media | subscript, superscript, charmap, blockquote",
                "print, preview | forecolor, backcolor | emoticons, formatselect, fontselect, fontsizeselect | cut, copy, paste, pastetext | codesample | removeformat, insertdatetime ,nonbreaking,pagebreak,searchreplace | fullscreen"
            ],
            init_instance_callback: function (editor) {
                console.log("Editor: " + editor.id + " is now initialized.");
            },
            color_picker_callback: function (callback, value) {
                callback('#FF00FF');
            },

//        file_browser_callback: function(field_name, url, type, win) {
//            win.document.getElementById(field_name).value = 'my browser value';
//        },
//        urlconverter_callback : function(url, node, on_save, name){
//            url = url.substring(3);
//
//            // Return new URL
//            return url;
//        },

//        file_picker_callback: function(callback, value, meta) {
//            // Provide file and text for the link dialog
//            if (meta.filetype == 'file') {
//                callback('mypage.html', {text: 'My text'});
//            }
//
//            // Provide image and alt text for the image dialog
//            if (meta.filetype == 'image') {
//            }
//        },

//        images_upload_url: "postAcceptor.php",
//        images_upload_base_path: "/some/basepath",
//        images_upload_handler: function (blobInfo, success, failure) {
//            var xhr, formData;
//            xhr = new XMLHttpRequest();
//            xhr.withCredentials = false;
//            xhr.open('POST', "postAcceptor.php");
//            xhr.onload = function() {
//                var json;
//
//                if (xhr.status != 200) {
//                    failure("HTTP Error: " + xhr.status);
//                    return;
//                }
//                json = JSON.parse(xhr.responseText);
//
//                if (!json || typeof json.location != "string") {
//                    failure("Invalid JSON: " + xhr.responseText);
//                    return;
//                }
//                success(json.location);
//            };
//            formData = new FormData();
//            formData.append('file', blobInfo.blob(), fileName(blobInfo));
//            xhr.send(formData);
//        },
            plugins: [
                "advlist autolink link image colorpicker imagetools lists charmap print preview hr anchor pagebreak spellchecker",
                "searchreplace wordcount visualblocks visualchars code codesample fullscreen insertdatetime media nonbreaking",
                "save table contextmenu directionality emoticons template paste textcolor fullpage codesample autoresize autosave tabfocus"
            ],
            templates: [
                {title: 'Test template 1', content: 'Test 1'},
                {title: 'Test template 2', content: 'Test 2'}
            ]
        });
    });
</script>

</body>
</html>
