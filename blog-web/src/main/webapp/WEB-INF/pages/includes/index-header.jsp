<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2015/11/17
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <a class="active item" href="<c:url value="/admin/index"/>" style="width:5%;min-width:60px;">首页</a>

    <a class="item" href="javascript:void(0);" style="width:5%;min-width:60px;">发现</a>
    <a class="item" style="width:5%;min-width:60px;" href="javascript:void(0);">博客</a>

    <div class="item" style="width:10%"></div>
    <a class="item" style="max-width:50px;" href="javascript:void(0);"> <i class="alarm icon"></i></a>

    <div class="item" style="min-width:125px;">
        <div class="ui inline dropdown">
            <div class="text">
                <c:set var="header" value="/resources/images/default-head.png" scope="page"/>
                <c:if test="${not empty sessionScope.user.header}">
                    <c:set var="header" value="${sessionScope.user.header}" scope="page"/>
                </c:if>
                <img class="ui middle aligned mini image sun-header"
                     src="<c:url value="${pageScope.header}"/>">
            </div>
            <i class="dropdown icon"></i>

            <div class="menu">
                <a class="item" href="<c:url value="/admin/index"/> ">
                    <i class="user icon"></i>
                    博客管理
                </a>
                <div class="item">
                    <i class="mail icon"></i>
                    私信
                </div>
                <a class="item" href="<c:url value="/setting/profile"/> ">
                    <i class="setting icon"></i>
                    设置
                </a>
                <a class="item" href="<c:url value="/logout" />">
                    <i class="sign out icon"></i>
                    登出
                </a>
            </div>
        </div>
    </div>
    <div class="item" style="width:10%"></div>
</div>
