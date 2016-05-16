app.controller('profileController', ['$scope', '$stateParams', '$uibModal', 'membersService', 'transactionService', 'authService', 'growl',
    function ($scope, $stateParams, $uibModal, membersService, transactionService, authService, growl) {

    var getCurrentUserProfule = function () {
        membersService.getCurrentUserProfile().then(function (response) {
            $scope.user = response.data;
        }).catch(function (response) {
            growl.error('Nepavyko gauti profilio duomenų!');
        });
    };

    var getMemberProfile = function (id) {
        membersService.getMemberProfile(id).then(function (response) {
            $scope.user = response.data;
        }).catch(function (response) {
            growl.error('Nepavyko gauti profilio duomenų!');
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
                growl.success('Užsakymas atliktas sėkmingai!');
            }).catch(function (response) {
                growl.error('Nepavyko atlikti užsakymo!');
            })
        });
    };

    $scope.update = function () {
        var successHandler = function (response) {
          growl.success('Profilis atnaujintas sėkmingai!');
        };

        var failureHandler = function (response) {
            growl.error('Nepavyko atnaujinti profilio!');
        };

        if ($scope.isCurrentUser) {
            membersService.updateCurrentUserProfile($scope.user).then(successHandler.catch(failureHandler));
        } else {
            membersService.updateProfile($scope.user).then(successHandler).catch(failureHandler);
        }
    }
}]);

