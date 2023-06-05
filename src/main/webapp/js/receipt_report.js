import {createGenericTable} from "./table/table.js";
export const onInitial = () => {

    // const receiptData = [{
    //     ID : 1,
    //     Price: 32,
    //     PaymentMethod: "CASH",
    //     Date: "10-07-2023"
    // },{
    //     ID : 2,
    //     Price: 2,
    //     PaymentMethod: "CREDIT_CARD",
    //     Date: "23-03-2023"
    // },{
    //     ID : 3,
    //     Price: 55,
    //     PaymentMethod: "DEBIT_CARD",
    //     Date: "23-05-2023"
    // },{
    //     ID : 4,
    //     Price: 169,
    //     PaymentMethod: "PAYPAL",
    //     Date: "05-12-2023"
    // }]

    let receiptData = []
    if(localStorage.getItem("getData")) {
        JSON.parse(localStorage.getItem("getData")).map((item, index) => {
            const newObject = {
                id: item.id,
                paymentMethod: item.paymentMethod,
                date: item.date.split("T")[0]
            }
            return receiptData.push(newObject)
        })
    }


    const footerContent = {
        text: {
            active: false,
            left: "Total: 5$",
            center: ""
        },
        button: {
            active: false
        }
    }

    createGenericTable(
        "receipt_list",
        ["id", "PaymentMethod", "date"],
        receiptData,
        footerContent,
        'default'
    );

};