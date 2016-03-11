/**
 * Created by Žilvinas on 2016-03-11.
 */
angular.module('labanoroDraugaiApp').controller('mainController', ['$scope', 'userService', function ($scope, userService) {
    userService.test().success(function (result) {//TODO remove
        $scope.testResult = result.email;
    });
}]);

