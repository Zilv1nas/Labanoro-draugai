app.controller('createResidenceController', ['$scope', 'residencesService', function ($scope, residencesService) {

    $scope.service = '';
    $scope.services = [];

    $scope.create = function () {
        console.log('aha');
        residencesService.createService()
            .then(function (response) {
                authService.setAuthData(response.data);
                $state.go('main');
            }).catch(function (response) {
                //TODO error handling
                alert("Couldn't log in" + response.data);
            })
    }
    
    $scope.createService = function() {
        $scope.services.push($scope.service);
        $scope.service = '';
    }
    
    $scope.removeService = function(key) {
        $scope.services.splice(key, 1);
    }

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
