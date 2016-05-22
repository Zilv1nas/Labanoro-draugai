app.controller('reservationController', ['$scope', 'growl', '$filter', '$uibModalInstance', 'residencesService', 'residence', function ($scope, growl, $filter ,$uibModalInstance, residencesService, residence) {

	$scope.residence = residence;
	$scope.selected = {};
    $scope.selected.services = [];
    $scope.duration = {};

    $scope.ok = function () {
    	var model = {};
    	model.residence = $scope.residence;
    	model.extraServices = $scope.selected.services;
    	model.duration = $scope.duration;

    	// loses the timezone
    	model.duration.dateFrom = $filter('date')(model.duration.dateFrom, 'yyyy-MM-dd');
    	model.duration.dateTo = $filter('date')(model.duration.dateTo, 'yyyy-MM-dd');

    	console.log(model);
    	residencesService.reserveResidence(model).then(function(success){
    		growl.success("valio");
    		$uibModalInstance.close();
    	}, function(error){
    		console.log(error);
    	});
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss();
    };
}]);