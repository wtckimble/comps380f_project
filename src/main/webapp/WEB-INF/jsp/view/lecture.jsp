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
            Welcome, ${username}
        <h2>Lecture List</h2>
        <a href="<c:url value="/lecture/create" />">Create a Lecture Topic</a><br /><br />
        <c:choose>
            <c:when test="${fn:length(lecturelist) == 0}">
                <i>Sorry, there are no lectures in the system.</i>
            </c:when>
            <c:otherwise>
                
                <c:forEach items="${lecturelist}" var="entry">
                        ${entry.id}<br>
                        <a href="<c:url value="/lecture/view/${entry.subject}"/>">
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
