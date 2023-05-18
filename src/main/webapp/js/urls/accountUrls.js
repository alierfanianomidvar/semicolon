import BaseApiUrl from './baseUrl';

class accountUrls extends BaseApiUrl {

    static LOGIN = {
        url: `${BaseApiUrl.BASE_URL}/account`,
        method: 'GET'
    };

    static LOGOUT = {
        url: `${BaseApiUrl.BASE_URL}/account/`,
        method: 'POST'
    };

}
export default accountUrls;