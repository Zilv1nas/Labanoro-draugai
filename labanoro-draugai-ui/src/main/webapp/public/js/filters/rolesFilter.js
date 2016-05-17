app.filter('rolesFilter', [function () {

    return function (input) {
        switch (input) {
            case 'MEMBER':
                return 'Narys';
            case 'ADMIN':
                return 'Administratorius';
            case 'CANDIDATE':
                return 'Kandidatas';
            default:
                break;
        }
    };

}]);
