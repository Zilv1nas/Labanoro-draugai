app.filter('statesFilter', [function () {

    return function (input) {
        switch (input) {
            case 'COMPLETED':
                return 'Įvykdytas';
            case 'PENDING':
                return 'Laukiama';
            case 'DENIED':
                return 'Atmestas';
            default:
                break;
        }
    };

}]);
