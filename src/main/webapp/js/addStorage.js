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

        // Add more properties as needed
    };

    // Modify the URL to the appropriate endpoint on your backend
    const url = "https://example.com/api/data";

    // Configure the fetch request
    const options = {
        method: "POST", // Adjust the method as needed (e.g., GET, POST, etc.)
        headers: {
            "Content-Type": "application/json", // Adjust the content type as needed
        },
        body: JSON.stringify(data), // Convert the data to JSON format
    };

    // Send the fetch request
    try {
        const response = await fetch(url, options);
        const responseData = await response.json();
        return responseData;
    } catch (error) {
        throw new Error("Error sending data: " + error.message);
    }
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