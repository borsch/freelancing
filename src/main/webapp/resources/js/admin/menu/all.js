(function(){
    var RESTRICTION = {
        offset: 0,
        limit: 0 // select all rows
    };

    var app = angular.module('all', []);

    app.controller('all', function($scope, $http){
        $scope.number_on_page = 0;
        $scope.total_count = 0;

        count($scope, $http);
        get($scope, $http);
    });

    function get($scope, $http) {
        $scope.entities = [];
        $http.get(
            '/api/menu/',
            {
                params: {
                    fields: 'id,name',
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
        $http.get('/api/menu/count')
            .then(function(response){
                var number = response.data.result || 0;

                $scope.total_count = number;
            })
    }
})();