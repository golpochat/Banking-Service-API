/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var customerId = readCookie("loginID");
// Cookies functions were worked from https://www.quirksmode.org/js/cookies.html
function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ')
            c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0)
            return c.substring(nameEQ.length, c.length);
    }
    return null;
}

// Gets and returns the account name from html page
function getAccountDetails() {
    var accountName = $("#accountName").val();

    var accountDetails = {
        "accountName": accountName
    };

    return accountDetails;
}

// Creates an account with given account name
function createaccount() {
    sendRequest(("http://localhost:49000/api/customers/" + customerId + "/accounts/"), getAccountDetails(), "post");
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
            alert("You have created an account");
            window.location.assign("http://localhost:49000/account.html");
        },
        type: method,
        headers: {
            Accept: 'application/json;charset=utf-8',
            'Content-Type': 'application/json'
        },
        dataType: 'json'
    });
}


