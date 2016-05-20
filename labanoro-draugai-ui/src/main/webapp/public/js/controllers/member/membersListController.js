app.controller('membersListController', ['$scope', '$state', '$window', 'authService', 'membersService', 'members', '$uibModal', 'growl',
    function ($scope, $state, $window, authService, membersService, members, $uibModal, growl) {

        $scope.members = members;

        $scope.verifyUser = function (userId) {

            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'partials/modals/confirmModal.html',
                controller: 'confirmController',
                size: 'md',
                resolve: {
                    message: function () {
                        return 'Ar tikrai norite patvirtinti kandidatą?';
                    }
                }
            });

            modalInstance.result.then(function () {
                membersService.verifyUser(userId)
                    .then(function (response) {
                        growl.success('Kandidatas patvirtintas sėkmingai!');
                        $state.go('membersList', {}, { reload: 'membersList' });
                    }).catch(function (response) {
                        if (response.status === 409) {
                            growl.error('Peržengtas maksimalus narių skaičius!');
                        } else {
                            growl.error('Nepavyko patvirtintas kandidato!');
                        }
                    })
            });
        };

        $scope.invite = function (newUserEmail) {
            var invitationInfo = {
                toEmail: newUserEmail,
                fromName: authService.getAuthData().name,
                fromSurname: authService.getAuthData().surname,
                redirectUrl: $state.href('login', {}, { absolute: true })
            };

            membersService.invite(invitationInfo).then(function (response) {
                $scope.newUserEmail = null;
                growl.success('Sėkmingai išsiųstas pakvietimas!');
            }).catch(function (response) {
                growl.error('Nepavyko išsiųsti pakvietimo!');
            })
        }

    }]);

