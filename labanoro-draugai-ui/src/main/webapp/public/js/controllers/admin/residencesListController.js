app.controller('residencesListControllers', ['$scope', 'residencesService', function ($scope, residencesService) {

    $scope.create = function () {
        residencesService.createService()
            .then(function (response) {
                authService.setAuthData(response.data);
                $state.go('main');
            }).catch(function (response) {
                //TODO error handling
                alert("Couldn't log in" + response.data);
            })
    }


}]);