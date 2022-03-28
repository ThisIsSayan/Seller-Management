window.onload = e => {
    if (localStorage.length === 0 || localStorage.getItem("isAdmin") === "true") {
        window.location.href = "../error/error.html";
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