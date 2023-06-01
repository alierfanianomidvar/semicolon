function showPopup() {
    var popup = document.getElementById("popup");
    popup.style.display = "block";
}

function hidePopup() {
    var popup = document.getElementById("popup");
    popup.style.display = "none";
}

function calculateTotal() {
    var price = parseFloat(document.getElementById("price").value);
    var tax = parseFloat(document.getElementById("tax").value);
    var discount = parseFloat(document.getElementById("discount").value);

    var total = price + (price * tax / 100) - (price * discount / 100);

    document.getElementById("total").innerHTML = "Total Price: $" + total.toFixed(2);
}


