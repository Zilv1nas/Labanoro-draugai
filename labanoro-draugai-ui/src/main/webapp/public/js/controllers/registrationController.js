app.controller('registrationController', ['$scope', '$state', 'authService', function ($scope, $state, authService) {
    $scope.signupType = "pw";
    
    $scope.register = function () {
        authService.register($scope.newUser).success(function (data) {
            authService.setAuthData(data);
            $state.go('main');
        }).error(function () {
            //TODO error handling
            alert("Couldn't register");
        })
    }
}]);
