class Router {
    sendHttpRequest(method, url, data) {
        const requestOptions = {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            }
        };

        if (data) {
            requestOptions.body = JSON.stringify(data);
        }

        return fetch(url, requestOptions)
            .then(res => {
                if (res.ok) {
                    return res.json();
                } else {
                    throw new Error('Request failed');
                }
            }).catch(error => {
                console.error('Error fetching data:', error);
                // throw an error or return a default value
                throw error;
            });
    }

    getData(url, params) {
        if (params) {
            url += '?' + Object.keys(params).map(key => `${encodeURIComponent(key)}=${encodeURIComponent(params[key])}`).join('&');
        }
        this.sendHttpRequest('GET', url)
            .then(responseData => {
                console.log(responseData['data'])
                return responseData['data'];
            });
    }

    postData(url, data) {
        return this.sendHttpRequest('POST', url, data);
    }

    putData(url, data) {
        return this.sendHttpRequest('PUT', url, data);
    }

    deleteData(url) {
        return this.sendHttpRequest('DELETE', url);
    }


}