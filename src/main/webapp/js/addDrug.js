import orderUrls from "./urls/orderUrls";
import {showModal} from "../js/modal.js";
import {createGenericTable} from "./table/table.js";
export const onInitial = () => {

    const orderData = {
        price: "$13000",
        tax: "10%",
        discount: "12%"
    };
    showModal('Order', "Are you sure to submit your order?", 'Order_submit', orderData, "Total Price: 20$")
    localStorage.getItem("getData")
    createGenericTable("storage_list", ["","Name", "Price","IsActive","Quantity"]);
};

export const showPopup = () => {
        var popup = window.open("confirm-order.html", "_blank", "width=400,height=400");
        popup.addEventListener("DOMContentLoaded", function () {
            popup.document.getElementById("submit-order-btn").addEventListener("click", function () {
                alert("Order submitted");
            });
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

export const resetTotal = () =>{
    popup.addEventListener("DOMContentLoaded", function () {
        popup.document.getElementById("cancel-btn").addEventListener("click", function () {
            document.getElementById("total-price").textContent = "$0";
        });
    });
}

