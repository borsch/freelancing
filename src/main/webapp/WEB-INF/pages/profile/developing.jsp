<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <!DOCTYPE html>
    <html>
    <head>
        <jsp:include page="../common/include_external_head_top.jsp" />
        <title>I'm developing</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/include_external_body_top.jsp" />
    <jsp:include page="../common/header.jsp"/>
    <div class="container">
        <section>
            <h1 class="col-xs-12">I'm developing</h1>
            <div class="clearfix"></div>
            <div class="col-sm-9 col-sm-push-3">
                <c:choose>
                    <c:when test="${projects ne null}">
                        <c:forEach var="project" items="${projects}">
                            <div class="row list-item">
                                <p><strong>Name: </strong>${project.name}</p>
                                <p><strong>Status: </strong>${project.status}</p>
                                <p><strong>Minimum skill level: </strong>${project.min_skill_level}</p>
                                <p><strong>Project tags: </strong>${project.tags}</p>
                                <p><strong>Developer rating: </strong>${project.developer_rating}</p>
                                <p><strong>Client rating: </strong>${project.client_rating}</p>

                                <p class="text-center">
                                    <a href="/projects/${project.id}/view"><button class="btn-theme btn-2">View project</button></a>
                                </p>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        still no projects
                    </c:otherwise>
                </c:choose>
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