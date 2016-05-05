app.controller('profileController', ['$scope', "authService", function ($scope, authService) {
    
    //TODO fetch full info
    var authData = authService.getAuthData();
    $scope.user = {
        name: authData.name,
        surname: authData.surname,
        email: authData.email
    }
}]);

