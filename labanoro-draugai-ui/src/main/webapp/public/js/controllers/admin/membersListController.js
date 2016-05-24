app.controller('adminMembersListController', ['$scope', 'residencesService', 'members', 'membersService', 'growl', '$state', '$uibModal', 'transactionService', function ($scope, residencesService, members, membersService, growl, $state, $uibModal, transactionService) {
    $scope.members = members;
    $scope.filterKey = '';

    $scope.filterMyBitches = function (member) {
        var fullName = (member.name.toLowerCase() + ' ' + member.surname.toLowerCase());
        return (!$scope.filterKey || fullName.indexOf($scope.filterKey.toLowerCase()) != -1)
    };

    $scope.deleteUser = function (id) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'partials/modals/confirmModal.html',
            controller: 'confirmController',
            size: 'md',
            resolve: {
                message: function () {
                    return 'Ar tikrai norite ištrinti narį?';
                }
            }
        });

        modalInstance.result.then(function () {
            membersService.deleteMember(id)
                .then(function (response) {
                    growl.success('Narys ištrintas sėkmingai!');
                    $state.go('adminMembersList', {}, { reload: 'adminMembersList' });
                }).catch(function (response) {
                    growl.error('Nepavyko ištrinti nario!');
                })
        });
    };

    $scope.givePoints = function (member) {

        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'partials/modals/addPointsModal.html',
            controller: 'addPointsController',
            size: 'md',
            resolve: {
                header: function () {
                    return 'Skirti taškų ' + member.name + ' ' + member.surname;
                }
            }
        });

        modalInstance.result.then(function (points) {
            transactionService.sendPoints(member.id, points).then(function (response) {
                growl.success('Taškai skirti sėkmingai!');
            }).catch(function (response) {
                growl.error('Nepavyko skirti taškų!');
            })
        });
    };

}]);
