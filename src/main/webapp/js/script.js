fetch('sidebar.html')
    .then(response => response.text())
    .then(data => {
        document.getElementById('navbar-html').innerHTML = data;
    });