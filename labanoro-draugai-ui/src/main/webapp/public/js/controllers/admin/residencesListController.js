app.controller('adminResidencesListController', ['$scope', 'residencesService', 'authService', 'residences',
    function ($scope, residencesService, authService, residences) {

    $scope.residences = residences;

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