angular.module('labanoroDraugaiApp').controller('loginController', ['$scope', 'authService', function ($scope, authService) {
    $scope.login = function (loginInfo) {
        authService.login(loginInfo).success(function (data) {
            authService.userAuthData = data;
        }).error(function () {
            //TODO error handling
            alert("Couldn't log in");
        })
    }
}]);