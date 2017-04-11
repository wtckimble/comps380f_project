<%-- 
    Document   : topic
    Created on : Apr 6, 2017, 4:37:51 PM
    Author     : German
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <security:authorize access="isAuthenticated() ">
        Welcome, <security:authentication property="principal.username" />   <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </security:authorize>
    <security:authorize access="isAnonymous()">
        <a href="<c:url value="/login" />">Login</a>   
        <a href="<c:url value="/register" />">Register</a>
    </security:authorize><br><br>
    <h1>Hello World!</h1>
</body>
</html>
