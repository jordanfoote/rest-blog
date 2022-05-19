import createView from "../createView.js";

export default function Register() {
    return `<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Register</title>
</head>
<body>
<h1>Register</h1>

<form id="register-form">
    <label for="username">Username</label>
    <input id="username" name="username" type="text"/>
    <label for="email">Email</label>
    <input id="email" name="email" type="text"/>
    <label for="password">Password</label>
    <input id="password" name="password" type="password"/>
    <input id="register-btn" type="submit" value="Register"/>
</form>
</body>
</html>`;
}

export function RegisterEvent() {
    $(document).on('click', '#register-btn', function (e) {
        const reqBody = {
            username: $('#username').val(),
            email: $('#email').val(),
            password: $('#password').val()
        }

        let request = {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(reqBody)
        };
        console.log(reqBody);
        console.log(request);
        fetch("http://localhost:8080/api/users", request)
            .then((response) => {
                console.log(response.status)
                createView("/");
            });
    })
}