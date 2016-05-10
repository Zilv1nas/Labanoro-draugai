app.service('membersService', ['$http', function ($http) {
    
    var baseUrl = 'rest/user/';

    this.getAllMembers = function() {
        return $http.get(baseUrl + 'getAll');
    };
    
    this.verifyUser = function (userId) {
      return $http.post(baseUrl + "verify", userId);  
    };
    
    this.invite = function (invitationInfo) {
        return $http.post(baseUrl + "invite", invitationInfo);
    };
    
    this.getCurrentUserProfile = function () {
        return $http.get(baseUrl + "getProfile");
    }
    
}]);
