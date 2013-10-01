package com.travelplanner.model.business;

import com.travelplanner.model.service.exception.ServiceLoadException;
import com.travelplanner.model.domain.FavoriteList;
import com.travelplanner.model.domain.TravelPlannerAppUser;
import com.travelplanner.model.service.favoritelistsvc.IFavoriteListSvc;

/**
 * Class to define a Manager for FavoriteList objects.
 * Design Pattern: Facade - Hides the underlying details of Service layer from the Presentation Layer.
 * @author Gavin Rouleau
 */
public class FavoriteListManager extends AppManager {

    /**Method to decide what action is to be taken regarding favorite list management.
     * Returns true if action was successful, and false otherwise.
     */
    @Override 
    public boolean performAction(String command, TravelPlannerAppUser user, Object obj){
        
        FavoriteList list = (FavoriteList)obj;
        boolean isActionSuccessful = false;
        
        if(command.equals("CreateList")){  
            isActionSuccessful = createList(user, list);
        }
        
        return isActionSuccessful;
    }
    
    /**Method to create a new FavoriteList.
     * @param list Incoming parameter that specifies a FavoriteList.
     */
    private boolean createList(TravelPlannerAppUser user, FavoriteList list){
        
        IFavoriteListSvc iFavoriteListService = null;
        boolean isCreateSuccessful = false;
        
        try {
            iFavoriteListService = (IFavoriteListSvc) getService(IFavoriteListSvc.NAME);
        } catch (ServiceLoadException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        
        if(iFavoriteListService != null){
            isCreateSuccessful = iFavoriteListService.storeFavList(user, list);
        }
        
        return isCreateSuccessful;
    }
    
    /**Method to get a FavoriteList.
     * @param lName Incoming parameter that specifies the list name of the FavoriteList.
     * @return Returns a FavoriteList.
     */
    public FavoriteList retrieveList(String uName, String lName){
        
        IFavoriteListSvc iFavoriteListService = null;
        FavoriteList list = null;
        
        try {
            iFavoriteListService = (IFavoriteListSvc) getService(IFavoriteListSvc.NAME);
        } catch (ServiceLoadException e) {
            System.err.println("FavoriteListManager::retrieveList ServiceLoadException thrown: " + e.getMessage());
        }
        
        if(iFavoriteListService != null){
            list = iFavoriteListService.getFavList(uName, lName);
        }
        
        return list;
    }
}
