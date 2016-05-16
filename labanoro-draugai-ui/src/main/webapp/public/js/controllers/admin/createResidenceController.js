app.controller('createResidenceController', ['$scope', '$state', 'residencesService', function ($scope, $state, residencesService) {
    $scope.Title = 'Sukurti rezidenciją';
    $scope.residence = {};
    $scope.residence.availability = {};

    $scope.saveResidence = function (services) {
        $scope.residence.extraServices = services;
        //FixMe: This compensates for the -1 day bug.
        if ($scope.residence.availability.dateFrom)
            $scope.residence.availability.dateFrom.setHours(3, 0, 0, 0);
        if ($scope.residence.availability.dateTo)
            $scope.residence.availability.dateTo.setHours(3, 0, 0, 0);
        
        residencesService.createResidence($scope.residence)
            .then(function (response) {
                $state.go('main');
            }).catch(function (response) {
                console.log(response);
                //ToDo:
            })
    };
}]);

