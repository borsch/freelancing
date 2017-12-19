<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html>
    <head>
        <jsp:include page="../common/include_external_head_top.jsp" />
        <title>Developers</title>
        <jsp:include page="../common/include_resources.jsp" />
        <style>

        </style>
    </head>
    <body>
    <jsp:include page="../common/include_external_body_top.jsp" />
    <jsp:include page="../common/header.jsp"/>

    <div class="wrapper">
        <div class="container">
            <section>
                <div class="register-block">
                    <h4 class="subheader">Developers for project: ${project.name}</h4>
                    <div class="col-xs-12" style="border-bottom: 1px solid black;">
                        <p><strong>Minimum skill level: </strong>${project.min_skill_level}</p>
                        <p><strong>Tags: </strong>${project.tags}</p>
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2">
                    <c:choose>
                        <c:when test="${developers ne null}">
                            <c:forEach var="developer" items="${developers}">
                                <div class="col-xs-12 list-item">
                                    <p><strong>Name: </strong>${developer.user_name}</p>
                                    <p><strong>Rating: </strong>${developer.rating}</p>
                                    <p><strong>Skill level: </strong>${developer.skill_level}</p>
                                    <p><strong>Tags: </strong>${developer.tags}</p>
                                    <p><strong>Projects amount: </strong>${developer.projects_amount}</p>
                                    <p><strong>Developer score: ${developer.developer_score}</strong></p>
                                    <p><strong>Common tags: ${developer.common_tags_percents}%</strong></p>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            None of developers match this min skill level
                        </c:otherwise>
                    </c:choose>
                </div>
            </section>
        </div>
    </div>

    <jsp:include page="../common/footer.jsp" />
    </body>
    </html>
</compress:html>

