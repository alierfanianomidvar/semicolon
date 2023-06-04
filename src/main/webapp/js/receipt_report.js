import {createGenericTable} from "./table/table.js";

export const onInitial = () => {
    console.log("here");
    const orderData = [{
        id:1,
        price: "$13000",
        status: "active",
        date: "12-12-2023"
    },{
        id:2,
        price: "$13000",
        status: "active",
        date: "12-12-2023"
    },{
        id:3,
        price: "$13000",
        status: "active",
        date: "12-12-2023"
    },{
        id:4,
        price: "$13000",
        status: "active",
        date: "12-12-2023"
    }]

    const footerContent = {
        button: {
            active: true,
            cancel: "Cancel",
            onCancel: () => {},
            submit: "Submit",
            onSubmit: () => {}
        }
    }

    // showModal('Order', "Are you sure to submit your order?", 'Order_submit', orderData, "Total Price: 20$")
    createGenericTable(
        "order_report",
        ["","id", "price", "status", "date"],
        orderData,
    );

};