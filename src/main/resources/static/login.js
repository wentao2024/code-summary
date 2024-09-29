function event() {
    alert("Hello, World!");
}

function showPass() {
    let password = document.getElementsByName("password")[0];
    if (password.type === "password") {
        password.type = "text";
    } else {
        password.type = "password";
    }
}

function signUp(event) {
    event.preventDefault();

    let username = document.getElementsByName("username")[0].value;
    let password = document.getElementsByName("password")[0].value;

    if (username === "" || password === "") {
        alert("Please fill in all fields!");
    } else {
        // Make a POST request to the registration API
        fetch('/api/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert("Registration successful! Please log in.");
                window.location.replace("login");
            } else {
                alert("Registration failed: " + data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("An error occurred during registration. Please try again later.");
        });
    }
}


function logIn(event) {
    event.preventDefault();

    let username = document.getElementsByName("username")[0].value;
    let password = document.getElementsByName("password")[0].value;

    if (username === "" || password === "") {
        alert("Please fill in all fields!");
    } else {
        // Make a POST request to the login API
        fetch('/api/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert("Login successful! Welcome, " + username + "!");
                const cookie = data.cookie;
                // Set the cookie in the browser
                document.cookie = cookie;
                window.location.replace("code-summarization");
                if (data.needChangePwd) {
                    window.location.replace("admin-change");
                }
            } else {
                alert("Login failed: " + data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("An error occurred during login. Please try again later.");
        });
    }
}
