$(function(){

    $('#sign_up_form').submit(function(e){
        e.preventDefault();

        var file = $('#request_file')[0].files[0];

        var data = {
            role: 'user'
        };

        $('#sign_up_form .form-field').each(function(){
            var $self = $(this),
                name = $self.attr('name'),
                val = $self.val();

            if ($self.attr('type') === 'checkbox') {
                if ($self.is(':checked')) {
                    val = parseInt(val);
                    if (data[name]) {
                        data[name].push(val);
                    } else {
                        data[name] = [val];
                    }
                }
            } else {
                data[name] = $self.val();
            }
        });

        Ajax.put({
            url: '/api/users/registration',
            data: data,
            success: function(response) {
                if (response.result) {
                    if (file) {
                        var request_id = response.result.request_id;

                        Ajax.uploadFile(
                            '/api/file/',
                            {
                                id: request_id,
                                file: file,
                                type: 'REQUEST'
                            },
                            function (response) {
                                if (response.result) {
                                    showSuccessMessage('Thank you for your request.<br />We will be in contact shortly');

                                    Ajax.post({
                                        url: '/api/mail/set_password',
                                        success: function (response) {
                                            if (response.result) {
                                                showSuccessMessage('We send You email with activation link');
                                            } else {
                                                showErrorMessage('If you have not received email, please, click "Forgot password" in Sign in form to resend activation link');
                                            }
                                        }
                                    });
                                } else {
                                    var e = response.error;
                                    showErrorMessage(e.message + buildValidationErrors(e.errors));
                                }
                            }
                        );
                    } else {
                        showSuccessMessage('Thank you for your request.<br />We will be in contact shortly');

                        Ajax.post({
                            url: '/api/mail/set_password',
                            success: function (response) {
                                if (response.result) {
                                    showSuccessMessage('We send You email with activation link');
                                } else {
                                    showErrorMessage('If you have not received email, please, click "Forgot password" in Sign in form to resend activation link');
                                }
                            }
                        });
                    }
                } else {
                    showErrorMessage(response.error);
                }
            }
        });
    });

    $('#login_facebook').click(function(){
        FaceBook.getUserData(function(user){
            if (user) {
                for (var key in user) {
                    $('[name=' + key + ']').val(user[key]);
                }
            }
        });
    });
});