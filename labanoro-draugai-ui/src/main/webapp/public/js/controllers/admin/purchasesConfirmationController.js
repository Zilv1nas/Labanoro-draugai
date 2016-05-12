app.controller('purchasesConfirmationController', ['$scope', 'purchases', 'transactionService', function ($scope, purchases, transactionService) {

    $scope.purchases = purchases;

    $scope.confirmPurchase = function (purchaseId) {
        transactionService.confirmPurchase(purchaseId)
            .then(function (response) {
                console.log(response);
                $state.go('main');
            }).catch(function (response) {
                console.log(response);
                //TODO error handling
            })
    }

    $scope.rejectPurchase = function (purchaseId) {
        transactionService.rejectPurchase(purchaseId)
            .then(function (response) {
                console.log(response);
                $state.go('main');
            }).catch(function (response) {
                console.log(response);
                //TODO error handling
            })
    }

}]);