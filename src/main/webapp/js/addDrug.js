import orderUrls from "./urls/orderUrls";

$(document).ready(function() {
    const ar = new Router();
    const data = ar.createFetch(orderUrls.GET_ALL);
    console.log(data);

    $('#decrease-quantity').click(function() {
        let quantity = parseInt($('#quantity').val());
        if (quantity > 1) {
            quantity--;
            $('#quantity').val(quantity);
        }
    });

    $('#increase-quantity').click(function() {
        let quantity = parseInt($('#quantity').val());
        quantity++;
        $('#quantity').val(quantity);
    });

    function showPopup() {
        var popup = window.open("confirm-order.html", "_blank", "width=400,height=400");
        popup.addEventListener("DOMContentLoaded", function () {
            popup.document.getElementById("submit-order-btn").addEventListener("click", function () {
                alert("Order submitted");
            });
        });
    }

    function calculateTotal() {
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

    function resetTotal() {
        document.getElementById("total-price").textContent = "$0";
    }
});

