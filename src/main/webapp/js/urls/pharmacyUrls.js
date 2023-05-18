import BaseApiUrl from './baseUrl';
import orderUrls from "./orderUrls";

class pharmacyUrls extends BaseApiUrl {

    static PHARMACY_BASE_URL = 'pharmacy';

    static ADD = {
        url: `${BaseApiUrl.BASE_URL}/${pharmacyUrls.PHARMACY_BASE_URL}`,
        method: 'POST'
    };

    static EDIT = {
        url: `${BaseApiUrl.BASE_URL}/${pharmacyUrls.PHARMACY_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'PUT'
    };

    static ADD_STAFF = {
        url: `${BaseApiUrl.BASE_URL}/${pharmacyUrls.PHARMACY_BASE_URL}/add-staff/`, //NOTE: ADD THIS TO URL /{id}
        method: 'PUT'
    };

    static DELETE_STAFF = {
        url: `${BaseApiUrl.BASE_URL}/${pharmacyUrls.PHARMACY_BASE_URL}/delete-staff/`, //NOTE: ADD THIS TO URL /{id}
        method: 'DELETE',
    };

    static GET = {
        url: `${BaseApiUrl.BASE_URL}/${pharmacyUrls.PHARMACY_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'GET'
    };

    static DELETE = {
        url: `${BaseApiUrl.BASE_URL}/${pharmacyUrls.PHARMACY_BASE_URL}/`, //NOTE: ADD THIS TO URL /{id}
        method: 'DELETE',
    };

    static GET_ALL = {
        url: `${BaseApiUrl.BASE_URL}/${pharmacyUrls.PHARMACY_BASE_URL}/get-all`,
        method: 'GET'
    };

    static PHARMACY_ACTIVATION = {
        url: `${BaseApiUrl.BASE_URL}/${pharmacyUrls.PHARMACY_BASE_URL}/get-all`, //NOTE: ADD THIS TO URL /{pharmacyId}
        method: 'PATCH'
    };

}

export default pharmacyUrls;