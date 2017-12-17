<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="edit">
<head>
    <title>Edit info page #${id}</title>
</head>
<body>
    <jsp:include page="../header.jsp" />
    <div class="container col-md-10 col-md-offset-1" ng-controller="edit_forms">
        <div class="top-nav">
            <a href="/admin/info_pages/all"><i class="fa fa-long-arrow-left" aria-hidden="true"></i>Back to all Info pages</a>
        </div>
        <div class="page-title">Create / Edit Info page</div>
        <form ng-submit="save();">
            <div class="form-group">
                <label>URL</label>
                <input type="text" ng-model="entity.url" ng-change="validateUrl()" class="form-control">
            </div>
            <div class="form-group">
                <label>Header</label>
                <input type="text" ng-model="entity.header" class="form-control">
            </div>
            <div class="form-group">
                <label>Sub header</label>
                <input type="text" ng-model="entity.sub_header" class="form-control">
            </div>
            <div class="form-group">
                <label>Text</label>
                <textarea id="info_page_text" ng-model="entity.text" class="form-control"></textarea>
            </div>
            <div class="form-group">
                <input type="checkbox" ng-model="entity.active" class="form-control checkbox">
                <label>Active</label>
            </div>
            <div class="form-group">
                <label>Meta title</label>
                <input type="text" ng-model="entity.meta_title" class="form-control">
            </div>
            <div class="form-group">
                <label>Meta description</label>
                <textarea ng-model="entity.meta_description" class="form-control"></textarea>
            </div>
            <div class="form-group">
                <label>Meta keywords</label>
                <textarea ng-model="entity.meta_keywords" class="form-control"></textarea>
            </div>
            <div class="text-center"><button class="btn btn-theme-ok">Save</button></div>
        </form>
    </div>
    <div class="clearfix"></div>
<jsp:include page="../include.jsp" />
<script src="/resources/js/utils/tinymce/tinymce.min.js"></script>
<script src="/resources/js/admin/info_pages/edit.js?id=${id}" id="loader"></script>
</body>
</html>
