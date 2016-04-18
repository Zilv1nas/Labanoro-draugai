/**
 * Created by Žilvinas on 2016-03-11.
 */
$.material.init();
var app = angular.module('labanoroDraugaiApp', ['ui.router', 'smart-table']);

app.config(['$locationProvider', '$stateProvider', '$urlRouterProvider', '$httpProvider', function($locationProvider, $stateProvider, $urlRouterProvider, $httpProvider) {

    $urlRouterProvider.otherwise("/");

    $stateProvider
        .state('main', {
            url: "/",
            controller: 'mainController',
            templateUrl: 'partials/mainView.html'
        })
        .state('login', {
            url: "/login",
            controller: 'loginController',
            templateUrl: 'partials/loginView.html'
        })
        .state('register', {
            url: "/register",
            controller: 'registrationController',
            templateUrl: 'partials/registrationView.html'
        })
        .state('membersList', {
            url: "/membersList",
            controller: 'membersListController',
            templateUrl: 'partials/membersListView.html'
        })
        .state('profile', {
            url: "/profile",
            controller: 'profileController',
            templateUrl: 'partials/profileView.html'
        })
        .state('residencesList', {
            url: "/residencesList",
            controller: 'residencesListController',
            templateUrl: 'partials/residencesListView.html'
        });

    $httpProvider.interceptors.push('authHttpRequestInterceptor');

    $locationProvider.html5Mode({
        enabled: true
    });
}]);