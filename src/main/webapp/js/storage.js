import storageUrls from "./urls/storageUrls.js";
import {createTable, rWord} from "./table/table.js";

$(document).ready(function() {
    const ar = new Router();
    const data = ar.createFetch(storageUrls.GET_ALL);
    console.log(data);

    // Add Product button
    $('#add-button').click(function() {
        // Get the values from the input fields
        console.log("")  // console.log("hello") for control
        var name = $('#Name').val();
        var type = $('#Type').val();
        var price = $('#Price').val();
        var quantity = $('#Quantity').val();
        var threshold = $('#Threshold').val();

        // Create a new row with the entered data
        var newRow = '<tr>' +
            '<td>' + name + '</td>' +
            '<td>' + type + '</td>' +
            '<td>' + price + '</td>' +
            '<td>' + quantity + '</td>' +
            '<td>' + threshold + '</td>' +
            '</tr>';

        // Append the new row to the table
        $('#storage-data').append(newRow);

        // Clear the input fields
        $('#Name').val('');
        $('#Type').val('');
        $('#Price').val('');
        $('#Quantity').val('');
        $('#Threshold').val('');
    });

    // Filter button
    $('#filter-button').click(function() {
        // Get the selected filter values
        var selectedThreshold = $('#Threshold').val();
        var selectedType = $('#Type').val();
        var selectedAmount = $('#Amount').val();

        // Show/hide rows based on the filter values
        $('#storage-data tbody tr').each(function() {
            var rowThreshold = $(this).find('td:nth-child(6)').text();
            var rowType = $(this).find('td:nth-child(3)').text();
            var rowAmount = $(this).find('td:nth-child(4)').text();

            if ((selectedThreshold === '' || rowThreshold === selectedThreshold) &&
                (selectedType === '' || rowType === selectedType) &&
                (selectedAmount === '' || rowAmount === selectedAmount)) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    });

    $(() => {
        const tableId = "storage_list"; // Dynamic table ID
        const columnNames = ["", "Name", "Type", "Price", "Quantity", "Threshold"]; // Dynamic column names
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

         /*And make the table fancy
         const fancyTableA = $(`#${tableId}`).fancyTable({
             sortColumn: 0,
             pagination: true,
             perPage: 5,
             globalSearch: true,
        });
        */

        $('.search-box input[type="text"]').on('keyup', function() {
            const searchText = $(this).val().toLowerCase();
            $('table tbody tr').each(function() {
                const rowText = $(this).text().toLowerCase();
                $(this).toggle(rowText.indexOf(searchText) > -1);
            });
        });
    });
});
