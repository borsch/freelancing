(function(){
    const LIMIT = 10;
    var RESTRICTION = {
        offset: 0,
        limit: LIMIT // select all rows
    };

    var app = angular.module('all', []);

    app.controller('all', function($scope, $http){
        $scope.number_on_page = 0;
        $scope.total_count = 0;

        count($scope, $http);
        get($scope, $http);

        $scope.moveToPage = function() {
            RESTRICTION.offset = LIMIT * $('#pages').val() || 0;

            get($scope, $http);
        };

        $scope.filterForm = function(){
            buildRestriction($scope);
            RESTRICTION.limit = LIMIT;

            get($scope, $http);
            count($scope, $http);
        };

        setStatus($scope, $http);
    });

    function get($scope, $http) {
        $scope.entities = [];
        $http.get(
            '/api/request/',
            {
                params: {
                    fields: 'id,user_id,company_name,status,industry_name,interests_name,interest_alter',
                    restrict: JSON.stringify(RESTRICTION)
                }
            }
        )
            .then(function(response){
                if (response.data.result) {
                    $scope.entities = response.data.result;
                    $scope.entities.forEach(function(e){
                        loadUser(e, $http);
                        e.new_status = e.status;
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
            '/api/request/count',
            {
                params: {
                    restrict: JSON.stringify(RESTRICTION)
                }
            }
        )
            .then(function(response){
                var number = response.data.result || 0;

                $scope.total_count = number;
                formPages(number, LIMIT, $('#pages'));
            })
    }

    function setStatus($scope, $http) {
        $scope.setStatus = function(entity) {
            $http.post('/api/request/status/',
                JSON.stringify({id: entity.id, status: entity.new_status}),
                {headers: HEADERS})
                .then(function (response) {
                    if (response.data.result) {
                        showSuccessMessage('saved');
                        entity.status = entity.new_status;
                    } else {
                        showErrorMessage(response.data.error);
                    }
                });
        }
    }

    function loadUser(entity, $http) {
        $http.get(
            '/api/users/' + entity.user_id,
            {
                params: {
                    fields: 'email,first_name,last_name'
                }
            }
        )
            .then(function(response){
                var user = response.data.result;
                if (user) {
                    entity.user_full = user.first_name + ' ' + user.last_name + ', ' + user.email;
                } else {
                    entity.user_full = '-';
                }
            });
    }

    function buildRestriction($scope) {
        RESTRICTION = $scope.filters;

        if (!RESTRICTION.status)
            delete RESTRICTION.status;
    }
})();