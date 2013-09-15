package com.travelplanner.model.business;

import com.travelplanner.model.domain.TravelPlannerAppUser;
import com.travelplanner.model.domain.UserInformation;

/**
 * Class to manager user authentication.
 * @author Gavin Rouleau
 */
public class LoginManager {

    /**
     * Method for authenticating user and setting user information.
     * @param user Incoming TravelPlannerAppUser for login credential validation.
     * @return Method returns a UserInformation object with user's information.
     */
    public UserInformation authenticate(TravelPlannerAppUser user) {
        
        UserInformation userInformation = null;
        
        if (user.getUsername().equals("Gavin") && user.getPassword().equals("1234")) {
            userInformation = new UserInformation();
            userInformation.setFirstName("Gavin");
            userInformation.setMiddleName("Scott");
            userInformation.setLastName("McKellen");
            userInformation.setAddress("583 Scotch Way, Glasgow, Scotland");
            userInformation.setPhone("+44 133 423 1379");
            userInformation.setEmail("smckellen@scotmail.uk");
        }
        
        return userInformation;
    }
}
