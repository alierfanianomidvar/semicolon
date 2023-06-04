import userUrls from "./urls/userUrls.js";
import {getByteProfilePicture} from "./utils.js";

const addUserForm = document.getElementById('add-user-form');

addUserForm.addEventListener('submit', async function(event) {
    event.preventDefault();

    const formData = new FormData(addUserForm);
    const body = Object.fromEntries(formData)
    body.accountStatus = "ACTIVE";

    body.birthDate = new Date(body.birthDate).toISOString();

    body.role = {
        id: body.role
    }

    if (body.profilePicture.size !== 0) {
        body.profilePicture = await getByteProfilePicture();
    }
    else {
        body.profilePicture = null;
    }

    const token = localStorage.getItem("token");

    const router = new Router();
    try {
        await router.createFetch(userUrls.ADD,null,null, token, body);
        window.location.hash = "#user";
    } catch (error) {
        console.log("Error: ", error);
    }
});