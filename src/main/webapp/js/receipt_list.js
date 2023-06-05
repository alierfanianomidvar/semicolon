import {showModal} from "../js/modal.js";
import {createGenericTable} from "./table/table.js";
export const onInitial = () => {


    const receiptData = [{
        Name: "Ali",
        Telephone_Number: "1223321314",
        Address: "via settembre"
    },{
        Name: "Abi",
        Telephone_Number: "1454453532234",
        Address: "via romana"
    },{
        Name: "Alex",
        Telephone_Number: "1121212234",
        Address: "via napoli"
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

    createGenericTable(
        "reciept_list",
        ["","Name", "Telephone_Number",  "Address"],
        receiptData,
        footerContent
    );

};