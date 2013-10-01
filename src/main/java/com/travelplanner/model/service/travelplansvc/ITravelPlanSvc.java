package com.travelplanner.model.service.travelplansvc;

import com.travelplanner.model.domain.TravelPlan;
import com.travelplanner.model.domain.TravelPlannerAppUser;
import com.travelplanner.model.service.IService;

/**Interface to define an Travel Plan Service for Travel Planner Application.
 * Design Pattern: Separated Interface - interface that decouples the service 
 * implementations from the IService interface and the Factory.
 * @author Gavin Rouleau
 */
public interface ITravelPlanSvc extends IService {

	/** Field to store string constant of interface name. */
	public final String NAME = "ITravelPlanSvc";

	/**Method abstraction to store a Travel Plan object.
	 * @param user TravelPlannerAppUser is specified by incoming user parameter.
	 * @param plan TravelPlan is specified by incoming user parameter.
     * @return Returns boolean value indicating whether operation was successful or not.
	 */
	public boolean storeTravelPlan(TravelPlannerAppUser user, TravelPlan plan);
	
	/**Method abstraction to get a Travel Plan object.
	 * @param uName TravelPlannerAppUser username is specified by incoming uName parameter.
	 * @param tName Travel Plan name is specified by incoming uName parameter.
	 * @return Returns a Travel Plan.
	 */
	public TravelPlan getTravelPlan(String uName, String tName);
}
