app.controller('loginController', ['$scope', '$state', 'authService', function ($scope, $state, authService) {
    $scope.login = function () {
        authService.login($scope.loginInfo).success(function (data) {
            authService.setAuthData(data);
            $state.go('main');
        }).error(function () {
            //TODO error handling
            alert("Couldn't log in");
        })
    }
}]);