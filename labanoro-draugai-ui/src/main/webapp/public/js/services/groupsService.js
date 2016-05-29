app.service('groupsService', ['$http', function ($http) {
    var baseUrl = 'rest/groups/';

    this.getGroupSettings = function () {
        return $http.get(baseUrl + 'getGroupSettings')
            .then(function (response) {
                return response.data;
            }).catch(function (response) {
                console.log(response);
            });
    };


    this.update = function (groupSettings) {
        return $http.post(baseUrl + "updateGroups", groupSettings);
    }

    this.getUserPriority = function(){
        return $http.get(baseUrl + "getUserPriority")
                .then(function (response) {
                        return response.data;
                    }).catch(function (response) {
                        console.log(response);
                    });
    }

}]);
