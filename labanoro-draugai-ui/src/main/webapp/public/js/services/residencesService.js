app.service('residencesService', ['$http', function ($http) {
    var baseUrl = 'rest/';

    this.getAllResidences = function() {
        return $http.get(baseUrl + "residences/getAll");
    }
    
    this.createService = function(residence) {
        return $http.post(baseUrl + "residences/create", residence);
    }
    
}]);
