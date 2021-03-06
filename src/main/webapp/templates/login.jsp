<%@ page import="javax.servlet.http.Cookie"%>
<%@ page session="true"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String URI = (String)request.getAttribute("URI");
    Boolean isLgnForm = (Boolean)request.getSession().getAttribute("isLgnForm");
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
			<div class="logform" style='<%= (isLgnForm ? "" : "display: none;") %>'>
				<p> Login </p>
				<div class="inputs">
					<input class="login" type="text" name="login" placeholder="login or email" maxlength="15" required />
					<input class="password" type="password" name="password" placeholder="password" maxlength="50" required />
				</div>
				<div class="btn submit-btn" onclick="sendSubmit('<%=request.getContextPath()%>', 'login')">Sign In</div>
			</div>
		    <div class="regform" style='<%= (isLgnForm ? "display: none;" : "") %>'>
		    	<p>Register</p>
		    	<div class="inputs">
		    		<input class="login" name="login" type="text" placeholder="login" maxlength="15" required />
		    		<input class="mail" name="mail" type="text" placeholder="email" maxlength="50" required pattern="^\S+@\S+\.\S+$"/>
		    		<input class="password" name="password" type="password" placeholder="password" maxlength="50" required />
		    	</div>
		    	<div class="btn submit-btn" onclick="sendSubmit('<%=request.getContextPath()%>', 'register')">Sign Up</div>
		    </div>
		    <div class="authform">
		    	<span>Login</span>
		    	<div class='inp-login <%= (isLgnForm ? " checked" : "") %>' onclick='showLog(this)'></div>
		    	<div class='inp-register <%= (isLgnForm ? "" : "checked") %>' onclick="showReg(this)"></div>
			    <span>Register</span>
			</div>
			<div class='error' style='visibility: hidden'>Incorrect email/password</div>
		</div>
	</body>
	<script type="text/javascript" src="src/js/swforms.js"></script>
	<script type="text/javascript" src="src/js/ajaxerr.js"></script>
</html>