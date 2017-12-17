(function(){

    var app = angular.module('edit', []);

    app.controller('edit_forms', function($scope, $http) {
        tinymce.init({
            selector:'#description_text',
            relative_urls : false,
            remove_script_host : false,
            convert_urls : true,
            plugins: [
                "advlist autolink lists link image charmap print preview anchor",
                "searchreplace visualblocks code fullscreen",
                "insertdatetime media table contextmenu paste imagetools"
            ],
            toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
            content_css: [
                '//fonts.googleapis.com/css?family=Lato:300,300i,400,400i',
                '//www.tinymce.com/css/codepen.min.css'
            ],
            height: 400
        });

        var params = UrlUtil.parse(angular.element('#loader').attr('src'));
        $scope.entity = { };

        if (parseInt(params.id)) {
            $http.get(
                '/api/carousel_image/' + params.id,
                {
                    params: {
                        fields: 'id,description,has_image'
                    }
                }
            )
                .then(function(response){
                    if (response.data.result) {
                        $scope.entity = response.data.result;

                        if ($scope.entity.has_image) {
                            $scope.icon = '/api/file/' + $scope.entity.id + '?type=CAROUSEL_IMAGE';
                        }

                        tinymce.activeEditor.setContent($scope.entity.description);
                    } else {
                        showErrorMessage(response.data.error);
                        redirectAfterFreeze('/admin/carousel_images/all');
                    }
                })
        }

        save($scope, $http);
        uploadFile($scope);
    });

    function save($scope, $http) {
        $scope.save = function() {
            $scope.entity.description = tinymce.activeEditor.getContent();

            var id = $scope.entity.id;
            var method = (id ? $http.post : $http.put);

            method('/api/carousel_image/', JSON.stringify($scope.entity), {headers: HEADERS})
                .then(function (response) {
                    if (response.data.result) {
                        showSuccessMessage('saved');
                        if (!id) {
                            redirectAfterFreeze('/admin/carousel_images/' + response.data.result + '/edit');
                        }
                    } else {
                        showErrorMessage(response.data.error);
                    }
                });
        };
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
                    type: 'CAROUSEL_IMAGE'
                },
                function(response) {
                    if (response.result) {
                        showSuccessMessage('uploaded');
                        $scope.icon = '';
                        setTimeout(function(){
                            $scope.icon = '/api/file/' + $scope.entity.id + '?type=CAROUSEL_IMAGE';
                        }, 500);
                    } else {

                    }
                }
            );
        };
    }
})();