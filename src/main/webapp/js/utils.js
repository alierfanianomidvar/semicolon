export function showErrorMessage(message) {
    const error = document.getElementById("error-message");
    const alert = document.createElement("div");
    alert.className = "alert alert-danger";
    alert.textContent = message;
    error.appendChild(alert);
}

export function getRouteParam (index = 1) {
    return window.location.hash.split("/")[index]
}
