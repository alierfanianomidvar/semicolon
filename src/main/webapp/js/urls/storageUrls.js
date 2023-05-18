import BaseApiUrl from './baseUrl';

export default class storageUrls extends BaseApiUrl {

    static STORAGE_BASE_URL = 'storage';

    static ADD = {
        url: `${BaseApiUrl.BASE_URL}/${storageUrls.STORAGE_BASE_URL}`,
        method: 'POST'
    };

    static GET_BY_ID = {
        url: `${BaseApiUrl.BASE_URL}/${storageUrls.STORAGE_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'GET'
    };

    static EDIT = {
        url: `${BaseApiUrl.BASE_URL}/${storageUrls.STORAGE_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'PUT'
    };

    static DELETE_BY_ID = {
        url: `${BaseApiUrl.BASE_URL}/${storageUrls.STORAGE_BASE_URL}/`,
        method: 'DELETE'
    };

    static GET_ALL = {
        url: `${BaseApiUrl.BASE_URL}/${storageUrls.STORAGE_BASE_URL}`,
        method: 'GET',
    };

    static REPORT = {
        url: `${BaseApiUrl.BASE_URL}/${storageUrls.STORAGE_BASE_URL}/report/`, //NOTE: ADD THIS TO URL /{pharmacyId}
        method: 'GET',
    };


    static STATUS = {
        url: `${BaseApiUrl.BASE_URL}/${storageUrls.STORAGE_BASE_URL}/report`,
        method: 'GET'
    };


}