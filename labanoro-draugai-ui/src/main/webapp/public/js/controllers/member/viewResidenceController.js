app.controller('viewResidenceController', ['$scope', '$state', 'residencesService', 'residence', 'growl', function ($scope, $state, residencesService, residence, growl) {
    $scope.residence = residence;
}]);
