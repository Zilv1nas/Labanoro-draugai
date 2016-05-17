app.controller('adminMembersListController', ['$scope', 'residencesService', 'members', 'membersService', 'growl', '$state', '$uibModal', function ($scope, residencesService, members, membersService, growl, $state, $uibModal) {

    $scope.members = members;

    $scope.filterFunction = function(element) {
        return element.name.match(/^Ma/);
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

}]);