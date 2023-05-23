import {createTable} from "./table/table.js";

$(function () {
    // $("#navbar-html").load("sidebar.html");
    //
    // // TODO : we must call the api like this example ->
    // const router = new Router();
    // const data = router.createFetch(storageUrls.GET_ALL);
    // console.log(data);


});

//routing
$(window).on('hashchange load', function () {
    document.getElementById("hidden-content").innerHTML=document.title ;
    document.title=window.location.hash;
    const route = window.location.hash.replace('#', '') || 'home';
    $("#main-content").load(`${route}.html`, function () {
        if (route === "user") {
            // Generate a big table
            createTable("user");
        }
    });
});

