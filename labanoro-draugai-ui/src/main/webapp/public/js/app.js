/**
 * Created by Žilvinas on 2016-03-11.
 */
var app = angular.module('labanoroDraugaiApp', ['ngRoute']);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/',
        {
            controller: 'mainController',
            templateUrl: 'partials/mainView.html'
        }).otherwise({redirectTo: '/'});
}]);