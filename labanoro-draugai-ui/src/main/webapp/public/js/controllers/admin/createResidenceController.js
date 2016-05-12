app.controller('createResidenceController', ['$scope', 'residencesService', '$filter', function ($scope, residencesService, $filter) {
    $scope.residence = {};
    $scope.residence.availability = {};
    $scope.service = {};
    $scope.price = 0;
    $scope.name = '';
    $scope.services = [];

    // $scope.$watch('residence.availability', function (newValue, oldValue) {
    //     console.log('pasikeite');
    //     if (angular.isDefined(newValue)) {
    //         if (angular.isDefined(newValue.dateFrom)) {
    //             var dateStrToSend = newValue.dateFrom.getFullYear() + '-' + (newValue.dateFrom.getMonth() + 1) +  '-' + newValue.dateFrom.getDate();

    //             console.log(dateStrToSend);

    //             // $filter('date')(newValue.dateFrom, 'yyyy-MM-dd');
    //             //         console.log(newValue.dateFrom);
    //         }
    //     }

    // });

    $scope.create = function () {
        console.log($scope.residence);
        if (angular.isDefined($scope.dateFrom)) {
            $scope.residence.availability.dateFrom = $scope.dateFrom.getFullYear() + '-' + ($scope.dateFrom.getMonth() + 1) + '-' + $scope.dateFrom.getDate();
        }
        if (angular.isDefined($scope.dateTo)) {
            $scope.residence.availability.dateTo = $scope.dateTo.getFullYear() + '-' + ($scope.dateTo.getMonth() + 1) + '-' + $scope.dateTo.getDate();
        }
        console.log($scope.residence.availability);
        $scope.residence.extraServices = $scope.services;
        residencesService.createResidence($scope.residence)
            .then(function (response) {
                console.log(response);
                // $state.go('main');
            }).catch(function (response) {
                console.log(response);
                //TODO error handling
                // alert("Couldn't create residence" + response.data);
            })
    };

    $scope.createService = function () {
        if (($scope.name !== '') && (angular.isDefined($scope.name))) {
            if (($scope.price !== '') && (angular.isDefined($scope.price))) {
                $scope.service.name = $scope.name;
                $scope.service.price = $scope.price;
                $scope.services.push($scope.service);
                $scope.name = '';
                $scope.price = 0;
            }
        }
    };

    $scope.removeService = function (key) {
        $scope.services.splice(key, 1);
    };

    $scope.clear = function () {
        $scope.dt = null;
    };

    $scope.open1 = function () {
        $scope.popup1.opened = true;
    };

    $scope.open2 = function () {
        $scope.popup2.opened = true;
    };

    $scope.popup1 = {
        opened: false
    };

    $scope.popup2 = {
        opened: false
    };

}]);

app.directive("fileread", [function () {
    return {
        scope: {
            fileread: "="
        },
        link: function (scope, element, attributes) {
            element.bind("change", function (changeEvent) {
                var reader = new FileReader();
                reader.onload = function (loadEvent) {
                    scope.$apply(function () {
                        scope.fileread = loadEvent.target.result;
                    });
                };
                reader.readAsDataURL(changeEvent.target.files[0]);
            });
        }
    }
}]);
