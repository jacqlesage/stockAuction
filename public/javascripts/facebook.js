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
    FB.api('/me', {fields: 'first_name,last_name'}, function (response) {
        console.log(response.first_name);
        console.log(response.last_name);
        document.getElementById("fbFName").innerHTML = response.first_name;
        document.getElementById("fbLName").innerHTML = response.last_name;
    })
}

function getLoginInfo() {
    FB.getLoginStatus(function (response) {
        //statusChangeCallback(response);
        console.log(response.authResponse.accessToken);
        document.getElementById("fbLoginInfo").innerHTML = response.authResponse.accessToken;
    });
}

function getInfoAboutMe() {
    FB.api('/me', {fields: 'email'}, function (response) {
        document.getElementById("fbLoginInfo").innerHTML = response;
        console.log(JSON.stringify(response));
        console.log(response.email);
    });
}

function logOutOfFb(){
    FB.logout(function (response) {

    });
}