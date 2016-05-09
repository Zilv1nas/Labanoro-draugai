app.controller('navigationController', ['$scope', '$state', 'authService', function ($scope, $state, authService) {
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
            $state.go('main');
        }
    };
    
    $scope.isAdmin = function () {
        return authService.isAdmin();
    };
    
    $scope.isMember = function () {
        return authService.isMember() || authService.isAdmin();
    }
    
    $scope.isNotCandidate = function() {
        return !authService.isCandidate();
    }
    
}]);