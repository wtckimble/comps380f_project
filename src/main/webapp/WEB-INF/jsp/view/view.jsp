<!DOCTYPE html>
<html>
    <head>
        <title>Lecture view</title>
        <style>
            p{
                text-indent: 50px;
            }
        </style>
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
        <h2>Lecture #${lectureInfo.id}: <c:out value="${lectureInfo.subject}" /></h2>
        <i>Customer Name - <c:out value="${lectureInfo.customerName}" /></i><br /><br />
        <c:out value="${lectureInfo.body}" /><br /><br />
        <c:if test="${lectureInfo.numberOfAttachments > 0}">
            Attachments:
            <c:forEach items="${lectureInfo.attachments}" var="attachment"
                       varStatus="status">
                <c:if test="${!status.first}">, </c:if>
                <a href="<c:url value="/lecture/download/${lectureInfo.id}/attachment/${attachment.name}" />">
                    <c:out value="${attachment.name}" /></a>
            </c:forEach><br /><br />
        </c:if>
        --------------------------------------------------------------------------------------<br/><br/>
        <c:choose>
            <c:when test="${fn:length(replylist) == 0}">
                <i>----------------------------</i><br/>
            </c:when>
            <c:otherwise>

                <c:forEach items="${replylist}" var="entry">
                    ${entry.customerName}:
                    <p>
                        ${entry.body}

                        <br/>
                        <c:if test="${entry.numberOfAttachments > 0}">
                            Attachments:
                            <c:forEach items="${entry.attachments}" var="attachment"
                                       varStatus="status">
                                <c:if test="${!status.first}">, </c:if>
                                <a href="<c:url value="/lecture/download/${lectureInfo.id}/${entry.id}/attachment/${attachment.name}" />">
                                    <c:out value="${attachment.name}" /></a>
                            </c:forEach><br /><br />
                        </c:if>
                        <br>
                        <security:authorize access="hasRole('ROLE_ADMIN') ">
                            <a href="<c:url value="/lecture/view/${lectureInfo.id}/deleteReply/${entry.id}"/>">Delete</a>
                        </security:authorize>
                    </p>

                </c:forEach>
            </c:otherwise>
        </c:choose>

        <a href="<c:url value="/lecture/reply/${ticketId}" />">Reply</a>   
        <a href="<c:url value="/lecture" />">Back</a>
    </body>
</html>
