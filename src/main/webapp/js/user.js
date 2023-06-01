import {createTable, rWord} from "./table/table.js";



$(() => {


    console.log("Here");

    // Example usage: Create a table with dynamic column names and content
    const tableId = "dynamicTable"; // Dynamic table ID
    const columnNames = ["","Name", "Last Name", "Role", "Address", "Status"]; // Dynamic column names
    const numRows = 1000; // Total number of rows

    // Generate the table ID dynamically
    const table = $(`<table>`, {
        id: tableId,
        class: "table table-striped sampleTable",
    });

    // Append the table to the container
    $(".border").append(table);

    createTable(tableId, columnNames, numRows, () => {
        return rWord(8); // Generate random cell content
    }, "user");

    // And make the table fancy
    // const fancyTableA = $(`#${tableId}`).fancyTable({
    //     sortColumn: 0,
    //     pagination: true,
    //     perPage: 5,
    //     globalSearch: true,
    // });
});