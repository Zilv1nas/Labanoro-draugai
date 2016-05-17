/**
 * Created by Sebastianas.Malinaus on 15/5/2016.
 */
app.controller('servicesController', function($scope, $rootScope) {
    $scope.services = [];
    $scope.service = {};

    $scope.createService = function () {
        var isValid =((angular.isDefined($scope.service.name)) && (angular.isDefined($scope.service.price)) && $scope.service.name !== '') && ($scope.service.price !== '');
        console.log ("Name: " + $scope.service.name + ", price: " + $scope.service.price + " => " + (isValid ? "valid" : "invalid") + " request.");
        if (isValid) {
            var service = {};
            service.name = $scope.service.name;
            service.price = $scope.service.price;
            $scope.services.push(service);

            $scope.service.name = '';
            $scope.service.price = 0;
        }
    };

    $scope.removeService = function (key) {
        $scope.services.splice(key, 1);
    };
    
    $scope.$on('loadServices', function(event, services) {
        if (services)
            $scope.services = services;
    });

    $scope.$emit('requestServices');
});
