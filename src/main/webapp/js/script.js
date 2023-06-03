import receiptUrls from "./urls/receiptUrls.js";
import drugUrls from "./urls/drugUrls.js";
import materialUrls from "./urls/materialUrls.js";
import storageUrls from "./urls/storageUrls.js";
import orderUrls from "./urls/orderUrls.js";
import supplierUrls from "./urls/supplierUrls.js";
import userUrls from "./urls/userUrls.js";
import {createModal, showModal} from "./modal.js";
// $(function () {
//     $("#navbar-html").load("sidebar.html");
//
//     // TODO : we must call the api like this example ->
//     const router = new Router();
//     const data = router.createFetch(storageUrls.GET_ALL);
//     console.log(data);
//
//
// });



//routing
$(window).on('hashchange load', function () {
    // document.getElementById("hidden-content").innerHTML = document.title;
    document.title = window.location.hash;
    const route = window.location.hash.replace('#', '') || 'home';

    $("#main-content").load(`${route}.html`);

    // if(route=="user"){
    //
    //
    //
    // }

});



$(document).ready(function () {
    $("#sidebar-html").load("sidebar.html");
});

