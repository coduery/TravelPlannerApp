package com.travelplanner.model.service.factory;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.travelplanner.model.service.exception.ServiceLoadException;
import com.travelplanner.model.service.IService;

/**Class to define a Factory object for getting Service Implementations.
 * Design Pattern: Factory - serves up services and hides the details 
 * from the Business layer by interacting with the IService interface.
 * Design Pattern: Singleton - prevents more than one 
 * instance of Factory from being created at the same time.
 * @author Gavin Rouleau
 */
public class TravelPlanAppFactory {
    
    /**Field to store instance of TravelPlanAppFactory. 
    * Design Pattern: Singleton (one part of three).
    */
    private static TravelPlanAppFactory factory = new TravelPlanAppFactory();
    
    /** Default constructor 
     * Design Pattern: Singleton (one part of three).
     */
    private TravelPlanAppFactory(){}
    
    /**Method to get an instance of TravelPlanAppFactory.
     * Design Pattern: Singleton (one part of three).
     * @return Method returns instance of TravelPlanAppFactory.
     */
    public static TravelPlanAppFactory getInstance() {

        return factory; 
    }
    
    /**Method for getting a service.
     * @param serviceImplementationName Incoming parameter specifying the name of the service implementation.
     * @return Method returns an instance a service class.
     * @throws ServiceLoadException 
     * @throws Exception Method throws Exception.
     */
    public IService getService(String serviceInterfaceName) throws ServiceLoadException {
        
        try {
            Class<?> c = Class.forName(getServiceImplementationName(serviceInterfaceName));
            return (IService) c.newInstance();
        } catch (Exception e) {
            throw new ServiceLoadException(serviceInterfaceName + " not loaded: " + e.getMessage() + e.getStackTrace(), e);
        }
    }
    
    /**Method for getting a service implementation name.
     * @param serviceInterfaceName Incoming parameter specifying the name of a service interface.
     * @return Method returns a service implementation name.
     */
    private String getServiceImplementationName(String serviceInterfaceName) throws Exception {
        
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:comp/env");
        
        return (String) envContext.lookup(serviceInterfaceName);
    }
}
