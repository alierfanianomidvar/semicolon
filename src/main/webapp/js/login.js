function sendData() {
    const router = new Router();
    const username = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const keepLoggedIn = document.getElementById("keepLoggedIn").checked;

    // Validate email and password
    if (!validateForm(username, password)) {
        return false;
    }

    const loginData = {
        username,
        password,
    };
    try {
        const fetchedData = router.createFetch(AccountUrls.LOGIN,null,null,null, loginData);
        if (fetchedData) {
            $(document).ready(async () => {
                const url = '../pages/index.html'; // Specify the URL of the new HTML file

                try {
                    // Fetch the new HTML file
                    //TODO: we need to use the fetched data to store it into localstorage of our browser
                    window.location.href = url

                } catch (error) {
                    console.error('Error loading new HTML:', error);
                }
            });
        }
    } catch (e) {
        return false;
    }


    // fetch("http://localhost:", {
    //     method: "POST",
    //     headers: {
    //         "Content-Type": "application/json"
    //     },
    //     body: JSON.stringify(loginData)
    // })
    //     .then(response => {
    //         if (response.ok) {
    //             // Login successful, handle accordingly
    //             console.log("Login successful");
    //             // Redirect to the dashboard or perform other actions
    //         } else {
    //             // Login failed, handle accordingly
    //             console.error("Login failed");
    //             showError("Login failed. Please try again.");
    //         }
    //     })
    //     .catch(error => {
    //         console.error("Login failed:", error);
    //         showError("An error occurred during login. Please try again.");
    //     });

    // return false; // Prevent form submission
}

function validateForm(email, password) {
    // Email validation regex pattern
    const emailPattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    // Check if email and password are empty
    if (email.trim() === "" || password.trim() === "") {
        alert("Please enter both email and password.");
        return false;
    }

    // Check if email is valid
    if (!email.match(emailPattern)) {
        alert("Please enter a valid email address.");
        return false;
    }

    // Check if password is at least 6 characters long
    if (password.length < 6) {
        alert("Password should be at least 6 characters long.");
        return false;
    }

    // All validations passed
    return true;
}

function showError(message) {
    // Show error message to the user, e.g., using Bootstrap alert
    const errorElement = document.getElementById("error-message");
    errorElement.textContent = message;
    errorElement.style.display = "block";
}

function togglePasswordVisibility() {
    const passwordInput = document.getElementById("password");
    const passwordIcon = document.getElementById("password-icon");

    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        passwordIcon.classList.remove("bi-eye");
        passwordIcon.classList.add("bi-eye-slash");
    } else {
        passwordInput.type = "password";
        passwordIcon.classList.remove("bi-eye-slash");
        passwordIcon.classList.add("bi-eye");
    }
}