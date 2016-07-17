'use strict';

App.controller('CompanyController', ['$scope', '$sce', 'CompanyService', function($scope, $sce, CompanyService){
    var self = this;

    self.items = [];
    self.activeItem = null;

    self.pager = {
        totalItems: 0,
        currentPage : 1,
        onPageChanged: refreshList
    };

    self.alert = {};

    // public methods

    self.submit = function () {
        if (self.activeItem.id == null) {
            createItem(self.activeItem);
        } else {
            updateItem(self.activeItem);
        }
    };

    self.edit = function (item) {
        self.activeItem = angular.copy(item);
    };

    self.remove = function (id) {
        if (id === undefined)
            id = self.activeItem.id;

        self.reset();
        deleteItem(id);
    };

    self.reset = function () {
        resetActiveItem();
        $scope.detailsForm.$setPristine();
    };

    // initialization

    resetActiveItem();
    refreshList();

    // private methods

    function resetActiveItem() {
        self.activeItem = {id: null, name: '', description: '', address: '', phone: '', size: null};
    }

    function refreshList() {
        showItems(self.pager.currentPage);
    }

    function showItems(page) {
        CompanyService.query({page: page}).$promise
            .then(function (result) {
                self.items = result.content;
                self.pager.totalItems = result.totalElements;
            });
    }

    function createItem(item) {
        CompanyService.save(item).$promise
            .then(
                function(result) {
                    // mark the recently added item as an active in the master list
                    self.activeItem = item;
                    self.activeItem.id = result[0];

                    showInfoMsg('Company <strong>'+ item.name +'</strong> was successfully added to the catalog.');

                    refreshList();
                }
            )
            .catch(showErrorMsg);
    }

    function updateItem(item) {
        CompanyService.update({companyId: item.id}, item).$promise
            .then(refreshList)
            .catch(showErrorMsg);
    }

    function deleteItem(itemId) {
        CompanyService.delete({companyId: itemId}).$promise
            .then(refreshList)
            .catch(showErrorMsg);
    }


    function showInfoMsg(msgHtml) {
        self.alert.show = true;
        self.alert.class = 'alert-success';
        self.alert.msg = $sce.trustAsHtml(msgHtml);
    }

    function showErrorMsg(reason) {
        self.alert.show = true;
        self.alert.class = 'alert-danger';
        self.alert.msg = $sce.trustAsHtml('Oops. Something went wrong. Please try again later.');
    }
}]);
