<!DOCTYPE html>

<!-- Travel Planner Application, JSP File for User Login -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Travel Planner Login</title>
        <link rel="stylesheet" href="styles.css" type="text/css" />
    </head>
    <body>
        <div id="page_layout">
        
            <c:import url="/WEB-INF/jsp/app_header.jsp" />
            <h2>Enter Login Credentials</h2><br>
            
            <div id="center_layout">
                <form class="form_entries" action="welcome" method="post">
                    *Username:
                    <input id="username_entry" type="text" name="username"/>
                    *Password:
                    <input id="password_entry" type="password" name="password"/>
                    <br>
                    * - Required Entry
                    <br><br><br>
                    <input class="small_button" type="submit" value="Login"/>
                </form>
            </div>
            
        </div>
    </body>
</html>