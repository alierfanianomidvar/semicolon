// Define a function for creating the table

export const createTable = (tableId, columnNames, numRows, data, type = "default") => {
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
            generateTableRows(numRows, tableBody, columnNames, "user", data);
            break;
        default:
            generateTableRows(numRows, tableBody, columnNames, "default", data);
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

    const cell = $("<td>", {
        style: " padding: 0.1rem; padding-left: 0.5rem",
    });
    cell.append(image);
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


function generateTableRows(numRows, tableBody, columnNames, type, data) {
    for (let n = 0; n < numRows; n++) {
        const row = $("<tr>").appendTo(tableBody);

        columnNames.forEach((columnName, index) => {
            let cellContent = "";
            if (type === "user" && index === 0) {
                cellContent = generateUserCellContent(data[n]["image"]);
            } else {
                cellContent = data[n][`${columnName}`];
            }
            $("<td>", { html: cellContent, style:" padding: 0.1rem; padding-left: 0.75rem; vertical-align: middle;" }).appendTo(row);
        });

        if (type === "user") {
            $("<td>", { html: "<a href='#'>Edit</a>", style: " padding: 0.1rem; vertical-align: middle;" }).appendTo(row);
        }
    }
}

// Function to create buttons and/or text elements
export const createButtonsAndText = (createButtons = false,
                                     createText = false,
                                     firstTxt = "",
                                     secondTxt = "",
                                     cancelBtn = "",
                                     acceptBtn = "") => {
    // Create a div to contain the buttons and/or text elements
    const container = $("<div>", {
        class: "bottom-container",
        css: {
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            marginTop: "0.75rem", // Adjust the top margin as needed
        },
    });

    if (createText) {
        // Create the text elements container
        const textContainer = $("<div>", {
            class: "text-container",
            css: {
                display: "flex",
                justifyContent: "space-between",
                width: "40%",
            },
        });

        // Create the text elements
        const text1 = $("<span>", {
            text: `${firstTxt}`,
            css: {
                fontWeight: "bold",
                margin: "0.5rem", // Random margin between 1 and 10 pixels
            },
        });

        const text2 = $("<span>", {
            text: `${secondTxt}`,
            css: {
                fontWeight: "bold",
                margin: "0.5rem", // Random margin between 1 and 10 pixels
            },
        });

        // Append the text elements to the text container
        textContainer.append(text1, text2);

        // Append the text container to the main container
        container.append(textContainer);
    }

    if (createButtons) {
        // Create the buttons container
        const buttonsContainer = $("<div>", {
            class: "buttons-container",
            css: {
                display: "flex",
                justifyContent: "flex-end",
                width: createText ? "60%" : "100%",
            },
        });

        // Create the buttons
        const button1 = $("<button>", {
            text: `${cancelBtn}`,
            class: "btn btn-custom-secondary",
            css: {
                margin: "0.5rem", // Random margin between 1 and 10 pixels
            },
        });

        const button2 = $("<button>", {
            text: `${acceptBtn}`,
            class: "btn btn-custom",
            css: {
                margin: "0.5rem", // Random margin between 1 and 10 pixels
            },
        });

        // Append the buttons to the buttons container
        buttonsContainer.append(button1, button2);

        // Append the buttons container to the main container
        container.append(buttonsContainer);
    }

    // Return the main container
    return container;
}


function createGenericTable(generictableId,genericcolumnNames){
    ///showModal()
    // Example usage: Create a table with dynamic column names and content
    let tableId = generictableId; // Dynamic table ID
    let columnNames =genericcolumnNames;
    //TODO: number of rows must be edited and we need to put the correct number of rows based on our user list fetch api
    let numRows = 1000; // Total number of rows

    // Generate the table ID dynamically
    let table = $(`<table>`, {
        id: tableId,
        class: "table table-striped sampleTable",
    });

    // Append the table to the container
    $(".border").append(table);

    // for passing the cellContentGenerator we need to define a proper function that returns the user actual information
    createTable(tableId, columnNames, numRows, () => {
        return rWord(8); // Generate random cell content
    }, "user");

    // And make the table fancy
    // const fancyTableA = $(`#${tableId}`).fancyTable({
    //     sortColumn: 0,
    //     pagination: true,
    //     perPage: 5,
    //     globalSearch: true,
    // });
}


