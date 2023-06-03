import storageUrls from "./urls/storageUrls.js";
import {createTable, rWord} from "./table/table.js";

import {showModal} from "../js/modal.js";
import {createGenericTable} from "./table/table.js";
export const onInitial = () => {

    const orderData = {
        price: "$13000",
        tax: "10%",
        discount: "12%"
    };
    showModal('Order', "Are you sure to submit your order?", 'Order_submit', orderData, "Total Price: 20$")
    localStorage.getItem("getData")
    createGenericTable("storage_list", ["","Name", "Type", "Price", "Quantity", "Threshold"]);

    // const tableData = [
    //     { id: 1, name: 'John Doe', age: 25, city: 'New York' },
    //     { id: 2, name: 'Jane Smith', age: 30, city: 'London' },
    //     { id: 3, name: 'Bob Johnson', age: 40, city: 'Paris' },
    //     { id: 4, name: 'Alice Williams', age: 35, city: 'Tokyo' }
    // ];
    // showModal("Receipt", null, 'Receipt_submit', tableData, "Total Price: 20$")
    // const orderData = {
    //     price: "$13000",
    //     tax: "10%",
    //     discount: "12%"
    // };
    // showModal('Order', "Are you sure to submit your order?", 'Order_submit', orderData, "Total Price: 20$")
    // createGenericTable("user_list", ["","Name", "Last Name", "Role", "Address", "Status"]);
};

export const filtering = () => {
    /* Fetch data from the backend
    fetch('/api/data')
        .then(response => response.json())
        .then(data => {
            // Process the fetched data
            console.log(data);

            // Filter the data
            const selectedThreshold = document.getElementById('Threshold').value;
            const selectedType = document.getElementById('Type').value;
            const selectedAmount = document.getElementById('Amount').value;

            const filteredData = data.filter(item => {
                return (selectedThreshold === '' || item.threshold === selectedThreshold) &&
                    (selectedType === '' || item.type === selectedType) &&
                    (selectedAmount === '' || item.amount === selectedAmount);
            });

            console.log(filteredData);

            // Further processing or rendering of the filtered data
            // ...
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        }); */
    //localStorage.getItem("getData")

    const storedData = JSON.parse(localStorage.getItem("getData")); // Retrieve stored data from localStorage
    console.log(storedData);

    // Filter the data
    const selectedThreshold = document.getElementById('Threshold').value;
    const selectedType = document.getElementById('Type').value;
    const selectedAmount = document.getElementById('Amount').value;

    const filteredData = storedData.filter(item => {
        return (selectedThreshold === '' || item.threshold === selectedThreshold) &&
            (selectedType === '' || item.type === selectedType) &&
            (selectedAmount === '' || item.amount === selectedAmount);
    });

    console.log(filteredData);
};


/*
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
 */
/*
$(document).ready(function(){
    // Get all data
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

        //And make the table fancy
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

