app.service('errorInterceptor', ['$q', '$injector', function ($q, $injector) {
    this.responseError = function error(response) {
            if (response.status == 402) {
                var $state = $injector.get('$state');
                var growl = $injector.get('growl');
                $state.go('profile');
                growl.error("Norėdami toliau naudotis klubo paslaugomis, privalote sumokėti metinį mokestį!");
                return response;
            } else if (response.status == 401) {
                $injector.get('$state').go("login");
                return response;
            }

            return $q.reject(response);
        }
}
]);
