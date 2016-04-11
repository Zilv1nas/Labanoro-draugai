angular.module('labanoroDraugaiApp').service('residencesService', ['$http', function ($http) {
    var baseUrl = 'rest/';

    this.getAllResidences = function() {
        var url = "rest/residences/GetAll";
        return $http.GET(url);
    }
    
}]);
