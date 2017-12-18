<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<compress:html removeIntertagSpaces="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html>
    <head>
        <jsp:include page="../common/include_external_head_top.jsp" />
        <title>Developers</title>
        <jsp:include page="../common/include_resources.jsp" />
        <style>
            .developer {
                border-bottom: 1px solid black;
                margin-bottom: 20px;
            }

            .developer:last-of-type {
                border-bottom: none;
                margin-bottom: inherit;
            }
        </style>
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
                            <select name="skill_level" class="form-field col-xs-12">
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
                <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2" id="developers_list">
                </div>
            </section>
        </div>
    </div>

    <div class="col-xs-12 developer" id="developer_clone" style="display: none;">
        <p><strong>Name: </strong> <span data-field="user_name"></span></p>
        <p><strong>Rating: </strong> <span data-field="rating"></span></p>
        <p><strong>Skill level: </strong> <span data-field="skill_level"></span></p>
        <p><strong>Tags: </strong> <span data-field="tags"></span></p>
        <p><strong>Projects amount: </strong> <span data-field="projects_amount"></span></p>
    </div>

    <jsp:include page="../common/footer.jsp" />
    <script>
        var $developer_clone = $('#developer_clone'),
            $developers_list = $('#developers_list');

        $('#filter_form').submit(function(e){
            e.preventDefault();

            $developers_list.empty();

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
                url: '/api/developers/?fields=user_name,rating,skill_level,tags,projects_amount&restrict=' + encodeURIComponent(JSON.stringify(restrict)),
                success: function(response) {
                    var developers = response.result;

                    if (developers) {
                        developers.forEach(function(dev){
                            var $clone = $developer_clone.clone();
                            $clone.show();

                            for (var key in dev) {
                                var value = dev[key];

                                if (key === 'tags') {
                                    value = '[' + value.join(', ') + ']';
                                } else if (key === 'rating') {
                                    value = value.toFixed(1) + '/5';
                                }

                                $clone.find('[data-field=' + key + ']').text(value);
                            }

                            $developers_list.append($clone);
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

