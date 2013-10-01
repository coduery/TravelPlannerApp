package com.travelplanner.model.service.favoritelistsvc;

import com.travelplanner.model.domain.FavoriteList;
import com.travelplanner.model.domain.TravelPlannerAppUser;
import com.travelplanner.model.service.IService;

/**Interface to define a Favorite List Service for the Travel Planner Application.
 * Design Pattern: Separated Interface - interface that decouples the service 
 * implementations from the IService interface and the Factory.
 * @author Gavin Rouleau
 */
public interface IFavoriteListSvc extends IService {
	
	/** Field to store string constant of interface name. */
	public final String NAME = "IFavoriteListSvc";
	
	/**Method abstraction to store a Favorite List.
	 * @param list FavoriteList is specified by incoming list parameter.
     * @return Returns boolean value indicating whether operation was successful or not.
	 */
	public boolean storeFavList(TravelPlannerAppUser user, FavoriteList list);
	
	/**Method abstraction to get a Favorite List.
	 * @param lName Favorite List name is specified by incoming lName parameter.
	 * @return Method returns a FavoriteList object.
	 */
	public FavoriteList getFavList(String uName, String lName);
}
