<%-- 
    Document   : addPoll
    Created on : Apr 6, 2017, 7:55:42 PM
    Author     : IvanChau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Poll</title>
    </head>
       <body>
        <h1>Add Poll</h1>
        <form:form method="POST" modelAttribute="comment">
            <form:label path="topic">Topic:</form:label><br/>
            <form:textarea path="topic" type="body" rows="5" cols="30" /><br/>
            <form:label path="optionone">Option 1</form:label><br/>
            <form:input type="text" path="optionone" /><br/><br/>
            <form:label path="optiontwo">Option 2</form:label><br/>
            <form:input type="text" path="optiontwo" /><br/><br/>
            <form:label path="optionthree">Option 3</form:label><br/>
            <form:input type="text" path="optionthree" /><br/><br/>
            <form:label path="optionfour">Option 4</form:label><br/>
            <form:input type="text" path="optionfour" /><br/><br/>
            <input type="submit" value="Submit"/>
        </form:form>
    </body>
</html>