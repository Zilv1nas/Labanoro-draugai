app.controller('adminMembersListController', ['$scope', 'residencesService', 'members', function ($scope, residencesService, members) {

    $scope.members = members;

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