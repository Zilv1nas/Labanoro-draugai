app.controller('settingsController', ['$scope', 'settingsService', function ($scope, settingsService) {

    $scope.settings = {};

    $scope.confirm = function () {
        settingsService.confirmSettings($scope.settings)
            .then(function (response) {
                console.log(response);
                $state.go('main');
            }).catch(function (response) {
                console.log(response);
                //TODO error handling
            })
    }

}]);