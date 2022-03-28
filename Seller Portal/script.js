function preventBack() {
	window.history.forward();
}

setTimeout("preventBack()", 0);

window.onunload = function () {
	null
};

window.onload = e => {
	localStorage.clear();
}

const signUpButton = document.getElementById("signUp");
const signInButton = document.getElementById("signIn");
const container = document.getElementById("container");
var login = document.getElementById("loginForm");
var signup = document.getElementById("signUpform");

login.addEventListener("submit", function (e) {
	e.preventDefault();
});

signup.addEventListener("submit", function (e) {
	e.preventDefault();
});

signUpButton.addEventListener("click", () => {
	container.classList.add("right-panel-active");
});

signInButton.addEventListener("click", () => {
	container.classList.remove("right-panel-active");
});

function register() {
	var name = document.signUp.fullname.value;
	var regNo = document.signUp.regNo.value;
	var dob = document.signUp.dob.value;
	var date = new Date();
	var birthdate = new Date(dob);
	var age = date.getFullYear() - birthdate.getFullYear();
	var m = date.getMonth() - birthdate.getMonth();
	if (m < 0 || (m === 0 && date.getDate() < birthdate.getDate())) {
		age--;
	}
	var email = document.signUp.email.value;
	var pass = document.signUp.pass.value;
	var cpass = document.signUp.cpass.value;
	if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
		alert("Invalid Email ID");
	} else if (pass != cpass) {
		alert("You password does not match with confirm password");
	} else if (age < 18) {
		alert("You are under the minimum required age");
	} else {
		const http = new XMLHttpRequest();
		const url = "http://localhost:8080/signup";
		http.open("POST", url);
		http.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
		http.send(
			JSON.stringify({
				name: name,
				userName: email,
				password: pass,
				dob: dob,
				registration: regNo,
			})
		);
		http.onreadystatechange = e => {
			if (http.responseText === "Signup Successfull") {
				alert("You are registered successfully!");
				window.location.href = "./index.html";
			} else if (http.responseText === "User already exists") {
				alert("An account already exists with this Email ID");
			}
		};
	}
}

function signin() {
	var mail = document.login.email.value;
	var password = document.login.password.value;
	if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail)) {
		alert("Invalid Email ID");
		window.location.replace("./index.html");
	} else {
		const http = new XMLHttpRequest();
		const url = "http://localhost:8080/login";
		http.open("GET", url);
		http.setRequestHeader(
			"Authorization",
			"Basic " + btoa(mail + ":" + password)
		);
		http.send();
		http.onreadystatechange = e => {
			if (http.status === 200) {
				var response = JSON.parse(http.responseText);
				console.log(response);
				if (response.Status === "APPROVED" && response.Role === "ROLE_USER") {
					localStorage.setItem("email", mail);
					localStorage.setItem("password", password);
					localStorage.setItem("isAdmin", "false");
					window.location.replace("./homepage/homepage.html");
				} else if (
					response.Status === "APPROVED" &&
					response.Role === "ROLE_ADMIN"
				) {
					localStorage.setItem("email", mail);
					localStorage.setItem("password", password);
					localStorage.setItem("isAdmin", "true");
					window.location.replace("./admin-dashboard/dashboard.html");
				} else if (response.Status === "PENDING") {
					window.location.href = "./index.html";
					alert(
						"Your account is under verification. You can log in once it is approved."
					);
				} else if (response.Status === "DENIED") {
					window.location.href = "./index.html";
					alert("Sorry! You do not have access to login.");
				}
			} else if (http.status === 401) {
				window.location.replace("./index.html");
				alert("Incorrect Email ID and Password combination");
			}
		};
	}
}