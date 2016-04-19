app.service('authService', ['$http', '$rootScope', function ($http, $rootScope) {

    var baseUrl = 'rest/';
    
    this.login = function(loginInfo) {
        return $http.post(baseUrl + 'login', loginInfo);
    };
    
    this.register = function (userInfo) {
        return $http.post(baseUrl + 'register', userInfo);  
    };
    
    this.setAuthData = function (authData) {
        var userAuthData = {
            email: authData.email,
            name: authData.name,
            surname: authData.surname,
            role: authData.role,
            token: authData.token
        };

        localStorage.setItem('userAuthData', JSON.stringify(userAuthData));
        $rootScope.$broadcast('authChanged');
    };
    
    this.getAuthData = function () {
        return JSON.parse(localStorage.getItem('userAuthData'));
    };

    this.isAuthenticated = function () {
        return !!this.getAuthData();
    };

    this.isMember = function () {
        return this.getAuthData().role === 'MEMBER';
    };

    this.isCandidate = function () {
        return this.getAuthData().role === 'CANDIDATE';
    };

    this.isAdmin = function () {
        return this.getAuthData().role === 'ADMIN';
    };

    this.logout = function () {
        localStorage.removeItem('userAuthData');
        $rootScope.$broadcast('authChanged');
    }

}]);
