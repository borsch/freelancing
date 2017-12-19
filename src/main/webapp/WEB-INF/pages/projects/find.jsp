<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html>
    <head>
        <jsp:include page="../common/include_external_head_top.jsp" />
        <title>Find projects for developer ${developer.user_name}</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/include_external_body_top.jsp" />
    <jsp:include page="../common/header.jsp"/>

    <div class="wrapper">
        <div class="container">
            <section>
                <div class="col-xs-12" >
                    <p><strong>Name: </strong>${developer.user_name}</p>
                    <p><strong>Skill level: </strong>${developer.skill_level}</p>
                    <p><strong>Tags: </strong>${developer.tags}</p>
                    <p><strong>Rating: </strong>${developer.rating}</p>
                </div>
                <div class="clearfix"></div>
                <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2">
                    <c:choose>
                        <c:when test="${projects ne null}">
                            <c:forEach var="project" items="${projects}">
                                <div class="col-xs-12 list-item">
                                    <p><strong>Name: </strong>${project.name}</p>
                                    <p><strong>Minimum skill level: </strong>${project.min_skill_level}</p>
                                    <p><strong>Tags: </strong>${project.tags}</p>
                                    <p><strong>Score: </strong>${project.score}</p>
                                    <p><strong>Common tags: </strong>${project.common_tags_percents}%</p>
                                    <p class="text-center">
                                        <button class="btn-theme btn-2 select-project" data-id="${project.id}">Select project</button>
                                    </p>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            Nothing found for You
                        </c:otherwise>
                    </c:choose>
                </div>
            </section>
        </div>
    </div>
    <jsp:include page="../common/footer.jsp" />
    <script>
        $('.select-project').click(function(){
            var $self = $(this),
                project_id = $self.data('id'),
                data = { id: project_id, developer_id: ${developer.id}, status: 'DONE' };

            Ajax.post({
                url: '/api/projects/',
                data: data,
                success: function(response) {
                    if (response.result) {
                        showSuccessMessage('Project selected');

                        setTimeout(function(){
                            location.href = '/projects/' + project_id + '/view';
                        }, 500);
                    } else {
                        showErrorMessage(response.result);
                    }
                }
            })
        });
    </script>
    </body>
    </html>
</compress:html>

