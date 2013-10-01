package com.travelplanner.model.service.usersvc;

import com.travelplanner.model.domain.TravelPlannerAppUser;
import com.travelplanner.model.service.IService;

/**Interface to define an User Service for Travel Planner Application.
 * Design Pattern: Separated Interface - interface that decouples the service 
 * implementations from the IService interface and the Factory.
 * @author Gavin Rouleau
 */
public interface IUserSvc extends IService {
	
	/** Field to store string constant of interface name. */
	public final String NAME = "IUserSvc";
	
	/**Method abstraction to store a Travel Planner Application User.
	 * @param user TravelPlannerAppUser is specified by incoming user parameter.
     * @return Returns boolean value indicating whether operation was successful or not.
	 */
	//public boolean storeUser(TravelPlannerAppUser user);
	
	/**Method abstraction to get a Travel Planner Application User.
	 * @param user TravelPlannerAppUser is specified by incoming user parameter.
	 * @return Method returns a TravelPlannerAppUser object.
	 */
	public TravelPlannerAppUser getUser(TravelPlannerAppUser user);
}
