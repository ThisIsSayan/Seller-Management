window.onload = e => {
	if (localStorage.length === 0 || localStorage.getItem("isAdmin") === "true") {
		window.location.href = "../error/error.html";
	}
	getItemList();
};

const ctx2 = document.getElementById("salesChart").getContext("2d");

const data2 = {
	labels: ["January", "February", "March", "April", "May", "June"],
	datasets: [{
		label: "Sales Chart",
		data: [65, 59, 80, 81, 56, 90],
		fill: true,
		borderColor: "rgb(75, 192, 192)",
		tension: 0.1,
	}, ],
};

const MysecondChart = new Chart(ctx2, {
	type: "line",
	data: data2,
});

function addItem() {
	var name = document.getElementById("itemName").value;
	var img = document.getElementById("itemImage").value;
	var cat = document.getElementById("category").value;
	var pprice = document.getElementById("itemPrice").value;
	var qty = document.getElementById("quantity").value;
	var desc = document.getElementById("description").value;
	var header = new Headers();
	header.append(
		"Authorization",
		"Basic " +
		btoa(
			localStorage.getItem("email") +
			":" +
			localStorage.getItem("password")
		)
	);
	header.append("Content-Type", "application/json;charset=UTF-8");
	const url = "http://localhost:8080/addproduct";
	fetch(url, {
			method: "POST",
			headers: header,
			body: JSON.stringify({
				productName: name,
				price: pprice,
				productImage: img,
				category: cat,
				quantity: qty,
				description: desc,
			}),
		})
		.then(response => response.text())
		.then(data => getItemList())
		.then(window.location.reload());
}

function getItemList() {
	const url = "http://localhost:8080/getproducts";
	var row = (document.getElementById("itemBody").innerHTML = "");
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
				var row = document.getElementById("itemBody");
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
						data[i].quantity +
						`</td>
					<td>` +
						data[i].description +
						`</td>
					<td>
						<i style='cursor: pointer; color:#cc0606' onclick="deleteItem(` +
						data[i].id +
						`)" class='fas fa-trash'></i>
					</td>
				</tr>`;
				}

				var m = new Map();

				m.set("Clothing", 0);
				m.set("Mobile", 0);
				m.set("Tablet", 0);
				m.set("Laptop", 0);
				m.set("Others", 0);
				var counter = 0;
				for (i = 0; i < data.length; i++) {
					if (data[i].category == "Clothing") {
						m.set("Clothing", (m.get("Clothing")) + data[i].quantity);
					}
					if (data[i].category == "Mobile") {
						m.set("Mobile", (m.get("Mobile")) + data[i].quantity);
					}
					if (data[i].category == "Laptop") {
						m.set("Laptop", (m.get("Laptop")) + data[i].quantity);
					}
					if (data[i].category == "Tablet") {
						m.set("Tablet", (m.get("Tablet")) + data[i].quantity);
					}
					if (data[i].category == "Others") {
						m.set("Others", (m.get("Others")) + data[i].quantity);
					}
					counter += data[i].quantity;
				}
				document.getElementById("totalProducts").innerHTML = "Total Products: " + counter;
				document.getElementById("productCount").innerHTML = counter + ` <i class="fas fa-laptop"></i>`;

				// Chart manupulation
				var ctx = document.getElementById("productChart").getContext("2d");
				var data1 = {
					labels: ["Clothing", "Mobile", "Tablet", "Laptop", "Others"],
					datasets: [{
						label: "Products",
						data: [m.get("Clothing"), m.get("Mobile"), m.get("Tablet"), m.get("Laptop"), m.get("Others")],
						backgroundColor: [
							"rgb(255, 99, 132)",
							"rgb(54, 162, 235)",
							"rgb(255, 205, 86)",
							"rgba(86, 255, 170)",
							"rgba(205, 206, 208)",
						],
						hoverOffset: 4,
					}, ],
				};
				var myChart = new Chart(ctx, {
					type: "doughnut",
					data: data1,
				});
				myChart.update();
			}

		);

}

function deleteItem(id) {
	const url = "http://localhost:8080/deleteproduct?id=" + id;
	console.log(url);
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
		.then(data => getItemList())
		.then(window.location.reload());
}

function logout() {
	const http = new XMLHttpRequest();
	const url = "http://localhost:8080/logout";
	http.open("GET", url);
	http.send();
	localStorage.clear();
	window.location.replace("../index.html");
}