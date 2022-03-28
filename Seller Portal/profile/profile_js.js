window.onload = e => {
	if (localStorage.length === 0 || localStorage.getItem("isAdmin") === "false") {
		localStorage.clear();
		window.location.href = "../error/error.html";
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
		var res = JSON.parse(http.responseText);
		document.getElementById("name").placeholder = res.name;
		document.getElementById("reg").placeholder = res.registration;
		document.getElementById("email").placeholder = res.userName;
		document.getElementById("dob").placeholder = res.dob;
	};
};

function deleteAcc() {
	const url = "http://localhost:8080/deleteaccount";
	fetch(url, {
			headers: {
				Authorization: "Basic " +
					btoa(
						localStorage.getItem("email") +
						":" +
						localStorage.getItem("password")
					),
			},
		})
		.then(response => response.text())
		.then(data => alert(data))
		.then(localStorage.clear())
		.then(window.location.replace("../index.html"));
}

function logout() {
	const http = new XMLHttpRequest();
	const url = "http://localhost:8080/logout";
	http.open("GET", url);
	http.send();
	localStorage.clear();
	window.location.replace("../index.html");
}



// Sidebar Toggle Codes;

var sidebarOpen = false;
var sidebar = document.getElementById("sidebar");
var sidebarCloseIcon = document.getElementById("sidebarIcon");

function toggleSidebar() {
	if (!sidebarOpen) {
		sidebar.classList.add("sidebar_responsive");
		sidebarOpen = true;
	}
}

function closeSidebar() {
	if (sidebarOpen) {
		sidebar.classList.remove("sidebar_responsive");
		sidebarOpen = false;
	}
}