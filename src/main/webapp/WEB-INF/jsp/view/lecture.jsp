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
            <c:when test="!not empty ${}username}">
                Welcome, ${username}  <a href=<c:out
            </c:when>
            <c:when test="${condition2}">
                ...
            </c:when>
            <c:otherwise>
                ...
            </c:otherwise>
        </c:choose> <br>
        <h2>Lecture List</h2>
        <a href="<c:url value="/lecture/create" />">Create a Lecture Topic</a><br /><br />
        <c:choose>
            <c:when test="${fn:length(ticketDatabase) == 0}">
                <i>There are no lectures in the system.</i>
            </c:when>
            <c:otherwise>
                <c:forEach items="${ticketDatabase}" var="entry">
                    Ticket ${entry.key}:
                    <a href="<c:url value="/lecture/view/${entry.key}" />">
                        <c:out value="${entry.value.subject}" /></a>
                    (customer: <c:out value="${entry.value.customerName}" />)<br />
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </body>
</html>
