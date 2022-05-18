import createView from "../createView.js";

const BASE_URL = "http://localhost:8080/api/posts";
let requestMethod = "POST";
let postId = "";

export default function PostIndex(props) {
    // language=HTML
    return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
            <div id="posts-container">
                ${props.posts.map(post => `<h3 id="title-${post.id}">${post.title}</h3>
                                            <p id="content-${post.id}">${post.content}</p>
<button type="submit" class="btn btn-primary edit-button" data-id="${post.id}">Edit</button>
<button type="submit" class="btn btn-danger delete-button" data-id="${post.id}">Delete</button>`).join('')}
            </div>
            <div id="add-post-form">
                <div>
                    <input type="text" class="form-control" id="add-post-title" placeholder="Add Post Title">
                </div>
                <br>
                <div>
                    <textarea class="form-control" rows="4" id="add-post-content"
                              placeholder="Add Post Content"></textarea>
                </div>
                <br>
                <div>
                    <button type="submit" class="btn btn-primary" id="submit-btn">Submit</button>
                </div>
            </div>
        </main>
    `;
}

export function PostsEvent() {
    createSubmitPostListener();
    createEditPostListener();
    createDeletePostListener();
}

function createSubmitPostListener() {
    $(document).on('click', '#submit-btn', function (e) {
        e.preventDefault();
        const newPost = {
            title: $("#add-post-title").val(),
            content: $("#add-post-content").val()
        }

        const request = {
            method: requestMethod,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newPost)
        }

        let requestUrl = "";

        if (postId !== "") {
            requestUrl = `${BASE_URL}/${postId}`;
        } else {
            requestUrl = `${BASE_URL}`;
        }

        fetch(requestUrl, request)
            .then(res => {
                console.log(res.status);
                // createView("/posts")
            }).catch(error => {
            console.log(error);
            // createView("/posts");
        }).finally(() => {
            postId = "";
            requestMethod = "POST";
            createView("/posts")
        })

    })
}

function createEditPostListener() {
    $(document).on('click', '.edit-button', function (e) {
        e.preventDefault();
        postId = $(this).data("id");
        requestMethod = "PUT";

        const postTitle = $(`#title-${postId}`).text();
        const postContent = $(`#content-${postId}`).text();

        $("#add-post-title").val(postTitle);
        $("#add-post-content").val(postContent);
    })
}

function createDeletePostListener() {
    $(document).on('click', '.delete-button', function (e) {
        e.preventDefault();

        const id = $(this).data("id");

        const request = {
            method: "DELETE"
        }

        fetch(`${BASE_URL}/${id}`, request)
            .then(res => {
                console.log(res.status);
                // createView("/posts")
            }).catch(error => {
            console.log(error);
            // createView("/posts");
        }).finally(() => {
            createView("/posts")
        })
    })
}