(function(){

    var app = angular.module('edit', []);
    var menu_item_template = new EJS({url: '/resources/template/admin/menu/menu_item.ejs'});
    var MENU_ID;

    app.controller('edit_forms', function($scope, $http) {
        var params = UrlUtil.parse(angular.element('#loader').attr('src'));
        $scope.entity = { };

        MENU_ID = params.id;

        if (parseInt(params.id)) {
            $http.get(
                '/api/menu/' + params.id,
                {
                    params: {
                        fields: 'id,name,menu_items'
                    }
                }
            )
                .then(function(response){
                    if (response.data.result) {
                        $scope.entity = response.data.result;

                        var menu_item_ids = $scope.entity.menu_items;

                        if (menu_item_ids && menu_item_ids.length) {
                            $http.get(
                                '/api/menu/item/',
                                {
                                    params: {
                                        fields: 'id,name,url,parent_menu,child_items',
                                        restrict: JSON.stringify({ids: menu_item_ids})
                                    }
                                }
                            )
                                .then(function(response){
                                    var menus = response.data.result;

                                    if (menus) {
                                        menus.forEach(initMenuItem);
                                    }
                                });
                        }
                    } else {
                        showErrorMessage(response.data.error);
                        redirectAfterFreeze('/admin/menu/all');
                    }
                })
        } else {
            showErrorMessage('menus can not be created');
            redirectAfterFreeze('/admin/menu/all');
        }

        save($scope, $http);

        $('#add_global_menu_item').click(function(){
            var $name = $('#global_menu_name'),
                $url = $('#global_menu_url');

            var new_menu_item = {
                name: $name.val(),
                url: $url.val(),
                parent_menu: 0,
                menu: MENU_ID
            };

            Ajax.put({
                url: '/api/menu/item/',
                data: new_menu_item,
                success: function(response) {
                    if (response.result) {
                        new_menu_item.id = response.result;

                        initMenuItem(new_menu_item);

                        $name.val('');
                        $url.val('');
                    } else {
                        showErrorMessage(response.error);
                    }
                }
            })
        });
    });

    function initMenuItem(menu_item){
        var $html = $(menu_item_template.render({menu: menu_item}));

        $html.find('.show-hide').click(function () {
            $html.find('.list').toggle();
        });

        $html.find('.delete').click(function(){
            var length = $('.list[data-menu=' + (+menu_item.id) + ']').find('*').length;

            if (length > 0) {
                if (!confirm('All children menu items will be deleted too. Continue?')) {
                    return;
                }
            }

            Ajax.delete({
                url: '/api/menu/item/' + menu_item.id,
                success: function(response) {
                    if (response.result) {
                        $html.remove();
                    } else {
                        showErrorMessage(response.error);
                    }
                }
            })
        });

        $html.find('.update').click(function(){
            var name = $html.find('[name=name][data-menu=' + menu_item.id + ']').val(),
                url = $html.find('[name=url][data-menu=' + menu_item.id + ']').val();

            Ajax.post({
                url: '/api/menu/item/',
                data: {
                    name: name,
                    url: url,
                    id: menu_item.id
                },
                success: function(response) {
                    if (response.result) {
                        $html.find('.name[data-menu=' + menu_item.id + ']').text(name);
                        showSuccessMessage('saved');
                    } else {
                        showErrorMessage(response.error);
                    }
                }
            })
        });

        $html.find('.add-item').click(function(){
            var $name = $html.find('[name=menu_name_new][data-menu=' + menu_item.id + ']'),
                $url = $html.find('[name=menu_url_new][data-menu=' + menu_item.id + ']');

            if (!$name.val() || !$url.val()) {
                showErrorMessage('name and url are required');

                return;
            }

            var new_menu_item = {
                name: $name.val(),
                url: $url.val(),
                parent_menu: menu_item.id,
                menu: MENU_ID
            };

            Ajax.put({
                url: '/api/menu/item/',
                data: new_menu_item,
                success: function(response) {
                    if (response.result) {
                        new_menu_item.id = response.result;

                        initMenuItem(new_menu_item);

                        $name.val('');
                        $url.val('');
                    } else {
                        showErrorMessage(response.error);
                    }
                }
            })
        });

        $('.list[data-menu=' + (+menu_item.parent_menu) + ']').append($html);
    }


    function save($scope, $http) {
        $scope.save = function() {
            $http.post('/api/menu/', JSON.stringify($scope.entity), {headers: HEADERS})
                .then(function (response) {
                    if (response.data.result) {
                        showSuccessMessage('saved');
                    } else {
                        showErrorMessage(response.data.error);
                    }
                });
        };
    }
})();