(function(){

    var app = angular.module('edit', []);

    app.controller('edit_forms', function($scope, $http) {
        tinymce.init({
            selector:'#info_page_text',
            relative_urls : false,
            remove_script_host : false,
            convert_urls : true,
            plugins: [
                "advlist autolink lists link image charmap print preview anchor",
                "searchreplace visualblocks code fullscreen",
                "insertdatetime media table contextmenu paste imagetools"
            ],
            toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image| " + TinyMCEUtil.images_fields,
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
                '/api/company/' + params.id,
                {
                    params: {
                        fields: 'id,name,text,active,has_image'
                    }
                }
            )
                .then(function(response){
                    if (response.data.result) {
                        $scope.entity = response.data.result;

                        tinymce.activeEditor.setContent($scope.entity.text);

                        if ($scope.entity.has_image) {
                            $scope.icon = '/api/file/' + $scope.entity.id + '?type=COMPANY';
                        }
                    } else {
                        showErrorMessage(response.data.error);
                        redirectAfterFreeze('/admin/companies/all');
                    }
                })
        }

        save($scope, $http);
        uploadFile($scope);
    });

    function save($scope, $http) {
        $scope.save = function() {
            $scope.entity.text = tinymce.activeEditor.getContent();

            var id = $scope.entity.id;
            var method = (id ? $http.post : $http.put);

            method('/api/company/', JSON.stringify($scope.entity), {headers: HEADERS})
                .then(function (response) {
                    if (response.data.result) {
                        showSuccessMessage('saved');
                        if (!id) {
                            redirectAfterFreeze('/admin/companies/' + response.data.result + '/edit');
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

            var payload = new FormData();
            payload.append('id', $scope.entity.id);
            payload.append('file', file);
            payload.append('type', 'COMPANY');

            var request = new XMLHttpRequest();
            request.open('POST', '/api/file/');

            for (var key in HEADERS) {
                if (key.toLowerCase().indexOf('content') < 0) {
                    request.setRequestHeader(key, HEADERS[key]);
                }
            }

            request.onload = function () {
                var response = request.response;
                if (typeof response === 'string') {
                    response = JSON.parse(response);
                }

                if (response.result) {
                    showSuccessMessage('uploaded');
                    $scope.icon = '';
                    setTimeout(function(){
                        $scope.icon = '/api/file/' + $scope.entity.id + '?type=COMPANY';
                    }, 500);
                } else {

                }
            };
            request.send(payload);
        };
    }
})();