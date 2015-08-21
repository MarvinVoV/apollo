<%--
  Created by IntelliJ IDEA.
  User: sunyameng
  Date: 2014/5/12
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <!-- standard meta -->
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <title>Template Page</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/semantic-ui/semantic.min.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/jquery/jquery-2.1.4.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/semantic-ui/semantic.min.js"/>"></script>

      <style type="text/css">
          body {
              background-color: #DADADA;
          }
          body > .grid {
              height: 100%;
          }
          .column {
              max-width: 450px;
          }
      </style>
  </head>
  <body>
       <div class="ui middle aligned center aligned grid">
            <div class="column">
                <form class="ui large form">
                    <div class="ui stacked segment">
                        <div class="field">
                            <div class="ui left icon input">
                                <i class="user icon"></i>
                                <input type="text" name="email" placeholder="E-mail address">
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui left icon input">
                                <i class="lock icon"></i>
                                <input type="password" name="password" placeholder="Password">
                            </div>
                        </div>
                        <div class="ui fluid large blue submit button">Login</div>
                    </div>
                </form>
            </div>
       </div>
  </body>
</html>
