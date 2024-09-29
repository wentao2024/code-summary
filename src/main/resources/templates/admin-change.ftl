<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Admin Password</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f2f2f2; /* Background color for the entire page */
        }

        .container {
            background-color: #ffffff; /* Background color for the container */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            width: 300px; /* Set a fixed width for better presentation */
        }

        h2 {
            margin-top: 0; /* Remove default margin for the heading */
            color: #333333; /* Text color for the heading */
            text-align: center; /* Center-align the heading */
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            color: #666666; /* Text color for labels */
            margin-bottom: 5px; /* Add a bit of space below each label */
        }

        input[type="password"],
        input[type="submit"] {
            padding: 8px; /* Increase padding for better input field appearance */
            border: 1px solid #cccccc; /* Add a subtle border for input fields */
            border-radius: 5px; /* Add some border radius to soften the edges */
            margin-bottom: 10px; /* Add space below each input field */
        }

        input[type="submit"] {
            background-color: #4CAF50; /* Background color for the submit button */
            color: white; /* Text color for the submit button */
            cursor: pointer; /* Change cursor to pointer on hover */
        }

        input[type="submit"]:hover {
            background-color: #45a049; /* Darker background color on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Change Admin Password</h2>
        <form id="changePasswordForm">
            <label for="currentPassword">Current Password:</label>
            <input type="password" id="currentPassword" name="currentPassword" required><br>
            <label for="newPassword">New Password:</label>
            <input type="password" id="newPassword" name="newPassword" required><br>
            <label for="confirmPassword">Confirm New Password:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required><br>
            <input type="submit" value="Change Password">
        </form>
    </div>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        const form = document.getElementById("changePasswordForm");

        form.addEventListener("submit", function(event) {
            event.preventDefault(); // Prevent the default form submission

            // Fetch the form data
            const formData = new FormData(form);
            const currentPassword = formData.get("currentPassword");
            const newPassword = formData.get("newPassword");
            const confirmPassword = formData.get("confirmPassword");

            if (newPassword !== confirmPassword) {
                alert("New password and confirm password do not match.");
                return; // Exit early if passwords do not match
            }

            if (currentPassword === newPassword) {
                alert("New password should be different from the current password.");
                return; // Exit early if new password is the same as current password
            }

            const requestData = {
                currentPassword: formData.get("currentPassword"),
                newPassword: formData.get("newPassword")
            };

            // Send a POST request to the server
            fetch("/api/changeAdminPassword", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(requestData)
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert(data.message); // Show success message
                    window.location.replace("code-summarization");
                } else {
                    alert("Error: " + data.message); // Show error message
                }
            })
            .catch(error => {
                console.error("Error:", error);
                alert("An error occurred. Please try again."); // Show generic error message
            });
        });
    });


</script>


</body>
</html>