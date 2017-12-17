<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<compress:html removeIntertagSpaces="true">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../common/include_external_head_top.jsp" />
    <title>Sign in</title>
    <jsp:include page="../common/include_resources.jsp" />
</head>
<body>
<jsp:include page="../common/include_external_body_top.jsp" />
<jsp:include page="../common/header.jsp"/>

<div class="wrapper">
    <div class="container">
        <section>
            <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2">
                <form id="sign_in_form" class="col-sm-12">
                    <div class="register-block">
                        <div class="col-xs-12">
                            <input type="text" name="email" placeholder="Email" class="form-field col-xs-12" />
                        </div>
                    </div>
                    <div class="col-xs-12 text-center">
                        <button type="submit" class="btn-theme btn-2">Sign in</button>
                    </div>
                    <div class="clearfix"></div>
                </form>
            </div>
            <div class="clearfix"></div>
        </section>
    </div>
</div>

<jsp:include page="../common/footer.jsp" />
<script type="text/javascript">
    $(document).ready( function() {
        $('#sign_in_form').submit(function(e){
            e.preventDefault();

            var data = {
                email: $(this).find('[name=email]').val()
            };

            Ajax.post({
                url: '/api/users/sign_in',
                data: data,
                success: function(response){
                    if (response.result) {
                        location.href = '/';
                    } else {
                        showErrorMessage(response.error);
                    }
                }
            });
        });
    });
</script>
</body>
</html>
</compress:html>

