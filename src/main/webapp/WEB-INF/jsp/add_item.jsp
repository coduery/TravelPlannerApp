<!DOCTYPE html>

<%-- Travel Planner Application, JSP File for User to Add Item to Favorite List --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Add Item to List</title>
        <link rel="stylesheet" href="styles.css" type="text/css" />
    </head>
    <body>
        <div id="page_layout">
        
            <c:import url="app_header.jsp" />
            <h2>Add Item to Favorite List</h2>
            <br>
            
            <form class="form_entries" action="save_item" method="post">
                <div id="left_layout">
                    <input class="button" type="submit" value="Save Favorite Item">
                    <br><br><br><br><br><br>
                    <a class="button" href="welcome">Cancel</a>
                </div>
                
                <div id="middle_layout">
                    Enter Item Name, Select List, and Add Information:
                    <br><br>
                    *Name of Favorite Item:
                    <input id="text_entry" type="text" name="item_name">
                    Location Description:
                    <textarea id="text_entry_multiline" name="location"></textarea>
                    Address:
                    <input id="text_entry" type="text" name="address">
                    City:
                    <input id="text_entry" type="text" name="city">
                    Country:
                    <input id="text_entry" type="text" name="country">
                    Phone:
                    <input id="text_entry" type="text" name="phone">
                    Notes:
                    <textarea id="text_entry_multiline" name="notes"></textarea>
                    * - Required Entry
                </div>
                <div id="right_layout">
                    <br><br>
                    *Select List to Add Item to:
                    <br>
                    <select id="list_selection" name="list_type" size="1">
                        
                        <option value=""></option>
                        <c:forEach var="list" items="${user.allFavLists}">
                            <option value="${list.nameFavList}"> ${list.nameFavList} </option>
                        </c:forEach>
                        
                    </select>

                    <div id="state_zip">
	                    State:
	                    <input id="text_entry" type="text" name="state">
	                    Zip:
	                    <input id="text_entry" type="text" name="zip">
                    </div>
                </div>
            </form>
            
        </div>
    </body>
</html>