app.controller('loginController', ['$scope', '$state', 'authService', function ($scope, $state, authService) {
    $scope.login = function () {
        authService.login($scope.loginInfo).then(function (response) {
            authService.setAuthData(response.data);
            $state.go('main');
        }).catch(function (response) {
            //TODO error handling
            alert("Couldn't log in" + response.data);
        })
    };
    
    $scope.loginWithFb = function () {
      authService.authenticateFB().then(function (response) {
          authService.setAuthData(response.data);
          $state.go('main');
      }).catch(function (response) {
          //TODO
          alert("Couldn't log in" + response.data);
      })
    }
}]);