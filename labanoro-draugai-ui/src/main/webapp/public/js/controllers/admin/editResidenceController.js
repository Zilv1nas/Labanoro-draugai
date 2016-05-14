app.controller('editResidenceController', ['$scope', '$state', 'residencesService', 'residence', function ($scope, $state, residencesService, residence) {

    $scope.residence = residence;
    $scope.service = '';
    if (angular.isDefined($scope.residence.services)) {
        $scope.services = $scope.residence.services;
    } else {
        $scope.services = [];
    }


    $scope.update = function () {
        console.log(residence);
        residencesService.updateResidence($scope.residence)
            .then(function (response) {
                $state.go('main');
            }).catch(function (response) {
                //TODO error handling
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

    $scope.clear = function () {
        $scope.dt = null;
    };

    $scope.open1 = function () {
        $scope.popup1.opened = true;
    };

    $scope.open2 = function () {
        $scope.popup2.opened = true;
    };

    $scope.popup1 = {
        opened: false
    };

    $scope.popup2 = {
        opened: false
    };

}]);