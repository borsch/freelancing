<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
            <h1 class="col-xs-12">User Profile</h1>
            <div class="clearfix"></div>
            <div class="col-sm-9 col-sm-push-3">
                <div class="row">
                    <p><strong>Developer skill level: </strong>${user.developer_skill_level}</p>
                    <p><strong>Developer rating: </strong>${user.developer_rating}</p>
                    <p><strong>Developer tags: </strong>${user.developer_tags}</p>
                    <p><strong>Developer projects amount: </strong>${user.developer_projects_amount}</p>
                    <p><strong>Client rating: </strong>${user.client_rating}</p>
                    <p><strong>Client projects amount: </strong>${user.client_projects_amount}</p>
                </div>
                <div class="text-center">
                    <a href="/projects/find?developer_id=${user.developer_id}"><button class="btn-theme btn-2">Search projects for me</button></a>
                </div>
            </div>
            <div class="col-sm-3 col-sm-pull-9">
                <jsp:include page="user_menu.jsp" />
            </div>
            <div class="clearfix"></div>
        </section>
    </div>
    <jsp:include page="../common/footer.jsp" />
    </body>
    </html>
</compress:html>