app.controller('groupsController', ['$scope', '$state', 'groupsService', 'growl', 'groups', function ($scope, $state, groupsService, growl, groups) {

    $scope.groups = groups;

    $scope.confirm = function () {
        groupsService.update($scope.groups)
            .then(function (response) {
                growl.success('Grupės atnaujintos sėkmingai!');
                $state.go('groups', {}, { reload: 'groups' });
            }).catch(function (response) {
                growl.error('Nepavyko atnaujinti grupių!');
            })
    }

}]);