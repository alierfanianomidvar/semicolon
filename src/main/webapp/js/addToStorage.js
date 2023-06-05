// Get all drugs data
import {createGenericTable} from "./table/table";
import drugUrls from "./urls/drugUrls";

let data;
const router = new Router();

export const onInitial = async () => {
    try {
        data = await router.createFetch(drugUrls.GET_ALL);
    console.log("MAMADjvkug",data)
        populateTable(data);
    } catch (e) {
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
        ["Name", "Age Group", "Sensivity", "Price", "Limitation"],
        tableData,
    );
}
