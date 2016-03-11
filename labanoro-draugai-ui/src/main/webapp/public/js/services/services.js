/**
 * Created by Žilvinas on 2016-03-11.
 */
angular.module('labanoroDraugaiApp').service('userService', ['$http', function ($http) {
    var baseUrl = 'rest/';

    this.test = function() { //TODO remove this
        return $http.get(baseUrl);
    };
}]);
