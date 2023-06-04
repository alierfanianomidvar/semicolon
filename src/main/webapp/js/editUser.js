import userUrls from "./urls/userUrls.js";
import {getByteProfilePicture, getRouteParam} from "./utils.js";

const router = new Router();

export const onInitial =async () => {
    const userId = getRouteParam();
    
    try {
        const fetchedData = await router.createFetch(userUrls.GET_BY_ID(userId));
        fillFormData(fetchedData);
    } catch (error) {
        console.log("Error: ", error);
    }
}

const addUserForm = document.getElementById('edit-user-form');
addUserForm.addEventListener('submit', async function(event) {
    event.preventDefault();
    const userId = getRouteParam();
    const formData = new FormData(addUserForm);
    const body = Object.fromEntries(formData);

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

    const updatedData = await router.createFetch(userUrls.EDIT(userId),null, null, token, body);
    
});

const statusBtn = document.getElementById('status-btn');
statusBtn.addEventListener('click', async function(event) {
    event.preventDefault();
    const userId = getRouteParam();
    const token = localStorage.getItem("token");
    const accountStatus = document.querySelector("[name='accountStatus']");
    const newStatus = accountStatus.value == "ACTIVE" ? "DEACTIVE" : "ACTIVE";

    const updatedData = await router.createFetch(userUrls.CHANGE_STATUS(userId, newStatus),null, null, token, null);
    accountStatus.value = newStatus;
    event.target.textContent = newStatus === "ACTIVE" ? "Disable" : "Enable";
});

function fillFormData (data) {
    const addUserForm = document.getElementById('edit-user-form');
    const statusBtn = document.getElementById('status-btn');

    addUserForm.querySelector('input[name="name"]').value = data.name;
    addUserForm.querySelector('input[name="lastName"]').value = data.lastName;
    addUserForm.querySelector('input[name="birthDate"]').value = data.birthDate.split("T")[0];
    addUserForm.querySelector('select[name="gender"]').value = data.gender;
    addUserForm.querySelector('select[name="role"]').value = data.role.id;
    addUserForm.querySelector('input[name="phoneNumber"]').value = data.phoneNumber;
    addUserForm.querySelector('input[name="email"]').value = data.email;
    addUserForm.querySelector('input[name="address"]').value = data.address;
    addUserForm.querySelector('input[name="accountStatus"]').value = data.accountStatus;

    statusBtn.textContent = data.accountStatus === "ACTIVE" ? "Disable" : "Enable";
}
