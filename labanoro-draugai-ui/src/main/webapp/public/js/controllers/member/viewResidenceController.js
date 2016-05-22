app.controller('viewResidenceController', ['$scope', '$state', 'residencesService', 'residence', 'growl', function ($scope, $state, residencesService, residence, growl) {
    $scope.residence = residence;

    $scope.fullPrice = $scope.residence.weeklyPrice;

    $scope.setFullPrice = function(){
        var price = $scope.residence.weeklyPrice;
        angular.forEach($scope.residence.extraServices, function(extraService){
            if (!!extraService.selected) price += extraService.price;
        })
        $scope.fullPrice = price;
    }
}]);
