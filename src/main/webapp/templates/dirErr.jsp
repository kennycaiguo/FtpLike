<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String URI = (String)request.getAttribute("URI");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset='UTF-8' />
        <link rel="icon" href="res/icons/icon.jpg">
        <link rel="stylesheet" type="text/css" href="src/css/errStyles.css" />
        <title>FTP-Like</title>
    </head>
    <body>
        <h1 class='Title'> ERROR </h1>
        <h3 class='errortext'> Incorrect path!!!</h3>
        <p>it someone else dir</p>
        <a class="btn homedir-btn" href="/">To HomeDir</a>
    </body>
</html>