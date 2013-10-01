<!DOCTYPE html>

<%-- Travel Planner Application, JSP for displaying a welcome page to user after login. --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="format" uri="ViewFunctionsAndTags" %>

<html>
    <head>
        <title>Welcome</title>
        <link rel="stylesheet" href="styles.css" type="text/css" />
    </head>
    <body>
        <div id="page_layout">
        
            <c:import url="app_header.jsp" />
            
            <h2>Welcome ${userInformation.firstName}!</h2>
            <br>

            <div id="left_layout">
                <form class="form_entries" action="create_plan" method="get">
                    <input class="button" type="submit" value="New Travel Plan" />
                </form>
                <form class="form_entries" action="create_list" method="get">
                    <input class="button" type="submit" value="Create Favorite List" />
                </form>
                <form class="form_entries" action="add_item" method="get">
                    <input class="button" type="submit" value="Add Item to List" />
                </form>

                <p id="date"><format:current_date /></p>
            </div>
            
            <div id="middle_layout">
            
                Favorite List Summary:
                <c:set var="user" value="${user}" />
                <c:set var="fav_list_types" value="${applicationScope.favListTypes}" />
                <textarea readonly id="text_display_multiline" name="favorite_lists">${format:FormatFavoriteListSummary(user, fav_list_types)}</textarea>
                
                Travel Plan Summary:
                <textarea readonly id="text_display_multiline_future" name="travel_plans">Future Area Under Construction!</textarea>
            </div>

        </div>
    </body>
    
</html>