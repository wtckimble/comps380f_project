<%-- 
    Document   : register
    Created on : 2017/3/31, 下午 10:24:14
    Author     : EricYan
--%>

<!DOCTYPE html>

<html>

    <head>
        <title>Register Page</title>
    </head>

    <c:if test="${not empty existed}">
        ${existed}
    </c:if>
    <br>
    <c:if test="${not empty pwNotMatched}">
        ${pwNotMatched}
    </c:if>
    <br>
    <body>
        <h2>Register</h2>
        <form method="POST" modelAttribute="registerForm" />">
            Username<br />
            <input type="text" name="username" /><br /><br />
            Password<br />
            <input type="password" name="password" /><br /><br />
            Confirm Password<br />
            <input type="password" name="password2" /><br /><br />
            <input type="submit" value="Register" />
        </form>
    </body>
</html>
