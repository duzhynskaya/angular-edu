'use strict';

App.controller('CompanyController', ['CompanyService', function(CompanyService){
    var catalog = this;

    function getItems() {
        CompanyService.query().$promise
            .then(function (result) {
                catalog.items = result;
            });
    }

    function createItem(item) {
        CompanyService.save(item).$promise
            .then(function () {
                initCreateForm();
                getItems();
            });
    }

    function updateItem(item) {
        CompanyService.update({companyId: item.id}, item).$promise
            .then(function () {
                cancelEditing();
                getItems();
            });
    }

    function deleteItem(itemId) {
        CompanyService.delete({companyId: itemId}).$promise
            .then(function () {
                cancelEditing();
                getItems();
            });
    }

    function initCreateForm() {
        catalog.newItem = { name: '', description: '' };
    }

    function setEditedItem(item) {
        catalog.editedItem = angular.copy(item);
        catalog.isEditing = true;
    }

    function isCurrentItem(itemId) {
        return catalog.editedItem !== null && catalog.editedItem.id === itemId;
    }

    function cancelEditing() {
        catalog.editedItem = null;
        catalog.isEditing = false;
    }

    catalog.items = [];

    catalog.getItems = getItems;
    catalog.createItem = createItem;
    catalog.updateItem = updateItem;
    catalog.deleteItem = deleteItem;

    catalog.editedItem = null;
    catalog.isEditing = false;

    catalog.setEditedItem = setEditedItem;
    catalog.isCurrentItem = isCurrentItem;
    catalog.cancelEditing = cancelEditing;

    initCreateForm();
    getItems();
}]);
