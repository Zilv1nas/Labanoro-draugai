app.controller('settingsController', ['$scope', '$state', 'settingsService', 'growl', function ($scope, $state, settingsService, growl) {

    settingsService.getAllSettings().then(function (response) {
         $scope.settings = response.data;
    }).catch(function (response) {
        growl.error('Nepavyko gauti duomenų!');
    });

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