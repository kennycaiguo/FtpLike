function showReg(value) {
	let lgn = document.querySelector(".inp-login");
	let rgnform = document.querySelector(".regform");
	let lgnform = document.querySelector(".logform");

	if(!value.classList.contains("checked")){
		rgnform.style.display = "";
		lgnform.style.display = "none";
		lgn.classList.remove("checked");
		value.classList.add("checked");
	}
}
function showLog(value){
	let rgn = document.querySelector(".inp-register");
	let rgnform = document.querySelector(".regform");
	let lgnform = document.querySelector(".logform");

	if(!value.classList.contains("checked")){
		rgnform.style.display = "none";
		lgnform.style.display = "";
		rgn.classList.remove("checked");
		value.classList.add("checked");
	}
}