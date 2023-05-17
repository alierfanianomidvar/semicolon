import BaseApiUrl from './baseUrl';

export default class drugUrls extends BaseApiUrl {

    static DRUG_BASE_URL = '';

    static ADD = {
        url: `${BaseApiUrl.BASE_URL}/${drugUrls.DRUG_BASE_URL}`,
        method: 'POST'
    };

    static GET_BY_ID = {
        url: `${BaseApiUrl.BASE_URL}//${drugUrls.DRUG_BASE_URL}/`,
        method: 'POST'
    };

    static EDIT = {
        url: `${BaseApiUrl.BASE_URL}/${drugUrls.DRUG_BASE_URL}/`,
        method: 'PUT'
    };

    static GET_ALL = {
        url: `${BaseApiUrl.BASE_URL}/${drugUrls.DRUG_BASE_URL}`,
        method: 'GET',
        queryParameters: {
            countryOfProduction: '',
            supplierId: '',
            gender: '',
            isSensitive: '',
            shape: ''
        }
    };
}