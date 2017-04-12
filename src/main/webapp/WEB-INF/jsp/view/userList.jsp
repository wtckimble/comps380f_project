<%-- 
    Document   : userList
    Created on : Apr 12, 2017, 4:51:15 PM
    Author     : German
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users List</title>
    </head>
    <body>
        <h2>Users List</h2>
        <c:choose>
            <c:when test="${fn:length(userlist) == 0}">
                <i>There are no user in the system.</i>
            </c:when>
            <c:otherwise>
                <c:forEach items="${userlist}" var="entry">
                    ${entry.username}:${entry.roles}-----
                        <security:authorize access="hasRole('ROLE_ADMIN') ">
                            [<a href="<c:url value="/promoteUser/${entry.username}" />">Promote User</a>]
                            [<a href="<c:url value="/deleteUser/${entry.username}" />">Delete User</a>]
                        </security:authorize>
                    <br/>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        <br>
        <br>
        <br>
        <a href="<c:url value="/" />">Back</a>
    </body>
</html>
