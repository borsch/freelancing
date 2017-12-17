(function(){

    const LIMIT = 20;
    var OFFSET = 0;
    var RESTRICTION = {};

    var app = angular.module('all', ['ngSanitize']);

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
            '/api/testimonial/',
            {
                params: {
                    fields: 'id,name,text,active,position',
                    restrict: JSON.stringify(RESTRICTION)
                }
            }
        )
            .then(function(response){
                if (response.data.result) {
                    $scope.entities = response.data.result;

                    $scope.entities.forEach(function(e){

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
            '/api/testimonial/count',
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

        // Gson on server side transfer empty string to 'false'
        // TODO: find a better solution
        if (!RESTRICTION.active) {
            delete RESTRICTION.active;
        }

        RESTRICTION.offset = OFFSET;
        RESTRICTION.limit = LIMIT;
    }
})();