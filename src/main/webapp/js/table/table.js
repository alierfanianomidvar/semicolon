// Define a function for creating the table
export const createTable = (tableId, columnNames, numRows, cellContentGenerator) => {
    // Generate the table headers
    const tableHeaders = $(`<thead>`).appendTo($(`#${tableId}`));
    const headerRow = $(`<tr>`).appendTo(tableHeaders);
    columnNames.forEach((columnName) => {
        $(`<th>`, { text: columnName }).appendTo(headerRow);
    });

    // Generate the table rows
    const tableBody = $(`<tbody>`).appendTo($(`#${tableId}`));
    for (let i = 0; i < numRows; i++) {
        const row = $(`<tr>`).appendTo(tableBody);
        columnNames.forEach(() => {
            const cellContent = cellContentGenerator();
            $(`<td>`, { html: cellContent, style: "padding:2px;" }).appendTo(row);
        });
    }
};
