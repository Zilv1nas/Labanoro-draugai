app.controller('viewResidenceController', ['$scope', '$state', '$uibModal', 'residencesService', 'residence', 'residenceHistory', 'growl', function ($scope, $state, $uibModal, residencesService, residence, residenceHistory, growl) {
    $scope.residence = residence;
    $scope.residenceHistory = residenceHistory;

    $scope.reserveResidence = function (id) {
        var modalInstance = $uibModal.open({
            templateUrl: 'partials/modals/reservationModal.html',
            controller: 'reservationController',
            resolve: {
                residence: function () {
                    return residencesService.getResidence(id);
                }
            }
        });
    }
}]);
