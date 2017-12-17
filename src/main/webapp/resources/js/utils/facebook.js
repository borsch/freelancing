(function(exports){
    window.fbAsyncInit = function() {
        FB.init({
            appId            : SocialNetworks.Facebook.app_id,
            autoLogAppEvents : true,
            xfbml            : true,
            version          : 'v2.10'
        });
    };

    exports.getUserData = function(callback) {
        FB.login(function (response) {
            if (response.authResponse) {
                FB.api(
                    '/' + response.authResponse.userID,
                    'get',
                    { fields: 'first_name,last_name,email' },
                    function (response) {
                        callback && callback(response);
                    }
                );
            }
        }, {scope: 'public_profile,email'});
    };

    (function(d, s, id){
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) {return;}
        js = d.createElement(s); js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));
})(window.FaceBook = {});