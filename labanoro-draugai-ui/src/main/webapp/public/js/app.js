/**
 * Created by Žilvinas on 2016-03-11.
 */
$.material.init();
var app = angular.module('labanoroDraugaiApp', ['ui.router', 'satellizer', 'angular-loading-bar', 'ui.bootstrap']);

app.config(['$locationProvider', '$stateProvider', '$urlRouterProvider', '$httpProvider', '$authProvider', 'cfpLoadingBarProvider',
    function ($locationProvider, $stateProvider, $urlRouterProvider, $httpProvider, $authProvider, cfpLoadingBarProvider) {

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
            })
            .state('createResidence', {
                url: "/admin/createResidence",
                controller: 'createResidenceController',
                templateUrl: 'partials/admin/createResidenceView.html'
            })
            .state('editResidence', {
                url: "/admin/editResidence",
                controller: 'editResidenceController',
                templateUrl: 'partials/admin/editResidenceView.html'
            })
            .state('adminResidencesList', {
                url: "/residencesLists",
                controller: 'adminResidencesListController',
                templateUrl: 'partials/admin/residencesListView.html'
            });

        $locationProvider.html5Mode({
            enabled: true
        });

        cfpLoadingBarProvider.includeSpinner = false;
        $authProvider.loginUrl = 'rest/login';
        $authProvider.signupUrl = 'rest/register';
        $authProvider.baseUrl = $('base').attr('href');
        $authProvider.tokenPrefix = 'labanoro_draugai';
        $authProvider.facebook({
            clientId: '1538319626473322',
            url: 'rest/register/facebook'
        });
    }]);