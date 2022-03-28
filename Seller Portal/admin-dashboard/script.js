window.onload = e => {
	if (localStorage.length === 0 || localStorage.getItem("isAdmin") === "false") {
		localStorage.clear();
		window.location.href = "../error/error.html";
	}
	loadData();
	const url1 = "http://localhost:8080/totalitemsno";
	const url2 = "http://localhost:8080/totalsellersno";
	const url3 = "http://localhost:8080/pendingsellers";
	const url4 = "http://localhost:8080/approvedsellers";
	const http1 = new XMLHttpRequest();
	const http2 = new XMLHttpRequest();
	const http3 = new XMLHttpRequest();
	const http4 = new XMLHttpRequest();
	http1.open("GET", url1);
	http2.open("GET", url2);
	http3.open("GET", url3);
	http4.open("GET", url4);
	http1.setRequestHeader(
		"Authorization",
		"Basic " +
		btoa(
			localStorage.getItem("email") +
			":" +
			localStorage.getItem("password")
		)
	);
	http2.setRequestHeader(
		"Authorization",
		"Basic " +
		btoa(
			localStorage.getItem("email") +
			":" +
			localStorage.getItem("password")
		)
	);
	http3.setRequestHeader(
		"Authorization",
		"Basic " +
		btoa(
			localStorage.getItem("email") +
			":" +
			localStorage.getItem("password")
		)
	);
	http4.setRequestHeader(
		"Authorization",
		"Basic " +
		btoa(
			localStorage.getItem("email") +
			":" +
			localStorage.getItem("password")
		)
	);
	http1.send();
	http2.send();
	http3.send();
	http4.send();

	http1.onreadystatechange = e => {
		var res = JSON.parse(http1.responseText);
		document.getElementById("itemNo").innerHTML = res;
	};
	http2.onreadystatechange = e => {
		var res = JSON.parse(http2.responseText);
		document.getElementById("sellerNo").innerHTML = res;
	};
	http3.onreadystatechange = e => {
		var res = JSON.parse(http3.responseText);
		document.getElementById("reqNo").innerHTML = res.length;
	};
	http4.onreadystatechange = e => {
		var res = JSON.parse(http4.responseText);
		document.getElementById("activeNo").innerHTML = res.length;
	};
};

var options = {
	series: [{
			name: "Net Profit",
			data: [44, 55, 57, 56, 61, 58, 63, 60, 66],
		},
		{
			name: "Revenue",
			data: [76, 85, 101, 98, 87, 105, 91, 114, 94],
		},
		{
			name: "Free Cash Flow",
			data: [35, 41, 36, 26, 45, 48, 52, 53, 41],
		},
	],
	chart: {
		type: "bar",
		height: 250, // make this 250
		sparkline: {
			enabled: true, // make this true
		},
	},
	plotOptions: {
		bar: {
			horizontal: false,
			columnWidth: "55%",
			endingShape: "rounded",
		},
	},
	dataLabels: {
		enabled: false,
	},
	stroke: {
		show: true,
		width: 2,
		colors: ["transparent"],
	},
	xaxis: {
		categories: ["Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct"],
	},
	yaxis: {
		title: {
			text: "$ (thousands)",
		},
	},
	fill: {
		opacity: 1,
	},
	tooltip: {
		y: {
			formatter: function (val) {
				return "$ " + val + " thousands";
			},
		},
	},
};
var chart = new ApexCharts(document.querySelector("#apex1"), options);
chart.render();

function goToPending() {
	window.location.href = "../seller_management/index.html";
}

function loadData() {
	const url = "http://localhost:8080/approvedsellers";
	const http1 = new XMLHttpRequest();
	http1.open("GET", url);
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
		var row = document.getElementById("activeSeller");
		row.innerHTML = "";
		for (var i = 0; i < data.length; i++) {
			row.innerHTML +=
				`<tr>
					<th scope='row'>` + (i + 1) + `</th>
					<td>` +
				data[i].userName +
				`</td>
					<td>` +
				data[i].registration +
				`</td>
				</tr>`;
		}
	}

}

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

function logout() {
	const http = new XMLHttpRequest();
	const url = "http://localhost:8080/logout";
	http.open("GET", url);
	http.send();
	localStorage.clear();
	window.location.replace("../index.html");
}