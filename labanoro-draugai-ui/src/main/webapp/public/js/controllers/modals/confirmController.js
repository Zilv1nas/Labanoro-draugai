app.controller('confirmController', ['$scope', '$uibModalInstance', 'message', function ($scope, $uibModalInstance, message) {

    $scope.message = message;

    $scope.ok = function () {
        $uibModalInstance.close();
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
}]);
