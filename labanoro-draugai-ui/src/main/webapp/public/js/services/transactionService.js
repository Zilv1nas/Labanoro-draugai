app.service('transactionService', ['$http', function ($http) {
    var baseUrl = 'rest/purchases';

    this.createPurchase = function(amount) {
        return $http.post(baseUrl + "residences/getAll", amount);
    };
    
}]);
