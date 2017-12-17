(function(){

    const LIMIT = 20;
    var RESTRICTION = {
        limit: LIMIT
    };

    var app = angular.module('all', []);

    app.controller('all', function($scope, $http){
        $scope.filters = {};
        $scope.number_on_page = 0;
        $scope.total_count = 0;

        count($scope, $http);
        get($scope, $http);

        $scope.moveToPage = function() {
            RESTRICTION.offset = LIMIT * $('#pages').val() || 0;

            get($scope, $http);
        };

        $scope.filterForm = function(){
            RESTRICTION = $scope.filters;
            RESTRICTION.limit = LIMIT;

            get($scope, $http);
            count($scope, $http);
        };

    });

    function get($scope, $http) {
        $scope.entities = [];
        $http.get(
            '/api/email/template/',
            {
                params: {
                    fields: 'id,subject',
                    restrict: JSON.stringify(RESTRICTION)
                }
            }
        )
            .then(function(response){
                if (response.data.result) {
                    $scope.entities = response.data.result;

                    $scope.number_on_page = $scope.entities.length;
                } else {
                    showErrorMessage(response.data.error);

                    $scope.number_on_page = 0;
                }
            })
    }

    function count($scope, $http) {
        $http.get(
            '/api/email/template/count',
            {
                params: {
                    restrict: JSON.stringify(RESTRICTION)
                }
            }
        )
            .then(function(response){
                var numberOfUsers = response.data.result || 0;

                $scope.total_count = numberOfUsers;
                formPages(numberOfUsers, LIMIT, $('#pages'));
            })
    }
})();