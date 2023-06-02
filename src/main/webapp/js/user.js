import {createButtonsAndText, createTable, rWord} from "./table/table.js";



$(() => {

    // Example usage: Create a table with dynamic column names and content
    const tableId = "user_list"; // Dynamic table ID
    const columnNames = ["","Name", "Last Name", "Role", "Address", "Status"]; // Dynamic column names
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
    }, "user");



    // Call the createButtonsAndText function with the createButtons and createText parameters
    const bottomContainer = createButtonsAndText(false, true, "salam", "s", "Cancel", "Submit");

    // Append the main container to the page
    $(".border").append(bottomContainer);

});