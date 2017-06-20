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
        //document.getElementById("fbLoginInfo").innerHTML = response;
        console.log(JSON.stringify(response));
        console.log(response.email);
    });
}

function logOutOfFb(){
    FB.logout(function (response) {

    });

   }

function checkLoginState(){

    FB.getLoginStatus(function(response) {
        statusChangeCallback(response);

    });



}

function statusChangeCallback(response) {
    var $infoFromUser;
    console.log('statusChangeCallback');
    console.log(response);
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
        // Logged into your app and Facebook.
        //testAPI();
        //getInfoAboutMe();

        FB.api('/me', {fields: 'email, first_name, last_name'}, function (response) {
            //document.getElementById("fbLoginInfo").innerHTML = response;
            infoFromUser = JSON.stringify(response);
        });

        appRoutes.controllers.JavaApplicationDatabase.getAllUsers().ajax({
           // url: 'http://localhost:9000/test',
            type: 'get',
            dataType: 'json',
            success: function (data) {
                //console.log(data.);
                var peopleList = $('#peopleList');
                $.each(data, function (index, value) {
                    console.log(value.firstName);
                })

            }
        });

        //console.log(infoFromUser.toString() + " my own one");

        // routes.javascript.JavaApplicationDatabase.getAllUsers().ajax({
        //
        //
        //     }
        // });

    } else {
        // The person is not logged into your app or we are unable to tell.
        document.getElementById('status').innerHTML = 'Please log ' +
            'into this app.';
    }


}
//
// appRoutes.controllers.Application.create().ajax({
//     data : JSON.stringify(data),
//     contentType : 'application/json',
//     success : function (person) {
//         $('#peopleList').append('<li>' + person.name + ' <a href="#" data-id="' + person.id + '" class="deletePerson">Delete</a></li>');
//         personNameInput.val('');
//     }
// });
//peopleList.append('<li>' + person.name + ' <a href="#" data-id="' + person.id + '" class="deletePerson">Delete</a></li>');