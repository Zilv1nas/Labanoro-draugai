app.service('residencesService', ['$http', function ($http) {
    var baseUrl = 'rest/residence/';

    this.getAllResidences = function () {
        return $http.get(baseUrl + "getAll")
            .then(function (result) {
                return result.data;
            }).catch(function (response) {
                console.log(response);
            });
    };

    this.getResidence = function (id) {
        return $http.get(baseUrl + "get" + id);
    };

    this.createResidence = function (residence) {
        return $http.post(baseUrl + "save", residence);
    };

    this.updateResidence = function (residence) {
        return $http.post(baseUrl + "update", residence);
    }

}]);
