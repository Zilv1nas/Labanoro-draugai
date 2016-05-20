app.service('settingsService', ['$http', function ($http) {
    var baseUrl = 'rest/settings/';

    this.getAllSettings = function () {
        return $http.get(baseUrl + 'getAllSettings');
    };

    this.confirmSettings = function (settings) {
        return $http.post(baseUrl + "updateSettings", settings);
    }

}]);
