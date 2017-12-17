(function(){

    var chat_message_template = new EJS({url: '/resources/template/requests/chat_message.ejs'});

    var current_user_id;

    var LIMIT = 10;
    var RESTRICTION = {
        order_direction: 'DESC',
        offset: 0,
        limit: LIMIT + 1
    };

    $(function(){
         var params = UrlUtil.parse($('#loader').attr('src'));
        current_user_id = parseInt(params.current_user_id);
        RESTRICTION.request_ids = [params.id];

        loadComments();

        $('#more_comments').click(function(){
            RESTRICTION.offset += LIMIT;

            loadComments();
        });

        $('#add_comment_form').submit(function(e){
            e.preventDefault();

            var comment = {
                text: $('#comment_text').val(),
                user_id: current_user_id,
                request_id: params.id,
                has_file: false
            };

            Ajax.put({
                url: '/api/request/comment/',
                data: comment,
                success: function(response){
                    comment.id = response.result;
                    comment.date = new Date().getTime();

                    if (comment.id) {
                        ++RESTRICTION.offset;
                        $('#comment_text').val('');

                        var file = $('#comment_file')[0].files[0];

                        if (file) {
                            comment.has_file = true;
                            var $html = renderComment(comment);
                            $html.find('.file-block').hide();
                            $('#comments').prepend($html);

                            showInfoMessage('uploading file..');
                            Ajax.uploadFile(
                                '/api/file/',
                                {
                                    id: comment.id,
                                    file: file,
                                    type: 'REQUEST_COMMENT'
                                },
                                function(response) {
                                    if (response.result) {
                                        showSuccessMessage('file uploaded');
                                        $html.find('.file-block').show();
                                        $('#comment_file').val('').trigger('change');
                                    } else {
                                        var e = response.error;
                                        showErrorMessage(e.message + buildValidationErrors(e.errors));
                                    }
                                }
                            )
                        } else {
                            $('#comments').prepend(renderComment(comment));
                        }
                    } else {
                        showErrorMessage(response.error);
                    }
                }
            })
        });

        $(document).ready( function() {
            $(".inputfile").change(function(){
                var filename = $(this).val().replace(/.*\\/, "");
                $("#filename").val(filename);
            });
        });
    });

    function loadComments() {
        Ajax.get({
            url: '/api/request/comment/?fields=id,user_id,text,has_file,date&restrict=' + encodeURI(JSON.stringify(RESTRICTION)),
            success: function(response) {
                var comments = response.result;
                if (comments) {
                    if (comments.length <= LIMIT) {
                        $('#more_comments').hide();
                    } else {
                        comments.pop();
                    }

                    comments.forEach(function(comment){
                        var result = renderComment(comment);

                        $('#comments').append(result);
                    });
                } else {
                    $('#more_comments').hide();
                }
            }
        });
    }

    function renderComment(comment) {
        var $html = $(chat_message_template.render(comment));

        if (comment.user_id === current_user_id) {
            $html.find('.user').text('Me');
        } else {
            $html.find('.user').text('Moderator');
        }

        return $html;
    }
})();