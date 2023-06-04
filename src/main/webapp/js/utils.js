export function getByteProfilePicture() {
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

export function getTokenFromLocalStorage() {
    return localStorage.getItem("token");
}

export function getRouteParam (index = 1) {
    return window.location.hash.split("/")[index]
}

// Helper function to parse the JWT token payload
export function parseJwt(token) {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(window.atob(base64).split('').map(c => '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)).join(''));

    return JSON.parse(jsonPayload);
}

// Function to dynamically generate the dropdown options based on the user's role
export function generateRoleOptions(role, userForm) {
    const roleSelect = userForm.querySelector('select[name="role"]');
    const options = [
        { value: "3", label: "Staff" }
    ];

    if (role.toLowerCase() === "superadmin") {
        options.unshift({ value: "2", label: "Admin" });
    }

    // Add options to the dropdown
    options.forEach(option => {
        const optionElement = document.createElement('option');
        optionElement.value = option.value;
        optionElement.textContent = option.label;
        roleSelect.appendChild(optionElement);
    });
}


export function extractRole(token) {
    const payload = parseJwt(token);
    const role = payload.Role;
    return role;
}