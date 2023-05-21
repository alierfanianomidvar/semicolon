import receiptUrls from "./urls/receiptUrls.js";
import drugUrls from "./urls/drugUrls.js";
import materialUrls from "./urls/materialUrls.js";
import storageUrls from "./urls/storageUrls.js";
import orderUrls from "./urls/orderUrls.js";
import supplierUrls from "./urls/supplierUrls.js";
import userUrls from "./urls/userUrls.js";
$(function () {

    // TODO : we must call the api like this example ->

    // const data = router.createFetch(storageUrls.GET_ALL);
    // console.log(data);
    // Loop through each <li> element and add a click event listener to it
    const listItems = document.querySelectorAll('li');
    let selectedItem = null;
    let fetchedData = null;
    listItems.forEach(item => {
        item.addEventListener('click', async () => {
            if(selectedItem) {
                selectedItem.style.backgroundColor = '#F2F9F9ED';
            }
            item.style.backgroundColor = '#DEF3EE';
            selectedItem = item;
            const router = new Router();
            //TODO: Need to fix the url for each page
            switch (item.id) {
                //TODO: use the fetched data in each case if you need to change the content
                case "receipt":
                    //TODO: url must change
                    fetchedData = router.createFetch(receiptUrls.GET_BY_ID)
                    break;
                case "drug":
                    fetchedData = router.createFetch(drugUrls.GET_ALL)
                    break;
                case "material":
                    fetchedData = router.createFetch(materialUrls.GET_ALL)
                    break;
                case "storage":
                    fetchedData = router.createFetch(storageUrls.GET_ALL)
                    break;
                case "order":
                    fetchedData = router.createFetch(orderUrls.GET)
                    break;
                case "supplier":
                    fetchedData = router.createFetch(supplierUrls.GET_ALL)
                    break;
                case "report":
                    //TODO: url must change ( we dont have urls for report)
                    fetchedData = router.createFetch(receiptUrls.GET_BY_ID)
                    break;
                case "user":
                    fetchedData = router.createFetch(userUrls.GET_BY_ID)
                    break;
                case "setting":
                    //TODO: url must change
                    // the data for setting page is static?!
                    break;
                default:
                    break;
            }
            // TODO: Do something when the <li> element is clicked, such as navigate to a new page or show/hide content
            // console.log('You clicked on an <li> element!');
        });
    });
});

//routing
$(window).on('hashchange load', function () {
    document.getElementById("hidden-content").innerHTML=document.title ;
    document.title=window.location.hash;
    const route = window.location.hash.replace('#', '') || 'home';
    $("#main-content").load(`${route}.html`);
});

// Word genarator
function rWord(r){var t,n="bcdfghjklmnpqrstvwxyz",a="aeiou",e=function(r){return Math.floor(Math.random()*r)},o="";r=parseInt(r,10),n=n.split(""),a=a.split("");for(t=0;t<r/2;t++){var l=n[e(n.length)],p=a[e(a.length)];o+=0===t?l.toUpperCase():l,o+=2*t<r-1?p:""}return o}

$(document).ready(function() {
    // Generate a big table
    for(var n=0;n<1000;n++){
        var row = $("<tr>");
        $("#sampleTableA").find("thead th").each(function() {
            $("<td>",{
                html: rWord(8),
                style:"padding:2px;"
            }).appendTo($(row));
        });
        row.appendTo($("#sampleTableA").find("tbody"));
    }


    // And make them fancy
    var fancyTableA = $("#sampleTableA").fancyTable({
        sortColumn:0,
        pagination: true,
        perPage:5,
        globalSearch:true
    });
});
