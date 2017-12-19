<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html>
    <head>
        <jsp:include page="../common/include_external_head_top.jsp" />
        <title>Coefficients</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/include_external_body_top.jsp" />
    <jsp:include page="../common/header.jsp"/>

    <div class="wrapper">
        <div class="container">
            <section>
                <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2">
                    <div class="register-block">
                        <h4 class="subheader">Coefficients</h4>
                        <c:forEach var="coefficient" items="${coefficients}">
                            <form class="row" method="post" action="/coefficients">
                                <div class="col-xs-12 col-sm-5">
                                        ${coefficient.key}
                                    <input type="hidden" name="code" value="${coefficient.key}" />
                                </div>
                                <div class="col-xs-12 col-sm-5">
                                    <input type="text" name="value" value="${coefficient.value}" placeholder="Value" class="form-field col-xs-12" />
                                </div>
                                <div class="col-xs-12 col-sm-2 text-center">
                                    <button type="submit" class="btn-theme btn-2">Update</button>
                                </div>
                            </form>
                        </c:forEach>
                    </div>
                </div>
                <div class="clearfix"></div>
            </section>
        </div>
    </div>

    <jsp:include page="../common/footer.jsp" />
    </body>
    </html>
</compress:html>

