app.controller('residencesListController', ['$scope', '$state', '$uibModal', 'residencesService', 'priority', function($scope, $state, $uibModal, residencesService, priority) {
	$scope.searchRequest = {};
	$scope.searchRequest.pageSize = 5;
	$scope.searchRequest.page = 1;
	
	var search = function () {
		residencesService.searchResidences($scope.searchRequest).then(function (response) {
			$scope.totalItems = response.data.total;
			$scope.residences = response.data.residences;

			angular.forEach($scope.residences, function(value, key){
				var created = new Date(value.dateOfRegistration);
				var availableToReserveFrom = new Date(created.setTime(created.getTime() + (priority - 1) * 7 * 86400000));
				var today = new Date();
				var availableFrom = new Date(value.availableFrom);
				if(availableToReserveFrom < today){
					value.ableToSeeForUser = true;
				}
				else if(availableFrom < today){
					value.ableToSeeForUser = true;
				}
				else{
					var oneDay = 24*60*60*1000;
					if(availableFrom < availableToReserveFrom){
						value.daysLeftToReserve = Math.round(Math.abs((availableFrom.getTime() - today.getTime())/(oneDay))) + 1;
					}
					else{
						value.daysLeftToReserve = Math.round(Math.abs((availableToReserveFrom.getTime() - today.getTime())/(oneDay))) + 1;
					}
					value.ableToSeeForUser = false;
				}
			});
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
	        },
	        residenceHistory: function() {
	        	return residencesService.getResidenceHistory(id);
	        }
	      }
	    });
	}
}]);
