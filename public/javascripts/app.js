$(document).ready(function() {
    var lock = new Auth0Lock(AUTH0_CLIENT_ID, AUTH0_DOMAIN);

    $('.btn-login').click(function(e) {
        e.preventDefault();
        console.log(AUTH0_CALLBACK_URL);
        lock.show({
            callbackURL: AUTH0_CALLBACK_URL
        });
    });
});