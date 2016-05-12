/**
 * Created by Žilvinas on 2016-03-11.
 */
$.material.init();
var app = angular.module('labanoroDraugaiApp', ['ui.router', 'satellizer', 'angular-loading-bar', 'ui.bootstrap', 'angular-growl']);

app.config(['$locationProvider', '$stateProvider', '$urlRouterProvider', '$httpProvider', '$authProvider', 'cfpLoadingBarProvider', 'growlProvider',
    function ($locationProvider, $stateProvider, $urlRouterProvider, $httpProvider, $authProvider, cfpLoadingBarProvider, growlProvider) {

        growlProvider.globalTimeToLive(5000);
        growlProvider.globalPosition('top-right');

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
                templateUrl: 'partials/membersListView.html',
                resolve: {
                    members: function (membersService) {
                        console.log(membersService.getAllMembers());
                        return membersService.getAllMembers();
                    }
                }
            })
            .state('profile', {
                url: "/profile",
                controller: 'profileController',
                templateUrl: 'partials/profileView.html'
            })
            .state('memberProfile', {
                url: "/profile/:memberId",
                controller: 'profileController',
                templateUrl: 'partials/profileView.html'
            })
            .state('residencesList', {
                url: "/residencesList",
                controller: 'residencesListController',
                templateUrl: 'partials/residencesListView.html',
                resolve: {
                     residences: function (residencesService) {
                         return residencesService.getAllResidences();
                     }
                 }
            })
            .state('createResidence', {
                url: "/admin/createResidence",
                controller: 'createResidenceController',
                templateUrl: 'partials/admin/createResidenceView.html'
            })
            .state('editResidence', {
                url: "/admin/editResidence",
                controller: 'editResidenceController',
                templateUrl: 'partials/admin/editResidenceView.html',
                // resolve: {
                //     residence: function (residencesService, $stateParams) {
                //         return residencesService.getResidence($stateParams.id);
                //     }
                // }
            })
            .state('adminResidencesList', {
                url: "/admin/residencesLists",
                controller: 'adminResidencesListController',
                templateUrl: 'partials/admin/residencesListView.html',
                resolve: {
                    residences: function (residencesService) {
                        return residencesService.getAllResidences();
                    }
                }
            })
            .state('adminMembersList', {
                url: "/admin/membersList",
                controller: 'adminMembersListController',
                templateUrl: 'partials/admin/membersListView.html',
                resolve: {
                    members: function (membersService) {
                        return membersService.getAllMembers();
                    }
                }
            })
            .state('purchasesList', {
                url: "/admin/purchasesList",
                controller: 'purchasesConfirmationController',
                templateUrl: 'partials/admin/purchasesConfirmationView.html',
                resolve: {
                    purchases: function (transactionService) {
                        return transactionService.getAllPurchases();
                    }
                }
            })
            .state('settings', {
                url: "/admin/settings",
                controller: 'settingsController',
                templateUrl: 'partials/admin/settingsView.html',
                // resolve: {
                //     settings: function (membersService) {
                //         return settingsService.getSettings();
                //     }
                // }
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