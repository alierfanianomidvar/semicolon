import supplierUrls from "./urls/supplierUrls.js";

import {showModal} from "../js/modal.js";
import {createTable, rWord} from "./table/table.js";
import {createGenericTable} from "./table/table.js";
import drugUrls from "./urls/drugUrls.js";
import materialUrls from "./urls/materialUrls.js";

/*
export const onInitial = () => {

    const orderData = {
        price: "$13000",
        tax: "10%",
        discount: "12%"
    };

    showModal('Order', "Are you sure to submit your order?", 'Order_submit', orderData, "Total Price: 20$")
    localStorage.getItem("getData")
    createGenericTable("order_list", ["","Name", "Price","IsActive","Quantity"]);
};
*/



// Define the createTable function
function createTable(tableId, columnNames, numRows, cellContentGenerator) {
    const table = $(`#${tableId}`);

    // Create the table header
    const thead = $("<thead>");
    const headerRow = $("<tr>");

    columnNames.forEach((columnName) => {
        const th = $("<th>", { text: columnName });
        headerRow.append(th);
    });

    thead.append(headerRow);
    table.append(thead);

    // Create the table body with rows and cells
    const tbody = $("<tbody>");

    for (let i = 0; i < numRows; i++) {
        const row = $("<tr>");

        columnNames.forEach(() => {
            const cell = $("<td>", {
                text: cellContentGenerator(),
            });
            row.append(cell);
        });
        tbody.append(row);
    }
    table.append(tbody);
}

$(() => {

    // Example usage: Create a table with dynamic column names and content
    const tableId = "addOrder_list"; // Dynamic table ID
    const columnNames = ["Name", "Price", "Is Active", "Quantity"]; // Dynamic column names
    //TODO: number of rows must be edited and we need to put the correct number of rows based on our user list fetch api
    const numRows = 1000; // Total number of rows

    // Generate the table ID dynamically
    const table = $(`<table>`, {
        id: tableId,
        class: "table table-striped sampleTable",
    });

    // Append the table to the container
    $(".border").append(table);

    // for passing the cellContentGenerator we need to define a proper function that returns the user actual information
    createTable(tableId, columnNames, numRows, () => {
        return rWord(8); // Generate random cell content
    });

    // And make the table fancy
    // const fancyTableA = $(`#${tableId}`).fancyTable({
    //     sortColumn: 0,
    //     pagination: true,
    //     perPage: 5,
    //     globalSearch: true,
    // });
});


function generateFooterTextAndButtons(firstText, secondText, cancelButtonText, acceptButtonText) {
    // Create the text element
    const textElement = $("<div>", {
        text: firstText,
        css: {
            fontWeight: "bold",
            margin: "0.5rem", // Random margin between 1 and 10 pixels
        },
    });

    // Create the cancel button
    const cancelButton = $("<button>", {
        text: cancelButtonText,
        class: "btn btn-custom-secondary",
        css: {
            margin: "0.5rem", // Random margin between 1 and 10 pixels
        },
    });

    // Create the accept button
    const acceptButton = $("<button>", {
        text: acceptButtonText,
        class: "btn btn-custom",
        data_toggle: "modal",
        data_target: "showModal",
        css: {
            margin: "0.5rem", // Random margin between 1 and 10 pixels
        },
    });

    // Create the container for the buttons
    const buttonsContainer = $("<div>", {
        class: "buttons-container",
        css: {
            display: "flex",
            justifyContent: "flex-end",
            width: "60%", // Adjust the width as needed
        },
    });

    // Append the buttons to the container
    buttonsContainer.append(cancelButton, acceptButton);

    // Create the container for the text and buttons
    const container = $("<div>", {
        class: "bottom-container",
        css: {
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            marginTop: "0.75rem", // Adjust the top margin as needed
        },
    });

    // Append the text and buttons to the container
    container.append(textElement, buttonsContainer);

    // Append the container to the page or desired parent element
    $(".border").append(container);
}

// Example usage
generateFooterTextAndButtons("Total Price : $12000", "Second Text", "Cancel", "Submit Order");

/*
export const supplierOption = async () =>{
    const data = JSON.parse(localStorage.getItem("getData"))
    console.log(data)

    const router = new Router()
    let supplierData;
    const supplier = Promise.resolve(router.createFetch(supplierUrls.GET_ALL))

    supplierData = await supplier
    console.log(supplierData);

    //supplier option
    const selectedSupplier = supplierData.map(obj => obj.name)
    console.log(selectedSupplier);

    const selectElementSupplier = document.getElementById('filterSupplier');

    selectedSupplier.forEach(name =>{
        const option = document.createElement('option');
        option.text = name;
        selectElementSupplier.add(option);
    });
}
 */

/*
export const supplierOption = async () =>{
    const router = new Router()
    let supplierData;
    const supplier = Promise.resolve(router.createFetch(supplierUrls.GET_ALL))

    supplierData = await supplier
    console.log(supplierData);

    //supplier option
    const selectedSupplier = supplierData.map(obj => obj.name)
    console.log(selectedSupplier);

    const selectElementSupplier = document.getElementById('filterSupplier');

    selectedSupplier.forEach(name =>{
        const option = document.createElement('option');
        option.text = name;
        selectElementSupplier.add(option);
    });
}

export const sendOptionData = async () => {
    const data = JSON.parse(localStorage.getItem("getData"))
    console.log(data)

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

export async function optionData () {
    let drug = [];
    let material = [];
    const data = await sendOptionData();
    const drugMaterial = [...data.drug, ...data.material]
    console.log("dm: ", drugMaterial)

    const selectElement = document.getElementById('filterProduct');

    drugMaterial.forEach(material => {
        const option = document.createElement('option');
        option.text = material.name;
        selectElement.add(option);
    });
}

export const calculateTotal = () => {
    var total = 0;
    var checkboxes = document.getElementsByClassName("order-checkbox");

    for (var i = 0; i < checkboxes.length; i++) {
        var checkbox = checkboxes[i];
        if (checkbox.checked) {
            var price = parseFloat(checkbox.getAttribute("data-price"));
            total += price;
        }
    }
    document.getElementById("total-price").textContent = "Total Price: $" + total.toFixed(2);
}
*/

/*
export const resetTotal = () => {
    $(document).ready(() => {
        const cancelButton = $('#cancel-btn');
        if (cancelButton) {
            cancelButton.on('click', () => {
                const totalPrice = $('#total-price');
                if (totalPrice) {
                    totalPrice.text('Total Price: $0');
                }
            });
        }
    });
};


export const linking = () => {
    $(document).ready(() => {
        const submitButton = $('#submit-order-btn');
        if (submitButton) {
            submitButton.on('click', () => {
                window.location.href = 'confirm-order.html';
            });
        }
    });
};
*/