<%-- 
    Document   : editUser
    Created on : Apr 12, 2017, 5:23:49 PM
    Author     : German
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User</title>
    </head>
    <body>
        ${username}<br />
        [<a href="<c:url value="/promoteUser" />">Promote User</a>]<br />
        [<a href="<c:url value="/deleteUser" />">Delete User</a>]<br />
    </body>
</html>
