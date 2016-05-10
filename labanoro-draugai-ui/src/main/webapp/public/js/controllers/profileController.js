app.controller('profileController', ['$scope', '$uibModal', 'membersService', 'transactionService', function ($scope, $uibModal, membersService, transactionService) {

    membersService.getCurrentUserProfile().then(function (response) {
        $scope.user = response.data;
    }).catch(function (response) {
        //TODO
    });

    $scope.openModal = function () {

        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'partials/modals/addPointsModal.html',
            controller: 'addPointsController',
            size: 'md'
        });

        modalInstance.result.then(function (points) {
            transactionService.createPurchase(points).then(function (response) {
                //TODO 
            }).catch(function (response) {
                //TODO
            })
        });
    }
}]);

app.controller('addPointsController', ['$scope', '$uibModalInstance', function ($scope, $uibModalInstance) {
    $scope.ok = function () {
        $uibModalInstance.close($scope.points);
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
}]);

