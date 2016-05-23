app.service('annualPaymentInterceptor', ['$q', '$injector', function ($q, $injector) {
    this.responseError = function error(response) {
            if (response.status == 402) {
                var $state = $injector.get('$state');
                var growl = $injector.get('growl');
                $state.go('profile');
                growl.error("Norėdami toliau naudotis klubo paslaugomis, privalote sumokėti metinį mokestį!");
                return response;
            }

            return $q.reject(response);
        }
}
]);
