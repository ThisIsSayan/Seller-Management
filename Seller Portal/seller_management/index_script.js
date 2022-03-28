window.onload = e => {
	if (localStorage.length === 0 || localStorage.getItem("isAdmin") === "false") {
		localStorage.clear();
		window.location.href = "../error/error.html";
	}
	loadDataSets();
};

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

function loadDataSets() {
	const url1 = "http://localhost:8080/getallusers";
	const url2 = "http://localhost:8080/pendingsellers";
	const http1 = new XMLHttpRequest();
	http1.open("GET", url1);
	http1.setRequestHeader(
		"Authorization",
		"Basic " +
		btoa(
			localStorage.getItem("email") +
			":" +
			localStorage.getItem("password")
		)
	);
	http1.send();
	http1.onreadystatechange = e => {
		var data = JSON.parse(http1.response);
		console.log(data);
		var row = document.getElementById("sellerBody");
		row.innerHTML = "";
		for (i = 0; i < data.length; i++) {
			row.innerHTML +=
				`<tr>
					<th scope='row'>` +
				(i + 1) +
				`</th>
					<td>
						` +
				data[i].name +
				`						
					</td>
					<td>` +
				data[i].userName +
				`</td>
					<td>` +
				data[i].registration +
				`</td>
					<td>` +
				data[i].dob +
				`</td>
        <td>` +
				data[i].status +
				`</td>
				</tr>`;
		}
		fetch(url2, {
				headers: {
					Authorization: "Basic " +
						btoa(
							localStorage.getItem("email") +
							":" +
							localStorage.getItem("password")
						),
				},
			})
			.then(response => response.json())
			.then(d => {
				console.log(d);
				var row = document.getElementById("pendingSeller");
				row.innerHTML = "";
				for (i = 0; i < d.length; i++) {
					row.innerHTML +=
						`<tr>
					<th scope='row'>` +
						(i + 1) +
						`</th>
					<td>
						` +
						d[i].name +
						`						
					</td>
					<td>` +
						d[i].userName +
						`</td>
					<td>` +
						d[i].registration +
						`</td>
					<td>` +
						d[i].dob +
						`</td>
        <td>` +
						d[i].status +
						`</td>
            <td>
						<i style='cursor: pointer; color:green' onclick="approve(` +
						d[i].id +
						`)" class='fa fa-check-circle'></i>
					</td>
					<td>
						<i style='cursor: pointer; color:#cc0606' onclick="reject(` +
						d[i].id +
						`)" class='fa fa-times-circle'></i>
					</td>
				</tr>`;
				}
			});
	};
}

function approve(id) {
	const url =
		"http://localhost:8080/setuserstatus?id=" + id + "&status=APPROVED";
	const http = new XMLHttpRequest();
	http.open("POST", url);
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
		loadDataSets();
	};
}

function reject(id) {
	const url = "http://localhost:8080/setuserstatus?id=" + id + "&status=DENIED";
	const http = new XMLHttpRequest();
	http.open("POST", url);
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
		loadDataSets();
	};
}

function logout() {
	const http = new XMLHttpRequest();
	const url = "http://localhost:8080/logout";
	http.open("GET", url);
	http.send();
	localStorage.clear();
	window.location.replace("../index.html");
}