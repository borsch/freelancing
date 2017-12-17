function showErrorMessage(error) {
    if (typeof error === 'string') {
        alertify.error(error);
    } else {
        alertify.error(error.message + buildValidationErrors(error.errors));
    }
}

function showSuccessMessage(message) {
    alertify.success(message);
}

function showInfoMessage(message) {
    alertify.message(message);
}

function buildValidationErrors(errors) {
    if (!errors || errors.length < 1) {
        return '';
    }

    return '<br />[' + errors.join(', ') + ']';
}


$(document).ready(function() {
    // Scroll to top
    $(window).scroll(function () {
        if ($(this).scrollTop() > 0) {
            $('#scroller').fadeIn();
        } else {
            $('#scroller').fadeOut();
        }
    });
    $('#scroller').click(function () {
        $('body,html').animate({
            scrollTop: 0
        }, 400);
        return false;
    });

});

