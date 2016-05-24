app.service('settingsService', ['$http', function ($http) {
    var baseUrl = 'rest/settings/';

    this.getAllSettings = function () {
        return $http.get(baseUrl + 'getAllSettings')
            .then(function (response) {
                return response.data;
            }).catch(function (response) {
                console.log(response);
            });
    }


    this.confirmSettings = function (settings) {
        return $http.post(baseUrl + "updateSettings", settings);
    }

}]);
