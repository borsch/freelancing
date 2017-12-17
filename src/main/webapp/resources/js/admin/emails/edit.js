(function(){

    var app = angular.module('edit', []);

    app.controller('edit_forms', function($scope, $http) {
                var params = UrlUtil.parse(angular.element('#loader').attr('src'));
        $scope.entity = { };

        if (parseInt(params.id)) {
            $http.get(
                '/api/email/template/' + params.id,
                {
                    params: {
                        fields: 'id,subject,text,code'
                    }
                }
            )
                .then(function(response){
                    if (response.data.result) {
                        $scope.entity = response.data.result;

                        setupTinyMCE($scope.entity.code);

                        tinymce.activeEditor.setContent($scope.entity.text);
                    } else {
                        showErrorMessage(response.data.error);
                        redirectAfterFreeze('/admin/emails/all');
                    }
                })
        } else {
            showErrorMessage('Email can not be created');
            redirectAfterFreeze('/admin/emails/all');
        }

        save($scope, $http);
    });

    function setupTinyMCE(email_code) {
        function urlConverter(url){
            var openBracket = url.indexOf('{{'),
                closeBracket = url.indexOf('}}') + 1;   // add 1 cause indexOf return index of first substring element

            if (openBracket > -1 && closeBracket > -1) {
                url = url.substr(openBracket, closeBracket + 1);
            }

            return url;
        }

        tinymce.init({
            selector:'#text',
            relative_urls : false,
            remove_script_host : false,
            plugins: [
                "advlist autolink lists link image charmap print preview anchor",
                "searchreplace visualblocks code fullscreen",
                "insertdatetime media table contextmenu paste imagetools"
            ],
            toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image | email_fields| " + TinyMCEUtil.images_fields,
            urlconverter_callback: urlConverter,
            setup: function(editor) {
                Ajax.get({
                    url: '/api/mail/' + email_code + '/email_fields',
                    async: false,
                    success: function(response) {
                        var menus = [];
                        if (response.result) {
                            response.result.forEach(function(field){
                                menus.push({
                                    text: field.label,
                                    onclick: function() {
                                        editor.insertContent('{{' + field.value + '}}');
                                    }
                                })
                            });
                        }
                        editor.addButton('email_fields', {
                            type: 'menubutton',
                            text: 'Email fields',
                            icon: false,
                            menu: menus
                        });
                    }
                });

                TinyMCEUtil.append_images_buttons(editor);
            },
            content_css: [
                '//fonts.googleapis.com/css?family=Lato:300,300i,400,400i',
                '//www.tinymce.com/css/codepen.min.css'
            ],
            height: 400
        });
    }

    function save($scope, $http) {
        $scope.save = function() {
            $scope.entity.text = tinymce.activeEditor.getContent();

            var id = $scope.entity.id;
            var method = (id ? $http.post : $http.put);

            method('/api/email/template/', JSON.stringify($scope.entity), {headers: HEADERS})
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