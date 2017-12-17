<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<%--<compress:html removeIntertagSpaces="true">--%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <jsp:useBean id="dateValue" class="java.util.Date"/>

    <!DOCTYPE html>
    <html>
    <head>
        <jsp:include page="../common/include_external_head_top.jsp" />
        <title><s:message code="brand"/></title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/include_external_body_top.jsp" />
        <jsp:include page="../common/header.jsp"/>

        <div class="wrapper index-page">
            <div class="visible-print-inline print-slider">
                <img src="/resources/images/slides/slide-1.jpg" class="img-responsive visible-print" />
            </div>
            <div class="clearfix"></div>
            <div>

            </div>
        </div>
        <div class="clearfix"></div>

        <jsp:include page="../common/footer.jsp" />
    </body>
    </html>
<%--</compress:html>--%>