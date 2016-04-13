angular.module('labanoroDraugaiApp').service('authService', ['$http', function ($http) {

    var baseUrl = 'rest/';
    var userAuthData;

    this.login = function(loginInfo) {
        return $http.post(baseUrl + 'login', loginInfo);
    };

}]);
