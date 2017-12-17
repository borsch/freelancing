(function(){

    const LIMIT = 20;
    var OFFSET = 0;
    var RESTRICTION = {};

    var app = angular.module('all', []);

    app.controller('all', function($scope, $http){
        $scope.filters = {};
        $scope.number_on_page = 0;
        $scope.total_count = 0;


        buildRestriction($scope);
        count($scope, $http);
        get($scope, $http);

        $scope.moveToPage = function() {
            OFFSET = LIMIT * $('#pages').val() || 0;

            RESTRICTION.offset = OFFSET;

            get($scope, $http);
        };

        $scope.filterForm = function(){
            OFFSET = 0;
            buildRestriction($scope);
            get($scope, $http);
            count($scope, $http);
        };

    });

    function get($scope, $http) {
        $scope.entities = [];
        $http.get(
            '/api/employee/',
            {
                params: {
                    fields: 'id,name,position,email,phone,has_image',
                    restrict: JSON.stringify(RESTRICTION)
                }
            }
        )
            .then(function(response){
                if (response.data.result) {
                    $scope.entities = response.data.result;

                    $scope.entities.forEach(function(e){
                        e.icon = '/api/file/' + e.id + '?type=EMPLOYEE';
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
            '/api/employee/count',
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

    /**
     * use this to temporary save filters
     *
     * @param $scope - angular app scope
     */
    function buildRestriction($scope) {
        RESTRICTION = $scope.filters;

        RESTRICTION.offset = OFFSET;
        RESTRICTION.limit = LIMIT;
    }
})();