app.controller('settingsController', ['$scope', '$state', 'settingsService', 'growl', 'settings', function ($scope, $state, settingsService, growl, settings) {

    $scope.settings = settings;

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