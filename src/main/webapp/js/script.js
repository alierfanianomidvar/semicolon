fetch('sidebar.html')
    .then(response => response.text())
    .then(data => {
        document.getElementById('navbar-html').innerHTML = data;
        // Get all the <li> elements in the document
        const listItems = document.querySelectorAll('li');

// Loop through each <li> element and add a click event listener to it
        let selectedItem = null;
        listItems.forEach(item => {
            item.addEventListener('click', async () => {
                if(selectedItem) {
                    selectedItem.style.backgroundColor = '#F2F9F9ED';
                }
                item.style.backgroundColor = '#DEF3EE';
                selectedItem = item;
                //TODO: Need to fix the url for each page
                switch (item.id) {
                    case "receipt":
                        await loadPage("receipt.html", "", "Receipt", "receipt_list");
                        break;
                    case "product":
                        await loadPage("product.html", "", "Product", "products");
                        break;
                    case "storage":

                        await loadPage("storage.html", "", "Storage", "storage");
                        break;
                    case "order":
                        await loadPage("order.html", "", "Order", "order");
                        break;
                    case "supplier":
                        await loadPage("supplier.html", "", "Supplier", "supplier");
                        break;
                    case "report":
                        await loadPage("report.html", "", "Report", "report");
                        break;
                    case "user":
                        await loadPage("user.html", "", "UserInfo", "user");
                        break;
                    case "setting":
                        await loadPage("setting.html", "", "Setting", "setting");
                        break;
                    default:
                        break;
                }
                // // Do something when the <li> element is clicked, such as navigate to a new page or show/hide content
                // console.log('You clicked on an <li> element!');
            });
        });
    });

// Add a popstate event listener to handle back and forward navigation
window.addEventListener('popstate', event => {
    if (event.state) {
        loadPage(event.state.page, event.state.title, false);
    }
});

// Function to load the content of a page into the "main-content" div
async function loadPage(page, url, title, route, pushState = true) {
    const response = await fetch(page);
    const data = await response.text();

    document.getElementById('main-content').innerHTML = data;
    document.title = title;

    // Call the API to get additional data for the page
    const apiResponse = await fetch(url);
    const apiData = await apiResponse.json();

    // Update the contents of the page with the data returned from the API
    const element = document.getElementById('user-info');
    const headerElement = element.querySelector('h5')
    const paragraphElement = element.querySelector('p')
    headerElement.textContent = "use apidata to give the right name"
    paragraphElement.textContent = "use apidata to give the right role"

    if (pushState) {
        history.pushState({ page, title }, title, `/${route}`);
    }
}