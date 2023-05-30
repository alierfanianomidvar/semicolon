import storageUrls from './urls/storageUrls.js';

$(function () {
    $("#navbar-html").load("sidebar.html");

    // TODO : we must call the api like this example ->
    const router = new Router();
    const data = router.createFetch(storageUrls.GET_ALL);
    console.log(data);


});

//routing
$(window).on('hashchange load', function () {
    // document.getElementById("hidden-content").innerHTML = document.title;
    document.title = window.location.hash;
    const route = window.location.hash.replace('#', '') || 'home';
    $("#main-content").load(`${route}.html`,);
});

// Handle page navigation
$(window).on("popstate", function () {
    // Update document title and load page content
    // document.getElementById("hidden-content").innerHTML = document.title;
    document.title = window.location.hash;
    const route = window.location.hash.replace("#", "") || "home";
    $("#main-content").load(`${route}.html`);
});


$(document).ready(function () {
    $("#sidebar-html").load("sidebar.html");
    // Generate a big table
    for (var n = 0; n < 1000; n++) {
        var row = $("<tr>");
        $("#sampleTableA").find("thead th").each(function () {
            $("<td>", {
                html: rWord(8),
                style: "padding:2px;"
            }).appendTo($(row));
        });
        row.appendTo($("#sampleTableA").find("tbody"));
    }

    // And make them fancy
    var fancyTableA = $("#sampleTableA").fancyTable({
        sortColumn: 0,
        pagination: true,
        perPage: 5,
        globalSearch: true
    });
});

