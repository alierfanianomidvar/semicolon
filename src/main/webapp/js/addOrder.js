import supplierUrls from "./urls/supplierUrls.js";
import {createButtonsAndText, createGenericTable, createTable} from "./table/table.js";
import '../js/table/fancyTable.js'
import drugUrls from "./urls/drugUrls.js";
import materialUrls from "./urls/materialUrls.js";
import {showModal} from "./modal.js";

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
    console.log(data)

    //Name: item.name, Price:item.price, Supplier: item["supplier"].name, id : item.id ,

    const tableData = data.map(obj => {
        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';


        // Create an empty array to store the selected products
        const selectedProducts = [];

// Event listener for checkbox clicks
        checkbox.addEventListener('change', () => {
            if (checkbox.checked) {
                // Checkbox is checked, add product details to the selectedProducts array
                selectedProducts.push({
                    Name: obj.name, // Store the name of the product
                    Price: obj.price * parseInt(quantityInput.value) // Multiply the price by the quantity
                });
            } else {
                // Checkbox is unchecked, remove product details from the selectedProducts array
                const index = selectedProducts.findIndex(product => product.Name === obj.name);
                if (index !== -1) {
                    selectedProducts.splice(index, 1);
                }
            }
        });



        // document.addEventListener(()=>{

        // })

        // const decreaseBtn = document.createElement('button');
        // decreaseBtn.className = 'btn btn-outline-secondary';
        // decreaseBtn.textContent = '-';

        // const increaseBtn = document.createElement('button');
        // increaseBtn.className = 'btn btn-outline-secondary';
        // increaseBtn.textContent = '+';

        const quantityInput = document.createElement('input');
        quantityInput.type = 'number';
        quantityInput.className = 'form-control';
        quantityInput.id = 'quantity';
        quantityInput.min = '0';
        quantityInput.value = '0';


        // decreaseBtn.addEventListener('click', () => {
        //     let quantity = parseInt(quantityInput.value);
        //     if (quantity > 1) {
        //         quantity--;
        //         quantityInput.value = quantity;
        //     }
        // });
        //
        // increaseBtn.addEventListener('click', () => {
        //     let quantity = parseInt(quantityInput.value);
        //     quantity++;
        //     quantityInput.value = quantity;
        // });

        const newObj = {
            Checkbox: checkbox,
            Name: obj.name, //isOrder ? obj.drug.name : obj.material.name,
            Price: obj.price, //isOrder ? obj.drug.price : obj.material.price,
            Supplier: obj.supplier.name,
            Quantity: [quantityInput]
        };
        return newObj;
    });


    /*
    const tableData = data.map(obj => {
        const newObj = {
            Name: obj.name, //isOrder ? obj.drug.name : obj.material.name,
            Price: obj.price, //isOrder ? obj.drug.price : obj.material.price,
            Supplier: obj.supplier.name,
            Quantity: "button"
        };
        return newObj;
    });
    */
    console.log(tableData)

    const footerContent = {
        button: {
            active: true,
            cancel: "Cancel",
            submit: "Submit Order",
            onCancel: () => {
                    window.location.href = 'orders.html';
                },
            onSubmit:() => {
                //window.location.href = '#confirm-order.html';
                showModal("Submit", "", "Order_submit", {
                    price:"1200",
                    tax:"10",
                    discount:"12",
                    totalPrice:""
                }, null)

            },

        },
        text: {
            active: true,
            left: " ",
        }
    }
    createGenericTable(
        "order-list",
        ["Checkbox","Name", "Price", "Supplier", "Quantity"],
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

        console.log(filteredData)
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
