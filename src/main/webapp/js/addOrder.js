import supplierUrls from "./urls/supplierUrls.js";
import {createButtonsAndText, createGenericTable, createTable} from "./table/table.js";
import '../js/table/fancyTable.js'
import drugUrls from "./urls/drugUrls.js";
import materialUrls from "./urls/materialUrls.js";

let tableData;
const router = new Router();

export const onInitial = async () => {
    try {
        const data = await Promise.allSettled([router.createFetch(drugUrls.GET_ALL), router.createFetch(materialUrls.GET_ALL)])
        const productData =[
            ...data[0].value.map(item => ({ ...item,type : "drug"})),
            ...data[1].value.map(item => ({ ...item,type : "material"}))
        ]
        populateTable(productData);
    } catch (e) {
        console.log("Error: ", e);
    }
}

// Populate table with data
function populateTable(data) {
    console.log("raw",data)

    //Name: item.name, Price:item.price, Supplier: item["supplier"].name, id : item.id ,
    const tableData = data.map(obj => {
        const newObj = {
            Name: obj.name, //isOrder ? obj.drug.name : obj.material.name,
            Price: obj.price, //isOrder ? obj.drug.price : obj.material.price,
            Supplier: obj.supplier.name,
            Quantity: "button"
        };
        return newObj;
    });
    console.log("tbldt",tableData)

    const footerContent = {
        button: {
            active: true,
            cancel: "Cancel",
            submit: "Submit Order",
            //onCancel: ,
            //onSubmit:

        },
        text: {
            active: true,
            left: "Total Price: $",
        }
    }
    createGenericTable(
        "order-list",
        ["","Name", "Price", "Supplier", "Quantity"],
        tableData,
        footerContent,
    );

    const filterForm = document.getElementById('filterForm');
    filterForm.addEventListener('submit', (event) => {
        event.preventDefault();

        // Filter the data
        const selectedSupplier = document.getElementById('filterSupplier').value;
        const selectedType = document.getElementById('filterType').value;
        console.log("type",selectedType,"sup",selectedSupplier)

        const filteredData = data.filter(obj => {

            return (
                (selectedType === '' || selectedType === 'All' || obj.type === selectedType) &&
                (selectedSupplier === '' || selectedSupplier === 'All' || obj.supplier.name === selectedSupplier)
            );
        });

        console.log("fıııl",filteredData)
        const elem = document.getElementById('order-list');
        elem.parentNode.replaceChildren("");

        if (selectedSupplier === 'All') {
            populateTable(filteredData); // Display all data
        } else {
            populateTable(filteredData); // Display filtered data
        }
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
        option.value = name;
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