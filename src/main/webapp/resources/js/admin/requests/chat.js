(function(){

    var app = angular.module('chat', []);
    var current_user_id;

    var LIMIT = 20;
    var RESTRICTION = {
        order_direction: 'DESC',
        offset: 0,
        limit: LIMIT
    };

    app.controller('chat', function($scope, $http) {
        var params = UrlUtil.parse(angular.element('#loader').attr('src'));
        $scope.entity = { };
        $scope.comments = [];
        RESTRICTION.request_ids = [params.id];

        if (parseInt(params.id)) {
            $http.get(
                '/api/request/' + params.id,
                {
                    params: {
                        fields: 'id,user_id,company_name,status,industry_name,interests_name,interest_alter,has_file'
                    }
                }
            )
                .then(function(response){
                    if (response.data.result) {
                        $scope.entity = response.data.result;
                        $scope.entity.new_status = $scope.entity.status;

                        RESTRICTION.request_ids = [params.id];

                        loadUser($http, $scope.entity.user_id, function(user){
                            $scope.entity.user_full = user;
                        });
                    } else {
                        showErrorMessage(response.data.error);
                        redirectAfterFreeze('/admin/requests/all');
                    }
                })
        }

        $scope.moreComments = function() {
            RESTRICTION.offset += LIMIT;

            loadComments($scope, $http);
        };

        current_user_id = params.current_user_id;

        loadComments($scope, $http);
        setStatus($scope, $http);
        addComment($scope, $http);
    });

    function loadUser($http, user_id, callback) {
        $http.get(
            '/api/users/' + user_id,
            {
                params: {
                    fields: 'email,first_name,last_name'
                }
            }
        )
            .then(function(response){
                var user = response.data.result,
                    result;
                if (user) {
                    result = user.first_name + ' ' + user.last_name + ', ' + user.email;
                } else {
                    result = '-';
                }

                callback(result);
            });
    }

    function setStatus($scope, $http) {
        $scope.setStatus = function() {
            var entity = $scope.entity;

            $http.post(
                '/api/request/status/',
                JSON.stringify({id: entity.id, status: entity.new_status}),
                { headers: HEADERS }
            )
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

    function addComment($scope, $http) {
        $scope.addComment = function() {
            var toSend = {
                text: $scope.new_comment_text,
                user_id: current_user_id,
                request_id: $scope.entity.id
            };

            $http.put('/api/request/comment/', JSON.stringify(toSend), { headers: HEADERS })
                .then(function (response) {
                    if (response.data.result) {
                        ++RESTRICTION.offset;

                        $scope.comments.push(toSend);
                        var lastComment  = $scope.comments[$scope.comments.length - 1];

                        lastComment.id = response.data.result;
                        loadUser($http, lastComment.user_id, function(user){
                            lastComment.user_full = user;
                        });

                        var file = $('#comment_file')[0].files[0];

                        if (file) {
                            showInfoMessage('uploading file..');
                            Ajax.uploadFile(
                                '/api/file/',
                                {
                                    id: lastComment.id,
                                    file: file,
                                    type: 'REQUEST_COMMENT'
                                },
                                function(response) {
                                    if (response.result) {
                                        lastComment.has_file = true;
                                        showSuccessMessage('saved');
                                        $('#comment_file').val('');
                                    } else {
                                        var e = response.error;
                                        showErrorMessage(e.message + buildValidationErrors(e.errors));
                                    }
                                }
                            )
                        } else {
                            showSuccessMessage('saved');
                        }
                    } else {
                        showErrorMessage(response.data.error);
                    }
                });
        };
    }

    function loadComments($scope, $http) {
        $http.get(
            '/api/request/comment/',
            {
                params: {
                    restrict: JSON.stringify(RESTRICTION),
                    fields: 'id,user_id,text,has_file'
                }
            }
        )
            .then(function(response){
                if (response.data.result) {
                    response.data.result.forEach(function(comment){
                        $scope.comments.unshift(comment);

                        loadUser($http, comment.user_id, function(user){
                            comment.user_full = user;
                        });
                    });
                }
            });
    }
})();