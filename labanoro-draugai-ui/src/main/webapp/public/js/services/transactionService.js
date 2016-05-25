app.service('transactionService', ['$http', function ($http) {
    var baseUrl = 'rest/purchases/';

    this.createPurchase = function (amount) {
        return $http.post(baseUrl + 'createPurchase', amount);
    };

    this.sendPoints = function (id, amount) {
        return $http.post(baseUrl + 'sendPoints/' + id, amount);
    };

    this.payAnnualPayment = function () {
        return $http.get(baseUrl + 'payAnnualPayment');
    };

    this.getAllPurchases = function () {
        return $http.get(baseUrl + 'getAllPurchases')
            .then(function (result) {
                return result.data;
            }).catch(function (response) {
                console.log(response);
            });
    };

    this.confirmPurchase = function (purchaseId) {
        return $http.post(baseUrl + "confirmPurchase", purchaseId);
    };

    this.rejectPurchase = function (purchaseId) {
        return $http.post(baseUrl + "rejectPurchase", purchaseId);
    };

    this.getAnnualPaymentSize = function () {
        return $http.get(baseUrl + 'getAnnualPaymentSize')
            .then(function (result) {
                return result.data;
            }).catch(function (response) {
                console.log(response);
            });
    };

}]);
