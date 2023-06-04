import userUrls from "./urls/userUrls.js";
import {
    generateRoleOptions,
    getByteProfilePicture,
    getTokenFromLocalStorage,
    extractRole
} from "./utils.js";

const addUserForm = document.getElementById('add-user-form');

addUserForm.addEventListener('submit', async function (event) {
    event.preventDefault();

    const formData = new FormData(addUserForm);
    const body = Object.fromEntries(formData)
    body.accountStatus = "ACTIVE";

    body.birthDate = new Date(body.birthDate).toISOString();

    body.role = {
        id: body.role
    }

    if (body.profilePicture.size !== 0) {
        body.profilePicture = await getBase64ProfilePicture();
    } else {
        body.profilePicture = null;
    }

    const token = getTokenFromLocalStorage();

    const router = new Router();
    try {
        await router.createFetch(userUrls.ADD,null,null, token, body);
        window.location.hash = "#user";
    } catch (error) {
        console.log("Error: ", error);
    }
});

export const onInitial = async () => {
    const addUserForm = document.getElementById('add-user-form');
    // Extract the role from the JWT token payload
    const token = getTokenFromLocalStorage();
    const role = extractRole(token);

    // Generate the role dropdown options based on the user's role
    generateRoleOptions(role, addUserForm);
}