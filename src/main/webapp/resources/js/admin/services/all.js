(function(){
    var RESTRICTION = {offset: 0, limit: 0};

    var app = angular.module('all', []);

    app.controller('all', function($scope, $http){
        $scope.filters = {};
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

        $scope.delete = function(entity) {
            if (confirm('You really want to delete this service?')) {
                $http.delete('/api/service/'+entity.id, JSON.stringify($scope.entity), {headers: HEADERS})
                    .then(function (response) {
                        if (response.data.result) {
                            showSuccessMessage('deleted');

                            for (var i = 0; i < $scope.entities.length; ++i) {
                                if (entity.id === $scope.entities[i].id) {
                                    $scope.entities.splice(i, 1);

                                    break;
                                }
                            }

                        } else {
                            showErrorMessage(response.data.error);
                        }
                    });
            }
        }
    });

    function get($scope, $http) {
        $scope.entities = [];
        $http.get(
            '/api/service/',
            {
                params: {
                    fields: 'id,name,active,has_icon,position',
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
        $http.get(
            '/api/service/count',
            {
                params: {
                    restrict: JSON.stringify(RESTRICTION)
                }
            }
        )
            .then(function(response){
                var numberOfUsers = response.data.result || 0;

                $scope.total_count = numberOfUsers;
            })
    }

    function swap(i, j, $scope, $http) {
        var a = $scope.entities[i],
            b = $scope.entities[j];

        $http.post('/api/service/swap', JSON.stringify({ids: [a.id, b.id]}), {headers: HEADERS})
            .then(function (response) {
                if (response.data.result) {
                    var temp = a.position;
                    a.position = b.position;
                    b.position = temp;

                    $scope.entities.sort(function(a, b){
                        return a.position - b.position;
                    });
                } else {
                    showErrorMessage(response.data.error);
                }
            });
    }
})();