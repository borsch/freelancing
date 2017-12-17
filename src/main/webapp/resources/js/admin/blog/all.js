(function(){
    var RESTRICTION = {
        offset: 0,
        limit: 0, // select all rows
        order_by: 'position'
    };

    var app = angular.module('all', []);

    app.controller('all', function($scope, $http){
        $scope.number_on_page = 0;
        $scope.total_count = 0;

        count($scope, $http);
        get($scope, $http);

        $scope.min = function(entity) {
            var min = true;
            $scope.entities.forEach(function(e){
                if (e.position < entity.position) {
                    min = false;
                }
            });
            return min;
        };

        $scope.max = function(entity) {
            var max = true;
            $scope.entities.forEach(function(e){
                if (e.position > entity.position) {
                    max = false;
                }
            });
            return max;
        };

        $scope.up = function(entity){
            var index = $scope.entities.indexOf(entity);
            if (index > 0) {
                swap(index, index-1, $scope, $http);
            }
        };

        $scope.down = function(entity){
            var index = $scope.entities.indexOf(entity);
            if (index > -1 && index < $scope.entities.length - 1) {
                swap(index, index+1, $scope, $http);
            }
        };
    });

    function get($scope, $http) {
        $scope.entities = [];
        $http.get(
            '/api/blog/category/',
            {
                params: {
                    fields: 'id,name,code,position',
                    restrict: JSON.stringify(RESTRICTION)
                }
            }
        )
            .then(function(response){
                if (response.data.result) {
                    $scope.entities = response.data.result;
                    $scope.entities.sort(function(a, b){
                        return a.position - b.position;
                    });

                    $scope.number_on_page = $scope.entities.length;
                } else {
                    showErrorMessage(response.data.error);

                    $scope.number_on_page = 0;
                }
            })
    }

    function count($scope, $http) {
        $http.get('/api/blog/category/count')
            .then(function(response){
                var number = response.data.result || 0;

                $scope.total_count = number;
            })
    }

    function swap(i, j, $scope, $http) {
        var a = $scope.entities[i],
            b = $scope.entities[j];

        $http.post('/api/blog/category/swap', JSON.stringify({ids: [a.id, b.id]}), {headers: HEADERS})
            .then(function (response) {
                if (response.data.result) {
                    showSuccessMessage('swapped');

                    get($scope, $http);
                } else {
                    showErrorMessage(response.data.error);
                }
            });
    }
})();