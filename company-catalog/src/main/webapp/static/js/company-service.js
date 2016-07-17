'use strict';

App.factory("CompanyService", ["$resource", function ($resource) {
    return $resource("http://localhost:8080/companies/:companyId", {companyId: '@id'},
        {'update': {method: 'PUT'}}
    );
}]);