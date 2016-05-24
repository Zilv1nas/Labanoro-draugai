app.controller('residencesListController', ['$scope', '$state', '$uibModal', 'residencesService', function($scope, $state, $uibModal, residencesService) {
	$scope.searchRequest = {};
	$scope.searchRequest.pageSize = 5;
	$scope.searchRequest.page = 1;
	var search = function () {
		residencesService.searchResidences($scope.searchRequest).then(function (response) {
			$scope.totalItems = response.data.total;
			$scope.residences = response.data.residences;
		});
	};

	search();
	
	$scope.pageChanged = function () {
		search();
	};

	$scope.search = function () {
		$scope.searchRequest.page = 1;
		search();
	};
	
	$scope.clearSearch = function () {
		var pageSize = $scope.searchRequest.pageSize;
		var page = $scope.searchRequest.page;
		$scope.searchRequest = {};
		$scope.searchRequest.pageSize = pageSize;
		$scope.searchRequest.page = page;
	};
    
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
