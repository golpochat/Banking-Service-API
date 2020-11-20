/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var customerId = readCookie("loginID");
var accID;
var externalCustomerID;
var accountsdata;


//Gets the data of the accounts for the current user and assigns them to the dropdown
$(document).ready(function () {
    $.ajax({
        url: ("http://localhost:49000/api/customers/" + customerId + "/accounts")
    }).then(function (data) {
        if (Array.isArray(data) && data.length) {
            window.accountsdata = data;
            $('.dropdown select').empty();
            for (var i = 0; i < data.length; i++) {
                $('.dropdown select').append('<option value=' + i + '>' + data[i].accountName + '</option>');
            }
            window.accID = document.getElementById("accountSelector");
            $('#balance').text(accountsdata[accID.value].currentBalance);
            $('#sortCode').text(accountsdata[accID.value].sortCode);
        } else {
            $('.dropdown select').append('<option value=0> Select Account </option>');
            alert("Please create an account");
        }

    });
});

//This gets the transactions for the current customers account
function getTransactions() {
    window.accID = document.getElementById("accountSelector");
    if (accID.value !== null) {
        var strUser = (parseInt(accID.value) + 1);
    } else {
        var strUser = 1;
    }
    $.ajax({
        url: ("http://localhost:49000/api/customers/" + customerId + "/accounts/" + strUser + "/transactions")
    }).then(function (data) {
        if (Array.isArray(data) && data.length) {
            $('.accountsTransactions').text("");
            $('.accountsTransactions').append('<tr><th>Date</th><th>Description</th><th>Balance</th><th>Transaction ID</th><th>Amount</th><th>Type</th></tr>');
            for (var i = 0; i < data.length; i++) {
                $('.accountsTransactions').append('<tr>' + '<td>' + data[i].date + '</td>' + '<td>' + data[i].description + '</td>' + '<td>' + data[i].postBalance + '</td>' + '<td>' + data[i].transactionID + '</td>' + '<td>' + data[i].transactionAmount + '</td>' + '<td>' + data[i].transactionType + '</td>' + '</tr>');
            }
        } else {
            $('.accountsTransactions').text("No transactions for this account yet");
        }

    });
}
//for transfer to external accounts
$(document).ready(function () {
    $('#dropdownexternalaccount select').append('<option value=0> Select Account </option>');
    $.ajax({
        url: ("http://localhost:49000/api/customers/all")
    }).then(function (data) {
        if (Array.isArray(data) && data.length) {
            $('#dropdownexternal select').empty();
            $('#dropdownexternal select').append('<option value="" disabled selected>Select Customer</option>');
            for (var i = 0; i < data.length; i++) {
                $('#dropdownexternal select').append('<option value=' + i + '>' + data[i].name + '</option>');
            }
        } else {
            $('.dropdownexternal select').append('<option value=0> No Clients </option>');
        }

    });
});

// Checks if the dropdown has change for external Customers
// Updates the external accounts dropdown for the Customer change
$(document).ready(function () {
    $('#dropdownexternal').change(function () {
        window.externalCustomerID = document.getElementById("externalCustomerSelector");
        if (externalCustomerID.value !== null) {
            var strUser = (parseInt(externalCustomerID.value) + 1);
        } else {
            var strUser = 0;
        }
        if (strUser > 0) {
            $.ajax({
                url: ("http://localhost:49000/api/customers/" + strUser + "/accounts")
            }).then(function (data) {
                if (Array.isArray(data) && data.length) {
                    $('#dropdownexternalaccount select').empty();
                    $('#dropdownexternalaccount select').append('<option value="" disabled selected>Select Account</option>');
                    for (var i = 0; i < data.length; i++) {
                        $('#dropdownexternalaccount select').append('<option value=' + i + '>' + data[i].accountName + '</option>');
                    }
                } else {
                    $('#dropdownexternalaccount select').append('<option value=0> No Accounts </option>');
                }

            });

        }

    });
});


// When a customer picks a different account it updates the displayed account balance
$(document).ready(function () {
    $('#dropdown').change(function () {
        window.accID = document.getElementById("accountSelector");
        if (accID.value !== null) {
            var strUser = accID.value;
        } else {
            var strUser = -1;
        }
        if (strUser > -1) {
            $('#balance').text(accountsdata[strUser].currentBalance);

        }

    });
});


// checks which account is selected and what the account detailsa are and sends a request to lodge funds
function lodgefunds() {
    var e = document.getElementById("accountSelector");
    var strUser = parseInt(e.value) + 1;
    sendRequest(("http://localhost:49000/api/customers/" + customerId + "/accounts/" + strUser + "/transactions/lodge"), getLodgeDetails(), "post");
}

// Gets and returns the details for lodging funds
function getLodgeDetails() {
    var description = $("#description").val();
    var lodgeAmount = $("#lodgeAmount").val();

    var lodgeDetails = {
        "description": description,
        "transactionAmount": Math.abs(lodgeAmount)
    };

    return lodgeDetails;
}
// Withdraws funds to atm
function withdrawfunds() {
    var e = document.getElementById("accountSelector");
    var strUser = parseInt(e.value) + 1;
    sendRequest(("http://localhost:49000/api/customers/" + customerId + "/accounts/" + strUser + "/transactions/withdraw"), getWithdrawDetails(), "post");
}

// Gets and returns the details for withdrawing funds
function getWithdrawDetails() {
    var description = $("#withdrawDescription").val();
    var withdrawAmount = $("#withdrawAmount").val();

    var withdrawDetails = {
        "description": description,
        "transactionAmount": Math.abs(withdrawAmount)
    };

    return withdrawDetails;
}


// Transfers funds between two internal accounts
function transferfunds() {
    var e = document.getElementById("accountSelector");
    var strUser = parseInt(e.value) + 1;
    var f = document.getElementById("otherAccountSelector");
    var otherAccount = parseInt(f.value) + 1;
    sendRequest(("http://localhost:49000/api/customers/" + customerId + "/accounts/" + strUser + "/transactions/transfer/" + otherAccount), getTransferDetails(), "post");
}
// This function sends funds to other account and reduces them from original account
function transferfundsExternal() {
    // customers/*/accounts/#/transactions/transfer/@?otherCustomerID=&
    var e = document.getElementById("accountSelector");
    var strUser = parseInt(e.value) + 1;
    var f = document.getElementById("externalAccountSelector");
    var otherAccount = parseInt(f.value) + 1;
    var k = document.getElementById("externalCustomerSelector");
    var otherCustomer = parseInt(k.value) + 1;
    sendRequest(("http://localhost:49000/api/customers/" + customerId + "/accounts/" + strUser + "/transactions/transfer/" + otherAccount + "?otherCustomerID=" + otherCustomer), getExternalTransferDetails(), "post");
}
// Gets and returns the details for external funds transfer
function getExternalTransferDetails() {
    var description = $("#transferDescriptionEx").val();
    var transferAmount = $("#transferAmountEx").val();

    var transferDetails = {
        "description": description,
        "transactionAmount": Math.abs(transferAmount)
    };

    return transferDetails;
}
// Gets and returns the details for internal funds transfer
function getTransferDetails() {
    var description = $("#transferDescription").val();
    var transferAmount = $("#transferAmount").val();

    var transferDetails = {
        "description": description,
        "transactionAmount": Math.abs(transferAmount)
    };

    return transferDetails;
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
            location.reload();
            console.log(" Successfull");
        },
        type: method,
        headers: {
            Accept: 'application/json;charset=utf-8',
            'Content-Type': 'application/json'
        },
        dataType: 'json'
    });
}