app.service('settingsService', ['$http', function ($http) {
    var baseUrl = 'rest/settings/';

    this.getSettings = function () {
        return $http.get(baseUrl + 'getSettings')
            .then(function (result) {
                return result.data;
            }).catch(function (response) {
                console.log(response);
            });
    };

    this.confirmSettings = function (settings) {
        console.log(settings);
        return $http.post(baseUrl + "confirm", settings);
    }

}]);
