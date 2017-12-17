(function(){

    var app = angular.module('edit', []);

    app.controller('edit_forms', function($scope, $http) {
        var params = UrlUtil.parse(angular.element('#loader').attr('src'));
        $scope.entity = { };

        if (parseInt(params.id)) {
            $http.get(
                '/api/block/' + params.id,
                {
                    params: {
                        fields: 'id,title,code'
                    }
                }
            )
                .then(function(response){
                    if (response.data.result) {
                        $scope.entity = response.data.result;
                    } else {
                        showErrorMessage(response.data.error);
                        redirectAfterFreeze('/admin/blocks/all');
                    }
                })
        } else {
            showErrorMessage('blocks can not be created');
            redirectAfterFreeze('/admin/blocks/all');
        }

        save($scope, $http);
    });

    function save($scope, $http) {
        $scope.save = function() {
            $http.post('/api/block/', JSON.stringify($scope.entity), {headers: HEADERS})
                .then(function (response) {
                    if (response.data.result) {
                        showSuccessMessage('saved');
                    } else {
                        showErrorMessage(response.data.error);
                    }
                });
        };
    }
})();