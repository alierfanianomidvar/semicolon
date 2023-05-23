import storageUrls from "./urls/storageUrls.js";

export async function sendData() {
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
    validateForm();
    // Modify the URL to the appropriate endpoint on your backend
    const router = new Router();
    return router.createFetch(storageUrls.ADD);
}
export function validateForm() {
    // Reset validation styles
    const form = document.getElementById("add-storage");
    Array.from(form.elements).forEach((element) => {
        element.classList.remove("is-invalid");
        element.classList.remove("is-valid");
    });

    // Validate name field
    const name = document.getElementById("product-name");
    if (name.value.trim() === "") {
        name.classList.add("is-invalid");
        return false;
    } else {
        name.classList.add("is-valid");
    }

    // Validate limitation field
    const limitation = document.getElementById("limitation");
    if (isNaN(limitation.value) || limitation.value.trim() === "") {
        limitation.classList.add("is-invalid");
        return false;
    } else {
        limitation.classList.add("is-valid");
    }

    // Validate price field
    const price = document.getElementById("price");
    if (isNaN(price.value) || price.value.trim() === "") {
        price.classList.add("is-invalid");
        return false;
    } else {
        price.classList.add("is-valid");
    }

    return true;
}
export async function addStorage() {
    document.getElementById("add-storage").addEventListener("submit", async function (event) {
        event.preventDefault(); // Prevent the default form submission behavior

        if (validateForm()) {
            try {
                const responseData = await sendData();
                console.log("Data sent to the backend:", responseData);
                // Add any additional actions or logic here based on the response from the backend
            } catch (error) {
                console.error("Error sending data:", error);
            }
        } else {
            // Reset validation styles
            const form = document.getElementById("add-storage");
            Array.from(form.elements).forEach((element) => {
                element.classList.remove("is-invalid");
                element.classList.remove("is-valid");
            });

            // Validate name field
            const name = document.getElementById("product-name");
            if (name.value.trim() === "") {
                name.classList.add("is-invalid");
            } else {
                name.classList.add("is-valid");
            }

            // Validate limitation field
            const limitation = document.getElementById("limitation");
            if (isNaN(limitation.value) || limitation.value.trim() === "") {
                limitation.classList.add("is-invalid");
            } else {
                limitation.classList.add("is-valid");
            }

            // Validate price field
            const price = document.getElementById("price");
            if (isNaN(price.value) || price.value.trim() === "") {
                price.classList.add("is-invalid");
            } else {
                price.classList.add("is-valid");
            }
        }
        console.log("hi");
    });
}
