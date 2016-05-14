app.controller('createResidenceController', ['$scope', 'residencesService', '$filter', function ($scope, residencesService, $filter) {
    $scope.residence = {};
    $scope.residence.availability = {};
    $scope.service = {};
    $scope.price = 0;
    $scope.name = '';
    $scope.services = [];

    $scope.Title = 'Sukurti rezidenciją';

    $scope.saveResidence = function () {
        console.log($scope.residence);

        $scope.residence.extraServices = $scope.services;
        residencesService.createResidence($scope.residence)
            .then(function (response) {
                console.log(response);
                // $state.go('main');
            }).catch(function (response) {
                //TODO error handling
                var errorMessage = "";
                for (var i = 0; i < response.data.length; i++)
                    errorMessage += response.data[i].message + "\n"
                console.log(response.data);
                alert("Error: " + errorMessage);
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
