<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>This is lab page</title>
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
        <h2>lab List</h2>
        <a href="<c:url value="/lab/create" />">Create a lab Topic</a><br /><br />
        <c:choose>
            <c:when test="${fn:length(lablist) == 0}">
                <i>Sorry, there are no labs in the system.</i>
            </c:when>
            <c:otherwise>

                <c:forEach items="${lablist}" var="entry">
                    ${entry.id}<br>
                    <a href="<c:url value="/lab/view/${entry.id}"/>">
                        <c:out value="${entry.subject}" /></a>  
                        <security:authorize access="hasRole('ROLE_ADMIN') ">
                            <a href="<c:url value="/lab/delete/${entry.id}"/>">Delete</a>
                        </security:authorize>
                    <br/>
                    --------------------------------------------------------------------------------------------------
                    <br/>
                </c:forEach>

                <%--         
               <c:forEach items="${lablist}" var="entry">
                   lab Topics ${entry.value}: <br/>
                   <a href="<c:url value="/lab/view/${entry.key}" />">
                       <c:out value="${entry.value}" /></a>
               </c:forEach>
                --%>
            </c:otherwise>
        </c:choose>
        <a href="<c:url value="/" />">Back</a>
    </body>
</html>