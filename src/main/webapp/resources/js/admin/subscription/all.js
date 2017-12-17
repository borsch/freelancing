(function(){
    var popup_template = new EJS({url: '/resources/template/admin/subscription/add.ejs'});
    const LIMIT = 50;

    var RESTRICTION = {
        offset: 0,
        limit: LIMIT
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
            RESTRICTION = $scope.filters || {};
            RESTRICTION.offset = 0;

            get($scope, $http);
            count($scope, $http);
        };

        $scope.deleteSubscription = function(entity) {
            $http.delete('/api/subscription/' + entity.id,
                {headers: HEADERS})
                .then(function(response){
                    if (response.data.result) {
                        var index = $scope.entities.indexOf(entity);
                        if (index > -1) {
                            $scope.entities.splice(index, 1);
                            --$scope.number_on_page;
                            --$scope.total_count;
                        }
                    } else {
                        showErrorMessage(response.data.error);
                    }
                });
        };

        $scope.addSubscription = function() {
            var $popup = $(popup_template.render({}));

            $popup.find('.close').click(function(){
                $popup.remove();
            });

            $popup.find('form').submit(function(e){
                e.preventDefault();

                var $email = $(this).find('[name=email]');

                $http.put(
                    '/api/blog/category/',
                    JSON.stringify({email: $email.val()}),
                    { headers: HEADERS }
                )
                    .then(function(response){
                        if (response.data.result) {
                            $popup.remove();

                            showSuccessMessage('added');
                            $scope.filterForm();
                        } else {
                            showErrorMessage(response.data.error);
                        }
                    });
            });

            $('body').append($popup);
        }
    });

    function get($scope, $http) {
        $scope.entities = [];
        $http.get(
            '/api/subscription/',
            {
                params: {
                    fields: 'id,email',
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
            '/api/subscription/count',
            {
                params: {
                    restrict: JSON.stringify(RESTRICTION)
                }
            }
        )
            .then(function(response){
                var number = response.data.result || 0;

                $scope.total_count = number;
            })
    }
})();