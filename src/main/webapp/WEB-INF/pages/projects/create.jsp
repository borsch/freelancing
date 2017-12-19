<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html>
    <head>
        <jsp:include page="../common/include_external_head_top.jsp" />
        <title>Create project</title>
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
                            <h4 class="subheader">Project info <span class="text-error">*</span></h4>
                            <div class="row">
                                <div class="col-xs-12 ">
                                    <input type="text" name="name" placeholder="Project name *" class="form-field col-xs-12" />
                                </div>
                                <div class="col-xs-12">
                                    <select name="min_skill_level" class="form-field col-xs-12">
                                        <option value="" selected disabled>Minimum skill level *</option>
                                        <option value="">Any</option>
                                        <option value="JUNIOR">Junior</option>
                                        <option value="MIDDLE">Middle</option>
                                        <option value="SENIOR">Senior</option>
                                        <option value="GOD">God mode</option>
                                    </select>
                                </div>
                                <div class="col-xs-12 ">
                                    <input type="text" name="tags" placeholder="Skills separated with comma *" class="form-field col-xs-12" />
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 text-center">
                            <button type="submit" class="btn-theme btn-2">Create</button>
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
        var client_id;

        $(document).ready( function() {
            Ajax.get({
                url: '/api/users/me?fields=client_id',
                success: function(response) {
                    if (response.result) {
                        client_id = response.result.client_id;
                    } else {
                        showErrorMessage('Internal server error');
                    }
                }
            });

            $('#sign_up_form').submit(function(e){
                e.preventDefault();

                if (!client_id) {
                    showErrorMessage('Can not create project');
                    return;
                }

                var data = { client_id: client_id, status: 'NEW' },
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

                if (Object.keys(data).length - 2 !== $fields.length) {
                    showErrorMessage('All fields are required');

                    return;
                }

                Ajax.put({
                    url: '/api/projects/',
                    data: data,
                    success: function(response){
                        if (response.result) {
                            showSuccessMessage('Successfully created');

                            setTimeout(function(){
                                location.href = '/profile/my_projects';
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

