import storageUrls from "./urls/storageUrls.js";

async function sendData() {
    // Prepare data as an object
    const data = {
        productType: document.getElementById("product-type").value,
        name: document.getElementById("product-name").value,
        supplier: document.getElementById("supplier").value,
        gender: document.getElementById("gender").value,
        shape: document.getElementById("shape").value,
        ageGroup: document.getElementById("age-group").value,
        sensitivity: document.getElementById("sensitivity").value,
        expirationDate: document.getElementById("expiration-date").value,
        countryOfProduction: document.getElementById("country-of-production").value,
        limitation: document.getElementById("limitation").value,
        price: document.getElementById("price").value,
    };
    validateForm()
    // Modify the URL to the appropriate endpoint on your backend
    const router = new Router()
    return router.createFetch(storageUrls.ADD)
}

function validateForm() {
    // Validate name field
    const name = document.getElementById("productName").value;
    console.log(name)
    if (name === "") {
        alert("Please enter a name.");
        return false;
    }

    // Validate limitation field
    const limitation = document.getElementById("limitation").value;
    if (isNaN(limitation)) {
        alert("Limitation must be a number.");
        return false;
    }

    // Validate price field
    const price = document.getElementById("price").value;
    if (isNaN(price)) {
        alert("Price must be a number.");
        return false;
    }

    const addButton = document.getElementById("add-btn")
    addButton.addEventListener("click", async function () {
        if (validateForm()) {
            try {
                const responseData = await sendData();
                console.log("Data sent to the backend:", responseData);
                // Add any additional actions or logic here based on the response from the backend
            } catch (error) {
                console.error("Error sending data:", error);
            }
        }
    });
}

const buttons = document.querySelectorAll('#toggleButton button');

// Add event listeners to the buttons
buttons.forEach(button => {
    button.addEventListener('click', function() {
        // Remove the "active" class from all buttons
        buttons.forEach(btn => btn.classList.remove('active'));

        // Add the "active" class to the clicked button
        this.classList.add('active');
    });
});