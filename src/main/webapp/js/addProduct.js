import materialUrls from "./urls/materialUrls.js";
import drugUrls from "./urls/drugUrls.js";


export function sendData() {
    const productType = document.getElementById("product-type").value;
    const name = document.getElementById("product-name").value;
    const supplier = supplierListInt;
    const gender = document.getElementById("gender").value;
    const ageGroup = document.getElementById("age-group").value;
    const expirationDate = document.getElementById("expiration-date").value;
    const countryOfProduction = document.getElementById("country-of-production").value;
    const price = document.getElementById("price").value;
    const description = document.getElementById("description").value;
    const image = null;

    const expirationDateInput = document.getElementById("expiration-date");
    const currentDate = new Date().toISOString();

    if (expirationDateInput.value < currentDate) {
        console.error("Expiration date should not be before the current date");
        expirationDateInput.classList.add("is-invalid");
        return null;
    } else {
        expirationDateInput.classList.remove("is-invalid");
    }

    const priceInput = document.getElementById("price");
    const limitationInput = document.getElementById("limitation");
    const limitation = parseFloat(limitationInput.value);

    if (isNaN(price) || isNaN(limitation)) {
        console.error("Price and limitation should be numeric values");
        priceInput.classList.add("is-invalid");
        limitationInput.classList.add("is-invalid");
        return null;
    } else {
        priceInput.classList.remove("is-invalid");
        limitationInput.classList.remove("is-invalid");
    }

    const nameInput = document.getElementById("product-name");
    const productName = nameInput.value.trim();
    const nameError = nameInput.nextElementSibling;

    if (!productName) {
        console.error("Name should not be empty");
        nameError.textContent = "Name should not be empty";
        nameInput.classList.add("is-invalid");
        return null;
    } else if (/^\d/.test(productName) || /^\d+$/.test(productName)) {
        console.error("Name should not start with a number or be only a number");
        nameError.textContent = "Name should not start with a number or be only a number";
        nameInput.classList.add("is-invalid");
        return null;
    } else {
        nameError.textContent = "";
        nameInput.classList.remove("is-invalid");
    }

    const data = {
        productType,
        name,
        supplier,
        gender,
        ageGroup,
        expirationDate,
        countryOfProduction,
        description,
        price,
        image
    };

    if (productType === "DRUG") {
        const isSensitive = document.getElementById("sensitivity").value;
        const limitation = document.getElementById("limitation").value;
        const needPrescription = document.getElementById("need-prescription").value;
        const shape = document.getElementById("shape").value;
        data.shape = shape;
        data.isSensitive = isSensitive;
        data.limitation = limitation;
        data.needPrescription = needPrescription;
    }

    console.log(data);

    const router = new Router();
    if (data.productType === "DRUG") {
        return router.createFetch(drugUrls.ADD, null , null , null , data);
    } else if (data.productType === "MATERIAL") {
        return router.createFetch(materialUrls.ADD , null , null , null ,data);
    }
    return null;
}

export const addProduct = async (event) => {
    event.preventDefault();

    try {
        const responseData = await sendData();
    } catch (error) {
        let errorMessage = "An error occurred while sending data. Please try again later.";
        if (error.response && error.response.data && error.response.data.msg) {
            errorMessage = error.response.data.msg;
        }
        alert("Error: " + errorMessage);
    }
};


export const cancelButton = () => {
    window.location.href = "supplier.html"; // Replace with the desired URL
};



