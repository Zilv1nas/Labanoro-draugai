app.controller('residencesListController', ['$scope', '$state', 'residencesService', 'residences', function($scope, $state, residencesService, residences) {

    $scope.residences = residences;
    $scope.onView = function(id){
        $state.go('viewResidence', {"id":id});
    }
}]);
