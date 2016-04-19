app.controller('navigationController', ['$scope', 'authService', function ($scope, authService) {
    function setData() {
        $scope.isAuthenticated = authService.isAuthenticated();
        if (authService.isAuthenticated()) {
            $scope.userName = authService.getAuthData().name;
        }
    }

    setData();

    $scope.$on('authChanged', function () {
        setData();
    });
    
    $scope.logout = function () {
        if (authService.isAuthenticated()) {
            authService.logout();
        }
    }
}]);