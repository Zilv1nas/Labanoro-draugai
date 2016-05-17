app.directive("validation", [function () {
    return {
        scope: {
            errorMessages: "="
        },
        link: function (scope, element, attributes) {
            console.log(scope.errorMessages);
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
