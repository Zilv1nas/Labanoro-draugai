app.factory('authHttpRequestInterceptor', ['$injector', function ($injector, authService) {
    return {
        request: function ($request) {
            var authService = $injector.get('authService');
            if (authService.isAuthenticated()) {
                $request.headers['Authorization'] = 'Bearer ' + authService.getAuthData().token;
            }

            return $request;
        }
    };
}]);