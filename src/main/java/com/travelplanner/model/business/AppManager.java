package com.travelplanner.model.business;

import com.travelplanner.model.domain.TravelPlannerAppUser;
import com.travelplanner.model.service.exception.ServiceLoadException;
import com.travelplanner.model.service.factory.TravelPlanAppFactory;
import com.travelplanner.model.service.IService;

/**Class to define a Supertype for managing the Business layer.
 * Design Pattern: Layer Supertype - abstracts common functionality for subordinate Manager classes.
 * @author Gavin Rouleau
 */
public abstract class AppManager {
    
    /** Field to store an instance of TravelPlanAppFactory. */
    private TravelPlanAppFactory factory = TravelPlanAppFactory.getInstance();
    
       /**Method to perform a specified action on a specified object.
     * @param command Incomimg parameter representing the command to process.
     * @param obj Incoming parameter representing the object to process the command against.
     * @return Returns true if action was successful, and false otherwise.
     */
    protected abstract boolean performAction(String command, TravelPlannerAppUser user, Object obj);
    
    /**Method to get a service.
     * @param serviceInterfaceName Incoming parameter that specifies name of service interface.
     * @return Returns a service as an instance of IService.
     * @throws ServiceLoadException 
     */
    protected IService getService(String serviceInterfaceName) throws ServiceLoadException {

        return factory.getService(serviceInterfaceName);
    }
}
