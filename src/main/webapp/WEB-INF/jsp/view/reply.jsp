<%-- 
    Document   : reply
    Created on : Apr 11, 2017, 3:39:33 PM
    Author     : German
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>Reply</title>
    </head>
    <body>
        <h2>Reply</h2>
        
        <form:form method="POST" enctype="multipart/form-data"
                   modelAttribute="replyForm">
            <form:label path="body">Reply:</form:label><br/>
            <form:textarea path="body" rows="5" cols="30" /><br/><br/>
            <b>Attachments</b><br/>
            <input type="file" name="attachments" multiple="multiple"/><br/><br/>
            <input type="submit" value="Submit"/>
        </form:form>
    </body>
</html>