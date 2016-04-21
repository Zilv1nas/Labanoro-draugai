app.controller('membersListController', ['$scope', 'membersService', function ($scope, membersService) {
    membersService.getAllMembers().then(function (response) {
        $scope.members = response.data;
    }).catch(function (response) {
        //TODO
        alert('Xujnia' + response.data);
    })
}]);

