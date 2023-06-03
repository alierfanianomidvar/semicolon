import {showModal} from "../js/modal.js";
import {createGenericTable} from "./table/table.js";
export const onInitial = () => {

    const orderData = {
        price: "$13000",
        tax: "10%",
        discount: "12%"
    };
    showModal('Order', "Are you sure to submit your order?", 'Order_submit', orderData, "Total Price: 20$")
    createGenericTable("user_list", ["","Name", "LastName", "Role", "Address", "Status"]);

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