app.controller('createResidenceController', ['$scope', 'residencesService', function ($scope, residencesService) {
    $scope.residence = {};
    $scope.service = '';
    $scope.services = [];

    $scope.create = function () {
        $scope.residence.services = $scope.services;
        residencesService.createResidence($scope.residence)
            .then(function (response) {
                $state.go('main');
            }).catch(function (response) {
                //TODO error handling
                alert("Couldn't create residence" + response.data);
            })
    }

    $scope.createService = function () {
        if (($scope.service !== '') && (angular.isDefined($scope.service))) {
            $scope.services.push($scope.service);
            $scope.service = '';
        }
    }

    $scope.removeService = function (key) {
        $scope.services.splice(key, 1);
    }

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
                }
                reader.readAsDataURL(changeEvent.target.files[0]);
            });
        }
    }
}]);
