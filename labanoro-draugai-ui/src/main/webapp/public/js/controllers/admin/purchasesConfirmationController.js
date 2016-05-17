app.controller('purchasesConfirmationController', ['$scope', 'purchases', 'transactionService', 'growl', '$state', '$uibModal', function ($scope, purchases, transactionService, growl, $state, $uibModal) {

    $scope.purchases = purchases;
    console.log(purchases);

    $scope.confirmPurchase = function (id) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'partials/modals/confirmModal.html',
            controller: 'confirmController',
            size: 'md',
            resolve: {
                message: function () {
                    return 'Ar tikrai norite patvirtinti mokėjimą?';
                }
            }
        });

        modalInstance.result.then(function () {
            transactionService.confirmPurchase(id)
                .then(function (response) {
                    growl.success('Mokėjimas patvirtintas sėkmingai!');
                    $state.go('purchasesList', {}, { reload: 'purchasesList' });
                }).catch(function (response) {
                    console.log(response);
                    growl.error('Nepavyko patvirtinti mokėjimo!');
                })
        });
    };

    $scope.rejectPurchase = function (id) {
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'partials/modals/confirmModal.html',
            controller: 'confirmController',
            size: 'md',
            resolve: {
                message: function () {
                    return 'Ar tikrai norite atmesti mokėjimą?';
                }
            }
        });

        modalInstance.result.then(function () {
            transactionService.rejectPurchase(id)
                .then(function (response) {
                    growl.success('Mokėjimas atmestas sėkmingai!');
                    $state.go('purchasesList', {}, { reload: 'purchasesList' });
                }).catch(function (response) {
                    console.log(response);
                    growl.error('Nepavyko atmesti mokėjimo!');
                })
        });
    }

}]);