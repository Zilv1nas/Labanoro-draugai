/**
 * Created by Sebastianas.Malinaus on 15/5/2016.
 */
app.controller('datePickerController', function($scope) {
    // Disable weekend selection
    function disabled(data) {
        var date = data.date,
            mode = data.mode;
        return mode === 'day' && (date.getDay() !== 0 && date.getDay() !== 1);
    }

    $scope.inlineOptions = {
        dateDisabled: disabled,
        startingDay: 1,
        minDate: new Date(),
        showWeeks: false
    };

    $scope.open = function () {
        this.popup.opened = true;
    };

    $scope.popup = {
        opened: false
    };
});