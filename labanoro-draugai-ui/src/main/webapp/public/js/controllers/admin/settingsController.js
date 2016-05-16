app.controller('settingsController', ['$scope', 'settingsService', 'growl', function ($scope, settingsService, growl) {

    $scope.settings = {};

    $scope.confirm = function () {
        settingsService.confirmSettings($scope.settings)
            .then(function (response) {
                growl.success('Nustatymai atnaujinti sėkmingai!');
                $state.go('settings', {}, { reload: 'settings' });
            }).catch(function (response) {
                growl.error('Nepavyko atnaujinti nustatymų!');
            })
    }

}]);