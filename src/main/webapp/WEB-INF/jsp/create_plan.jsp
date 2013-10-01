<!DOCTYPE html>

<!-- Travel Planner Application, JSP File for User to Create Travel Plan -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Create Travel Plan</title>
        <link rel="stylesheet" href="styles.css" type="text/css" />
    </head>
    <body>
        <div id="page_layout">
        
            <c:import url="app_header.jsp" />
            <h2>Create Travel Plan</h2>
            <br>
            
            <form class="form_entries" action="create_plan" method="post">
                <div id="left_layout">
                    <input class="button" type="submit" value="Save Travel Plan">
                    <br><br><br><br><br><br>
                    <a class="button" href="welcome">Cancel</a>
                </div>
                
                <div id="middle_layout">
                    Enter Plan Name and Description:
                    <br>
                    <br>
                    *Name of Travel Plan:
                    <br>
                    <input id="text_entry" type="text" name="plan_name"><br>
                    *Travel Plan Description:
                    <br>
                    <textarea id="text_entry_multiline" name="plan_description"></textarea>
                    <br>
                    <textarea readonly id="text_display_multiline_future" name="travel_plan_details">Future Area Under Construction!</textarea>
                    * - Required Entry
                </div>
            </form>
            
        </div>
    </body>
</html>