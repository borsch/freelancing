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
                '/api/testimonial/' + params.id,
                {
                    params: {
                        fields: 'id,name,text,active'
                    }
                }
            )
                .then(function(response){
                    if (response.data.result) {
                        $scope.entity = response.data.result;

                        tinymce.activeEditor.setContent($scope.entity.text);
                    } else {
                        showErrorMessage(response.data.error);
                        redirectAfterFreeze('/admin/testimonials/all');
                    }
                })
        }

        save($scope, $http);
    });

    function save($scope, $http) {
        $scope.save = function() {
            $scope.entity.text = tinymce.activeEditor.getContent();

            var id = $scope.entity.id;
            var method = (id ? $http.post : $http.put);

            method('/api/testimonial/', JSON.stringify($scope.entity), {headers: HEADERS})
                .then(function (response) {
                    if (response.data.result) {
                        showSuccessMessage('saved');
                        if (!id) {
                            redirectAfterFreeze('/admin/testimonials/' + response.data.result + '/edit');
                        }
                    } else {
                        showErrorMessage(response.data.error);
                    }
                });
        };
    }
})();