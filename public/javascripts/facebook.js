/**
 * Created by james on 24/05/17.
 */

function checkLoginState() {
    FB.getLoginStatus(function(response) {
        statusChangeCallback(response);

    });

}

function faceBookLogin() {
    alert("in post to fb")
    FB.login(function () {
        FB.api('/me/feed', 'post', {message: 'Hello, world!'});
    }, {scope: 'publish_actions'});
}

/** get name of user **/
function getFBName() {
    FB.api('/me', {fields: 'last_name'}, function (response) {
        console.log(response.last_name);
        document.getElementById("fbName").innerHTML = response.last_name;
    })
}

function getLoginInfo() {
    FB.getLoginStatus(function (response) {
        //statusChangeCallback(response);
        console.log(response.authResponse.accessToken);
        document.getElementById("fbLoginInfo").innerHTML = response.authResponse.accessToken;
    });
}

function logOutOfFb(){
    FB.logout(function (response) {

    });
}