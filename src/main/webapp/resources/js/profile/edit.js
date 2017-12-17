(function(){

    $('#main_info_form').submit(function(e){
        e.preventDefault();

        var data = {};

        $(this).find('input').each(function(){
            var $self = $(this);

            data[$self.attr('name')] = $self.val();
        });

        Ajax.post({
            url: '/api/users/',
            data: data,
            success: function(response) {
                if (response.result) {
                    showSuccessMessage('Saved');
                } else {
                    showErrorMessage(response.error);
                }
            }
        });
    });

    $('#password_form').submit(function(e){
        e.preventDefault();

        var data = {};

        $(this).find('input').each(function(){
            var $self = $(this);

            data[$self.attr('name')] = md5($self.val());
        });

        data.id = $(this).find('[name=id]').val();

        if (data.password_new !== data.password_new_repeat) {
            showErrorMessage('password are not equals');

            return;
        }

        Ajax.post({
            url: '/api/users/password',
            data: data,
            success: function(response) {
                if (response.result) {
                    showSuccessMessage('Updated');
                } else {
                    showErrorMessage(response.error);
                }
            }
        });
    });
})();