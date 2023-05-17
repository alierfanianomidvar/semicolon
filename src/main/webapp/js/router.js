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


    getData(url, params) {
        if (params) {
            url += '?' + Object.keys(params).map(key => `${encodeURIComponent(key)}=${encodeURIComponent(params[key])}`).join('&');
        }
        this.sendHttpRequest('GET', url)
            .then(responseData => {
                console.log("ali")
                console.log(responseData['data']);
                return responseData['data'];
            });
    }
}