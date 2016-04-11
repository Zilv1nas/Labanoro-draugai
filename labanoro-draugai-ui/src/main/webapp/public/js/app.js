/**
 * Created by Žilvinas on 2016-03-11.
 */
$.material.init();
var app = angular.module('labanoroDraugaiApp', ['ngRoute']);

app.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    $routeProvider
        .when('/',
            {
                controller: 'mainController',
                templateUrl: 'partials/mainView.html'
            })
        .when('/login',
            {
                controller: 'loginController',
                templateUrl: 'partials/loginView.html'
            })
        .when('/register',
            {
                controller: 'registrationController',
                templateUrl: 'partials/registrationView.html'
            })
        .otherwise({redirectTo: '/'});

    $locationProvider.html5Mode({
        enabled: true
    });
}]);