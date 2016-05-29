app.controller('viewResidenceController', ['$scope', '$state', '$uibModal', 'residencesService', 'residence', 'residenceHistory', 'growl', 'priority', function ($scope, $state, $uibModal, residencesService, residence, residenceHistory, growl, priority) {
    $scope.residence = residence;
    $scope.residenceHistory = residenceHistory;
    var created = new Date($scope.residence.dateOfRegistration);
    var availableToReserveFrom = new Date(created.setTime(created.getTime() + (priority - 1) * 7 * 86400000));
    var today = new Date();
    var availableFrom = new Date($scope.residence.availability.dateFrom);
    if(availableToReserveFrom < today){
        $scope.residence.ableToSeeForUser = true;
    }
    else if(availableFrom < today){
        $scope.residence.ableToSeeForUser = true;
    }
    else{
        var oneDay = 24*60*60*1000;
        if(availableFrom < availableToReserveFrom){
            $scope.residence.daysLeftToReserve = Math.round(Math.abs((availableFrom.getTime() - today.getTime())/(oneDay))) + 1;
        }
        else{
            $scope.residence.daysLeftToReserve = Math.round(Math.abs((availableToReserveFrom.getTime() - today.getTime())/(oneDay))) + 1;
        }
        $scope.residence.ableToSeeForUser = false;
    }

    $scope.reserveResidence = function (id) {
        var modalInstance = $uibModal.open({
            templateUrl: 'partials/modals/reservationModal.html',
            controller: 'reservationController',
            resolve: {
                residence: function () {
                    return residencesService.getResidence(id);
                },
                residenceHistory: function() {
                    return residencesService.getResidenceHistory(id);
                }
            }
        });
    }
}]);
