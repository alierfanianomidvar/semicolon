import supplierUrls from "./urls/supplierUrls.js";

export function sendSupplierData() {
    const name = document.getElementById("supplier-name").value;
    const email= document.getElementById("email").value;
    const telephone_number = document.getElementById("telephone-number").value;
    const address = document.getElementById("telephone-number").value;



    // Validate name
    if (!name) {
        console.error("Name should not be empty");
        alert("Name should not be empty");
        return null;
    }

    // Validate email
    const emailRegex = /^\S+@\S+\.\S+$/;
    if (!emailRegex.test(email)) {
        console.error("Invalid email address");
        alert("Invalid email address");
        return null;
    }

    // Validate phone number
    const phoneRegex = /^\d{10}$/;
    if (!phoneRegex.test(telephone_number)) {
        console.error("Invalid phone number");
        alert("Invalid phone number");
        return null;
    }



    const data = {
        name,
        email,
        telephone_number,
        address
    }

    const router = new Router();
    return router.createFetch(supplierUrls.ADD ,null, null, "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiUm9sZSI6InN1cGVyQWRtaW4iLCJpYXQiOjE2ODI2ODQyOTQsImV4cCI6MTcxODY4NDI5NH0.ycF1GB9BkTH4qg3uAINMHmuTg1kHwSP15z7r_A6nONQ", data);
}

export const addSupplier = async() => {
    event.preventDefault();
    try {
        const responseData = await sendSupplierData();
        console.log(responseData);
        console.log("Data sent to the backend:", responseData);
    } catch (error) {
        let errorMessage = "An error occurred while sending data. Please try again later.";
        if (error.response && error.response.data && error.response.data.msg) {
            errorMessage = error.response.data.msg;
        }
        alert("Error: " + errorMessage);
    }
};

export const cancelButton = () => {
    window.location.href = "../pages/supplier.html"; // Replace with the desired URL
};

$(function () {
    document.querySelector("#add-btn").addEventListener("click", addSupplier);
    document.querySelector("#cancel-btn").addEventListener("click", cancelButton);
});