app.controller('adminResidencesListController', ['$scope', '$state', '$uibModal', 'transactionService', 'residencesService', 'authService', 'residences',
    function ($scope, $state, $uibModal, transactionService, residencesService, authService, residences) {

    $scope.residences = residences;

    $scope.create = function () {
        residencesService.createService()
            .then(function (response) {
                authService.setAuthData(response.data);
                $state.go('main');
            }).catch(function (response) {
                //TODO error handling
                alert("Couldn't log in" + response.data);
            })
    };

    $scope.editResidence = function (ID) {
        $state.go('editResidence', {"ID": ID});
    };

    $scope.deleteResidence = function (ID) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'partials/modals/confirmModal.html',
            controller: 'confirmController',
            size: 'md'
        });

        modalInstance.result.then(function () {
            residencesService.deleteResidence(ID);
            //ToDo: Refresh the list
        });
    };
}]);
