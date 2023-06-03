import materialUrls from "./urls/materialUrls.js";
import drugUrls from "./urls/drugUrls.js";

const supplierListValue = document.getElementById("supplierId").value;
const supplierListInt = parseInt(supplierListValue, 10);

console.log(supplierListInt);

const countryOfProductionSelect = document.getElementById("country-of-production");

const countryCodes = [
    "AF", "AX", "AL", "DZ", "AS", "AD", "AO", "AI", "AQ", "AG", "AR", "AM", "AW", "AU", "AT", "AZ", "BS", "BH", "BD", "BB", "BY", "BE", "BZ", "BJ", "BM", "BT", "BO", "BQ", "BA", "BW", "BV", "BR", "IO", "BN", "BG", "BF", "BI", "KH", "CM", "CA", "CV", "KY", "CF", "TD", "CL", "CN", "CX", "CC", "CO", "KM", "CG", "CD", "CK", "CR", "CI", "HR", "CU", "CW", "CY", "CZ", "DK", "DJ", "DM", "DO", "EC", "EG", "SV", "GQ", "ER", "EE", "ET", "FK", "FO", "FJ", "FI", "FR", "GF", "PF", "TF", "GA", "GM", "GE", "DE", "GH", "GI", "GR", "GL", "GD", "GP", "GU", "GT", "GG", "GN", "GW", "GY", "HT", "HM", "VA", "HN", "HK", "HU", "IS", "IN", "ID", "IR", "IQ", "IE", "IM", "IL", "IT", "JM", "JP", "JE", "JO", "KZ", "KE", "KI", "KP", "KR", "KW", "KG", "LA", "LV", "LB", "LS", "LR", "LY", "LI", "LT", "LU", "MO", "MK", "MG", "MW", "MY", "MV", "ML", "MT", "MH", "MQ", "MR", "MU", "YT", "MX", "FM", "MD", "MC", "MN", "ME", "MS", "MA", "MZ", "MM", "NA", "NR", "NP", "NL", "NC", "NZ", "NI", "NE", "NG", "NU", "NF", "MP", "NO", "OM", "PK", "PW", "PS", "PA", "PG", "PY", "PE", "PH", "PN", "PL", "PT", "PR", "QA", "RE", "RO", "RU", "RW", "BL", "SH", "KN", "LC", "MF", "PM", "VC", "WS", "SM", "ST", "SA", "SN", "RS", "SC", "SL", "SG", "SX", "SK", "SI", "SB", "SO", "ZA", "GS", "SS", "ES", "LK", "SD", "SR", "SJ", "SZ", "SE", "CH", "SY", "TW", "TJ", "TZ", "TH", "TL", "TG", "TK", "TO", "TT", "TN", "TR", "TM", "TC", "TV", "UG", "UA", "AE", "GB", "US", "UM", "UY", "UZ", "VU", "VE", "VN", "VG", "VI", "WF", "EH", "YE", "ZM", "ZW"
];

countryCodes.forEach(countryCode => {
    const option = document.createElement("option");
    option.value = countryCode;
    option.textContent = countryCode;
    countryOfProductionSelect.appendChild(option);
});

export function sendData() {
    const productType = document.getElementById("product-type").value;
    const name = document.getElementById("product-name").value;
    const supplier = supplierListInt;
    const gender = document.getElementById("gender").value;
    const shape = document.getElementById("shape").value;
    const ageGroup = document.getElementById("age-group").value;
    const expirationDate = document.getElementById("expiration-date").value;
    const countryOfProduction = document.getElementById("country-of-production").value;
    const limitation = document.getElementById("limitation").value;
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
    if (isNaN(parseFloat(price)) || isNaN(parseFloat(limitation))) {
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
        shape,
        ageGroup,
        expirationDate,
        countryOfProduction,
        limitation,
        description,
        price,
        image
    };

    if (productType === "DRUG") {
        const sensitivity = document.getElementById("sensitivity").value;
        data.sensitivity = sensitivity;
    }

    console.log(data);

    const router = new Router();
    if (data.productType === "DRUG") {
        return router.createFetch(drugUrls.ADD, null, null, null, data);
    } else if (data.productType === "MATERIAL") {
        return router.createFetch(materialUrls.ADD, null, null, null, data);
    }

    return null;
}

export const addProduct = async () => {
    event.preventDefault();

    console.log("HERE");
    try {
        const responseData = await sendData();
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
    window.location.href = "supplier.html"; // Replace with the desired URL
};
$(function () {
    console.log("A");
    document.querySelector("#add-btn").addEventListener("click", addProduct);
    document.querySelector("#cancel-btn").addEventListener("click",cancelButton);
});

$(document).ready(function() {
    $('#product-type').change(function() {
        const selectedOption = $(this).val();
        if (selectedOption === 'MATERIAL') {
            $('#sensitivity').parent().parent().hide();
        } else {
            $('#sensitivity').parent().parent().show();
        }
    });
});
