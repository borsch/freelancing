<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:forEach var="menu" items="${menu.childItems}">
    <li>
        <c:choose>
            <c:when test="${fn:length(menu.childItems) > 0}">
                <a href="#" data-toggle="collapse" data-target="#${menu.id}">${menu.name} <span class="caret"></span></a>
                <ul id="${menu.id}">
                    <c:set var="menu" value="${menu}" scope="request"/>
                    <jsp:include page="menu_node.jsp"/>
                </ul>
            </c:when>
            <c:otherwise>
                <a href="${menu.url}">${menu.name}</a>
            </c:otherwise>
        </c:choose>
    </li>
</c:forEach>