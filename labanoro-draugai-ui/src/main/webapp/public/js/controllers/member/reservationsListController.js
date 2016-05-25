app.controller('reservationsListController', ['$scope', '$state', '$uibModal', 'residencesService', 'reservations', 'growl', function ($scope, $state, $uibModal, residencesService, reservations, growl) {
    $scope.reservations = reservations;

    $scope.cancelReservation = function (reservationId) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'partials/modals/confirmModal.html',
            controller: 'confirmController',
            size: 'md',
            resolve: {
                message: function () {
                    return 'Ar tikrai norite atšaukti rezervaciją?';
                }
            }
        });

        modalInstance.result.then(function () {
            residencesService.cancelReservation(reservationId).then(function (response) {
                growl.success("Rezervacija sėkmingai atšaukta");
                $state.go('reservationsList', {}, { reload: 'reservationsList' });
            }).catch(function (response) {
                growl.error("Nepvyko atšaukti rezervacijos.")
            })
        });
    };
}]);

