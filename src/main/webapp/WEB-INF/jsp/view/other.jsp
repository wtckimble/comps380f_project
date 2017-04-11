<%-- 
    Document   : other
    Created on : 2017/3/31, 下午 10:19:41
    Author     : EricYan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>This is other page</title>
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
    <h1>Hello Other!</h1>
</body>
</html>
