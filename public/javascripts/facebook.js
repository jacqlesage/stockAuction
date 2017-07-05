/**
 * Created by james on 24/05/17.
 */

function checkLoginState() {
    FB.getLoginStatus(function(response) {
       // statusChangeCallback(response);

    });



}


function faceBookLogin() {
    alert("in post to fb");
    FB.api('/me', {fields: 'email, first_name, last_name'}, function (response1) {

        $.ajax({
            url: "http://localhost:9000/saveFbInfo",
            type: "POST",
            datatype: "json",
            data: response1,
            success: function (d) {

               // alert(d + "is winning" + " " + response1.toString());
            },

            error: function (e) {

                alert(e + " is not winning");
            }
        });
    // FB.login(function () {
    //     FB.api('/me/feed', 'post', {message: 'Hello, world!'});
    // }, {scope: 'publish_actions'});

        $("#test").html(response1.first_name);
});
}

/** get name of user **/
function getFBName() {
    FB.api('/me', {fields: 'first_name,last_name'}, function (response) {
        console.log(response.first_name);
        console.log(response.last_name);
        // document.getElementById("fbFName").innerHTML = response.first_name;
        // document.getElementById("fbLName").innerHTML = response.last_name;
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





function fbUser() {
    return FB.api('/me', {fields: 'email, first_name, last_name'}, function (response) {

          $.ajax({
            url: "http://localhost:9000/saveFbInfo",
            type: "POST",
            datatype: "json",
            data: response,
            success: function (d) {

                //alert(d + "is winning");
            },

            error: function (e) {

                alert(e + " is not winning");
            }
        });

        //callback(response);


    });


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