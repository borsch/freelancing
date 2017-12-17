<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="/resources/icons/favicon.png" rel="shortcut icon" type="image/png" />

<jsp:include page="../common/bootstrap_include.jsp" />
<script src="/resources/js/jquery-3.1.0.min.js"></script>
<script src="/resources/js/ejs.min.js"></script>
<script src="/resources/js/ajax.js"></script>
<link rel="stylesheet" href="/resources/js/utils/notification/css/alertify.min.css">
<link rel="stylesheet" href="/resources/js/utils/notification/css/themes/default.min.css">
<link rel="stylesheet" href="/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/admin.css">
<script src="/resources/js/utils/notification/alertify.min.js"></script>
<script src="/resources/js/auth/sign_in.js"></script>
<script src="/resources/js/utils/parse_url.js"></script>

<script src="/resources/js/angular.min.js"></script>
<script>
    var HEADERS = {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    };

    function formPages(number_of_entities, per_page, container) {
        container.empty();
        var i = 0;
        do {
            container.append(['<option value="', i, '">', i+1, '</option>'].join(''));
            ++i;
            number_of_entities -= per_page;
        } while (number_of_entities > per_page);

        if (number_of_entities > 0)
            container.append(['<option value="', i, '">', i+1, '</option>'].join(''));
    }

    function redirectAfterFreeze(page) {
        setTimeout(function(){
            location.href = page;
        }, 1000);
    }

    $(function(){
        var li = $('a[href="'+location.pathname+'"]').parent().addClass('active');

        if(li.parent().hasClass('dropdown-menu')){
            li.parent().parent().addClass('active');
        }

        $('#logout').click(function(e){
            e.preventDefault();

            Ajax.post({
                url: '/api/users/logout',
                success: function(){
                    location.href = '/';
                }
            });
        });
    });

    $(document).ready( function() {
        $(".inputfile").change(function(){
            var filename = $(this).val().replace(/.*\\/, "");
            $("#filename").val(filename);
        });
    });

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

</script>

