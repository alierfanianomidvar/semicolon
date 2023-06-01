import materialUrls from "./urls/materialUrls.js";
import drugUrls from "./urls/drugUrls.js";

const supplierListValue = document.getElementById("supplierId").value;
const supplierListInt = parseInt(supplierListValue, 10); // 10 represents the radix/base (decimal in this case)

console.log(supplierListInt); // Output the converted integer value

export function sendData() {
    // Prepare data as an object
    const productType = document.getElementById("product-type").value;
    const name = document.getElementById("product-name").value;
    const supplier = supplierListInt;
    const gender = document.getElementById("gender").value;
    const shape = document.getElementById("shape").value;
    const ageGroup = document.getElementById("age-group").value;
    const sensitivity = document.getElementById("sensitivity").value;
    const expirationDate = document.getElementById("expiration-date").value;
    const countryOfProduction = document.getElementById("country-of-production").value;
    const limitation = document.getElementById("limitation").value;
    const price = document.getElementById("price").value;
    const image = null;


    // Validate expiration date
    const expirationDateInput = document.getElementById("expiration-date");
    const currentDate = new Date().toISOString(); // Get the current local date and time

    if (expirationDateInput.value < currentDate) {
        console.error("Expiration date should not be before the current date");
        expirationDateInput.classList.add("is-invalid"); // Add the "is-invalid" class
        return null;
    } else {
        expirationDateInput.classList.remove("is-invalid"); // Remove the "is-invalid" class
    }



// Validate price and limitation as numbers
    const priceInput = document.getElementById("price");
    const limitationInput = document.getElementById("limitation");
    if (isNaN(parseFloat(price)) || isNaN(parseFloat(limitation))) {
        console.error("Price and limitation should be numeric values");
        priceInput.classList.add("is-invalid");
        limitationInput.classList.add("is-invalid");
        return null;
    } else {
        priceInput.classList.remove("is-invalid");
        limitationInput.classList.remove("is-invalid");
    }

// Validate name should not start with a number or be only a number
    // Validate name should not start with a number or be only a number and should not be null
    // Validate name should not start with a number or be only a number and should not be null
    const nameInput = document.getElementById("product-name");
    const productName = nameInput.value.trim(); // Trim whitespace from the input value
    const nameError = nameInput.nextElementSibling; // Get the error feedback element

    if (!productName) {
        console.error("Name should not be empty");
        nameError.textContent = "Name should not be empty"; // Set the error message
        nameInput.classList.add("is-invalid"); // Add the "is-invalid" class
        return null;
    } else if (/^\d/.test(productName) || /^\d+$/.test(productName)) {
        console.error("Name should not start with a number or be only a number");
        nameError.textContent = "Name should not start with a number or be only a number"; // Set the error message
        nameInput.classList.add("is-invalid"); // Add the "is-invalid" class
        return null;
    } else {
        nameError.textContent = ""; // Clear the error message
        nameInput.classList.remove("is-invalid"); // Remove the "is-invalid" class
    }



    // Prepare the data object
    const data = {
        productType,
        name,
        supplier,
        gender,
        shape,
        ageGroup,
        sensitivity,
        expirationDate,
        countryOfProduction,
        limitation,
        price,
        image
    };

    // Modify the URL to the appropriate endpoint on your backend
    const router = new Router();
    if (data.productType === "DRUG") {
        const a = router.createFetch(drugUrls.ADD, null, null, null, data);
        return a;
    } else if (data.productType === "MATERIAL") {
        return router.createFetch(materialUrls.ADD, null, null, null, data);
    }

    // Return a default value if the productType is neither DRUG nor MATERIAL
    return null;
}

export const addProduct = async () => {
    event.preventDefault();

    console.log("HERE");
    try {
        const responseData = await sendData();
        console.log(responseData);
        console.log("Data sent to the backend:", responseData);
        // Add any additional actions or logic here based on the response from the backend
    } catch (error) {
        let errorMessage = "An error occurred while sending data. Please try again later.";
        if (error.response && error.response.data && error.response.data.msg) {
            errorMessage = error.response.data.msg;
        }
        alert("Error: " + errorMessage);
    }
};

$(function () {
    console.log("A");
    document.querySelector("#add-btn").addEventListener("click", addProduct);
});
