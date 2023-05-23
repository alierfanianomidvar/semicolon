$(document).ready(function() {
    $('.search-box input[type="text"]').on('keyup', function() {
        const searchText = $(this).val().toLowerCase();
        $('table tbody tr').each(function() {
            const name = $(this).find('.name').text().toLowerCase();
            const type = $(this).find('.type').text().toLowerCase();
            const price = $(this).find('.price').text().toLowerCase();
            const quantity = $(this).find('.quantity').text().toLowerCase();
            const threshold = $(this).find('.threshold').text().toLowerCase();

            const match = name.includes(searchText) || type.includes(searchText) || price.includes(searchText) || quantity.includes(searchText) || threshold.includes(searchText);

            $(this).toggle(match);
        });
    });
});
