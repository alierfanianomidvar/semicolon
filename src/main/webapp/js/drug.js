import drugUrls from './urls/drugUrls.js';
// import {showErrorMessage} from "./utils.js";
// import {showModal} from "./modal";
import {createGenericTable} from "./table/table.js";

// Get all drugs data
let data;
const router = new Router();

export const onInitial =async () => {
    try{
        data = await router.createFetch(drugUrls.GET_ALL);
        //ToDo maybe use modal to show there is no drug?
        // if (!data.length){
        //     showModal('Order', "Are you sure to submit your order?", 'Order_submit', orderData, "Total Price: 20$")
        // }
        // showModal('Order', "Are you sure to submit your order?", 'Order_submit', orderData, "Total Price: 20$")
        populateTable(data);
    }catch (e) {
        console.log("Error: ", e);
    }

}

// Populate table with data
function populateTable(data) {

    const tableData = data.map(obj => {
        const newObj = {
            Name: obj.name,
            "Age Group": obj.ageGroup,
            Sensivity: obj.sensitive ? "Sensitive" : "Not Sensitive",
            Price: obj.price,
            Limitation: obj.limitation
        };
        return newObj;
    });
    console.log(tableData)
    createGenericTable(
        "drug-list",
        ["", "Name", "Age Group", "Sensivity", "Price", "Limitation"],
        tableData,
    );
}

// Filter table based on selected options
const filterForm = document.getElementById('filterForm');
filterForm.addEventListener('submit', (event) => {
    event.preventDefault();
    const sensitivityFilter = document.getElementById('filterSensitivity').value;
    const ageFilter = document.getElementById('filterAge').value;

    let filteredData = data.filter((drug) => {
        let pass = true;

        if (ageFilter !== '' && drug.ageGroup !== ageFilter) {
            pass = false;
        }
        if (sensitivityFilter !== '' && drug.sensitive !== (sensitivityFilter == 'true')) {
            pass = false;
        }

        return pass;
    });

    const elem = document.getElementById('drug-list');
    elem.remove();
    populateTable(filteredData);
});