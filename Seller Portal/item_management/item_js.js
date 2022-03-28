window.onload = e => {
	if (localStorage.length === 0 || localStorage.getItem("isAdmin") === "false") {
		localStorage.clear();
		window.location.href = "../error/error.html";
	}
	getItemList();
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

function getItemList() {
	const url = "http://localhost:8080/getallproducts";
	var row = document.getElementById("itemList");
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
		.then(response => response.json())
		.then(data => {
			row.innerHTML = "";
			for (i = 0; i < data.length; i++) {
				row.innerHTML +=
					`<tr>
					<th scope='row'>` +
					(i + 1) +
					`</th>
					<td>
						<img
							class='itemImage'
							src='` +
					data[i].productImage +
					`'
							alt='Image'
							srcset=''
						/>
					</td>
					<td>` +
					data[i].productName +
					`</td>
					<td>` +
					data[i].price +
					`</td>
          <td>` +
					data[i].userName +
					`</td>
          <td>` +
					data[i].quantity +
					`</td>
					<td>` +
					data[i].description +
					`</td>
					<td>
						<i style='cursor: pointer; color:#cc0606' onclick="deleteItem(` +
					data[i].id +
					`)" class='fa fa-trash'></i>
					</td>
				</tr>`;
			}
		});
}

function deleteItem(id) {
	const url = "http://localhost:8080/deleteproduct?id=" + id;
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
		.then(response => response.status)
		.then(data => {
			console.log(data);
			getItemList();
		});
}

function logout() {
	const http = new XMLHttpRequest();
	const url = "http://localhost:8080/logout";
	http.open("GET", url);
	http.send();
	localStorage.clear();
	window.location.replace("../index.html");
}