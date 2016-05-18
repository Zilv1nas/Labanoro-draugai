app.controller('createResidenceController', ['$scope', '$state', 'residencesService', 'growl', function ($scope, $state, residencesService, growl) {
    $scope.Title = 'Sukurti rezidenciją';
    $scope.residence = {};
    $scope.residence.availability = {};
    $scope.errorMessages = [];

    $scope.saveResidence = function (services) {
        $scope.residence.extraServices = services;
        //FixMe: This compensates for the -1 day bug.
        if ($scope.residence.availability.dateFrom)
            $scope.residence.availability.dateFrom.setHours(3, 0, 0, 0);
        if ($scope.residence.availability.dateTo)
            $scope.residence.availability.dateTo.setHours(3, 0, 0, 0);

        residencesService.createResidence($scope.residence)
            .then(function (response) {
                growl.success('Vasarnamis sukurtas sėkmingai!');
                $state.go('adminResidencesList');
            }).catch(function (response) {
                $scope.errorMessages = response.data;
                growl.error('Nepavyko sukurti vasarnamio!');
            })
    };
}]);

