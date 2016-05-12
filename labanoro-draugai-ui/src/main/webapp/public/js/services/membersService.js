app.service('membersService', ['$http', function ($http) {

    var baseUrl = 'rest/user/';

    this.getAllMembers = function () {
        return $http.get(baseUrl + 'getAll')
            .then(function (result) {
                return result.data;
            }).catch(function (response) {
                console.log(response);
            });
    };

    this.verifyUser = function (userId) {
        return $http.post(baseUrl + "verify", userId);
    };

    this.invite = function (invitationInfo) {
        return $http.post(baseUrl + "invite", invitationInfo);
    };

    this.getCurrentUserProfile = function () {
        return $http.get(baseUrl + "getProfile");
    };

    this.getMemberProfile = function (id) {
        return $http.get(baseUrl + "getMemberProfile/" + id);
    };

    this.updateCurrentUserProfile = function(user) {
        return $http.post(baseUrl + 'updateCurrentUserProfile', user);
    };

    this.updateProfile = function (user) {
        return $http.post(baseUrl + 'updateProfile', user);
    }

}]);

