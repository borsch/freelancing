<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<header class="navbar-fixed-top">
    <div class="header">
        <div class="container">
            <div>
                <div class="col-xs-12 col-md-4 brand-logo text-uppercase">
                    <a href="/">
                        <img src="/resources/images/logo_header.png" alt="<s:message code='brand'/>" class="hidden-print"/>
                        <img src="/resources/images/logo_header_green.png" class="visible-print-inline-block"/>
                        <h1 class="font-theme-medium"><s:message code="brand.name"/><br/><span class="font-theme-normal"><s:message code="brand.subheader"/></span></h1>
                    </a>
                </div>
                <div class="col-md-8 text-right menu hidden-print">
                    <div class="menu-mob-button visible-xs visible-sm"><a href="#"><i class="fa fa-bars" aria-hidden="true"></i></a></div>
                    <nav>
                        <ul class="topmenu">
                            <li id="home"><a href="/"><s:message code="navmenu.home"/></a></li>
                            <li><a href="/developers">Developers</a></li>
                            <li><a href="/projects">Projects</a></li>
                            <security:authorize access="isAnonymous()">
                                <li><a href="/register">Registration</a></li>
                                <li><a href="/sign_in">Log in</a></li>
                            </security:authorize>
                            <security:authorize access="isAuthenticated()">
                                <li><a href="/profile">Profile</a></li>
                                <li><a id="logout" href="#">Log out</a></li>
                            </security:authorize>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</header>
<div class="clearfix"></div>