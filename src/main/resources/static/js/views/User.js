import createView from "../createView.js";

const BASE_URL = "http://localhost:8080/api/users"

export default function UserIndex(props) {
    //language=HTML
    return `
        <header>
            <h1>Welcome, ${props.user.username}</h1>
        </header>
        <main>
            <form id="user-info-form">
                <label for="username">Username</label>
                <input disabled id="username" type="text" name="username" value="${props.user.username}">
                <label for="email">Email</label>
                <input disabled id="email" type="email" name="email" value="${props.user.email}">
                <label for="old-password">Old Password</label>
                <input disabled id="old-password" name="old-password" type="password" value="Old Password"/>
                <label for="new-password">New Password</label>
                <input id="new-password" name="new-password" type="password" value="New Password"/>
                <button id="update-password-btn" data-id="${props.user.id}" type="submit">Update Password</button>
            </form>
        </main>
    `
}

export function UserEvent() {
    addUpdatePasswordListener();
}

function addUpdatePasswordListener() {
    $(document).on('click', '#update-password-btn', function (e) {
        e.preventDefault();
        const id = $(this).data("id");
        const newPassword = $("#new-password").val();
        const request = {
            method: "PUT",
            headers: {
                'Content-Type': 'application/json'
            }
        }

        fetch(`${BASE_URL}/${id}/updatePassword?newPassword=${newPassword}`, request)
            .then(res => {
                console.log(res.status);
            }).catch(error => {
            console.log(error);
        }).finally(() => {
            createView("/user")
        })
    })
}
