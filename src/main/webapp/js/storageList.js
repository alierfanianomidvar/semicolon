$(document).ready(function() {
    // Add Product button
    $('.add-btn button').click(function() {
        // Get the values from the input fields
        var name = $('#Name').val();
        var type = $('#Type').val();
        var price = $('#Price').val();
        var quantity = $('#Quantity').val();
        var threshold = $('#Threshold').val();

        // Create a new row with the entered data
        var newRow = '<tr>' +
            '<td>' + name + '</td>' +
            '<td>' + type + '</td>' +
            '<td>' + price + '</td>' +
            '<td>' + quantity + '</td>' +
            '<td>' + threshold + '</td>' +
            '</tr>';

        // Append the new row to the table
        $('#storage-data').append(newRow);

        // Clear the input fields
        $('#Name').val('');
        $('#Type').val('');
        $('#Price').val('');
        $('#Quantity').val('');
        $('#Threshold').val('');
    });

    // Filter button
    $('.filter-btn button').click(function() {
        // Get the selected filter values
        var selectedThreshold = $('#FilterThreshold').val();
        var selectedType = $('#FilterType').val();

        // Show/hide rows based on the filter values
        $('#storage-data tr').each(function() {
            var rowThreshold = $(this).find('td:nth-child(5)').text();
            var rowType = $(this).find('td:nth-child(2)').text();

            if ((selectedThreshold === '' || rowThreshold === selectedThreshold) &&
                (selectedType === '' || rowType === selectedType)) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    });


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