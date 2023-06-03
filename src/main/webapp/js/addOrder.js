import {createTable, rWord} from "./table/table.js";
import {showModal} from "../js/modal.js";
export const onInitial = () => {



const orderData = {
    price: "$13000",
    tax: "10%",
    discount: "12%"
};
showModal('Order', "Are you sure to submit your order?", 'Order_submit', orderData, "Total Price: 20$")
};
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
