<%@ page import="javax.servlet.http.Cookie"%>
<%@ page session="true"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Boolean errLog  = (Boolean)request.getSession().getAttribute("errLog");
    Boolean errMail = (Boolean)request.getSession().getAttribute("errMail");
    Boolean errPass = (Boolean)request.getSession().getAttribute("errPass");
    Boolean isLgnForm = (Boolean)request.getSession().getAttribute("isLgnForm");

    if(errLog == null){
        errLog = false;
    }
    if(errMail == null){
        errMail = false;
    }
    if(errPass == null){
        errPass = false;
    }
    if(isLgnForm == null){
        isLgnForm = true;
    }

%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset='UTF-8' />
	    <link rel="icon" href="res/icons/icon.jpg" />
	    <link rel="stylesheet" href="src/css/logStyle.css" />
	    <title>FTP-Like</title>
	</head>
	<body>
		<div class="wrapper">
			<form class="logform" action="/login" method="post" name="login" style='<%= (isLgnForm ? "" : "display: none;") %>'>
				<p> Login </p>
				<div class="inputs">
					<input class="login" type="text" name="login" placeholder="login or email" required />
					<input class="password" type="password" name="password" placeholder="password" required />
				</div>
				<input class="btn" type="submit" value="Login"/>
			</form>

		    <form class="regform" action="/register" method="post" name="register" style='<%= (isLgnForm ? "display: none;" : "") %>'>
		        <p>Register</p>
		        <div class="inputs">
			        <input class="login" type="text" name="login" placeholder="login" required />
			        <input class="mail" type="text" name="email" placeholder="email" required pattern="^\S+@\S+\.\S+$" />
			        <input class="password" type="password" name="password" placeholder="password" required />
		    	</div>	
		        <input class="btn" type="submit" value="Register"/>
		    </form>
		    
		    <div class="authForm">
		    	<span>Login</span>
		    	<div class='inp-login <%= (isLgnForm ? " checked" : "") %>' onclick='showLog(this)'></div>
		    	<div class='inp-register <%= (isLgnForm ? "" : "checked") %>' onclick="showReg(this)"></div>
			    <span>Register</span>
			</div>
            <%
                if(errLog){
                    out.println("<span class='error'>login error</span>");
                    request.getSession().setAttribute("errLog", false);
                }
                if(errMail){
                    out.println("<span class='error'>email error</span>");
                    request.getSession().setAttribute("errMail", false);
                }
                if(errPass){
                    out.println("<span class='error'>password error</span>");
                    request.getSession().setAttribute("errMail",false);
                }
            %>
		</div>
	</body>
	<script type="text/javascript" src="src/js/swforms.js"></script>
</html>