<%-- 
    Document   : lecture
    Created on : 2017/3/31, 下午 10:16:17
    Author     : EricYan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>This is lecture page</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${not empty username}">
                Welcome, ${username}  <c:url var="logoutUrl" value="/logout"/>
                <form action="${logoutUrl}" method="post">
                    <input type="submit" value="Log out" />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </c:when>
            <c:otherwise>
                <a href="<c:url value="/login" />">Login</a>   
                <a href="<c:url value="/register" />">Register</a>
            </c:otherwise>
        </c:choose> <br>
        <h2>Lecture List</h2>
        <a href="<c:url value="/lecture/create" />">Create a Lecture Topic</a><br /><br />
        <c:choose>
            <c:when test="${fn:length(lecturelist) == 0}">
                <i>Sorry, there are no lectures in the system.</i>
            </c:when>
            <c:otherwise>
                
                <c:forEach items="${lecturelist}" var="entry">
                        ${entry.id}<br>
                        <a href="<c:url value="/lecture/view/${entry.id}"/>">
                           <c:out value="${entry.subject}" /></a>
                        <br/>
                </c:forEach>
                
                 <%--         
                <c:forEach items="${lecturelist}" var="entry">
                    Lecture Topics ${entry.value}: <br/>
                    <a href="<c:url value="/lecture/view/${entry.key}" />">
                        <c:out value="${entry.value}" /></a>
                </c:forEach>
                        --%>
            </c:otherwise>
        </c:choose>
    </body>
</html>
