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


    const response = await fetch(userUrls.ADD.url, {
        method: userUrls.ADD.method,
        headers: {
            'Content-Type': 'application/json',
            //TODO: read it from local storage when we merge with login branch!
            'Authorization': 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiUm9sZSI6InN1cGVyQWRtaW4iLCJpYXQiOjE2ODI2MTQ4MzcsImV4cCI6MTcxODYxNDgzN30.Jm_t27ViO5F_4ENkPP89dZummxgut4l_97e-fST5Qh4'
        },
        body: JSON.stringify(body)
    })
    const data = await response.json();
    if (response.ok) {
        console.log('User added:', data);
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