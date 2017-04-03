<html>
    <body>
    <h1>Login</h1> 
    <form action="login" method='POST'>
        User: <input type='text' name='username'><br />
        Password: <input type='password' name='password' /><br />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input name="submit" type="submit" value="Log In" /><br />
    </form>
        <a href="/register">Register</a><br>
    </body>
</html>
