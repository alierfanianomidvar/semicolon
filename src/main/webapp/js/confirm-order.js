import orderUrls from "./urls/orderUrls.js";

$(document).ready(function() {
    const fetchData = async () => {
        try {
            const response = await fetch(orderUrls.GET_ALL);
            const data = await response.json();

            // Update the price, tax, and discount values in the HTML
            document.getElementById("price").innerText = data.price;
            document.getElementById("tax").innerText = data.tax;
            document.getElementById("discount").innerText = data.discount;

            calculateTotal(); // Calculate and update the total price
        } catch (error) {
            console.log(error);
        }
    };

    /*
    function showPopup() {
        var popup = document.getElementById("popup");
        popup.style.display = "block";
    }
    */

    function hidePopup() {
        var popup = document.getElementById("popup");
        popup.style.display = "none";
        window.location.href = "add-drug.html";
    }

    function calculateTotal() {
        var price = parseFloat(document.getElementById("price").innerText);
        var tax = parseFloat(document.getElementById("tax").innerText);
        var discount = parseFloat(document.getElementById("discount").innerText);

        var total = price + (price * tax / 100) - (price * discount / 100);

        document.getElementById("total-price").innerText = total.toFixed(2);
    }

    fetchData();
});
