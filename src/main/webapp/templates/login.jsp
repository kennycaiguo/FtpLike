<%@ page import="javax.servlet.http.Cookie"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Boolean isLogin = (Boolean)request.getAttribute("isLogin");
    Boolean errLog  = (Boolean)request.getAttribute("errLog");
    Boolean errMail = (Boolean)request.getAttribute("errMail");
    Boolean errPass = (Boolean)request.getAttribute("errPass");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset='UTF-8' />
	    <link rel="icon" href="/res/icons/icon.jpg" />
	    <title>File Tree View</title>
	    <link rel="stylesheet" href="" />
	</head>
	<body>
		<form class="login" action="/login" method="post" name="login" style='<%= (isLogin ? "" : "display: none;") %>'>
			<p> Login </p>
			<input type="text" name="login" placeholder="login or email" />
			<input type="password" name="password" placeholder="password"/>
			<input class="btn" type="submit" />
		</form>

	    <form class="register" action="/register" method="post" name="register" style='<%= (isLogin ? "display: none" : "") %>'>
	        <p>Register</p>
	        <input type="text" name="login" placeholder="login" />
	        <input type="text" name="email" placeholder="email" />
	        <input type="password" name="password" placeholder="password" />
	        <input class="btn" type="submit" />
	    </form>
	    <%
	    	if(errLog){
	    		out.println("<h6 style='color:red'>login error</h6>");
	    	}
	    	if(errMail){
	    		out.println("<h6 style='color:red'>email error</h6>");
	    	}
	    	if(errPass){
	    		out.println("<h6 style='color:red'>password error</h6>");
	    	}
	    %>
	    <div class="authForm">
	    	<span>Login</span>
		    <input class="inp-login" type="radio" name="authchose" value="lgn" checked onchange="changeForm(this.value)" />
		    <input class="inp-register" name="authchose" type="radio" value="rgs" onchange="changeForm(this.value)" />
		    <span>Register</span>
		</div>
	</body>

	<script type="text/javascript">
		function changeForm(value) {
			let lgn = document.querySelector(".inp-login");
			let rgn = document.querySelector(".inp-register");
			let rgnform = document.querySelector(".register");
			let lgnform = document.querySelector(".login");

 			if(value == 'lgn'){
 				rgnform.style.display = "none";
				lgnform.style.display = "";
			}else{
				rgnform.style.display = "";
				lgnform.style.display = "none";
			}

		}
	</script>
</html>