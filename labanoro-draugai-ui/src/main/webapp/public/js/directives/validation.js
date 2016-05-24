app.directive("validation", [function () {
    return {
        restrict: "E",
        replace: true,
        scope: {
            errorMessages: "=errors"
        },
        templateUrl: 'partials/directives/validationView.html',
        link: function (scope, element, attributes) {
            var parentElement = angular.element(element.parent().parent());
            scope.$watch('errorMessages', function (newValue, oldValue) {
                scope.errors = [];
                angular.forEach(scope.errorMessages, function (value, key) {
                    if (value.key === attributes.key) {
                        scope.errors.push(value);
                        if (parentElement !== undefined) {
                            parentElement.addClass("has-error");
                        }
                    }
                });
            });
        }
    }
}]);
