import {showModal} from "../js/modal.js";
import {createGenericTable} from "./table/table.js";
export const onInitial = () => {

    const orderData = {
        price: "$13000",
        tax: "10%",
        discount: "12%"
    };
    const userData = [{
        Id: 1,
        Name: "Ali",
        "Last Name": "Mahdavi",
        Role: "Admin",
        Address: "via romana",
        Status: "Active",
        image: "../images/users.svg"
    },{
        Id: 2,
        Name: "Abi",
        "Last Name": "mor",
        Role: "Staff",
        Address: "via romana",
        Status: "Active",
        image: "../images/users.svg"
    },{
        Id: 3,
        Name: "Alex",
        "Last Name": "pegi",
        Role: "Admin",
        Address: "via romana",
        Status: "Active",
        image: "../images/users.svg"
    }]

    const footerContent = {
        button: {
            active: true,
            cancel: "Cancel",
            onCancel: () => {},
            submit: "Submit",
            onSubmit: () => {}
        }
        ,
        text: {
            active: false,
            left: "Total: 5$",
            center: "WhatEver!"
        }
    }

    showModal('Order', "Are you sure to submit your order?", 'Order_submit', orderData, "Total Price: 20$")
    createGenericTable(
        "user_list",
        ["","Name", "Last Name", "Role", "Address", "Status"],
        userData,
        footerContent,
        'user'
        );

};