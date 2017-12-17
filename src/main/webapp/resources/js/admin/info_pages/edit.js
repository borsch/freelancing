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
                '/api/infopage/' + params.id,
                {
                    params: {
                        fields: 'id,url,header,sub_header,text,active,meta_title,meta_description,meta_keywords'
                    }
                }
            )
                .then(function(response){
                    if (response.data.result) {
                        $scope.entity = response.data.result;

                        tinymce.activeEditor.setContent($scope.entity.text);
                    } else {
                        showErrorMessage(response.data.error);
                        redirectAfterFreeze('/admin/info_pages/all');
                    }
                })
        }

        save($scope, $http);
        validateUrl($scope);
    });

    function save($scope, $http) {
        $scope.save = function() {
            if (!isUrlValid($scope.entity.url)) {
                showErrorMessage('url has an invalid value');

                return;
            }
            $scope.entity.text = tinymce.activeEditor.getContent();

            var id = $scope.entity.id;
            var method = (id ? $http.post : $http.put);

            method('/api/infopage/', JSON.stringify($scope.entity), {headers: HEADERS})
                .then(function (response) {
                    if (response.data.result) {
                        showSuccessMessage('saved');
                        if (!id) {
                            redirectAfterFreeze('/admin/info_pages/' + response.data.result + '/edit');
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
})();