/* for increasing and decreasing quantity

$(document).ready(function() {
    $('#decrease-quantity').click(function() {
        var quantity = parseInt($('#quantity').val());
        if (quantity > 1) {
            quantity--;
            $('#quantity').val(quantity);
        }
    });

    $('#increase-quantity').click(function() {
        var quantity = parseInt($('#quantity').val());
        quantity++;
        $('#quantity').val(quantity);
    });
});
 */

