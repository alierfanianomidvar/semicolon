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
                switch (item.id) {
                    case "receipt":
                        await loadPage("storage.html", "", "Storage");
                        break;
                    case "product":
                        await loadPage("storage.html", "", "Storage");
                        break;
                    case "storage":

                        await loadPage("storage.html", "", "Storage");
                        break;
                    case "order":
                        await loadPage("storage.html", "", "Storage");
                        break;
                    case "supplier":
                        await loadPage("storage.html", "", "Storage");
                        break;
                    case "report":
                        await loadPage("storage.html", "", "Storage");
                        break;
                    case "user":
                        await loadPage("storage.html", "", "Storage");
                        break;
                    case "setting":
                        await loadPage("storage.html", "", "Storage");
                        break;
                    default:
                        break;
                }
                // Do something when the <li> element is clicked, such as navigate to a new page or show/hide content
                console.log('You clicked on an <li> element!');
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
async function loadPage(page, url, title, pushState = true) {
    const response = await fetch(page);
    const data = await response.text();

    document.getElementById('main-content').innerHTML = data;
    document.title = title;

    // Call the API to get additional data for the page
    const apiResponse = await fetch(url);
    const apiData = await apiResponse.json();

    // Update the contents of the page with the data returned from the API
    // const element = document.getElementById('api-data');
    // element.innerHTML = apiData;

    if (pushState) {
        history.pushState({ page, title }, title, page);
    }
}