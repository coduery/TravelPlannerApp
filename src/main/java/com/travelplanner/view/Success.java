package com.travelplanner.view;

import javax.servlet.http.HttpSession;

import com.travelplanner.model.domain.FavoriteItem;
import com.travelplanner.model.domain.FavoriteList;
import com.travelplanner.model.domain.TravelPlan;

/**
 * Helper class for outputting successful action messages via success.jsp page.
 * @author Gavin Rouleau
 */
public class Success {

    /**
     * Method to construct a String array of successful messages to display to user.
     * @param session Incoming HttpSession
     * @return Returns a String array with successful messages.
     */
    public static String[] successMessage(HttpSession session) {
        
        String confirmation = (String) session.getAttribute("confirmation");
        String heading = null;
        String message = null;
        
        if (confirmation.equals("list_saved")) {
            FavoriteList favoriteList = (FavoriteList) session.getAttribute("favoriteList");
            heading = "Favorite List Successfully Saved!";
            message = "List: \"" + favoriteList.getNameFavList() + "\"";
        }
        else if (confirmation.equals("item_saved")) {
            FavoriteItem item = (FavoriteItem) session.getAttribute("favoriteItem");
            heading = "Favorite Item Successfully Saved!";
            message = "Item: \"" + item.getNameFavItem() + "\"";
        }
        else if (confirmation.equals("plan_saved")) {
            TravelPlan plan = (TravelPlan) session.getAttribute("plan");
            heading = "Travel Plan Successfully Saved!";
            message = "Plan: \"" + plan.getTravelPlanName() + "\"";
        }
        
        String[] messages = { heading, message };
        
        return messages;
    }
}
