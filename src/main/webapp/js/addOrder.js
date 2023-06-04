import supplierUrls from "./urls/supplierUrls.js";
import {showModal} from "../js/modal.js";
import {createGenericTable} from "./table/table.js";
import orderUrls from "./urls/orderUrls";

let data;
const router = new Router();

export const onInitial = async () => {
    try {
        data = await router.createFetch(orderUrls.GET_ALL);
        console.log(data)
        populateTable(data);
    } catch (e) {
        console.log("Error: ", e);
    }
}

// Populate table with data
function populateTable(data) {

    const tableData = data.map(obj => {
        const isDrug = obj.drug !== null;
        const newObj = {
            Name: isDrug ? obj.drug.name : obj.material.name,
            Price: isDrug ? obj.drug.price : obj.material.price,
            IsActive: isDrug ? "Active" : "Not Active",
            Amount: obj.amount
        };
        return newObj;
    });
    console.log(tableData)
    createGenericTable(
        "order-list",
        ["Name", "Price", "IsActive", "Amount"],
        tableData,
    );
}

const filterForm = document.getElementById('filterForm');
filterForm.addEventListener('submit', (event) => {
    event.preventDefault();

    // Filter the data
    const selectedType = document.getElementById('filterType').value;

    const filteredData = data.filter(obj => {

        return selectedType === '' || obj[selectedType] !== null;
    });


    const elem = document.getElementById('order-list');
    elem.remove();
    populateTable(filteredData);

});

export const supplierOption = async () =>{
    const router = new Router()
    let supplierData;
    const supplier = Promise.resolve(router.createFetch(supplierUrls.GET_ALL))

    supplierData = await supplier
    console.log(supplierData);

    //supplier option
    const selectedSupplier = supplierData.map(obj => obj.name)
    console.log(selectedSupplier);

    const selectElementSupplier = document.getElementById('filterSupplier');

    selectedSupplier.forEach(name =>{
        const option = document.createElement('option');
        option.text = name;
        selectElementSupplier.add(option);
    });
}


/*
//for calculation total price
export const calculateTotal = () => {
    var total = 0;
    var checkboxes = document.getElementsByClassName("order-checkbox");

    for (var i = 0; i < checkboxes.length; i++) {
        var checkbox = checkboxes[i];
        if (checkbox.checked) {
            var price = parseFloat(checkbox.getAttribute("data-price"));
            total += price;
        }
    }
    document.getElementById("total-price").textContent = "Total Price: $" + total.toFixed(2);
}


//for making total 0 when clicking cancel button
export const resetTotal = () => {
    $(document).ready(() => {
        const cancelButton = $('#cancel-btn');
        if (cancelButton) {
            cancelButton.on('click', () => {
                const totalPrice = $('#total-price');
                if (totalPrice) {
                    totalPrice.text('Total Price: $0');
                }
            });
        }
    });
};

//link for confirm-order.html
export const linking = () => {
    $(document).ready(() => {
        const submitButton = $('#submit-order-btn');
        if (submitButton) {
            submitButton.on('click', () => {
                window.location.href = 'confirm-order.html';
            });
        }
    });
};
*/