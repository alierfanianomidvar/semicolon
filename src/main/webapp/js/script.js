import storageUrls from './urls/storageUrls.js';

$(function () {

    // TODO : we must call the api like this example ->
    const router = new Router();
    const data = router.createFetch(storageUrls.GET_ALL);
    console.log(data);


});

//routing
$(window).on('hashchange load', function () {
    document.getElementById("hidden-content").innerHTML=document.title ;
    document.title=window.location.hash;
    var route = window.location.hash.replace('#', '') || 'home';
    $("#main-content").load(`${route}.html`);
});