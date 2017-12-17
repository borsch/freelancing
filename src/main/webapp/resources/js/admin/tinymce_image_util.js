(function(exports){

    exports.append_images_buttons = function(editor) {
        editor.addButton('upload_image', {
            text: 'Upload image',
            onClick: function(){
                editor.windowManager.open({
                    title: 'Upload image form',
                    url: '/resources/template/tinymce/upload_image_popup.html',
                    width: 400,
                    height: 200
                });
            }
        });

        editor.addButton('select_image', {
            text: 'Select image from storage',
            onClick: function(){
                editor.windowManager.open({
                    title: 'Upload image form',
                    url: '/resources/template/tinymce/select_image_popup.html',
                    width: 800,
                    height: 600
                });
            }
        });
    };

    exports.images_fields = 'upload_image select_image';

})(window.TinyMCEUtil = {});