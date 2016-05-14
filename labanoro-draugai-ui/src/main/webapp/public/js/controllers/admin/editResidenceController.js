app.controller('editResidenceController', ['$scope', '$state', 'residencesService', 'residence', function ($scope, $state, residencesService, residence) {
    $scope.residence = residence;
    $scope.residence.availability.dateFrom = new Date(residence.availability.dateFrom);
    $scope.residence.availability.dateTo = new Date(residence.availability.dateTo);
    console.log($scope.residence);

    $scope.Title = "Redaguoti rezidenciją";
    $scope.service = '';

    if (angular.isDefined($scope.residence.services)) {
        $scope.services = $scope.residence.services;
    } else {
        $scope.services = [];
    }

    $scope.saveResidence = function () {
        $scope.residence.availability.dateFrom.setHours(3, 0, 0, 0);
        $scope.residence.availability.dateTo.setHours(3, 0, 0, 0);
        console.log($scope.residence);

        residencesService.updateResidence($scope.residence)
            .then(function (response) {
                $state.go('main');
            }).catch(function (response) {
            var errorMessage = "";
            for (var i = 0; i < response.data.length; i++)
                errorMessage += response.data[i].message + "\n"
            console.log(response.data);
            alert("Error: " + errorMessage);
        })
    };

    $scope.createService = function () {
        if (($scope.service !== '') && (angular.isDefined($scope.service))) {
            $scope.services.push($scope.service);
            $scope.service = '';
        }
    };

    $scope.removeService = function (key) {
        $scope.services.splice(key, 1);
    };
}]);
