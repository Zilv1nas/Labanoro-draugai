app.controller('addPointsController', ['$scope', '$uibModalInstance', 'header', function ($scope, $uibModalInstance, header) {
    $scope.header = header;
    
    $scope.ok = function () {
        $uibModalInstance.close($scope.points);
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
}]);
