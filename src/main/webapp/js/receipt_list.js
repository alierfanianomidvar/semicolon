import {showModal} from "../js/modal.js";
import {createGenericTable} from "./table/table.js";
export const onInitial =  () => {

    let receiptData = []
    JSON.parse(localStorage.getItem("getData")).map((item, index) => {
        const newObject = {
            id: item.id,
            paymentMethod: item.paymentMethod,
            date: item.date.split("T")[0]
        }
        return receiptData.push(newObject)
    })
    console.log("New Data: ", receiptData)
        // ['id', 'date', 'paymentMethod']
    // const receiptData = [{
    //     Name: "Ali",
    //     Telephone_Number: "1223321314",
    //     Address: "via settembre"
    // },{
    //     Name: "Abi",
    //     Telephone_Number: "1454453532234",
    //     Address: "via romana"
    // },{
    //     Name: "Alex",
    //     Telephone_Number: "1121212234",
    //     Address: "via napoli"
    // }]

    const footerContent = {
        button: {
            active: true,
            cancel: "Cancel",
            onCancel: () => {},
            submit: "Submit",
            onSubmit: () => {}
        },
        text: {
            active: false
        }
    }

    createGenericTable(
        "reciept_list",
        ['id', 'date', 'paymentMethod'],
        receiptData,
        null,
        "default"
    );

    // const addReceiptBtn = document.getElementById("add-receipt-btn");
    // addReceiptBtn.addEventListener("click", () => {
    //     window.location.href = "add-receipt.html";
    // });
};