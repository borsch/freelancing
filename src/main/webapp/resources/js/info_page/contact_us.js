(function(){

    $('#contact_us').submit(function(e){
        e.preventDefault();

        var data = {};
        $(this).find('[name]').each(function(){
            var $self = $(this);

            data[$self.attr('name')] = $self.val();
        });

        console.log(data);

        Ajax.post({
            url: '/api/mail/contact_us',
            data: data,
            success: function(response) {
                if (response.result) {
                    showSuccessMessage('Message send to our admins');
                    $(this).find('[name]').val('');
                } else {
                    showErrorMessage(response.error);
                }
            }
        });
    });

})();