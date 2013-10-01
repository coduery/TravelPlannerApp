package com.travelplanner.model.domain;

import java.io.Serializable;

import java.util.List;
//import org.apache.log4j.Logger;

/**
 * Class to define a User object for the Travel Planner Application.
 * Design Pattern: Value Object - an object that defines field values, the state of 
 * the field values, and allows for the analyzing and comparison of field values.
 * @author Gavin Rouleau
 */
public class TravelPlannerAppUser implements Serializable {

	/** Field to store default serial ID. */
    private static final long serialVersionUID = 1L;

    /** Field to store Travel Planner Application User's username. */
	private String username;
	
	/** Field to store Travel Planner Application User's password. */
	private String password;
	
	/** Field to store the List of Favorite Lists */
    private List<FavoriteList> allFavLists;
	
	/** Field to store the List of Favorite List Names */
	private String[] allFavListNames;
    
    /** Field to store a List of Travel Plans */
	private List<TravelPlan> allTravelPlans;
    
    /** Field to store the an integer id of travel plan user */
    private Integer userId;
    
    /** Field to store the user's information */
    private UserInformation userInformation;

    /**
	 * Method for getting TravelPlannerAppUser username.
	 * @return Method returns TravelPlannerAppUser username.
	 */
	public String getUsername(){
		return username;
	}
	
	/**
	 * Method for setting TravelPlannerAppUser username.
	 * @param uName TravelPlannerAppUser username is set to incoming uName parameter.
	 */
	public void setUsername(String uName){
		username = uName;
	}
	
	/**
	 * Method for getting TravelPlannerAppUser password.
	 * @return Method returns TravelPlannerAppUser password.
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * Method for setting TravelPlannerAppUser password.
	 * @param pWord TravelPlannerAppUser password is set to incoming pWord parameter.
	 */
	public void setPassword(String pWord){
		password = pWord;
	}
	
	/**
	 * Method for getting the List of Favorite Lists.
	 * @return Method returns a List of Favorite Lists.
	 */
	public List<FavoriteList> getAllFavLists(){
		return allFavLists;
	}
	
	/**
	 * Method for setting the List of Favorite Lists.
	 * @param allFavLists List of Favorite Lists is set to incoming allLists parameter.
	 */
	public void setAllFavLists(List<FavoriteList> allFavLists){
		this.allFavLists = allFavLists;
	}
    
	/**
	 * Method for getting the list of Favorite List Names.
	 * @return Returns the list of Favorite List Names.
	 */
	public String[] getAllFavListNames(){
		return allFavListNames;
	}
	
	/**
	 * Method for setting the list of Favorite List Names.
	 * @param allLists List of Favorite Lists is set to incoming allLists parameter.
	 */
	public void setAllFavListNames(List<FavoriteList> allLists){
		int i = 0;
		allFavListNames = new String[allLists.size()];
		for(FavoriteList list : allLists){
			allFavListNames[i] = list.getNameFavList();
			i++;
		}
	}
	
	/**
	 * Method for getting the List of Travel Plans.
	 * @return Method returns a List of Travel Plans.
	 */
	public List<TravelPlan> getAllTravelPlans(){
		return allTravelPlans;
	}
	
	/**
	 * Method for setting the List of Travel Plans.
	 * @param allPlans List of Travel Plans is set to incoming allPlans parameter.
	 */
	public void setAllTravelPlans(List<TravelPlan> allPlans){
		allTravelPlans = allPlans;
	}
    
    /**
     * Method for getting the userId of the travel planner app user.
     * @return Returns the userId of the travel planner app user.
     */
    public Integer getUserId(){
        return userId;
    }
    
    /**
     * Method for setting the userId of the travel planner app user.
     * @param userId Returns the userId of the travel planner app user.
     */
    public void setUserId(Integer userId){
        this.userId = userId;
    }
    
    /**
     * Method for getting the userInformation object associated with the user.
     * @return Returns the user's UserInformation object.
     */
    public UserInformation getUserInformation() {
        return userInformation;
    }

    /**
     * Method for setting the UserInformaton object of the travel planner app user.
     * @param userInformation Incoming parameter to set the user's UserInformation object.
     */
    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }
    
	/**
	 * Method for checking the equality of TravelPlannerAppUser object field values.
	 * Overrides java.lang.Object.equals(Object obj) method.
	 * @param obj Incoming Object obj parameter field values are compared to an 
	 * instance of TravelPlannerAppUser and its field values.
	 * @return Method returns false if a TravelPlannerAppUser field value is not equal, 
	 * if Object obj is not an instance of the TravelPlannerAppUser class,
	 * or true if all field values are equal.
	 */
	@Override public boolean equals(Object obj){
		if (obj instanceof TravelPlannerAppUser) {
			TravelPlannerAppUser user = (TravelPlannerAppUser)obj;
			if (!username.equals(user.getUsername())) return false;
			if (!password.equals(user.getPassword())) return false;
			return true;
		}
		return false;
	}
	
	/**
	 * Method for obtaining the hashcode of TravelPlannerAppUser object field values.
	 * Overrides java.lang.Object.hashCode() method.
	 * @return Method returns a hashcode value based upon TravelPlannerAppUser field values.
	 */
	@Override public int hashCode(){
		int fieldHashCode = username.hashCode() + password.hashCode();
		return fieldHashCode;
	}
	
	/**
	 * Method for displaying values of the TravelPlannerAppUser fields.
	 * Overrides java.lang.Object.toString() method.
	 * @return Method returns concatenated string of the class field values.
	 */
	@Override public String toString(){
		String tempString = new String();
		tempString = "Travel Planner Application User Field Values: "
					+ "\nUsername: " + username 
					+ "\nPassword: " + password;
		return tempString;
	}
	
	/**
	 * Method for checking the validity of TravelPlannerAppUser object field values.
	 * @return Method returns false if a TravelPlannerAppUser object field value has an invalid value, 
	 * or returns true if validated field values are set to valid values.
	 */
	public boolean validate(){
		if (username == null || username.isEmpty()) return false;
		if (password == null || password.isEmpty()) return false;
		return true;
	}
}
