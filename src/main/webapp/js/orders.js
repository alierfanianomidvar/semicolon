import {createTable, rWord} from "./table/table.js";



$(() => {

    // Example usage: Create a table with dynamic column names and content
    const tableId = "order_list"; // Dynamic table ID
    const columnNames = ["Order ID", "Price", "Status", "Supplier" , "Date"]; // Dynamic column names
    //TODO: number of rows must be edited and we need to put the correct number of rows based on our user list fetch api
    const numRows = 500; // Total number of rows

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



function generateFooterText(firstText, secondText) {
    // Create the first text element
    const firstTextElement = $("<span>", {
        text: firstText,
        css: {
            fontWeight: "bold",
            margin: "0.5rem", // Random margin between 1 and 10 pixels
        },
    });

    // Create the second text element
    const secondTextElement = $("<span>", {
        text: secondText,
        css: {
            fontWeight: "bold",
            margin: "0.5rem", // Random margin between 1 and 10 pixels
        },
    });

    // Create the container for the text elements
    const textContainer = $("<div>", {
        class: "text-container",
        css: {
            display: "flex",
            justifyContent: "space-between",
            width: "40%", // Adjust the width as needed
        },
    });

    // Append the text elements to the container
    textContainer.append(firstTextElement, secondTextElement);

    // Create the main container for the footer
    const container = $("<div>", {
        class: "bottom-container",
        css: {
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            marginTop: "0.75rem", // Adjust the top margin as needed
        },
    });

    // Append the table to the container
    $(".border").append(table);

    // Append the container to the page or desired parent element
    $(".border").append(container);

    // Append the text container to the main container
    container.append(textContainer);
}

// Example usage
generateFooterText("First Text", "Second Text");
