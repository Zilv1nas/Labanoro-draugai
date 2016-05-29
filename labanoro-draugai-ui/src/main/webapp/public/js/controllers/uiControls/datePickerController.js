/**
 * Created by Sebastianas.Malinaus on 15/5/2016.
 */
app.controller('datePickerController', function($scope) {
    // Disable weekend selection
    var daysMap = {
        monday: 1,
        sunday: 0
    };
    
    $scope.inlineOptions = {
        dateDisabled: disabled,
        startingDay: 1,
        showWeeks: false,
        mindate: new Date()
    };

    // TODO cleanup spagetti
    var extraOptions = $scope.$parent.datePickerOptions;
    if(angular.isDefined(extraOptions)){
        if(angular.isDefined(extraOptions.availability.dateFrom)){
            var minFromOptions = new Date(extraOptions.availability.dateFrom);
            $scope.inlineOptions.minDate = new Date < minFromOptions ? minFromOptions : new Date();
        }
        if(angular.isDefined(extraOptions.availability.dateTo)){
            $scope.inlineOptions.maxDate = new Date(extraOptions.availability.dateTo);
        }
    }

    // nusimusa laikai ir timezones ir kiti dalykeliai (cheap)
    function shortDateString(date){
        return JSON.stringify({
            year: date.getYear(),
            month: date.getMonth(),
            day: date.getDate()
        });
    }

    function disabled(data) {
        var available;
        var date = data.date,
            mode = data.mode;

        if(angular.isDefined(extraOptions) && angular.isDefined(extraOptions.unavailableRanges)){
            available = true;
            angular.forEach(extraOptions.unavailableRanges, function(value, key){
                if(available){
                    var dateFrom = new Date(value.dateFrom);
                    var dateTo = new Date(value.dateTo);
                    var dateString = shortDateString(date);
                    var dateFromString = shortDateString(dateFrom);
                    var dateToString = shortDateString(dateTo);

                    if((date > dateFrom || dateString === dateFromString) && (date < dateTo || dateString === dateToString)){
                        available = false;
                    }
                }
            });
        }
        if(angular.isDefined(available)){
            return mode === 'day' && (!available || (date.getDay() !== daysMap[$scope.dayFilter]));
        }
        else{
            return mode === 'day' && date.getDay() !== daysMap[$scope.dayFilter];
        }
    }

    $scope.open = function () {
        this.popup.opened = true;
    };

    $scope.popup = {
        opened: false
    };
});