app.controller('residencesListController', ['$scope', '$state', 'residencesService', 'residences', function($scope, $state, residencesService, residences) {
    $scope.residences = residences;

    //ToDo: Add logic for showing partial list for paging
}]);
