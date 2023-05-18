import BaseApiUrl from './baseUrl';

class materialUrls extends BaseApiUrl {

    static MATERIAL_BASE_URL = 'material';

    static ADD = {
        url: `${BaseApiUrl.BASE_URL}/${materialUrls.MATERIAL_BASE_URL}`,
        method: 'POST'
    };

    static GET_BY_ID = {
        url: `${BaseApiUrl.BASE_URL}/${materialUrls.MATERIAL_BASE_URL}/`,
        method: 'POST'
    };

    static EDIT = {
        url: `${BaseApiUrl.BASE_URL}/${materialUrls.MATERIAL_BASE_URL}/`,
        method: 'PUT'
    };

    static GET_ALL = {
        url: `${BaseApiUrl.BASE_URL}/${materialUrls.MATERIAL_BASE_URL}`,
        method: 'GET',
        queryParameters: {
            countryOfProduction: '',
            supplierId: '',
            gender: ''
        }
    };
}

export default materialUrls;