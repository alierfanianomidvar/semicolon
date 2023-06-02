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

    console.log(body.profilePicture.size)
    if (body.profilePicture.size === !0) {
        body.profilePicture = await getByteProfilePicture();
    }
    else {
        body.profilePicture = null;
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

function getByteProfilePicture() {
    return new Promise((resolve) => {
        const fileInput = document.querySelector('input[type="file"]');
        const file = fileInput.files[0];

        // Create a new FileReader object
        const reader = new FileReader();

        // Set up a function to be called when the file is read
        reader.onload = function (event) {
            // Get the loaded file data as a byte array
            const byteArray = new Uint8Array(event.target.result);

            // Send the byte array to the backend
            resolve(Array.from(byteArray));

        };

        // Read the file as an ArrayBuffer
        reader.readAsArrayBuffer(file);
    });
}
function showError(message) {
    const error = document.getElementById("error-message");
    const alert = document.createElement("div");
    alert.className = "alert alert-danger";
    alert.textContent = message;
    error.appendChild(alert);
}