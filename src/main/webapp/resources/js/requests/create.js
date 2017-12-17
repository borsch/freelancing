$(function(){

    var user_id = UrlUtil.parse($('#loader').attr('src')).current_user_id,
        request_id = null;

    $('#sign_up_form').submit(function(e){
        e.preventDefault();

        registerRequest(function(){
            var file = $('#request_file')[0].files[0];

            if (!file) {
                showErrorMessage('Please select file');

                return;
            }

            Ajax.uploadFile(
                '/api/file/',
                {
                    id: request_id,
                    file: file,
                    type: 'REQUEST'
                },
                function(response) {
                    if (response.result) {
                        showSuccessMessage('Thank you for your request.<br />We will be in contact shortly');
                        setTimeout(function(){
                            location.href = '/requests/' + request_id + '/chat';
                        }, 1000);
                    } else {
                        var e = response.error;
                        showErrorMessage(e.message + buildValidationErrors(e.errors));
                    }
                }
            )
        });
    });

    function registerRequest(callback) {
        if (request_id) {
            callback();

            return;
        }

        var data = {};

        $('#company_fields .form-field').each(function(){
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

        data.user_id = user_id;

        Ajax.put({
            url: '/api/request/',
            data: data,
            success: function(response) {
                if (response.result) {
                    request_id = response.result;
                    callback();
                } else {
                    var e = response.error;
                    showErrorMessage(e.message + buildValidationErrors(e.errors));
                }
            }
        });
    }
});