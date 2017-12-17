<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="user_edit">
<head>
    <title>Edit user #${id}</title>
</head>
<body>
    <jsp:include page="../header.jsp" />
    <div class="container" ng-controller="user_edit_forms">
        <div class="col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4">
            <div class="top-nav">
                <a href="/admin/users/all"><i class="fa fa-long-arrow-left" aria-hidden="true"></i>Back to all users</a>
            </div>
            <div class="page-title">Create / Edit user</div>
            <form ng-submit="saveUser();" class="edit-form">
                <div class="form-group">
                    <label>Email</label>
                    <input type="text" ng-model="user.email" class="form-control">
                </div>
                <div class="form-group">
                    <label>First name</label>
                    <input type="text" ng-model="user.first_name" class="form-control">
                </div>
                <div class="form-group">
                    <label>Last name</label>
                    <input type="text" ng-model="user.last_name" class="form-control">
                </div>
                <div class="form-group">
                    <label>Role</label>
                    <select ng-model="user.role" class="form-control">
                        <option value="user">user</option>
                        <option value="moderator">moderator</option>
                        <option value="admin">admin</option>
                    </select>
                </div>
                <div class="form-group">
                    <input type="checkbox" ng-model="user.active" class="form-control checkbox">
                    <label>Active</label>
                </div>
                <div class="text-center">
                    <button class="btn btn-theme-ok">Save</button>
                </div>
            </form>
            <div ng-show="user.id">
                <div class="hr-separator"></div>
                <form ng-submit="updatePassword();">
                    <div class="form-group">
                        <label>Password</label>
                        <input type="text" ng-model="user.password" class="form-control">
                    </div>
                    <div class="text-center">
                        <button class="btn btn-theme-ok">Save</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/users/edit.js?id=${id}" id="loader"></script>
</body>
</html>
