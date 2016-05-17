app.controller('adminResidencesListController', ['$scope', '$state', '$uibModal', 'transactionService', 'residencesService', 'authService', 'residences', 'growl',
    function ($scope, $state, $uibModal, transactionService, residencesService, authService, residences, growl) {

        $scope.residences = residences;

        $scope.deleteResidence = function (id) {
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'partials/modals/confirmModal.html',
                controller: 'confirmController',
                size: 'md',
                resolve: {
                    message: function () {
                        return 'Ar tikrai norite ištrinti vasarnamį?';
                    }
                }
            });

            modalInstance.result.then(function () {
                residencesService.deleteResidence(id)
                    .then(function (response) {
                        growl.success('Vasarnamis ištrintas sėkmingai!');
                        $state.go('adminResidencesList', {}, { reload: 'adminResidencesList' });
                    }).catch(function (response) {
                        growl.error('Nepavyko ištrinti vasarnamio!');
                    })
            });
        };

    }]);
