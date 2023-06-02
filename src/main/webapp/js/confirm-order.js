function showPopup() {
    var popup = document.getElementById("popup");
    popup.style.display = "block";
}

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
