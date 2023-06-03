import {showModal} from "./modal.js";
const tableData = [
    { id: 1, name: 'John Doe', age: 25, city: 'New York' },
    { id: 2, name: 'Jane Smith', age: 30, city: 'London' },
    { id: 3, name: 'Bob Johnson', age: 40, city: 'Paris' },
    { id: 4, name: 'Alice Williams', age: 35, city: 'Tokyo' }
];
$(() => {

    // showModal()
    showModal("Receipt", null, 'Receipt_submit', tableData, "Total Price: 20$")
});

