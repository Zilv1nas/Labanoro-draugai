/**
 * Created by Sebastianas.Malinaus on 15/5/2016.
 */
app.controller('datePickerController', function($scope) {
    // Disable weekend selection
    var daysMap = {
        monday: 1,
        sunday: 0
    };
    var minDate = new Date();
    var maxDate;

    var extraOptions = $scope.$parent.datePickerOptions;
    if(angular.isDefined(extraOptions)){
        if(angular.isDefined(extraOptions.availability.dateFrom)){
            var minFromOptions = new Date(extraOptions.availability.dateFrom);
            minDate = new Date < minFromOptions ? minFromOptions : new Date();
        }
        if(angular.isDefined(extraOptions.availability.dateTo)){
            maxDate = new Date(extraOptions.availability.dateTo);
        }
    }

    function disabled(data) {
        var date = data.date,
            mode = data.mode;
        return mode === 'day' && (date.getDay() !== daysMap[$scope.dayFilter]);
    }

    $scope.inlineOptions = {
        dateDisabled: disabled,
        startingDay: 1,
        minDate: minDate,
        maxDate: maxDate,
        showWeeks: false
    };

    $scope.open = function () {
        this.popup.opened = true;
    };

    $scope.popup = {
        opened: false
    };
});