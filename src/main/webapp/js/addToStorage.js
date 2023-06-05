// Get all drugs data
import {createGenericTable} from "./table/table.js";
import drugUrls from "./urls/drugUrls.js";

let data,drug,material;
const router = new Router();

export const onInitial = async () => {
    try {
        drug = await router.createFetch(drugUrls.GET_ALL);
        material = await router.createFetch(drugUrls.GET_ALL);

        data = [
            ...drug.map(item => ({ name: item.name, id : item.id ,type : "drug"})),
            ...material.map(item => ({ name: item.name, id : item.id , type : "material"}))
        ];
        console.log("drug and material",data)
        // populateTable(data);


        const selectElement = document.getElementById("product-list");

        data.forEach(item => {
            const option = document.createElement("option");
            option.text = item.name;
            selectElement.add(option);
        });

    } catch (e) {
        console.log("Error: ", e);
    }

}



// Populate table with data
// function populateTable() {
//
//     const tableData = data.map(obj => {
//         const newObj = {
//             Name: obj.name,
//             Amount: obj.amount,
//             Threshold: obj.threshold,
//             Discount: obj.discount,
//         };
//         return newObj;
//     });
//     console.log(tableData)
//     createGenericTable(
//         "storage-list",
//         ["Name", "Amount", "Threshold", "Discount"],
//         tableData,
//         null,
//         "default"
//     );
// }
