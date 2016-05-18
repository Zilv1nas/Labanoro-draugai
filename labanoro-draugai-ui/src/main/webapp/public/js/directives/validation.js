app.directive("validation", [function () {
    return {
        restrict: "E",
        templateUrl: 'partials/directives/validationView.html',
        link: function (scope, element, attributes) {
            scope.$watch('errorMessages', function (newValue, oldValue) {
                scope.errors = [];
                angular.forEach(scope.errorMessages, function (value, key) {
                    if (value.key === attributes.key) {
                        scope.errors.push(value);
                        element.parent().parent().addClass('has-error');
                    }
                });
                console.log(scope.errors);
            });

        }
    }
}]);
