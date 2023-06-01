import materialUrls from "./urls/materialUrls.js";
import drugUrls from "./urls/drugUrls.js";

const supplierListValue = document.getElementById("supplierId").value;
const supplierListInt = parseInt(supplierListValue, 10); // 10 represents the radix/base (decimal in this case)

console.log(supplierListInt); // Output the converted integer value
export function sendData() {
    // Prepare data as an object
    const data = {
        productType: document.getElementById("product-type").value,
        name: document.getElementById("product-name").value,
        supplier: supplierListInt,
        gender: document.getElementById("gender").value,
        shape: document.getElementById("shape").value,
        ageGroup: document.getElementById("age-group").value,
        sensitivity: document.getElementById("sensitivity").value,
        expirationDate: document.getElementById("expiration-date").value,
        countryOfProduction: document.getElementById("country-of-production").value,
        limitation: document.getElementById("limitation").value,
        price: document.getElementById("price").value,
        image: null
    };

    // Modify the URL to the appropriate endpoint on your backend
    const router = new Router();
    if (data.productType === "DRUG") {
        console.log("HOOYYY")
        const a = router.createFetch(drugUrls.ADD, null, null, null, data)
        console.log(a)
        return a;

    } else if (data.productType === "MATERIAL") {
        return router.createFetch(materialUrls.ADD, null, null, null, data);
    }
    console.log(router)
    // Return a default value if the productType is neither DRUG nor MATERIAL
    return null;
}

// export function validateForm() {
//     // Reset validation styles
//
//     // Validate name field
//     console.log("validate.")
//     const name = document.getElementById("product-name");
//     if (name.value.trim() === "") {
//         name.classList.add("is-invalid");
//         return false;
//     } else {
//         name.classList.add("is-valid");
//     }
//
//     // Validate limitation field
//     const limitation = document.getElementById("limitation");
//     console.log(limitation);
//     if (isNaN(limitation.value) || limitation.value.trim() === "") {
//         limitation.classList.add("is-invalid");
//         return false;
//     } else {
//         limitation.classList.add("is-valid");
//     }
//
//     // Validate price field
//     const price = document.getElementById("price");
//     if (isNaN(price.value) || price.value.trim() === "") {
//         price.classList.add("is-invalid");
//         return false;
//     } else {
//         price.classList.add("is-valid");
//     }
//
//     return true;
// }
//
export const addProduct = async () => {
    event.preventDefault();

    console.log("HERE");
    try {
        const responseData = await sendData();
        console.log(responseData)
        console.log("Data sent to the backend:", responseData);
        // Add any additional actions or logic here based on the response from the backend
    } catch (error) {
        console.error("Error sending data:", error);
    }
// }
//
// else
// {
//     // Reset validation styles
//     const form = document.getElementById("add-product");
//     Array.from(form.elements).forEach((element) => {
//         element.classList.remove("is-invalid");
//         element.classList.remove("is-valid");
//     });
//     // Validate name field
//     const name = document.getElementById("product-name");
//     if (name.value.trim() === "") {
//         name.classList.add("is-invalid");
//     } else {
//         name.classList.add("is-valid");
//     }
//
//     // Validate limitation field
//     const limitation = document.getElementById("limitation");
//     if (isNaN(limitation.value) || limitation.value.trim() === "") {
//         limitation.classList.add("is-invalid");
//     } else {
//         limitation.classList.add("is-valid");
//     }
//
//     // Validate price field
//     const price = document.getElementById("price");
//     if (isNaN(price.value) || price.value.trim() === "") {
//         price.classList.add("is-invalid");
//     } else {
//         price.classList.add("is-valid");
//     }
//
}

$(function () {
    console.log("A")
    document.querySelector('#add-btn').addEventListener('click', addProduct);
});