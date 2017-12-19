<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html>
    <head>
        <jsp:include page="../common/include_external_head_top.jsp" />
        <title>Project #${project.id}</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/include_external_body_top.jsp" />
    <jsp:include page="../common/header.jsp"/>

    <div class="wrapper">
        <div class="container">
            <section>
                <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2">
                    <div class="col-xs-12">
                        <p><strong>Name: </strong>${project.name}</p>
                        <p><strong>Status: </strong>${project.status}</p>
                        <p><strong>Minimum skill level: </strong>${project.min_skill_level}</p>
                        <p><strong>Tags: </strong>${project.tags}</p>
                        <p><strong>Developer rating: </strong><span data-field="developer_rating">${project.developer_rating}</span>/5</p>
                        <p><strong>Client rating: </strong><span data-field="client_rating">${project.client_rating}</span>/5</p>

                        <c:if test="${project.status eq 'DONE'}">
                            <c:if test="${developer_same ne null && project.client_rating eq 0}">
                                <div data-block="client_rating">
                                    <div class="col-xs-9">
                                        <input type="text" name="client_rating" placeholder="Rating for client(up to 5)" class="form-field col-xs-12" />
                                    </div>

                                    <div class="col-xs-3 text-center">
                                        <button data-field="client_rating" class="btn-theme btn-2 set">Set</button>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${client_same ne null && project.developer_rating eq 0}">
                                <div data-block="developer_rating">
                                    <div class="col-xs-9">
                                        <input type="text" name="developer_rating" placeholder="Rating for developer(up to 5)" class="form-field col-xs-12" />
                                    </div>

                                    <div class="col-xs-3 text-center">
                                        <button data-field="developer_rating" class="btn-theme btn-2 set">Set</button>
                                    </div>
                                </div>
                            </c:if>
                        </c:if>
                        <c:if test="${project.developer_id eq null}">
                            <div class="col-xs-12 text-center">
                                <a href="/developers/find?project_id=${project.id}"><button class="btn-theme btn-2">Find developer</button></a>
                            </div>
                        </c:if>
                    </div>
                </div>
                <div class="clearfix"></div>
            </section>
        </div>
    </div>

    <jsp:include page="../common/footer.jsp" />
    <script>
        $('.set').click(function(){
            var field = $(this).data('field'),
                value = $('[name=' + field + ']').val(),
                data = { id: ${project.id} };

            data[field] = value;

            Ajax.post({
                url: '/api/projects/',
                data: data,
                success: function(response) {
                    if (response.result) {
                        $('[data-block=' + field + ']').remove();
                        $('[data-field=' + field + ']').text(value);

                        if (field === 'developer_rating') {
                            Ajax.post({
                                url: '/api/developers/rating_recount',
                                data: {
                                    id: ${project.developer_id}
                                }
                            });
                        }
                    } else {
                        showErrorMessage(response.error);
                    }
                }
            })
        });
    </script>
    </body>
    </html>
</compress:html>

