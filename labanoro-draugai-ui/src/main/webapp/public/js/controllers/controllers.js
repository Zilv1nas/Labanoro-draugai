/**
 * Created by Žilvinas on 2016-03-11.
 */
angular.module('labanoroDraugaiApp').controller('mainController', ['$scope', 'userService', function ($scope, userService) {
    userService.test().success(function (result) {//TODO remove
        $scope.testResult = result.email;
    });
}]);

angular.module('labanoroDraugaiApp').controller('loginController', ['$scope', function ($scope) {
}]);

angular.module('labanoroDraugaiApp').controller('registrationController', ['$scope', function ($scope) {
}]);

