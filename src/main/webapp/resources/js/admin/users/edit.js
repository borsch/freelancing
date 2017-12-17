(function(){

    var app = angular.module('user_edit', []);

    app.controller('user_edit_forms', function($scope, $http) {
        var params = UrlUtil.parse(angular.element('#loader').attr('src'));
        $scope.user = {
            role: 'user'
        };

        if (parseInt(params.id)) {
            $http.get(
                '/api/users/' + params.id,
                {
                    params: {
                        fields: 'id,email,role,active,phone,last_name,first_name'
                    }
                }
            )
                .then(function(response){
                    if (response.data.result) {
                        $scope.user = response.data.result;
                    } else {
                        showErrorMessage(response.data.error);
                        redirectAfterFreeze('/admin/users/all');
                    }
                })
        } else {

        }

        $scope.saveUser = function() {
            saveUser($scope, $http);
        };

        $scope.updatePassword = function() {
            updatePassword($scope, $http);
        };
    });

    function saveUser($scope, $http) {
        var id = $scope.user.id;
        var method = (id ? $http.post : $http.put);

        method('/api/users/', JSON.stringify($scope.user), {headers: HEADERS})
            .then(function(response){
                if (response.data.result) {
                    showSuccessMessage('saved');
                    if (!id) {
                        redirectAfterFreeze('/admin/users/' + response.data.result + '/edit');
                    }
                } else {
                    showErrorMessage(response.data.error);
                }
            })
    }

    function updatePassword($scope, $http) {
        var id = $scope.user.id;
        var method = (id ? $http.post : $http.put);
        var password = md5($scope.user.password);

        method('/api/users/', JSON.stringify({id: id, password: password}), {headers: HEADERS})
            .then(function(response){
                if (response.data.result) {
                    showSuccessMessage('saved');
                    $scope.user.password = '';
                } else {
                    showErrorMessage(response.data.error);
                }
            })
    }

})();