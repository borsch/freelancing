<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html>
    <head>
        <jsp:include page="../common/include_external_head_top.jsp" />
        <title>Projects</title>
        <jsp:include page="../common/include_resources.jsp" />
    </head>
    <body>
    <jsp:include page="../common/include_external_body_top.jsp" />
    <jsp:include page="../common/header.jsp"/>

    <div class="wrapper">
        <div class="container">
            <section>
                <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2">
                    <form id="filter_form" class="col-sm-12"><div class="row">
                        <div class="col-xs-12 col-sm-6">
                            <select name="min_skill_level" class="form-field col-xs-12">
                                <option value="" selected disabled>Minimum skill level</option>
                                <option value="">Any</option>
                                <option value="JUNIOR">Junior</option>
                                <option value="MIDDLE">Middle</option>
                                <option value="SENIOR">Senior</option>
                                <option value="GOD">God mode</option>
                            </select>
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <input type="text" name="tags" placeholder="Skills separated with comma" class="form-field col-xs-12" />
                        </div>
                    </div>
                        <div class="col-xs-12 text-center">
                            <button type="submit" class="btn-theme btn-2">Search</button>
                        </div>
                        <div class="clearfix"></div>
                    </form>
                </div>
                <div class="clearfix"></div>
                <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2" id="projects_list">
                </div>
            </section>
        </div>
    </div>

    <div class="col-xs-12 list-item" id="project_clone" style="display: none;">
        <p><strong>Name: </strong> <span data-field="name"></span></p>
        <p><strong>Status: </strong> <span data-field="status"></span></p>
        <p><strong>Minimum skill level: </strong> <span data-field="min_skill_level"></span></p>
        <p><strong>Tags: </strong> <span data-field="tags"></span></p>
        <p><strong>Developer rating: </strong> <span data-field="developer_rating"></span></p>
        <p><strong>Client rating: </strong> <span data-field="client_rating"></span></p>

        <p class="text-center">
            <a ><button class="btn-theme btn-2">View project</button></a>
        </p>
    </div>

    <jsp:include page="../common/footer.jsp" />
    <script>
        var $project_clone = $('#project_clone'),
            $projects_list = $('#projects_list');

        $('#filter_form').submit(function(e){
            e.preventDefault();

            $projects_list.empty();

            var restrict = {};

            $(this).find('[name]').each(function(){
                var $self = $(this);

                if ($self.attr('name') === 'tags') {
                    restrict.tags = $self.val().split(',');
                    restrict.tags.forEach(function(element, i, array){
                        array[i] = element.trim();
                    });

                    restrict.tags = restrict.tags.filter(function(e){
                        return !!e;
                    });
                } else {
                    restrict[$self.attr('name')] = $self.val();
                }
            });

            Ajax.get({
                url: '/api/projects/?fields=id,name,status,min_skill_level,tags,developer_rating,client_rating,developer_id,client_id&restrict=' + encodeURIComponent(JSON.stringify(restrict)),
                success: function(response) {
                    var projects = response.result;

                    if (projects) {
                        projects.forEach(function(dev){
                            var $clone = $project_clone.clone();
                            $clone.show();

                            for (var key in dev) {
                                var value = dev[key];

                                if (key === 'tags') {
                                    value = '[' + value.join(', ') + ']';
                                } else if (key === 'developer_rating' || key === 'client_rating') {
                                    value = value.toFixed(1) + '/5';
                                }

                                $clone.find('[data-field=' + key + ']').text(value);
                            }

                            $clone.find('a').attr('href', '/projects/' + dev.id + '/view');

                            $projects_list.append($clone);
                        });
                    } else {
                        showErrorMessage(response.error)
                    }
                }
            })
        });
    </script>
    </body>
    </html>
</compress:html>

