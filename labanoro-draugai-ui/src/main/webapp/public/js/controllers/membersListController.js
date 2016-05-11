app.controller('membersListController', ['$scope', '$state', 'authService', 'membersService', 'members', function ($scope, $state, authService, membersService, members) {

    $scope.members = members;
    // membersService.getAllMembers().then(function (response) {
    //     $scope.members = response.data;
    // }).catch(function (response) {
    //     //TODO
    // });

    $scope.verifyUser = function (userId) {
        membersService.verifyUser(userId).then(function (response) {
            $state.reload();
        }).catch(function (response) {
            //TODO show error
        })
    };

    $scope.invite = function (newUserEmail) {
        var invitationInfo = {
            toEmail: newUserEmail,
            fromName: authService.getAuthData().name,
            fromSurname: authService.getAuthData().surname
        };

        membersService.invite(invitationInfo).then(function (response) {
            $scope.newUserEmail = null;
            //TODO
        }).catch(function (response) {
            //TODO show error
        })
    }
}]);

