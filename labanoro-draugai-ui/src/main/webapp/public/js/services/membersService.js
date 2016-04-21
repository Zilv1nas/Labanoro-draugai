app.service('membersService', ['$http', function ($http) {
    
    var baseUrl = 'rest/user/';

    this.getAllMembers = function() {
        return $http.get(baseUrl + 'getAll');
    }
    
}]);
