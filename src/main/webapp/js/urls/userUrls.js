import BaseApiUrl from './baseUrl';

export default class userUrls extends BaseApiUrl {

    static USER_BASE_URL = 'user';

    static ADD = {
        url: `${BaseApiUrl.BASE_URL}/${userUrls.USER_BASE_URL}`,
        method: 'POST'
    };

    static EDIT = {
        url: `${BaseApiUrl.BASE_URL}/${userUrls.USER_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'PUT'
    };

    static GET_ALL = {
        url: `${BaseApiUrl.BASE_URL}/${userUrls.USER_BASE_URL}`,
        method: 'GET',
    };


    static GET_BY_ID = {
        url: `${BaseApiUrl.BASE_URL}/${userUrls.USER_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'GET'
    };


    static DELETE_BY_ID = {
        url: `${BaseApiUrl.BASE_URL}/${userUrls.USER_BASE_URL}/delete/`, //NOTE: ADD THIS TO URL /{id}
        method: 'DELETE'
    };

    static CHANGE_STATUS = {
        url: `${BaseApiUrl.BASE_URL}/${userUrls.USER_BASE_URL}/change-status/`, //NOTE: ADD THIS TO URL /{id}/{newStatus}
        method: 'PATCH'
    };


}