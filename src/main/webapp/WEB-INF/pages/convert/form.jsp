<%--
  Created by IntelliJ IDEA.
  User: sunyameng
  Date: 2014/5/12
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
    <form:form commandName="article" action="/convert/formAction.do" method="post">
        <table>
            <tr>
                <td>
                    <form:label path="title">Title</form:label>
                </td>
                <td><form:input path="title"/></td>
            </tr>
            <tr>
                <td><form:label path="attachments">Attachments</form:label></td>
                <td><form:input path="attachments"/></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="submit"/>
                </td>
                <td>
                    <input type="reset" value="Reset"/>
                </td>
            </tr>
        </table>

    </form:form>
</body>
</html>
