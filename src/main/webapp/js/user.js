import {createButtonsAndText, createTable, rWord} from "./table/table.js";



$(() => {

    //TODO: instead of this we need to have real data. This is just an example

    const userData = [{
        Name: "Ali",
        LastName: "Mahdavi",
        Role: "Admin",
        Address: "via romana",
        Status: "Active",
        image: "../images/users.svg"
    },{
        Name: "Abi",
        LastName: "mor",
        Role: "Staff",
        Address: "via romana",
        Status: "Active",
        image: "../images/users.svg"
    },{
        Name: "Alex",
        LastName: "pegi",
        Role: "Admin",
        Address: "via romana",
        Status: "Active",
        image: "../images/users.svg"
    }]
    // Example usage: Create a table with dynamic column names and content
    const tableId = "user_list"; // Dynamic table ID
    const columnNames = ["","Name", "LastName", "Role", "Address", "Status"]; // Dynamic column names
    //TODO: number of rows must be edited and we need to put the correct number of rows based on our user list fetch api
    const numRows = userData.length; // Total number of rows

    // Generate the table ID dynamically
    const table = $(`<table>`, {
        id: tableId,
        class: "table table-striped sampleTable",
    });

    // Append the table to the container
    $(".border").append(table);

    // for passing the cellContentGenerator we need to define a proper function that returns the user actual information
    createTable(tableId, columnNames, numRows, userData, "user");



    // Call the createButtonsAndText function with the createButtons and createText parameters
    const bottomContainer = createButtonsAndText(false, true, "salam", "s", "Cancel", "Submit");

    // Append the main container to the page
    $(".border").append(bottomContainer);

});