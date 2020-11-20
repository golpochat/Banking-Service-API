
// Logs in the user if their details are correct or rejects them
$(document).ready(function () {
    $("button").click(function () {
        $.ajax({
            url: ("http://localhost:49000/api/customers/?username=" + $("#inputUsername").val() + "&" + "password=" + $("#inputPassword").val())
        }).then(function (data) {
            if (data.id !== 0) {
                createCookie('loginID', data.id, 7);
                window.location.assign("http://localhost:49000/account.html");
            } else {
                alert("Username or password is incorrect");
            }

        });
    });
});
