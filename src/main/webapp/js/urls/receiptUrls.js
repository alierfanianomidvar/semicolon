import BaseApiUrl from './baseUrl';

export default class receiptUrls extends BaseApiUrl {

    static PHARMACY_BASE_URL = 'pharmacy';

    static ADD = {
        url: `${BaseApiUrl.BASE_URL}/${receiptUrls.PHARMACY_BASE_URL}`,
        method: 'POST'
    };

    static GET_BY_ID = {
        url: `${BaseApiUrl.BASE_URL}/${receiptUrls.PHARMACY_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'GET'
    };


    static EDIT = {
        url: `${BaseApiUrl.BASE_URL}/${receiptUrls.PHARMACY_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'PUT'
    };

}