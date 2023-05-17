class Router {
    sendHttpRequest(method, url, data) {
        return fetch(url)
            .then(res => {
                if (res["status"] == 200) {
                    console.log(res);
                    return res.json();
                } else {
                    console.log(res["status"])
                }

            });
    }

}