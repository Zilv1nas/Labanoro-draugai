app.service('residencesService', ['$http', function ($http) {

    var baseUrl = 'rest/residence/';
    var reservationUrl = 'rest/reservation/';

    var getFunc = function (prefix) {
        return $http.get('rest/residence/' + prefix)
            .then(function (result) {
                return result.data;
            }).catch(function (response) {
                console.log(response);
            });
    };

    this.getResidenceHistory = function (id) {
        return $http.get('rest/reservation/getResidenceHistory/' + id)
            .then(function (result) {
                console.log(result.data);
                return result.data;
            }).catch(function (response) {
                console.log(response);
            });
    };

    this.getAllResidences = function () {
        return getFunc("getAll/");
    };

    this.getResidence = function (id) {
        return getFunc("get/" + id);
    };

    this.createResidence = function (residence) {
        return $http.post(baseUrl + "save/", residence);
    };

    this.deleteResidence = function (id) {
        return $http.post(baseUrl + "delete/" + id);
    };

    this.updateResidence = function (residence) {
        return $http.post(baseUrl + "update/", residence);
    }

    this.reserveResidence = function (reservation){
        return $http.post(reservationUrl + "reserve", reservation);
    }
}]);
