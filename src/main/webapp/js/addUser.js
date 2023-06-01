import userUrls from "./urls/userUrls.js";

const addUserForm = document.getElementById('add-user-form');

addUserForm.addEventListener('submit', function(event) {
    event.preventDefault();

    const formData = new FormData(addUserForm);
    const body = Object.fromEntries(formData)
    body.accountStatus = "ACTIVE";

    body.birthDate = new Date(body.birthDate).toISOString();

    body.role = {
        id: body.role
    }


    console.log(body)
    fetch(userUrls.ADD.url, {
        method: userUrls.ADD.method,
        headers: {
            'Content-Type': 'application/json',
            //TODO: read it from local storage when we merge with login branch!
            'Authorization': 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiUm9sZSI6InN1cGVyQWRtaW4iLCJpYXQiOjE2ODI2MTQ4MzcsImV4cCI6MTcxODYxNDgzN30.Jm_t27ViO5F_4ENkPP89dZummxgut4l_97e-fST5Qh4'
        },
        body: JSON.stringify(body)
    })
        .then(function(response) {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Network response was not ok.');
            }
        })
        .then(function(data) {
            console.log('User added:', data);
            // Do something with the response data after the user has been added
        })
        .catch(function(error) {
            console.log('There was a problem with the fetch operation:', error.message);
        });
});
