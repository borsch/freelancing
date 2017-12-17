<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="all_users">
<head>
    <title>Users</title>
</head>
<body>
<jsp:include page="../header.jsp" />

<div class="col-xs-12" ng-controller="all_users">
    <div class="col-xs-12 page-title">All users</div>
    <div class="col-sm-6 total-count text-muted">
        Total number: {{total_count}}, on page: {{number_on_page}}
    </div>
    <div class="col-sm-6 text-right add-new"><a href="/admin/users/create" class="btn btn-theme-ok">Add new user</a></div>
    <div class="clearfix"></div>
    <div class="col-sm-3">
        <div class="block-content bg-pattern">
        <div class="page-subtitle">User filter</div>
        <form ng-submit="filterForm()">
            <div class="form-group">
                <label>Query</label>
                <input ng-model="filters.query" class="form-control">
            </div>
            <div class="form-group">
                <label>Role</label>
                <select ng-model="filters.role" class="form-control">
                    <option value="">All</option>
                    <option value="user">User</option>
                    <option value="admin">Admin</option>
                    <option value="moderator">Moderator</option>
                </select>
            </div>
            <div class="form-group">
                <label>Active</label>
                <select ng-model="filters.active" class="form-control">
                    <option value="">Ignore</option>
                    <option value="true">Yes</option>
                    <option value="false">No</option>
                </select>
            </div>
            <div class="text-center"><button class="btn btn-theme-ok">Filter</button></div>
        </form>
        </div>
    </div>
    <div class="col-sm-9">
        <div class="block-content">
            <table class="table">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Role</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <tr  ng-repeat="user in entities">
                    <td>
                        {{user.first_name}} {{user.last_name}}
                        <br />
                        {{user.email || '-'}}
                    </td>
                    <td>{{user.role}}</td>
                    <td>
                        <a href="/admin/users/{{user.id}}/edit" class="btn btn-theme-ok">Edit user</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <form ng-submit="moveToPage()">
            <div class="form-group text-right page-nav">
                <label>Page</label>
                <select id="pages" class="form-control">
                    <option value="0">1</option>
                </select>
                <button class="btn btn-theme-ok">Move to page</button>
            </div>
        </form>
        <div class="clearfix"></div>
    </div>
</div>
<div class="clearfix"></div>

<jsp:include page="../include.jsp" />
<script src="/resources/js/admin/users/all.js"></script>
</body>
</html>
