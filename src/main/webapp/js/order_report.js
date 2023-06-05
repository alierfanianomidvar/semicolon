import {createGenericTable} from "./table/table.js";
export const onInitial = () => {

    // const orderData = [{
    //     ID:1 , Price:12 , Status:"DELIVERED" , Date:"12-01-2023"
    // },{
    //     ID:2 , Price:32 , Status:"PENDING" , Date:"05-04-2023"
    // },{
    //     ID:3 , Price:6 , Status:"DELIVERED" , Date:"30-11-2023"
    // },{
    //     ID:4 , Price:89 , Status:"CANCELLED" , Date:"11-09-2023"
    // }]

    let orderData = []
    JSON.parse(localStorage.getItem("getData")).map((item, index) => {
        const newObject = {
            id: item.id,
            price: item.price,
            status:item.status,
            date: item.date.split("T")[0]
        }
        return orderData.push(newObject)
    })
    console.log("Hamide analyzer",orderData);
    const footerContent = {
        text: {
            active: true,
            left: "Total: 5$",
            center: ""
        }
    }

    createGenericTable(
        "order_list",
        ["","ID", "Price", "Status", "Date"],
        orderData,
        footerContent,
        'default'
    );

};