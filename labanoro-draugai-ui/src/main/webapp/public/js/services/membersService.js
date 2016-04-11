angular.module('labanoroDraugaiApp').service('membersService', ['$http', function ($http) {
    
    var baseUrl = 'rest/';

    this.getAllMembers = function() {
        var url = "rest/members/GetAll";
        return $http.GET(url);
    }
    
}]);
