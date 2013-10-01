package com.travelplanner.view;

import java.util.Collections;
import java.util.List;

import com.travelplanner.model.domain.FavoriteList;
import com.travelplanner.model.domain.TravelPlannerAppUser;

/**
 * Helper class for the welcome.jsp page mapped through view.tld file.
 * @author Gavin Rouleau
 */
public class Welcome {

    /**
     * Method for formatting the Favorite List Summary text area on Welcome page.
     * @param user Incoming parameter that specifies the current user.
     */
    public static String formatFavoriteListSummary(TravelPlannerAppUser user, List<String> favListTypes){
        
        List<FavoriteList> tempAllFavLists = user.getAllFavLists();
        StringBuilder summaryList = new StringBuilder();
        boolean doesListTypeHaveEntries = false;
        
        if (tempAllFavLists != null){
            Collections.sort(tempAllFavLists, new FavoriteList());
            user.setAllFavLists(tempAllFavLists);
            
            for (int i = 1; i < favListTypes.size(); i++){
                summaryList.append(favListTypes.get(i));
                summaryList.append(":\r\n");
                
                for (FavoriteList list : tempAllFavLists){
                    if (list.getFavListType().equals(favListTypes.get(i))){
                        summaryList.append("     ");
                        summaryList.append(list.getNameFavList());
                        summaryList.append("\r\n");
                        doesListTypeHaveEntries = true;
                    }
                }
                
                if (doesListTypeHaveEntries){
                    summaryList.append("\r\n");
                }
                else
                {
                    summaryList.append("     No entries!\r\n\r\n");
                }
                
                doesListTypeHaveEntries = false;
            }
        }
        else
        {
            for(int i = 1; i < favListTypes.size(); i++){
                summaryList.append(favListTypes.get(i));
                summaryList.append(":\r\n     No entries!\r\n\r\n");
            }
        }
        
        return summaryList.toString();
    }
}
