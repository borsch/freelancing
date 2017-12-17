<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>
    <head>
        <jsp:include page="../common/include_external_head_top.jsp" />
        <title>Profile</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/include_external_body_top.jsp" />
    <jsp:include page="../common/header.jsp"/>
    <div class="container">
        <section>
        <h1 class="col-xs-12">Edit profile</h1>
            <div class="clearfix"></div>
            <div class="col-sm-9 col-sm-push-3">
                <div class="row">
                    <h4 class="subheader col-xs-12">Mail profile info</h4>
                    <form id="main_info_form">
                        <input type="hidden" name="id" value="${current_user.id}">
                        <div class="col-xs-12 col-sm-6">
                            <label>First name:</label>
                            <input name="first_name" value="${current_user.firstName}" required class="col-xs-12" />
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <label>Last name:</label>
                        <input name="last_name" value="${current_user.lastName}" required class="col-xs-12" />
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <label>Email:</label>
                            <input name="email" value="${current_user.email}" required class="col-xs-12" />
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <label>Phone:</label>
                            <input name="phone" value="${current_user.phone}" required class="col-xs-12" />
                        </div>
                        <div class="col-xs-12 text-center">
                            <button type="submit" class="btn-theme btn-1">Save</button>
                        </div>
                    </form>
                </div>
                <div class="row register-block">
                    <h4 class="subheader col-xs-12">Change password</h4>
                    <form id="password_form">
                        <input type="hidden" name="id" value="${current_user.id}">
                        <div class="col-xs-12">
                            <label>Old password:</label>
                            <input name="password" type="password" required class="col-xs-12" />
                        </div>
                        <div class="col-xs-12">
                            <label>New password:</label>
                            <input name="password_new" type="password" required class="col-xs-12" />
                        </div>
                        <div class="col-xs-12">
                            <label>New password repeat:</label>
                            <input name="password_new_repeat" type="password" required class="col-xs-12" />
                        </div>
                        <div class="col-xs-12 text-center">
                            <button type="submit" class="btn-theme btn-1">Save</button>
                        </div>
                    </form>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="col-sm-3 col-sm-pull-9">
                <jsp:include page="user_menu.jsp" />
            </div>
            <div class="clearfix"></div>
        </section>
    </div>

    <jsp:include page="../common/footer.jsp" />
    <script src="/resources/js/profile/edit.js"></script>
    <script>
        $(document).ready(function() {
            // Active menu
            $('#edit_profile').addClass('active');
        });
    </script>
    </body>
    </html>
</compress:html>