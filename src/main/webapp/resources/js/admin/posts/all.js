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

        $scope.format = function(date) {
            return new Date(date).format('d MMMM yyyy HH:mm:ss');
        };

        $scope.delete = function(entity) {
            if (confirm('You really want to delete this post?')) {
                $http.delete('/api/blog/post/'+entity.id, {headers: HEADERS})
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
            '/api/blog/post/',
            {
                params: {
                    fields: 'id,title,category,has_img,date',
                    restrict: JSON.stringify(RESTRICTION)
                }
            }
        )
            .then(function(response){
                if (response.data.result) {
                    $scope.entities = response.data.result;
                    $scope.entities.forEach(function(e){
                        loadCategory(e, $http);
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
            '/api/blog/post/count',
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

    function loadCategory(entity, $http) {
        if (entity.category) {
            $http.get('/api/blog/category/' + entity.category + '?fields=name')
                .then(function(response){
                    if (response.data.result) {
                        entity.category = response.data.result.name;
                    } else {
                        entity.category = '-';
                    }
                });
        }
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

        if (!RESTRICTION.has_img)
            delete RESTRICTION.has_img;

        if (!RESTRICTION.category)
            delete RESTRICTION.category;
    }
})();