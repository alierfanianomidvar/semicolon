$(document).ready(function() {

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
});

function showPopup() {
    var popup = window.open("confirm-order.html", "_blank", "width=400,height=400");
    popup.addEventListener("DOMContentLoaded", function () {
        popup.document.getElementById("submit-order-btn").addEventListener("click", function () {
            alert("Order submitted");
        });
    });
}

