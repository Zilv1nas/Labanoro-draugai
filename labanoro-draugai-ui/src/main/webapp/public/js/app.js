/**
 * Created by Žilvinas on 2016-03-11.
 */
$.material.init();
var app = angular.module('labanoroDraugaiApp', ['ngRoute', 'ui.router', 'smart-table']);

app.config(['$routeProvider', '$locationProvider', '$stateProvider', '$urlRouterProvider', function($routeProvider, $locationProvider, $stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise("/");


    $stateProvider
        .state('main', {
            url: "/main",
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


    $urlRouterProvider.otherwise("/main");

    $locationProvider.html5Mode({
        enabled: true
    });
}]);