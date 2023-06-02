import userUrls from "./urls/userUrls.js";

const addUserForm = document.getElementById('add-user-form');

addUserForm.addEventListener('submit', async function(event) {
    event.preventDefault();

    const formData = new FormData(addUserForm);
    const body = Object.fromEntries(formData)
    body.accountStatus = "ACTIVE";

    body.birthDate = new Date(body.birthDate).toISOString();

    body.role = {
        id: body.role
    }

    const token = localStorage.getItem("token");

    const response = await fetch(userUrls.ADD.url, {
        method: userUrls.ADD.method,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': token
        },
        body: JSON.stringify(body)
    })
    const data = await response.json();
    if (response.ok) {
        window.location.hash = "#user";
    } else {
        showError(data.msg);
        console.log('There was a problem with the fetch operation:', data.msg);
    }
});

function showError(message) {
    const error = document.getElementById("error-message");
    const alert = document.createElement("div");
    alert.className = "alert alert-danger";
    alert.textContent = message;
    error.appendChild(alert);
}