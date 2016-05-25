/**
 * Created by Žilvinas on 2016-03-11.
 */
$.material.init();
var app = angular.module('labanoroDraugaiApp', ['ui.router', 'ui.select', 'satellizer', 'angular-loading-bar', 'ui.bootstrap', 'angular-growl'])
    .filter('byFullName', function () {
        return function (name, surname) {
            var items = {
                genres: genres,
                out: []
            };
            angular.forEach(movies, function (value, key) {
                if (this.genres[value.genre] === true) {
                    this.out.push(value);
                }
            }, items);
            return items.out;
        };
    });

app.config(['$locationProvider', '$stateProvider', '$urlRouterProvider', '$httpProvider', '$authProvider', 'cfpLoadingBarProvider', 'growlProvider',
    function ($locationProvider, $stateProvider, $urlRouterProvider, $httpProvider, $authProvider, cfpLoadingBarProvider, growlProvider) {

        growlProvider.globalTimeToLive(5000);
        growlProvider.globalDisableCountDown(true);
        growlProvider.globalPosition('top-right');

        $urlRouterProvider.otherwise("/");

        var memberUrlPrefix = 'partials/member';
        var adminUrlPrefix = 'partials/admin';
        $stateProvider
            .state('main', {
                url: "/",
                controller: 'mainController',
                templateUrl: memberUrlPrefix + '/mainView.html'
            })
            .state('login', {
                url: "/login",
                controller: 'loginController',
                templateUrl: memberUrlPrefix + '/loginView.html'
            })
            .state('register', {
                url: "/register",
                controller: 'registrationController',
                templateUrl: memberUrlPrefix + '/registrationView.html'
            })
            .state('membersList', {
                url: "/membersList",
                controller: 'membersListController',
                templateUrl: memberUrlPrefix + '/membersListView.html',
                resolve: {
                    members: function (membersService) {
                        return membersService.getAllMembers();
                    }
                }
            })
            .state('profile', {
                url: "/profile",
                controller: 'profileController',
                templateUrl: memberUrlPrefix + '/profileView.html'
            })
            .state('memberProfile', {
                url: "/profile/:memberId",
                controller: 'profileController',
                templateUrl: memberUrlPrefix + '/profileView.html'
            })
            .state('residencesList', {
                url: "/residencesList",
                controller: 'residencesListController',
                templateUrl: memberUrlPrefix + '/residencesListView.html',
                resolve: {
                    residences: function (residencesService) {
                        return residencesService.getAllResidences();
                    }
                }
            })
            .state('viewResidence', {
                url: "/residence/:id",
                controller: 'viewResidenceController',
                templateUrl: memberUrlPrefix + '/residenceView.html',
                resolve: {
                    residence: function (residencesService, $stateParams) {
                        return residencesService.getResidence($stateParams.id);
                    },
                    residenceHistory: function (residencesService, $stateParams) {
                        return residencesService.getResidenceHistory($stateParams.id);
                    }
                }
            })
            .state('createResidence', {
                url: "/admin/createResidence",
                controller: 'createResidenceController',
                templateUrl: adminUrlPrefix + '/residenceFormView.html'
            })
            .state('editResidence', {
                url: "/admin/editResidence/:id",
                controller: 'editResidenceController',
                templateUrl: adminUrlPrefix + '/residenceFormView.html',
                resolve: {
                    residence: function (residencesService, $stateParams) {
                        return residencesService.getResidence($stateParams.id);
                    }
                }
            })
            .state('adminResidencesList', {
                url: "/admin/residencesLists",
                controller: 'adminResidencesListController',
                templateUrl: adminUrlPrefix + '/residencesListView.html',
                resolve: {
                    residences: function (residencesService) {
                        return residencesService.getAllResidences();
                    }
                }
            })
            .state('adminMembersList', {
                url: "/admin/membersList",
                controller: 'adminMembersListController',
                templateUrl: adminUrlPrefix + '/membersListView.html',
                resolve: {
                    members: function (membersService) {
                        return membersService.getAllMembers();
                    }
                }
            })
            .state('purchasesList', {
                url: "/admin/purchasesList",
                controller: 'purchasesConfirmationController',
                templateUrl: adminUrlPrefix + '/purchasesConfirmationView.html',
                resolve: {
                    purchases: function (transactionService) {
                        return transactionService.getAllPurchases();
                    }
                }
            })
            .state('settings', {
                url: "/admin/settings",
                controller: 'settingsController',
                templateUrl: adminUrlPrefix + '/settingsView.html',
                resolve: {
                    settings: function (settingsService) {
                        return settingsService.getAllSettings();
                    }
                }
            })
            .state('reservationsList', {
                url: "/reservationsList",
                controller: 'reservationsListController',
                templateUrl: memberUrlPrefix + '/reservationsListView.html',
                resolve: {
                    reservations: function (residencesService) {
                        return residencesService.getUserReservationHistory();
                    }
                }
            });

        $locationProvider.html5Mode({
            enabled: true
        });

        $httpProvider.interceptors.push('annualPaymentInterceptor');

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