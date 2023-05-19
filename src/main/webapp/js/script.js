import storageUrls from './urls/storageUrls.js';

$(function () {
    $("#navbar-html").load("sidebar.html");

    // TODO : we must call the api like this example ->
    const router = new Router();
    const data = router.createFetch(storageUrls.GET_ALL);
    console.log(data);


});
