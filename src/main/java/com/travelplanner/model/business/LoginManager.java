package com.travelplanner.model.business;

import com.travelplanner.model.domain.TravelPlannerAppUser;
import com.travelplanner.model.service.exception.ServiceLoadException;
import com.travelplanner.model.service.usersvc.IUserSvc;

/**
 * Class to manage user authentication, retrieval, and creation.
 * @author Gavin Rouleau
 */
public class LoginManager extends AppManager {

    @Override
    protected boolean performAction(String command, TravelPlannerAppUser user, Object obj) {
        // TODO Implement for user creation, updating, and deleting in future use cases
        return false;
    }

    /**
     * Method for authenticating user and setting user information.
     * @param user Incoming TravelPlannerAppUser for login credential validation.
     * @return Method returns a UserInformation object with user's information.
     */
    public TravelPlannerAppUser authenticate(TravelPlannerAppUser user) {

        user = retrieveUser(user);
        
        return user;
    }
    
    /**Method to get a TravelPlannerAppUser.
     * @param user Incoming TravelPlannerAppUser to retrieve.
     * @return Returns a TravelPlannerAppUser.
     */
    public TravelPlannerAppUser retrieveUser(TravelPlannerAppUser user){

        IUserSvc iUserService = null;
        
        try {
            iUserService = (IUserSvc) getService(IUserSvc.NAME);
        } catch (ServiceLoadException e) {
            System.out.println("exception thrown");
            System.err.println("LoginManager::retrieveUser ServiceLoadException thrown: " + e.getMessage());
            e.printStackTrace();
        }
        
        if (iUserService != null) {
            user = iUserService.getUser(user);
        }
        
        return user;
    }
}
