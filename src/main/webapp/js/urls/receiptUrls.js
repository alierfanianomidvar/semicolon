import BaseApiUrl from './baseUrl';

export default class receiptUrls extends BaseApiUrl {

    static RECEIPT_BASE_URL = 'receipt';

    static ADD = {
        url: `${BaseApiUrl.BASE_URL}/${receiptUrls.RECEIPT_BASE_URL}`,
        method: 'POST'
    };

    static GET_BY_ID = {
        url: `${BaseApiUrl.BASE_URL}/${receiptUrls.RECEIPT_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'GET'
    };

    static EDIT = {
        url: `${BaseApiUrl.BASE_URL}/${receiptUrls.RECEIPT_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'PUT'
    };

}