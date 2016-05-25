app.controller('reservationController', ['$scope', 'growl', '$filter', '$uibModalInstance', 'residencesService', 'residence', function ($scope, growl, $filter ,$uibModalInstance, residencesService, residence) {

	$scope.residence = residence;
	$scope.selected = {};
    $scope.selected.services = [];
    $scope.duration = {};
    $scope.totalPrice = 0;
    $scope.servicesPrice = 0;
    $scope.weekCount = 0;

    $scope.datePickerOptions = {
    	availability: $scope.residence.availability
    }

    $scope.$watch('selected.services', function(o, n){
    	updateTotalPrice();
    });

    $scope.$watch('duration.dateFrom', function(o, n){
    	updateTotalPrice();
    });

    $scope.$watch('duration.dateTo', function(o, n){
    	updateTotalPrice();
    });

    $scope.ok = function () {
    	var model = {};
    	model.residence = $scope.residence;
    	model.extraServices = $scope.selected.services;
    	model.duration = $scope.duration;

    	// loses the timezone
    	model.duration.dateFrom = $filter('date')(model.duration.dateFrom, 'yyyy-MM-dd');
    	model.duration.dateTo = $filter('date')(model.duration.dateTo, 'yyyy-MM-dd');

    	residencesService.reserveResidence(model).then(function(success) {
    		growl.success("Vasarnamis sėkmingai rezervuotas");
    		$uibModalInstance.close();
    	}).catch(function(error){
			growl.error(error.data.message);
			$uibModalInstance.close();
    	});
    };

    $scope.cancel = function () {
        $uibModalInstance.dismiss();
    };

    function calculateWeekCount(dateFrom, dateTo){
    	if(dateFrom && dateTo ){	    		
	    	var dF = new Date(dateFrom);
	    	var dT = new Date(dateTo);

	    	var oneDay = 24*60*60*1000;
	    	var daysCount = Math.round(Math.abs((dT.getTime() - dF.getTime())/(oneDay)));

	    	if(daysCount < 0){
	    		return 0;
	    	}

	    	return parseInt(daysCount / 7) + 1; 
	    }
    	return 0;
    }

    function updateTotalPrice(){
    	$scope.weekCount = calculateWeekCount($scope.duration.dateFrom, $scope.duration.dateTo);
    	var servicesPrice = 0;
    	angular.forEach($scope.selected.services, function(value, key){
    		servicesPrice += value.price;
    	});
    	$scope.servicesPrice = servicesPrice;
    	$scope.totalPrice = ($scope.servicesPrice + $scope.residence.weeklyPrice) * $scope.weekCount;
    }

}]);