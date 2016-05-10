app.service('residencesService', ['$http', function ($http) {
    var baseUrl = 'rest/';

    this.getAllResidences = function () {
        return $http.get(baseUrl + "residences/getAll")
            .then(function (result) {
                return result.data;
            }).catch(function (response) {
                console.log(response);
            });
    }

    this.getResidence = function (id) {
        return $http.get(baseUrl + "residences/get/" + id);
    }

    this.createResidence = function (residence) {
        return $http.post(baseUrl + "residences/create", residence);
    }

    this.updateResidence = function (residence) {
        return $http.post(baseUrl + "residences/update", residence);
    }

}]);
