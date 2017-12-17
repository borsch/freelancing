(function(){
    var app = angular.module('all', []);

    app.controller('all', function($scope, $http){
        $scope.number_on_page = 0;

        count($scope, $http);
        get($scope, $http);

        $scope.deleteCarouselImage = function(carousel_image) {
            if (confirm('You really want to delete this carousel image?')) {
                $http.delete('/api/carousel_image/' + carousel_image.id, JSON.stringify($scope.entity), {headers: HEADERS})
                    .then(function (response) {
                        if (response.data.result) {
                            showSuccessMessage('Deleted');

                            count($scope, $http);
                            get($scope, $http);
                        } else {
                            showErrorMessage(response.data.error);
                        }
                    });
            }
        };
    });

    function get($scope, $http) {
        $scope.entities = [];
        $http.get(
            '/api/carousel_image/',
            {
                params: {
                    fields: 'id,description,has_image',
                    restrict: JSON.stringify({limit: 0})
                }
            }
        )
            .then(function(response){
                if (response.data.result) {
                    $scope.entities = response.data.result;

                    $scope.entities.forEach(function(e){
                        e.icon = '/api/file/' + e.id + '?type=CAROUSEL_IMAGE';
                    });
                } else {
                    showErrorMessage(response.data.error);

                    $scope.number_on_page = 0;
                }
            })
    }

    function count($scope, $http) {
        $http.get(
            '/api/carousel_image/count'
        )
            .then(function(response){
                var numberOfUsers = response.data.result || 0;

                $scope.total_count = numberOfUsers;
            })
    }
})();