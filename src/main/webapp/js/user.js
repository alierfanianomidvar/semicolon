import {createTable, rWord} from "./table/table.js";
import {showModal} from "./modal.js";

//This is just an example to be used in modal
//TODO: Real data to be passed must be taken from the content in route.html using javascript selector or sth
const tableData = [
    { id: 1, name: 'John Doe', age: 25, city: 'New York' },
    { id: 2, name: 'Jane Smith', age: 30, city: 'London' },
    { id: 3, name: 'Bob Johnson', age: 40, city: 'Paris' },
    { id: 4, name: 'Alice Williams', age: 35, city: 'Tokyo' }
];

$(() => {

    showModal("Receipt", null, 'Receipt_submit', tableData, "Total Price: 20$")
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

    // And make the table fancy
    // const fancyTableA = $(`#${tableId}`).fancyTable({
    //     sortColumn: 0,
    //     pagination: true,
    //     perPage: 5,
    //     globalSearch: true,
    // });
});