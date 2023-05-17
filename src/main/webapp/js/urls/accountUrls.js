import BaseApiUrl from './baseUrl';

export default class accountUrls extends BaseApiUrl {

    static LOGIN = {
        url: `${BaseApiUrl.BASE_URL}/account`,
        method: 'GET'
    };

    static LOGOUT = {
        url: `${ApiUrls.BASE_URL}/account/`,
        method: 'POST'
    };

}