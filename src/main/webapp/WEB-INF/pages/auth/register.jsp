<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<compress:html removeIntertagSpaces="true">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../common/include_external_head_top.jsp" />
    <title>Register</title>
    <jsp:include page="../common/include_resources.jsp" />
</head>
<body>
<jsp:include page="../common/include_external_body_top.jsp" />
<jsp:include page="../common/header.jsp"/>

<div class="wrapper">
    <div class="container">
        <section>
            <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2">
                <form id="sign_up_form" class="col-sm-12">
                    <div class="register-block">
                        <h4 class="subheader">Personal info <span class="text-error">*</span></h4>
                        <div class="row">
                            <div class="col-xs-12 col-sm-6">
                                <input type="text" name="name" placeholder="Name *" class="form-field col-xs-12" />
                            </div>
                            <div class="col-xs-12 col-sm-6">
                                <input type="text" name="email" placeholder="Email *" class="form-field col-xs-12" />
                            </div>
                        </div>
                    </div>
                    <div class="register-block">
                        <h4 class="subheader">Developer info <span class="text-error">*</span></h4>
                        <div class="row">
                            <h4 class="subheader">Skill level <span class="text-error">*</span></h4>
                            <div class="col-xs-12">
                                <select name="skill_level" class="form-field col-xs-12">
                                    <option selected disabled>-- select --</option>
                                    <option value="JUNIOR">Junior</option>
                                    <option value="MIDDLE">Middle</option>
                                    <option value="SENIOR">Senior</option>
                                    <option value="GOD">God mode</option>
                                </select>
                            </div>
                            <div class="col-xs-12 ">
                                <input type="text" name="tags" placeholder="Skills separated with comma" class="form-field col-xs-12" />
                            </div>
                        </div>
                    </div>
                    <div class="register-block">
                        <h4 class="subheader">Client profile will be automatically created for You</h4>
                    </div>
                    <div class="col-xs-12 text-center">
                        <button type="submit" class="btn-theme btn-2">Register</button>
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
        $('#sign_up_form').submit(function(e){
            e.preventDefault();

            var data = {},
                $fields = $(this).find('[name]');

            $fields.each(function(){
                var $self = $(this);

                if ($self.attr('name') === 'tags') {
                    data.tags = $self.val().split(',');
                    data.tags.forEach(function(element, i, array){
                        array[i] = element.trim();
                    });
                } else {
                    data[$self.attr('name')] = $self.val();
                }
            });

            console.log(data);

            if (Object.keys(data).length !== $fields.length) {
                showErrorMessage('All fields are required');

                return;
            }

            Ajax.put({
                url: '/api/users/registration',
                data: data,
                success: function(response){
                    if (response.result) {
                        showSuccessMessage('Successfully registration');

                        setTimeout(function(){
                            location.href = '/profile';
                        }, 500);
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

