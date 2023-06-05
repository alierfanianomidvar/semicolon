import storageUrls from "./urls/storageUrls.js";
import materialUrls from "./urls/materialUrls.js";
import drugUrls from "./urls/drugUrls.js";
import {createGenericTable} from "./table/table.js";
import pharmacyUrls from "./urls/pharmacyUrls.js";
export async function sendPharmacyData() {


    const router = new Router()
    let materialData, drugData;
    const material  = Promise.resolve(router.createFetch(materialUrls.GET_ALL));

    materialData = await material;

    console.log("m", materialData);
    const drug  = Promise.resolve(router.createFetch(drugUrls.GET_ALL));

    drugData = await drug;
    return {
        material: materialData,
        drug: drugData
    };
}


export async function oninitial () {
    let drug = [];
    let material = [];
    const data = await sendPharmacyData();
    const drugMaterial = [...data.drug, ...data.material]
    console.log("dm: ", drugMaterial)
    console.log("this is sendPharmacy: ", data)

    const selectElement = document.getElementById('product-list');

    drugMaterial.forEach(material => {
        const option = document.createElement('option');
        option.text = material.name;
        selectElement.add(option);

    });
    // const receiptData = []
    // const footerContent = {
    //     button: {
    //         active: true,
    //         cancel: "Cancel",
    //         onCancel: () => {},
    //         submit: "Submit",
    //         onSubmit: () => {}
    //     }
    // }
    //
    // createGenericTable(
    //     "receipt_list",
    //     ["","Name", "threshold", "amount"],
    //     receiptData,
    //     footerContent
    // );
}

//export function addToReceipt(selectId, amountId, thresholdId, receiptData, footerContent) {
export function addToReceipt() {
    alert($( "#Drug-list option:selected" ).text())
    const selectValue = document.getElementById(selectId).value;
    const amountValue = document.getElementById(amountId).value;
    const thresholdValue = document.getElementById(thresholdId).value;

    // Validate the input
    if (!selectValue || !amountValue || !thresholdValue) {
        alert('Please fill out all fields before adding to the receipt.');
        return;
    }

    // Convert amount and threshold values to numbers
    const amount = parseInt(amountValue);
    const threshold = parseInt(thresholdValue);

    // Validate the number inputs
    if (isNaN(amount) || isNaN(threshold) || amount < 1 || threshold < 1) {
        alert('Please enter valid positive integers for the amount and threshold.');
        return;
    }

    // Check if an entry with the same name already exists in the receipt data
    const existingEntryIndex = receiptData.findIndex(entry => entry.Name === selectValue);
    if (existingEntryIndex !== -1) {
        alert('An entry with the same name already exists in the receipt. Please edit the existing entry instead of adding a new one.');
        return;
    }

    // Add the new entry to the receipt data
    receiptData.push({
        Name: selectValue,
        amount: amount,
        threshold: threshold
    });

    // Re-render the table with the new data
    const table = document.getElementById('receipt_list');
    table.innerHTML = '';
    createGenericTable('receipt_list', ['id', 'date', 'paymentMethod'], receiptData, footerContent, 'default');
}

export async function addPharmacy(event) {
    event.preventDefault()
    const  name = document.getElementById("name").value;
    const telephone_number = document.getElementById("telephone-number").value;
    const from_hour = document.getElementById("from-hour").value;
    const to_hour = document.getElementById("to-hour").value;
    const drug  = document.getElementById("product-list").value;
    const threshold = document.getElementById("threshold").value;
    const amount = document.getElementById("amount").value;


    const time_table = [from_hour, to_hour];

    const addPharmacyData = {
        name,
        telephone_number,
        time_table,
    }

    const router = new Router()
    let pharmacyData;
    const pharmacy  = Promise.resolve(router.createFetch(pharmacyUrls.ADD, null , null , "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiUm9sZSI6ImFkbWluIiwiaWF0IjoxNjg1ODczMzk2LCJleHAiOjE3MjE4NzMzOTZ9.NwfKwKnQsuFHhd65JWqz5V2IIiR3hEZHOLK5gxQIozU" , addPharmacyData));

    pharmacyData = await pharmacy;
    console.log(pharmacyData)

}

// $(function () {
//     console.log("A");
//     document.querySelector("#add-to-storage").addEventListener("click", addPharmacy);
// });