app.controller('adminMembersListController', ['$scope', 'residencesService', 'members', 'membersService', 'growl', '$state', '$uibModal', function ($scope, residencesService, members, membersService, growl, $state, $uibModal) {
    $scope.members = members;
    $scope.filterKey = '';

    $scope.filterMyBitches = function(member){
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
}]);
