(function(){

    var app = angular.module('edit', []);

    app.controller('edit_forms', function($scope, $http) {
        tinymce.init({
            selector:'#full_text',
            relative_urls : false,
            remove_script_host : false,
            convert_urls : true,
            plugins: [
                "advlist autolink lists link image charmap print preview anchor",
                "searchreplace visualblocks code fullscreen",
                "insertdatetime media table contextmenu paste imagetools"
            ],
            toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image | " + TinyMCEUtil.images_fields,
            content_css: [
                '//fonts.googleapis.com/css?family=Lato:300,300i,400,400i',
                '//www.tinymce.com/css/codepen.min.css'
            ],
            height: 400,
            setup: function(editor) {
                TinyMCEUtil.append_images_buttons(editor);
            }
        });

        var params = UrlUtil.parse(angular.element('#loader').attr('src'));
        $scope.entity = { };

        if (parseInt(params.id)) {
            $http.get(
                '/api/blog/post/' + params.id,
                {
                    params: {
                        fields: 'id,url,title,short_description,has_img,meta_title,meta_description,meta_keywords,category,post_text'
                    }
                }
            )
                .then(function(response){
                    if (response.data.result) {
                        $scope.entity = response.data.result;

                        if ($scope.entity.has_img) {
                            setImage('/api/file/' + $scope.entity.id + '?type=POST');
                        }

                        // select does not update if category is number
                        if ($scope.entity.category) {
                            $scope.entity.category = $scope.entity.category.toString();
                        }

                        tinymce.get(0).setContent($scope.entity.post_text);
                    } else {
                        showErrorMessage(response.data.error);
                        redirectAfterFreeze('/admin/posts/all');
                    }
                })
        }

        save($scope, $http);
        validateUrl($scope);
        uploadFile($scope);
    });

    function save($scope, $http) {
        $scope.save = function() {
            if (!isUrlValid($scope.entity.url)) {
                showErrorMessage('url has an invalid value');

                return;
            }
            $scope.entity.post_text = tinymce.get(0).getContent();

            var id = $scope.entity.id;
            var method = (id ? $http.post : $http.put);

            method('/api/blog/post/', JSON.stringify($scope.entity), {headers: HEADERS})
                .then(function (response) {
                    if (response.data.result) {
                        showSuccessMessage('saved');
                        if (!id) {
                            redirectAfterFreeze('/admin/posts/' + response.data.result + '/edit');
                        }
                    } else {
                        showErrorMessage(response.data.error);
                    }
                });
        };
    }

    function validateUrl($scope) {
        $scope.validateUrl = function(){
            var url = $scope.entity.url;

            if (!url) {
                return;
            }

            if (!isUrlValid(url)) {
                showErrorMessage('url has an invalid value');
            }
        };
    }

    function isUrlValid(url) {
        var regex = /^[A-Z1-9a-z_-]+$/g;

        return regex.test(url);
    }

    function uploadFile($scope) {
        $scope.uploadFile = function() {
            var element = angular.element('#image')[0];
            var file = element.files[0];

            if (!file) {
                showErrorMessage('select file');
                return;
            }

            Ajax.uploadFile(
                '/api/file/',
                {
                    id: $scope.entity.id,
                    file: file,
                    type: 'POST'
                },
                function(response) {
                    if (response.result) {
                        showSuccessMessage('uploaded');
                        setImage('/api/file/' + $scope.entity.id + '?type=POST');
                    } else {
                        var e = response.error;
                        showErrorMessage(e.message + buildValidationErrors(e.errors));
                    }
                }
            );
        };
    }

    function setImage(icon) {
        $('#image_container').html('<img src="' + icon + '" class="form-img">');
    }
})();