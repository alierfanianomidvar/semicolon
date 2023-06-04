import {showModal} from "../js/modal.js";
import {createGenericTable} from "./table/table.js";
import supplierUrls from "./urls/supplierUrls.js";
import drugUrls from "./urls/drugUrls.js";
import materialUrls from "./urls/materialUrls.js";
export const onInitial = () => {

    const orderData = {
        price: "$13000",
        tax: "10%",
        discount: "12%"
    };
    //showModal('Order', "Are you sure to submit your order?", 'Order_submit', orderData, "Total Price: 20$")
    localStorage.getItem("getData")
    createGenericTable("order_list", ["","Name", "Price","IsActive","Quantity"]);
};

export const supplierOption = async () =>{
    const router = new Router()
    let supplierData;
    const supplier = Promise.resolve(router.createFetch(supplierUrls.GET_ALL))

    supplierData = await supplier
    console.log(supplierData);

    const selectedSupplier = supplierData.map(obj => obj.name)
    console.log(selectedSupplier);

    const selectElementSupplier = document.getElementById('filterSupplier');

    selectedSupplier.forEach(name => {
        const option = document.createElement('option');
        option.text = name;
        selectElementSupplier.add(option);
    });
}

export const sendOptionData = async () => {
    const data = JSON.parse(localStorage.getItem("getData"))
    console.log(data)

    const router = new Router()
    let materialData, drugData;

    const material  = Promise.resolve(router.createFetch(materialUrls.GET_ALL));
    materialData = await material;
    console.log("m", materialData);

    const drug  = Promise.resolve(router.createFetch(drugUrls.GET_ALL));
    drugData = await drug;

    return {
        material: materialData,
        drug: drugData
    };
}

export async function optionData () {
    let drug = [];
    let material = [];
    const data = await sendOptionData();
    const drugMaterial = [...data.drug, ...data.material]
    console.log("dm: ", drugMaterial)

    const selectElement = document.getElementById('filterProduct');

    drugMaterial.forEach(material => {
        const option = document.createElement('option');
        option.text = material.name;
        selectElement.add(option);
    });
}

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

export const resetTotal = () => {
    var cancelBtn = document.getElementById("cancel-btn");

    cancelBtn.addEventListener("click", function () {
        document.getElementById("total-price").textContent = "Total Price: $0";
    });
}

export const linking = () => {
    const submitButton = document.getElementById('submit-order-btn');
    submitButton.addEventListener('click', function() {
        window.location.href = 'confirm-order.html';
    });
    //window.open("confirm-addOrder.html", "_blank", "width=600,height=400");
}
