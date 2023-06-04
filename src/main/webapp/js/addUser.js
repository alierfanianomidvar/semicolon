import userUrls from "./urls/userUrls.js";
import {showErrorMessage} from "./utils.js";

const addUserForm = document.getElementById('add-user-form');

addUserForm.addEventListener('submit', async function (event) {
    event.preventDefault();

    const formData = new FormData(addUserForm);
    const body = Object.fromEntries(formData)
    body.accountStatus = "ACTIVE";

    body.birthDate = new Date(body.birthDate).toISOString();

    body.role = {
        id: body.role
    }

    if (body.profilePicture.size !== 0) {
        body.profilePicture = await getBase64ProfilePicture();
    } else {
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
        showErrorMessage(data.msg);
        console.log('There was a problem with the fetch operation:', data.msg);
    }
});

function getBase64ProfilePicture() {
    return new Promise((resolve) => {
        const fileInput = document.querySelector('input[type="file"]');
        const file = fileInput.files[0];

        // Create a new FileReader object
        const reader = new FileReader();

        // Set up a function to be called when the file is read
        reader.onload = function (event) {
            // Get the loaded file data as a base64 encoded string
            const base64String = event.target.result;

            // Send the base64 string to the backend
            resolve(base64String);
        };

        // Read the file as a data URL (base64 encoded string)
        reader.readAsDataURL(file);
    });
}
