package com.travelplanner.model.business;

import com.travelplanner.model.service.exception.ServiceLoadException;
import com.travelplanner.model.domain.TravelPlan;
import com.travelplanner.model.domain.TravelPlannerAppUser;
import com.travelplanner.model.service.travelplansvc.ITravelPlanSvc;

/**
 * Class to define a Manager for TravelPlan objects.
 * Design Pattern: Facade - Hides the underlying details of Service layer from the Presentation Layer.
 * @author Gavin Rouleau
 */
public class TravelPlanManager extends AppManager {

    /**Method to decide what action is to be taken regarding Travel Plan management.
     * Returns true if action was successful, and false otherwise.
     */
    @Override 
    public boolean performAction(String command, TravelPlannerAppUser user, Object obj) {
        
        TravelPlan plan = (TravelPlan) obj;
        boolean isActionSuccessful = false;

        if (command.equals("CreatePlan")){  
            isActionSuccessful = createTravelPlan(user, plan);
        }
        
        return isActionSuccessful;
    }

    /**Method to create a new TravelPlan.
     * @param user Incoming parameter that specifies user of the TravelPlan.
     * @param plan Incoming parameter that specifies a TravelPlan.
     */
    private boolean createTravelPlan(TravelPlannerAppUser user, TravelPlan plan){

        ITravelPlanSvc iTravelPlanSvc = null;
        boolean isCreateSuccessful = false;

        try {
            iTravelPlanSvc = (ITravelPlanSvc) getService(ITravelPlanSvc.NAME);
        } catch (ServiceLoadException e) {
            e.printStackTrace();
        }
        
        if (iTravelPlanSvc != null) {
            isCreateSuccessful = iTravelPlanSvc.storeTravelPlan(user, plan);
        }
        
        return isCreateSuccessful;
    }

    /**Method to get a TravelPlan.
     * @param uName Incoming parameter that specifies the user of the TravelPlan.
     * @param tName Incoming parameter that specifies the name of a TravelPlan.
     * @return Returns a TravelPlan.
     */
    public TravelPlan retrieveTravelPlan(String uName, String tName){
        
        ITravelPlanSvc iTravelPlanSvc = null;
        TravelPlan plan = null;

        try {
            iTravelPlanSvc = (ITravelPlanSvc) getService(ITravelPlanSvc.NAME);
        } catch (ServiceLoadException e) {
            e.printStackTrace();
        }
        
        if (iTravelPlanSvc != null) {
            plan = iTravelPlanSvc.getTravelPlan(uName, tName);
        }
        
        return plan;
    }
}
