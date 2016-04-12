angular.module('labanoroDraugaiApp').controller('mainController', [ 'userService', function (userService) {
    userService.test().success(function (result) {//TODO remove
        $scope.testResult = result.email;
    });
}]);

