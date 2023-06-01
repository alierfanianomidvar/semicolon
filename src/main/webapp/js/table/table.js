// Define a function for creating the table

export const createTable = (tableId, columnNames, numRows, cellContentGenerator, type = "default") => {
    // Generate the table headers
    const tableHeaders = $(`<thead>`).appendTo($(`#${tableId}`));
    const headerRow = $(`<tr>`).appendTo(tableHeaders);
    columnNames.forEach((columnName) => {
        $(`<th>`, { text: columnName }).appendTo(headerRow);
    });

    // Generate the table rows
    const tableBody = $(`<tbody>`).appendTo($(`#${tableId}`));
    switch (type) {
        case "user":
            generateTableRows(numRows, tableBody, columnNames, "user", cellContentGenerator);
            break;
        default:
            generateTableRows(numRows, tableBody, columnNames, "default", cellContentGenerator);
            break;
    }



    // And make them fancy
    let fancyTableA = $(`#${tableId}`).fancyTable({
        sortColumn: 1,
        pagination: true,
        perPage: 5,
        globalSearch: true,
    });
};

// Generate cell content for the "User" type table
const generateUserCellContent = (src) => {
    const image = $("<img>", {
        src: src ? src : "../images/users.svg", // Replace "path_to_round_image" with the actual path to the round image
        class: "rounded-image", // Add a CSS class for styling if needed
        style: "width: 40px; height: 40px; margin-right: 20px; border: 1px solid lightgreen; border-radius: 50%;", // Adjust the width, height, and margin as desired
    });

    // You can modify this code to generate the desired cell content for the "User" type table
    const name = rWord(8);

    const cell = $("<td>", {
        style: "padding: 2px;",
    });
    cell.append(image);
    // cell.append(name);
    return cell;
};


export const rWord = (r) => {
    let t, n = "bcdfghjklmnpqrstvwxyz", a = "aeiou", e = function (r) {
        return Math.floor(Math.random() * r)
    }, o = "";
    r = parseInt(r, 10), n = n.split(""), a = a.split("");
    for (t = 0; t < r / 2; t++) {
        const l = n[e(n.length)], p = a[e(a.length)];
        o += 0 === t ? l.toUpperCase() : l, o += 2 * t < r - 1 ? p : ""
    }
    return o
};


function generateTableRows(numRows, tableBody, columnNames, type, cellContentGenerator) {
    for (let n = 0; n < numRows; n++) {
        const row = $("<tr>").appendTo(tableBody);

        columnNames.forEach((columnName, index) => {
            let cellContent = "";
            if (type === "user" && index === 0) {
                cellContent = generateUserCellContent();
            } else {
                cellContent = cellContentGenerator();
            }
            $("<td>", { html: cellContent, style: "padding:2px;" }).appendTo(row);
        });

        if (type === "user") {
            $("<td>", { html: "<a href='#'>Edit</a>", style: "padding:2px;" }).appendTo(row);
        }
    }
}