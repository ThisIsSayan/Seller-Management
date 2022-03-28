window.onload = e => {
	if (localStorage.length === 0 || localStorage.getItem("isAdmin") === "true") {
		window.location.href = "../error/error.html"
	}
	const url = "http://localhost:8080/getcurrentuser";
	const http = new XMLHttpRequest();
	http.open("GET", url);
	http.setRequestHeader(
		"Authorization",
		"Basic " +
		btoa(
			localStorage.getItem("email") +
			":" +
			localStorage.getItem("password")
		)
	);
	http.send();
	http.onreadystatechange = e => {
		var res = JSON.parse(http.response);
		document.getElementById("registrationNo").innerHTML =
			"Your account is verified under licence number " + res.registration;
	};
};

var i = false;

function newsletterSubcription() {
	if (i === false) {
		alert(
			"You are now subscribed to our newsletter. You will receive updates in your email ID."
		);
		i = true;
	} else {
		alert("You are already subscribed to our newsletter");
	}
}

function logout() {
	const http = new XMLHttpRequest();
	const url = "http://localhost:8080/logout";
	http.open("GET", url);
	http.send();
	localStorage.clear();
	window.location.replace("../index.html");
}