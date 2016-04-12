/**
 * Created by Žilvinas on 2016-03-11.
 */
$.material.init();
var app = angular.module('labanoroDraugaiApp', ['ngRoute', 'ui.router']);

app.config(['$routeProvider', '$locationProvider', '$stateProvider', '$urlRouterProvider', function($routeProvider, $locationProvider, $stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise("/");


    $stateProvider
        .state('main', {
            url: "/main",
            controller: 'mainController as Ctrl',
            templateUrl: 'partials/mainView.html'
        })
        .state('login', {
            url: "/login",
            controller: 'loginController as Ctrl',
            templateUrl: 'partials/loginView.html'
        })
        .state('register', {
            url: "/register",
            controller: 'registrationController as Ctrl',
            templateUrl: 'partials/registrationView.html'
        })
        .state('membersList', {
            url: "/membersList",
            controller: 'membersListController as Ctrl',
            templateUrl: 'partials/membersListView.html'
        })
        .state('profile', {
            url: "/profile",
            controller: 'profileController as Ctrl',
            templateUrl: 'partials/profileView.html'
        })
        .state('residencesList', {
            url: "/residencesList",
            controller: 'residencesListController as Ctrl',
            templateUrl: 'partials/residencesListView.html'
        });


    $urlRouterProvider.otherwise("/main");

    $locationProvider.html5Mode({
        enabled: true
    });
}]);