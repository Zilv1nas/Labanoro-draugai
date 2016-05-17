app.controller('editResidenceController', ['$scope', '$state', 'residencesService', 'residence', 'growl', function ($scope, $state, residencesService, residence, growl) {
    $scope.Title = "Redaguoti rezidenciją";
    $scope.residence = residence;
    $scope.residence.availability.dateFrom = new Date(residence.availability.dateFrom);
    $scope.residence.availability.dateTo = new Date(residence.availability.dateTo);

    $scope.saveResidence = function (services) {
        //FixMe: This compensates for the -1 day bug.
        $scope.residence.extraServices = services;
        $scope.residence.availability.dateFrom.setHours(3, 0, 0, 0);
        $scope.residence.availability.dateTo.setHours(3, 0, 0, 0);

        residencesService.updateResidence($scope.residence)
            .then(function (response) {
                growl.success('Vasarnamis atnaujintas sėkmingai!');
                $state.go('adminResidencesList');
            }).catch(function (response) {
                growl.error('Nepavyko atnaujinti vasarnamio!');
                console.log(response);
            })
    };

    $scope.$on('requestServices', function() {
        $scope.$broadcast('loadServices', residence.extraServices)
    });
}]);
