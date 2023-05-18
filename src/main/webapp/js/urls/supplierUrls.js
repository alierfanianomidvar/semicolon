import BaseApiUrl from './baseUrl';

export default class supplierUrls extends BaseApiUrl {

    static SUPPLIER_BASE_URL = 'supplier';

    static ADD = {
        url: `${BaseApiUrl.BASE_URL}/${supplierUrls.SUPPLIER_BASE_URL}`,
        method: 'POST'
    };

    static GET_BY_ID = {
        url: `${BaseApiUrl.BASE_URL}/${supplierUrls.SUPPLIER_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'GET'
    };

    static EDIT = {
        url: `${BaseApiUrl.BASE_URL}/${supplierUrls.SUPPLIER_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'PUT'
    };

    static REMOVE = {
        url: `${BaseApiUrl.BASE_URL}/${supplierUrls.SUPPLIER_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'DELETE'
    };

    static GET_ALL = {
        url: `${BaseApiUrl.BASE_URL}/suppliers`,
        method: 'GET',
    };


}