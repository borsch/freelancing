(function(){

    var app = angular.module('edit', []);

    app.controller('edit_forms', function($scope, $http) {
        var params = UrlUtil.parse(angular.element('#loader').attr('src'));
        $scope.entity = { };

        if (parseInt(params.id)) {
            $http.get(
                '/api/role/' + params.id,
                {
                    params: {
                        fields: 'id,name,permissions'
                    }
                }
            )
                .then(function(response){
                    if (response.data.result) {
                        $scope.entity = response.data.result;
                    } else {
                        showErrorMessage(response.data.error);
                        redirectAfterFreeze('/admin/roles/all');
                    }
                })
        } else {
            showErrorMessage('role can not be created');
            redirectAfterFreeze('/admin/roles/all');
        }

        $scope.deletePermission = function(permission) {
            $http.delete('/api/role/' + $scope.entity.id + '/permission?permission=' + permission.id,
                {headers: HEADERS})
                .then(function(response){
                    if (response.data.result) {
                        showSuccessMessage('removed');

                        reloadPermissions($scope, $http);
                    } else {
                        showErrorMessage(response.data.error);
                    }
                });
        };

        $scope.addPermission = function(){
            var newPermission = $scope.new_permission,
                added = false;

            $scope.entity.permissions.forEach(function(p){
                if (p.name === newPermission) {
                    added = true;
                }
            });

            if (added) {
                showErrorMessage('already added');

                return;
            }

            $http.put('/api/role/permission', JSON.stringify({
                id: $scope.entity.id,
                permission: newPermission
            }), {headers: HEADERS})
                .then(function(response){
                    if (response.data.result) {
                        showSuccessMessage('added');

                        reloadPermissions($scope, $http);
                    } else {
                        showErrorMessage(response.data.error);
                    }
                })
        };
    });

    function reloadPermissions($scope, $http) {
        $scope.entity.permissions = [];

        $http.get(
            '/api/role/' + $scope.entity.id,
            {
                params: {
                    fields: 'permissions'
                }
            }
        )
            .then(function(response){
                if (response.data.result) {
                    $scope.entity.permissions = response.data.result.permissions;
                }
            })
    }
})();