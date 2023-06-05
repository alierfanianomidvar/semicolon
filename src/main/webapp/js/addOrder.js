import supplierUrls from "./urls/supplierUrls.js";
import {createButtonsAndText, createGenericTable, createTable} from "./table/table.js";
import orderUrls from "./urls/orderUrls.js";
import drugUrls from "./urls/drugUrls.js";
import materialUrls from "./urls/materialUrls.js";
import '../js/table/fancyTable.js'

let data;
const router = new Router();
console.log(Promise.resolve(router.createFetch(orderUrls.GET_ALL)),"HEREEEE")
console.log(Promise.resolve(router.createFetch(supplierUrls.GET_ALL)),"SUPPLIER")

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
        const isOrder = obj.order !== null;
        const newObj = {
            Name: obj.name, //isOrder ? obj.drug.name : obj.material.name,
            Price: obj.price, //isOrder ? obj.drug.price : obj.material.price,
            IsActive: isOrder ? "Active" : "Not Active",
            Quantity: obj.quantity
        };
        return newObj;
    });
    console.log(tableData)
    const footerContent = {
        button: {
            active: true,
            cancel: "Cancel",
            submit: "Submit Order",
            //onCancel: resetTotal(),
            //onSubmit:

        },
        text: {
            active: true,
            left: "Total Price: $",
        }
    }
    createGenericTable(
        "order-list",
        ["Name", "Price", "IsActive", "Quantity"],
        tableData,
        footerContent,
    );

    const filterForm = document.getElementById('filterForm');
    filterForm.addEventListener('submit', (event) => {
        event.preventDefault();

        // Filter the data
        const selectedSupplier = document.getElementById('filterSupplier').value;
        const selectedType = document.getElementById('filterType').value;

        const filteredData = data.filter(obj => {

            return (selectedType === '' || obj[selectedType] !== null) && (selectedSupplier === '' || obj[selectedSupplier] !== null) ;
        });

        const elem = document.getElementById('order-list');
        elem.remove();
        console.log(elem)
        populateTable(filteredData);

    });
}

export const supplierOption = async () => {
    const router = new Router()
    let supplierData;
    const supplier = Promise.resolve(router.createFetch(supplierUrls.GET_ALL))

    supplierData = await supplier
    console.log(supplierData);

    //supplier option
    const selectedSupplier = supplierData.map(obj => obj.name)
    console.log(selectedSupplier);

    const selectElementSupplier = document.getElementById('filterSupplier');

    selectedSupplier.forEach(name => {
        const option = document.createElement('option');
        option.text = name;
        selectElementSupplier.add(option);
    });
}


/*
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