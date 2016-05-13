app.controller('membersListController', ['$scope', '$state', '$window', 'authService', 'membersService', 'members',
    function ($scope, $state, $window, authService, membersService, members) {

    $scope.members = members;

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
            fromSurname: authService.getAuthData().surname,
            redirectUrl: $state.href('login', {}, {absolute: true})
        };

        membersService.invite(invitationInfo).then(function (response) {
            $scope.newUserEmail = null;
            //TODO
        }).catch(function (response) {
            //TODO show error
        })
    }
}]);

