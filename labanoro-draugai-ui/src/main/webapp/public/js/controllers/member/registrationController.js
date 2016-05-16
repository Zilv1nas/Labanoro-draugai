app.controller('registrationController', ['$scope', '$state', 'authService', 'growl', function ($scope, $state, authService, growl) {
    $scope.signupType = "pw";
    
    $scope.register = function () {
        if ($scope.signupType === "pw") {
            registerWithUsername();
        } else {
            registerWithFB();
        }
    };
    
    var registerWithUsername = function () {
        authService.register($scope.newUser).then(function (response) {
            authService.setAuthData(response.data);
            $state.go('main');
        }).catch(function (response) {
            growl.error('Nepavyko užsiregistruoti!');
        })
    };

    var registerWithFB = function () {
        authService.authenticateFB().then(function (response) {
            authService.setAuthData(response.data);
            $state.go('main');
        }).catch(function (response) {
            growl.error('Nepavyko užsiregistruoti!');
        })
    }
}]);
