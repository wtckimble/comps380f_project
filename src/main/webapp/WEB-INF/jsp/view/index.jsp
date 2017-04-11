<!DOCTYPE html>
<html>
    <head>
        <title>Course Discussion Forum</title>
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
    <a href="<c:url value="/lecture" />">Lecture</a><br>
    <a href="<c:url value="/lab" />">Lab</a><br>
    <a href="<c:url value="/other" />">Other</a><br>
</body>
</html>