<!DOCTYPE html>

<!-- Travel Planner Application, JSP File for User to Create Favorite List  -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="format" uri="ViewFunctionsAndTags" %>

<html>
    <head>
        <title>Create Favorite List</title>
        <link rel="stylesheet" href="styles.css" type="text/css" />
    </head>
    <body>
        <div id="page_layout">
        
            <c:import url="app_header.jsp" />
            <h2>Create Favorite List</h2>
            <br>
            
            <form class="form_entries" action="save_list" method="post">
                <div id="left_layout">
                    <input class="button" type="submit" value="Save Favorite List">
                    <br><br><br><br><br><br>
                    <a class="button" href="welcome">Cancel</a>
                    
                </div>
                
                <div id="middle_layout">
                    Enter Favorite List Name and Type:
                    <br>
                    <br>
                    *Name of Favorite List:
                    <br>
                    <input id="text_entry" type="text" name="list_name"><br>
                    *Favorite List Type:
                    <br>
                    <format:select name="list_type" size="1" select_options="${applicationScope.favListTypes}" />
                    <br><br><br>
                    * - Required Entry
                </div>
            </form>

            
        </div>
    </body>
</html>