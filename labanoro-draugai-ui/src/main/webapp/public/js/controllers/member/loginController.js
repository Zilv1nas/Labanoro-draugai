app.controller('loginController', ['$scope', '$state', 'authService', 'growl', function ($scope, $state, authService, growl) {
    $scope.login = function () {
        authService.login($scope.loginInfo).then(function (response) {
            authService.setAuthData(response.data);
            $state.go('main');
        }).catch(function (response) {
            growl.error('Nepavyko prisijungti!');
        })
    };

    $scope.loginWithFb = function () {
        authService.authenticateFB().then(function (response) {
            authService.setAuthData(response.data);
            $state.go('main');
        }).catch(function (response) {
            growl.error('Nepavyko prisijungti!');
        })
    }
}]);