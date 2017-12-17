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
                    <c:choose>
                        <c:when test="${last_request != null}">
                            <div class="col-sm-12 reguest-wrapper">
                                <div class="request-item">
                                    <div>
                                        <h4 class="subheader">Your last request</h4>
                                        <p><strong>Company:</strong> ${last_request.company_name}</p>
                                        <p><strong>Industry:</strong> ${last_request.industry_name}</p>
                                        <p><strong>Interests:</strong>
                                            <c:choose>
                                                <c:when test="${last_request.interests_name ne null}">
                                                    <c:forEach items="${last_request.interests_name}" var="name" varStatus="i">
                                                        ${name}<c:if test="${!i.last}">,&nbsp;</c:if>
                                                    </c:forEach>
                                                    <c:if test="${last_request.interest_alter ne null}">
                                                        ,&nbsp;${last_request.interest_alter}
                                                    </c:if>
                                                </c:when>
                                                <c:otherwise>
                                                    ${last_request.interest_alter}
                                                </c:otherwise>
                                            </c:choose>
                                        </p>
                                        <p><strong>Request status:</strong> <spring:message code="request.status.${last_request.status}" /></p>
                                    </div>
                                    <div class="text-center action-link">
                                        <a href="/requests/${last_request.id}/chat" title="Chat"><i class="fa fa-comments" aria-hidden="true"></i></a>
                                        <c:if test="${last_request.has_file}">
                                            <a href="/api/file/${last_request.id}?type=REQUEST" title="Download Business Plan"><i class="fa fa-file-text" aria-hidden="true"></i></a>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="text-center no-info">You still have no requests. You can create it </div>
                            <div class="text-center">
                                <a href="/requests/create" class="btn-theme btn-1">Create request</a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="col-sm-3 col-sm-pull-9">
                <jsp:include page="user_menu.jsp" />
            </div>
            <div class="clearfix"></div>
        </section>
    </div>
    <jsp:include page="../common/footer.jsp" />
    <script>
        $(function() {
            // Active menu
            $('#user_profile').addClass('active');
        });
    </script>
    </body>
    </html>
</compress:html>