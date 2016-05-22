app.controller('residencesListController', ['$scope', '$state', '$uibModal', 'residencesService', 'residences', function($scope, $state, $uibModal, residencesService, residences) {
    $scope.residences = residences;

    //ToDo: Add logic for showing partial list for paging

    $scope.reserveResidence = function (id) {
	    var modalInstance = $uibModal.open({
	      templateUrl: 'partials/modals/reservationModal.html',
	      controller: 'reservationController',
	      resolve: {
	        residence: function () {
	          return residencesService.getResidence(id);
	        }
	      }
	    });
	}
}]);
