function sendSubmit(contextPath, type) {
	let diverr = document.querySelector(".error");
	let body;

	if(type == "register"){
		body = getBody(document.querySelector(".regform").childNodes[3]);
	}
	else{
		body = getBody(document.querySelector(".logform").childNodes[3]);
	}


	let xhreq = new XMLHttpRequest();
	let path = contextPath + "/" + type;

	xhreq.open("POST", path, true);

	xhreq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhreq.send(body);
	alert(xhreq.responseText);
	if(xhreq.responseText == "error"){
		diverr.style.visibility = "visible";
	}
}
	
function getBody(form) {
	let login;
	let email;
	let password;

	for(var i = 0; i < form.childNodes.length; i++){
		if(form.childNodes[i].className == "login"){
			login = form.childNodes[i].value;
		}
		if(form.childNodes[i].className == "mail"){
			email = form.childNodes[i].value;
		}
		if(form.childNodes[i].className == "password"){
			password = form.childNodes[i].value;
		}
	}

	let body = "login=" + encodeURIComponent(login) + "&email=" + encodeURIComponent(email) + "&password=" + encodeURIComponent(password);
	return body;
}