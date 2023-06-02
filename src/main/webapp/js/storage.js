import storageUrls from "./urls/storageUrls.js";
import {createTable, rWord} from "./table/table.js";

/*
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

         And make the table fancy
         const fancyTableA = $(`#${tableId}`).fancyTable({
             sortColumn: 0,
             pagination: true,
             perPage: 5,
             globalSearch: true,
        });


        $('.search-box input[type="text"]').on('keyup', function() {
            const searchText = $(this).val().toLowerCase();
            $('table tbody tr').each(function() {
                const rowText = $(this).text().toLowerCase();
                $(this).toggle(rowText.indexOf(searchText) > -1);
            });
        });
    });
});
*/
$(document).ready(function(){
    // Get all drugs data
    const ar = new Router();
    const data = ar.createFetch(storageUrls.GET_ALL);
    console.log(data);

    // Populate table with data
    function populateTable(data) {
        const tbody = document.querySelector('tbody');
        tbody.innerHTML = '';

        if (!data.length){
            const tr = document.createElement('tr');
            const td = document.createElement('td');
            td.textContent = "No Products Found!";
            td.className = "text-center";
            td.colSpan = 5;
            tr.appendChild(td);
            tbody.appendChild(tr);
        }
        data.forEach((storage) => {
            const tr = document.createElement('tr');

            const nameTd = document.createElement('td');
            nameTd.textContent = storage.name;
            tr.appendChild(nameTd);

            const typeTd = document.createElement('td');
            typeTd.textContent = storage.type;
            tr.appendChild(typeTd);

            const priceTd = document.createElement('td');
            priceTd.textContent = `$${storage.price}`;   //drug.sensitive ? 'Sensitive' : 'Not Sensitive';
            tr.appendChild(priceTd);

            const quantityTd = document.createElement('td');
            quantityTd.textContent = storage.quantity;
            tr.appendChild(quantityTd);

            const thresholdTd = document.createElement('td');
            thresholdTd.textContent = storage.threshold;
            tr.appendChild(thresholdTd);

            tbody.appendChild(tr);
        });
    }

    // Add Product button
    $('#add-button').click(function() {
        // Get the values from the input fields
        console.log("hello")  // console.log("hello") for control
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

    // Filter table
    const filterForm = document.getElementById('filterForm');
    filterForm.addEventListener('submit', (event) => {
        event.preventDefault();
        const thresholdFilter = document.getElementById('filterThreshold').value;
        const typeFilter = document.getElementById('filterType').value;
        const amountFilter = document.getElementById('filterAmount').value;

        let filteredData = data.filter((storage) => {
            let pass = true;

            if (thresholdFilter !== '' && !storage.name !== (thresholdFilter)) {
                pass = false;
            }
            if (typeFilter !== '' && storage.type !== (typeFilter)) {
                pass = false;
            }
            if (amountFilter !== '' && storage.amount !== (amountFilter)) {
                pass = false;
            }

            return pass;
        });

        populateTable(filteredData);
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

        /*$('.search-box input[type="text"]').on('keyup', function() {
            const searchText = $(this).val().toLowerCase();
            $('table tbody tr').each(function() {
                const rowText = $(this).text().toLowerCase();
                $(this).toggle(rowText.indexOf(searchText) > -1);
            });
        });
         */
    });
});

