import {createTable} from "./table/table.js";

$(() => {
    const rWord = (r) => {
        let t, n = "bcdfghjklmnpqrstvwxyz", a = "aeiou", e = function (r) {
            return Math.floor(Math.random() * r)
        }, o = "";
        r = parseInt(r, 10), n = n.split(""), a = a.split("");
        for (t = 0; t < r / 2; t++) {
            const l = n[e(n.length)], p = a[e(a.length)];
            o += 0 === t ? l.toUpperCase() : l, o += 2 * t < r - 1 ? p : ""
        }
        return o
    };

    console.log("Here");

    // Example usage: Create a table with dynamic column names and content
    const tableId = "dynamicTable"; // Dynamic table ID
    const columnNames = ["Col A", "Col B", "Col C"]; // Dynamic column names
    const numRows = 1000; // Total number of rows

    // Generate the table ID dynamically
    const table = $(`<table>`, {
        id: tableId,
        class: "table table-striped sampleTable",
    });

    // Append the table to the container
    $(".container").append(table);

    createTable(tableId, columnNames, numRows, () => {
        return rWord(8); // Generate random cell content
    });

    // And make the table fancy
    const fancyTableA = $(`#${tableId}`).fancyTable({
        sortColumn: 0,
        pagination: true,
        perPage: 5,
        globalSearch: true,
    });
});