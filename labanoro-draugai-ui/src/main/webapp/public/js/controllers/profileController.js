app.controller('profileController', ['$scope', '$stateParams', '$uibModal', 'membersService', 'transactionService', 'authService',
    function ($scope, $stateParams, $uibModal, membersService, transactionService, authService) {

    var getCurrentUserProfule = function () {
        membersService.getCurrentUserProfile().then(function (response) {
            $scope.user = response.data;
        }).catch(function (response) {
            //TODO
        });
    };

    var getMemberProfile = function (id) {
        membersService.getMemberProfile(id).then(function (response) {
            $scope.user = response.data;
        }).catch(function (response) {
            //TODO
        })
    };

    $scope.isCurrentUser = !angular.isDefined($stateParams.memberId);
    $scope.isAdmin = authService.isAdmin();
    if ($scope.isCurrentUser) {
        getCurrentUserProfule();
    } else {
        getMemberProfile($stateParams.memberId);
    }

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
    };

    $scope.update = function () {
        var successHandler = function (response) {
          //TODO
        };

        var failureHandler = function (response) {
            //TODO
        };

        if ($scope.isCurrentUser) {
            membersService.updateCurrentUserProfile($scope.user).then(successHandler.catch(failureHandler));
        } else {
            membersService.updateProfile($scope.user).then(successHandler).catch(failureHandler);
        }
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

