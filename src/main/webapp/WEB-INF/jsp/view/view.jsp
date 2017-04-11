<!DOCTYPE html>
<html>
    <head>
        <title>Lecture view</title>
    </head>
    <body>
        <<<<<<< HEAD
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
        <h2>Lecture #${lectureInfo.id}: <c:out value="${lectureInfo.subject}" /></h2>
        <i>Customer Name - <c:out value="${lectureInfo.customerName}" /></i><br /><br />
        <c:out value="${lectureInfo.body}" /><br /><br />
        <c:if test="${lectureInfo.numberOfAttachments > 0}">
            Attachments:
            <c:forEach items="${lectureInfo.attachments}" var="attachment"
                       varStatus="status">
                <c:if test="${!status.first}">, </c:if>
                <a href="<c:url value="/lecture/${lectureInfo.id}/attachment/${attachment.name}" />">
                    <c:out value="${attachment.name}" /></a>
            </c:forEach><br /><br />
        </c:if>
        <a href="<c:url value="/lecture/reply/${ticketId}" />">Reply</a>   
        <a href="<c:url value="/lecture" />">Return to list tickets</a>
    </body>
</html>
