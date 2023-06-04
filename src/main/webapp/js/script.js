import storageUrls from './urls/storageUrls.js';
import receiptUrls from "./urls/receiptUrls.js";
import drugUrls from "./urls/drugUrls.js";
import materialUrls from "./urls/materialUrls.js";
import orderUrls from "./urls/orderUrls.js";
import supplierUrls from "./urls/supplierUrls.js";
import userUrls from "./urls/userUrls.js";


//routing
$(window).on('hashchange load', function () {
    // document.getElementById("hidden-content").innerHTML = document.title;

    localStorage.removeItem('getData'); // removing all dats before finding the new Data.

    document.title = window.location.hash;
    const route = window.location.hash.replace('#', '') || 'home';

    let endPoint = null;
    const router = new Router();

    switch (route) {
        //TODO: use the fetched data in each case if you need to change the content
        case "receipt":
            //TODO: url must change
            endPoint = receiptUrls.GET_BY_ID;
            break;
        case "drug":
            endPoint = drugUrls.GET_ALL;
            break;
        case "material":
            endPoint = materialUrls.GET_ALL;
            break;
        case "storage":
            endPoint = storageUrls.GET_ALL;
            break;
        case "order":
            endPoint = orderUrls.GET_ALL;
            break;
        case "supplier":
            endPoint = supplierUrls.GET_ALL;
            break;
        case "report":
            //TODO: url must change ( we dont have urls for report)
            endPoint = receiptUrls.GET_BY_ID;
            break;
        case "user":
            endPoint = userUrls.GET_BY_ID;
            break;
        case "profile":
            //TODO: url must change ( we dont have urls for report)
            endPoint = userUrls.GET_BY_ID;
            break;
        default:
            endPoint = storageUrls.GET_ALL;
            break;
    }

    const fetch = router.createFetch(endPoint);
    fetch.then(data => {
        localStorage.setItem('getData', JSON.stringify(data));
        // Do something with the data here
    }).catch(error => {
        console.log("Error fetching data:", error);
    });

    const url = `${route}.html?`;
    $("#main-content").load(url, function (response, status, xhr) {
        if (status == "success") {
            console.log("Content loaded successfully");
        } else {
            console.log("Error loading content: " + xhr.status + " " + xhr.statusText);
        }
    });


    const userData = {
        name: "John",
        lastName: "Doe",
        birthDate: {
            day: "01",
            month: "01",
            year: "1990"
        },
        phoneNumber: "1234567890",
        email: "john.doe@example.com",
        address: "123 Main Street"
    };

    localStorage.setItem('userData', JSON.stringify(userData));

    // Log the default userData
    console.log("Default userData:", userData);
});


$(document).ready(function () {
    $("#sidebar-html").load("sidebar.html");
});

