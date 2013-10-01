<!DOCTYPE html>

<%-- Travel Planner Application, JSP for confirming successful action to user. --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="format" uri="ViewFunctionsAndTags" %>

<html>
    <head>
        <title>Favorite List Saved</title>
        <link rel="stylesheet" href="styles.css" type="text/css" />
    </head>
    <body>
        <div id="page_layout">
        
            <c:set var="session" value="${pageContext.session}" />
            <c:set var="messages" value="${format:SuccessMessage(session)}" />
            <br><h2>${messages[0]}</h2><br>
            
            <div id="center_layout">

	            <p>${messages[1]}</p>
                <form class="form_entries_message" action="welcome" method="get">
                    <br><br><br>
                    <input class="small_button" type="submit" value="OK"/>
                </form>
            </div>
            
        </div>
    </body>
</html>