app.service('profileService', ['$http', function ($http) {
    
    var baseUrl = 'rest/';

    this.getProfile = function() {
        var url = "rest/profile/GetProfile";
        return $http.GET(url);
    }

    
    
}]);
