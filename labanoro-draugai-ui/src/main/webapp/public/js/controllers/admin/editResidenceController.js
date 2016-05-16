app.controller('editResidenceController', ['$scope', '$rootScope', '$state', '$timeout', 'residencesService', 'residence', function ($scope, $rootScope, $state, $timeout, residencesService, residence) {
    $scope.residence = residence;
    $scope.residence.availability.dateFrom = new Date(residence.availability.dateFrom);
    $scope.residence.availability.dateTo = new Date(residence.availability.dateTo);

    $scope.Title = "Redaguoti rezidenciją";

    $scope.saveResidence = function (services) {
        //FixMe: This compensates for the -1 day bug.
        $scope.residence.extraServices = services;
        $scope.residence.availability.dateFrom.setHours(3, 0, 0, 0);
        $scope.residence.availability.dateTo.setHours(3, 0, 0, 0);
        console.log($scope.residence);

        residencesService.updateResidence($scope.residence)
            .then(function (response) {
                $state.go('main');
            }).catch(function (response) {
                console.log(response.data);
                var errorMessage = "";
                for (var i = 0; i < response.data.length; i++)
                    errorMessage += "\n" + response.data[i].message;
                alert("Error: " + errorMessage);
            })
    };

    $scope.$on('requestServices', function() {
        $scope.$broadcast('loadServices', residence.extraServices)
    });
}]);
