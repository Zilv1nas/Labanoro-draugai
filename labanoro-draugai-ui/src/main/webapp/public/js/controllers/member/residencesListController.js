app.controller('residencesListController', ['$scope', 'residencesService', 'residences', function($scope, residencesService, residences) {

    $scope.residences = residences;

}]);