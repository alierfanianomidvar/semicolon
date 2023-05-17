import BaseApiUrl from './baseUrl';

export default class orderUrls extends BaseApiUrl {

    static ORDER_BASE_URL = 'order';

    static ADD = {
        url: `${BaseApiUrl.BASE_URL}/${orderUrls.ORDER_BASE_URL}`,
        method: 'POST'
    };

    static GET_BY_ID = {
        url: `${BaseApiUrl.BASE_URL}/${orderUrls.ORDER_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}/{token}
        method: 'POST'
    };

    static DELETE_BY_ID = {
        url: `${BaseApiUrl.BASE_URL}/${orderUrls.ORDER_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}/{token}
        method: 'DELETE'
    };

    static GET = {
        url: `${BaseApiUrl.BASE_URL}/${orderUrls.ORDER_BASE_URL}/get-all`,
        method: 'GET',
    };

    static STATUS = {
        url: `${BaseApiUrl.BASE_URL}/${orderUrls.ORDER_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}/{status}/{token}
        method: 'PATCH'
    };

    static REPORT = {
        url: `${BaseApiUrl.BASE_URL}/${orderUrls.ORDER_BASE_URL}/report`,
        method: 'GET',
        queryParameters: {
            token: '',
            orderReport: '',
            num: 0
        }
    };

}