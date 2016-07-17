'use strict';

App.factory("CompanyService", ["$resource", function ($resource) {
    return $resource("http://localhost:8080/companies/:companyId", {companyId: '@id'},
        {   'update': {method: 'PUT'},
            'query': {method:'GET', params: {page: '@page', sort: 'id'}},
            'save': {method:'POST', transformResponse: []}
        }
    );
}]);