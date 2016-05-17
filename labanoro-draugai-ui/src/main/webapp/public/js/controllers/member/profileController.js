app.controller('profileController', ['$scope', '$stateParams', '$uibModal', '$state', 'growl', 'membersService', 'transactionService', 'authService',
    function ($scope, $stateParams, $uibModal, $state, growl, membersService, transactionService, authService) {

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

        $scope.remove = function () {
            var deleteProfileModal = $uibModal.open({
                animation: true,
                templateUrl: 'partials/modals/confirmModal.html',
                controller: 'confirmController',
                size: 'md',
                resolve: {
                    message: function () {
                        return 'Ar tikrai norite ištrinti savo paskyra? Šio veiksmo negalėsite atšaukti.';
                    }
                }
            });

            deleteProfileModal.result.then(function () {
                membersService.deleteCurrentMember().then(function (response) {
                    authService.logout();
                    $state.go("main");
                    growl.success('Paskyra buvu ištrinta sėkmingai.');
                }).catch(function (response) {
                    growl.error('Nepavyko ištrinti paskyros.');
                });
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

