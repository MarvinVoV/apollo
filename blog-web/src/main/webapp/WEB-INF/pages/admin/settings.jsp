<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2015/11/7
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>设置</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/semantic/semantic.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/site.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/jquery/plugins/jcrop/css/jquery.Jcrop.css"/> "
          type="text/css"/>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/semantic/themes/basic/assets/fonts/icons.ttf"/>">
    <script src="<c:url value="/resources/jquery/jquery-2.1.4.min.js"/>"></script>
    <script src="<c:url value="/resources/semantic/semantic.js"/>"></script>
    <script type="application/javascript"
            src="<c:url value="/resources/jquery/plugins/jcrop/js/jquery.Jcrop.js"/>"></script>
    <script src="<c:url value="/resources/js/common.js"/>"></script>
    <style type="text/css">

        .sun-file-input {
            position: relative;
            width: 160px;
            vertical-align: middle;
            text-align: center;
            display: inline-block;
            margin: 0;
            padding: 0;
            height: 40px;
            right: 0;
            top: 0;
            font-size: 100px;
            opacity: 0;
            filter: alpha(opacity=0);
        }
    </style>
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
                    设置
                </div>
                <a class="item" href="<c:url value="/setting/profile"/>" style="color:#4078c0;font-size:13px;">
                    账户设置
                </a>
                <a class="item" style="color:#4078c0;font-size:13px;">
                    隐私设置
                </a>
                <a class="item" style="color:#4078c0;font-size:13px;">
                    邮件和消息
                </a>
            </div>
        </div>
        <div id="panel" class="nine wide column">
            <div id="basicProfile" class="ui vertical menu" style="width:100%">
                <div class="header item" style="color:#555;font-size:13px;">
                    个人资料
                </div>
                <div class="item">
                    <div style="margin-bottom:10px;">
                        <h4 class="ui header">Profile picture</h4>
                        <table>
                            <tr>
                                <td>
                                    <div>
                                        <c:set var="header" value="/resources/images/default-head.png"
                                               scope="page"/>
                                        <c:if test="${not empty sessionScope.user.header}">
                                            <c:set var="header" value="${sessionScope.user.header}" scope="page"/>
                                        </c:if>
                                        <img id="showImage" class="ui middle aligned tiny image sun-header"
                                             src="<c:url value="${pageScope.header}"/>">
                                    </div>
                                </td>
                                <td>
                                    <div style="margin-left:20px;vertical-align: middle">
                                        <a style="width:160px;height:40px;margin:0;padding:0;vertical-align: middle;text-align: center;overflow:hidden;"
                                           class="ui positive button">
                                            <div style="position:fixed;padding-top:12px;padding-left:50px;">更新头像
                                            </div>
                                            <input class="sun-file-input" type="file" accept="image/*" name="file"
                                                   id="fileToUpload"/>
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <form class="ui form" action="<c:url value="/setting/updateEmail"/>" method="post">
                        <div class="field">
                            <label style="color:#666">用户名</label>
                            <input type="text" name="userId" value="${sessionScope.user.userName}" readonly
                                   placeholder="First Name">
                        </div>
                        <div class="field">
                            <label style="color:#666">公开邮箱</label>
                            <input type="text" name="email" value="${sessionScope.user.email}"
                                   placeholder="Last Name">
                        </div>
                        <div class="ui error message"></div>
                        <button class="ui primary button" type="submit">Submit</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="two wide column"></div>
    </div>

    <!-- mask div -->
    <div class="ui modal" >
        <div class="header">
            剪裁、调整
        </div>
        <div class="image content" style="margin:0;padding:0;overflow: hidden;">
            <img id="cropImage" class="ui middle aligned image"
                 src="<c:url value="/resources/images/default-head.png"/>">
        </div>
        <div class="actions">
            <div id="crop" class="ui button">OK</div>
            <div id="cropCancel" class="ui button">Cancel</div>
        </div>
    </div>

</div>
<script>

    var dataURL;
    var cor;
    var jcrop_api;

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
                userId: {
                    identifier: "userId",
                    rules: [
                        {
                            type: "empty",
                            prompt: "用户名不能为空"
                        }
                    ]
                },
                email: {
                    identifier: "email",
                    rules: [
                        {
                            type: "empty",
                            prompt: "邮箱不能为空"
                        },
                        {
                            type: 'email',
                            prompt: '请输入有效的邮箱地址'
                        }
                    ]
                }
            }
        });

        $('#cropCancel').click(function(){
            var modal = $('.ui.modal');
            if(jcrop_api) {
                jcrop_api.destroy();
            }
            dataURL = null;
            $('#fileToUpload').val(null);
            modal.modal('hide');
        });

        // Header profile process
        $('#crop').click(function () {

            var modal = $('.ui.modal');
            var img = modal.find('img').get(0);
            var canvas = document.createElement('canvas');
            canvas.width = cor.w;
            canvas.height = cor.h;
            var ctx = canvas.getContext("2d");
            ctx.drawImage(img, cor.x, cor.y, cor.w, cor.h, 0, 0, cor.w, cor.h);
            var data = canvas.toDataURL("image/jpeg");
            // update
            $('img').each(function () {
                if ($(this).hasClass('sun-header')) {
                    $(this).attr('src', data);
                }
            });

            // destroy jcrop
            if (jcrop_api) {
                jcrop_api.destroy();
            }
            $(this).addClass("loading");
            var that = this;
            $.ajax({
                type: 'post',
                data: {'header': data},
                url: '<c:url value="/setting/updateHeader"/>',
                success: function (e) {
                    $(that).removeClass("loading");
                    modal.modal('hide');
                },
                error: function (e) {
                    alert(e);
                }
            });

        });

        if (window.File && window.FileReader && window.FileList && window.Blob) {
            $('input[type=file]').change(function (e) {
                var file = e.target.files[0];
                if (!file.type.match('image.*'))
                    return;
                if (file.size > 512 * 1000) {  // max 512kb
                    alert("图片过大,请限制在1M以内");
                    return;
                }
                var fileReader = new FileReader();
                fileReader.onload = function (evt) {
                    var img = new Image();
                    img.src = evt.target.result;
                    var newImg = resizeImage(img, 430, 430);
                    layoutCropImageUI(newImg);
                };
                fileReader.readAsDataURL(file);
            });
        } else {
            alert("浏览器不支持html5");
        }
    });

    /**
     *  helper function
     *
     * */
    function layoutCropImageUI(img) {
        var H = img.height;
        var W = img.width;
        var data = img.src;

        // load modal
        var modal = $('.ui.modal');
        modal.find('img').height(H).width(W);
        modal.modal({
            blurring: true,
            closable: false,
            offset: H / 2
        }).modal('show').width(W).css("marginLeft", -W / 2);
        // load crop plugin
        $('.ui.modal img').empty().attr('src', data).Jcrop({
            boxWidth: 430, boxHeight: 430,
            bgColor: 'black',
            bgOpacity: .5,
            aspectRatio: 1,
            setSelect: [100, 100, 250, 250],
            onSelect: function (e) {
                cor = e;
            }
        }, function () {
            jcrop_api = this;
        });

    }


</script>

</body>
</html>
