/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Gets and returns the user details from the html page
function getUserDetails() {
    var name = $("#inputName").val();
    var userName = $("#inputUserName").val();
    var password = $("#inputPassword").val();
    var correspondenceAddress = $("#inputAddress").val();
    var email = $("#inputEmail").val();


    var userDetails = {
        "name": name,
        "userName": userName,
        "password": password,
        "correspondenceAddress": correspondenceAddress,
        "email": email
    };

    return userDetails;
}

// Creates a customer and checks if they don't already exist
function createuser() {
    //alert($("#inputName").val() + " " + $("#inputUserName").val() + " " + $("#inputPassword").val() + " " + $("#inputEmail").val() + " " + $("#inputAddress").val());
    $.ajax({
        url: ("http://localhost:49000/api/customers/?username=" + $("#inputUserName").val())
    }).then(function (data) {
        if (data.id !== 0) {
            alert("Username is already in use please pick another");
        } else {
            sendRequest("http://localhost:49000/api/customers", getUserDetails(), "post");
            window.location.assign("http://localhost:49000/signin.html/");
        }
    });

}

// Sends the request to api
function sendRequest(url, input, method) {
    $.ajax({
        url: url,
        data: JSON.stringify(input),
        error: function (response) {
            //displayResponseMessage(response);
            console.log("Error");
        },
        success: function (response) {
            console.log(" Successfull");
            alert("Thanks for signing up " + response.name + " now please sign in");
        },
        type: method,
        headers: {
            Accept: 'application/json;charset=utf-8',
            'Content-Type': 'application/json'
        },
        dataType: 'json'
    });
}