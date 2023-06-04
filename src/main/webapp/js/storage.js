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
    createGenericTable("storage_list", ["","Name", "Type", "Price", "Amount", "Threshold"]);

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

export const listTable = async () => {
    const data = JSON.parse(localStorage.getItem("getData"))
    console.log(data)

    const router = new Router()
    let storageData;
    const storage = Promise.resolve(router.createFetch(storageUrls.GET_ALL))

    storageData = await storage
    console.log(storageData);

    const tableBody = document.getElementById('tableBody');
    tableBody.innerHTML = ''; // Clear existing table body

    if (storageData.length === 0) {
        const tr = document.createElement('tr');
        const td = document.createElement('td');
        td.textContent = 'No Products Found!';
        td.className = 'text-center';
        td.colSpan = 5;
        tr.appendChild(td);
        tableBody.appendChild(tr);
    } else {
        storageData.forEach((storage) => {
            const tr = document.createElement('tr');

            const nameTd = document.createElement('td');
            nameTd.textContent = storage.name;
            tr.appendChild(nameTd);

            const typeTd = document.createElement('td');
            typeTd.textContent = storage.type;
            tr.appendChild(typeTd);

            const priceTd = document.createElement('td');
            priceTd.textContent = `$${storage.price}`;
            tr.appendChild(priceTd);

            const quantityTd = document.createElement('td');
            quantityTd.textContent = storage.amount;
            tr.appendChild(quantityTd);

            const thresholdTd = document.createElement('td');
            thresholdTd.textContent = storage.threshold;
            tr.appendChild(thresholdTd);

            tableBody.appendChild(tr);
        });
    }
}

export const filtering = () => {
    const filterButton = document.getElementById('filter-button');
    filterButton.addEventListener('click', () => {
        const storedData = JSON.parse(localStorage.getItem("getData")); // Retrieve stored data from localStorage
        console.log(storedData);

        // Filter the data
        const selectedName = document.getElementById('Name').value;
        const selectedType = document.getElementById('Type').value;
        const selectedPrice = document.getElementById('Price').value;
        const selectedQuantity = document.getElementById('Quantity').value;
        const selectedThreshold = document.getElementById('Threshold').value;

        const filteredData = storedData.filter(item => {
            return (selectedName === '' || item.threshold === selectedName) &&
                (selectedType === '' || item.type === selectedType) &&
                (selectedPrice === '' || item.type === selectedPrice) &&
                (selectedQuantity === '' || item.amount === selectedQuantity) &&
                (selectedThreshold === '' || item.threshold === selectedThreshold);
        });

        console.log(filteredData);
    });
};

export const createOption = async () => {
    const data = JSON.parse(localStorage.getItem("getData"))
    console.log(data)

    const router = new Router()
    let storageData;
    const storage = Promise.resolve(router.createFetch(storageUrls.GET_ALL))

    storageData = await storage
    console.log(storageData);

    //for amount option
    const selectedAmount = storageData.map(obj => obj.amount)
    console.log(selectedAmount);

    const selectElementAmount = document.getElementById('filterAmount');

    selectedAmount.forEach(amount => {
        const option = document.createElement('option');
        option.text = amount;
        selectElementAmount.add(option);
    });

    // for type option
    const selectedType = storageData.map(obj => obj.type)
    console.log(selectedType);

    const selectElementType = document.getElementById('filterType');

    selectedType.forEach(type => {
        const option = document.createElement('option');
        option.text = type;
        selectElementType.add(option);
    });

    //for threshold option
    const selectedThreshold = storageData.map(obj => obj.threshold)
    console.log(selectedThreshold);

    const selectElementThreshold = document.getElementById('filterThreshold');

    selectedThreshold.forEach(threshold => {
        const option = document.createElement('option');
        option.text = threshold;
        selectElementThreshold.add(option);
    });
}