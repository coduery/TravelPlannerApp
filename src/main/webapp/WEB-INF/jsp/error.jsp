<!DOCTYPE html>

<%-- Travel Planner Application, JSP for displaying an error to the user. --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="action" value="${action}" />
<c:if test="${action eq 'welcome'}">
    <c:remove var="user" />
</c:if>

<html>
    <head>
        <title>Error</title>
        <link rel="stylesheet" href="styles.css" type="text/css" />
    </head>
    <body>
        <div id="page_layout">
        
            <c:set var="errorMessages" value="${error}" />
            <br><h2>${errorMessages[0]}</h2><br>
        
            <div id="center_layout">
                <p>${errorMessages[1]}</p>
                <p>${errorMessages[2]}</p>
                <br><br><br>
                <input class="small_button" type="submit" value="OK" onclick="history.go(-1)" />
            </div>
        
        </div>
    </body>
</html> 