<!DOCTYPE html>
<html>  
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <script type="text/javascript" src="login.js"></script>
</head>
<body id="home">
    <div class="nav" id="nav">
        <ul>
            
            <li><a href="login">
                <div id="logo"></div>
            </a></li>
            <li style="float: right;"><a href="signup">Sign Up</a></li>

        </ul>
    </div>

    <div style="display: flex; justify-content: center; position: relative; top: 30vh;">
        <div id="login">
            <h2>Log In</h2>
            <form id="loginform" onsubmit="logIn(event)">
                <input type="text" name="username" placeholder="Username">
                <input type="password" name="password" placeholder="Password">
                <input type="checkbox" id="show-pass" onclick="showPass()" style="margin: 0; vertical-align:middle; cursor: pointer;">
                <label for="show-pass" style="font-size: 13px; vertical-align:middle;">Show Password</label>
                <button type="submit" id="login-button" >Log In</button>
            </form>
        </div>
    </div>        

</body>
</html>
