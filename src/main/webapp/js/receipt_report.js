import {createGenericTable} from "./table/table.js";
export const onInitial = () => {

    const receiptData = [{
        ID : 1,
        Price: 32,
        PaymentMethod: "CASH",
        Date: "10-07-2023"
    },{
        ID : 2,
        Price: 2,
        PaymentMethod: "CREDIT_CARD",
        Date: "23-03-2023"
    },{
        ID : 3,
        Price: 55,
        PaymentMethod: "DEBIT_CARD",
        Date: "23-05-2023"
    },{
        ID : 4,
        Price: 169,
        PaymentMethod: "PAYPAL",
        Date: "05-12-2023"
    }]

    const footerContent = {
        text: {
            active: true,
            left: "Total: 5$",
            center: ""
        }
    }

    createGenericTable(
        "receipt_list",
        ["","ID", "Price", "PaymentMethod", "Date"],
        receiptData,
        footerContent,
        'default'
    );

};