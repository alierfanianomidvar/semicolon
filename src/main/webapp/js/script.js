// Word genarator
function rWord(r) {
    var t, n = "bcdfghjklmnpqrstvwxyz", a = "aeiou", e = function (r) {
        return Math.floor(Math.random() * r)
    }, o = "";
    r = parseInt(r, 10), n = n.split(""), a = a.split("");
    for (t = 0; t < r / 2; t++) {
        var l = n[e(n.length)], p = a[e(a.length)];
        o += 0 === t ? l.toUpperCase() : l, o += 2 * t < r - 1 ? p : ""
    }
    return o
}

//routing
$(window).on('hashchange load', function () {
    document.getElementById("hidden-content").innerHTML = document.title;
    document.title = window.location.hash;
    const route = window.location.hash.replace('#', '') || 'home';
    $("#main-content").load(`${route}.html`,);
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
