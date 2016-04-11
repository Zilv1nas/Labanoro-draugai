angular.module('labanoroDraugaiApp').controller('mainController', ['$scope', 'userService', function ($scope, userService) {
    userService.test().success(function (result) {//TODO remove
        $scope.testResult = result.email;
    });
}]);

