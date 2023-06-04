export function getByteProfilePicture() {
    return new Promise((resolve) => {
        const fileInput = document.querySelector('input[type="file"]');
        const file = fileInput.files[0];

        // Create a new FileReader object
        const reader = new FileReader();

        // Set up a function to be called when the file is read
        reader.onload = function (event) {
            // Get the loaded file data as a byte array
            const byteArray = new Uint8Array(event.target.result);

            // Send the byte array to the backend
            resolve(Array.from(byteArray));

        };

        // Read the file as an ArrayBuffer
        reader.readAsArrayBuffer(file);
    });
}

export function getRouteParam (index = 1) {
    return window.location.hash.split("/")[index]
}
