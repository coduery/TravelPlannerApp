package com.travelplanner.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TravelPlan implements Serializable {

    /** Field to store default serial ID. */
    private static final long serialVersionUID = 1L;

	/** Field for storing a Travel Plan Name */
	private String travelPlanName;
	
	/** Field for storing a Travel Plan Description */
	private String travelPlanDesc;
	
	/** Field for storing a Travel Plan Trip Items */
	private List<FavoriteItem> tripItems;
	
	/** Field for storing a Travel Plan Item Details. */
	private String travelPlanDetails;
    
    /** Field to store the user the travel plan is associated with. */
    private TravelPlannerAppUser user;
    
    /** Field to store the an integer id of travel plan. */
    private Integer planId;

	/**Method for getting a Travel Plan Name.
	 * @return Returns a Travel Plan Name.
	 */
	public String getTravelPlanName(){
		return travelPlanName;
	}
	
	/**Method for setting a Travel Plan Name.
	 * @param tName Incoming parameter for setting Travel Plan Name.
	 */
	public void setTravelPlanName(String tName){
		travelPlanName = tName;
	}
	
	/**Method for getting a Travel Plan Description.
	 * @return Returns a Travel Plan Description.
	 */
	public String getTravelPlanDesc(){
		return travelPlanDesc;
	}
	
	/**Method for setting a Travel Plan Description.
	 * @param tName Incoming parameter for setting Travel Plan Description.
	 */
	public void setTravelPlanDesc(String tDescript){
		travelPlanDesc = tDescript;
	}
	
	/**Method for getting a Travel Plan Items.
	 * @return Returns Travel Plan Items.
	 */
	public  List<FavoriteItem> getTripItems(){
		return tripItems;
	}
	
	/**Method for setting a Travel Plan Items.
	 * @param tName Incoming parameter for setting Travel Plan Items.
	 */
	public void setTripItems(ArrayList<FavoriteItem> tItems){
		tripItems = tItems;
	}
	
	/**	/**Method for getting a travelPlanDetails.
	 * @return Returns travelPlanDetails.
	 */
	public String getTravelPlanDetails(){
		return travelPlanDetails;
	}
	
	/**Method for setting a travelPlanDetails.
	 * @param travPlanDetails Incoming parameter to set travelPlanDetails to.
	 */
	public void setTravelPlanDetails(String travPlanDetails){
		travelPlanDetails = travPlanDetails;
	}
    
    /**Method for getting the user associated with the travel plan, for use with Hibernate.
     * @return Returns user associated with travel plan.
     */
    public TravelPlannerAppUser getUser(){
        return user;
    }    
    
    /**Method for setting the user associated with travel plan, for use with Hibernate.
     * @param user Incoming parameter to set the user to.
     */        
    public void setUser(TravelPlannerAppUser user){
         this.user = user;
    }    
	
    /**
     * Method for getting the planId of the travel plan for use with Hibernate.
     * @return Returns the planId of the travel plan.
     */
    public Integer getPlanId(){
        return planId;
    }
    
    /**
     * Method for setting the planId of the travel plan for use with Hibernate.
     * @param planId Returns the planId of the travel plan.
     */
    public void setPlanId(Integer planId){
        this.planId = planId;
    }
    
	/**Method for checking the equality of TravelPlan object field values.
	 * Overrides java.lang.Object.equals(Object obj) method.
	 * @param obj Incoming Object obj parameter field values are compared to an 
	 * instance of TravelPlan and its field values.
	 * @return Method returns false if a TravelPlan field value is not equal, 
	 * if Object is not an instance of the TravelPlan class,
	 * or true if all field values are equal.
	 */
	/*public boolean equals(Object obj){
		if (obj instanceof TravelPlan) {
			TravelPlan travelPlan = (TravelPlan)obj;
			if (!travelPlanName.equals(travelPlan.getTravelPlanName())) return false;
            if (!travelPlanDesc.equals(travelPlan.getTravelPlanDesc())) return false;
			return true;
		}

		return false;
		
	}*/
	
	/**Method for obtaining the hashcode of TravelPlan object field values.
	 * Overrides java.lang.Object.hashCode() method.
	 * @return Method returns a hashcode value based upon TravelPlan field values.
	 */
	/*public int hashCode(){
		String tempString = this.toString();
		return tempString.hashCode();
	}*/
	
	/**Method for displaying values of the TravelPlan fields.
	 * Overrides java.lang.Object.toString() method.
	 * @return Method returns concatenated string of the class field values.
	 */
	public String toString(){
		String tempString = new String();
		tempString = "Travel Plan Field Values: "
					+ "\nTravel Plan Name: " + travelPlanName 
					+ "\nTravel Plan Decription: " + travelPlanDesc
					+ "\nTravel Plan Details: " + travelPlanDetails;
		return tempString;
	}
	
	/**Method for checking the validity of TravelPlan object field values.
	 * @return Method returns false if a TravelPlan object field value has an invalid value, 
	 * or returns true if validated field values are set to valid values.
	 */
	public boolean validate(){
		if (travelPlanName == null || travelPlanName.isEmpty()) return false;
		if (travelPlanDesc == null || travelPlanDesc.toString().isEmpty()) return false;
		return true;
	}
}
