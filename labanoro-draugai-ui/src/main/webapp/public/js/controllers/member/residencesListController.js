app.controller('residencesListController', ['$scope', '$state', 'residencesService', 'residences', function($scope, $state, residencesService, residences) {

    $scope.residences = residences;
    $scope.onView = function(ID){
        console.log("ID: "+ID);
        $state.go('viewResidence', {"ID":ID});
    }
}]);
