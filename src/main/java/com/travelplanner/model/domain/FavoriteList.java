package com.travelplanner.model.domain;

import java.io.Serializable;
//import java.util.List;
import java.util.Comparator;
//import org.apache.log4j.Logger;

/**Class to define a Favorite List for a Travel Planner Application User.
 * Design Pattern: Value Object - an object that defines field values, 
 * the state of the field values, and allows for the analyzing and comparison of field values.
 * @author Gavin Rouleau
 */
public class FavoriteList implements Serializable, Comparator<FavoriteList> {

    /** Field to store default serial ID. */
    private static final long serialVersionUID = 1L;

    /** Field to store the Name of a Favorite List */
	private String nameFavList;
	
	/** Field to store the Type of a Favorite List */
	private String favListType;
	
	/** Field to store a List of Favorite Items */
	//private List<FavoriteItem> favList;
    
    /** Field to store the user the favorite list is associated with, for use with Hibernate */
    private TravelPlannerAppUser user;
    
    /** Field to store the an integer id of the favorite list, for use with Hibernate */
    private Integer listId;
	
    /** Field for establishing a log4j logger. */
    //private static Logger logger = Logger.getLogger("com.travelplanner");
    
	/**
	 * Method for getting the name of a Favorite List.
	 * @return Method returns FavoriteList name.
	 */
	public String getNameFavList(){
		return nameFavList;
	}
	
	/**
	 * Method for setting the name of a Favorite List.
	 * @param nList Favorite List name is set to incoming nList parameter.
	 */
	public void setNameFavList(String nList){
		nameFavList = nList;
	}
	
	/**
	 * Method for getting the type of a Favorite List.
	 * @return Method returns FavoriteList type.
	 */
	public String getFavListType(){
		return favListType;
	}
	
	/**
	 * Method for setting the type of a Favorite List.
	 * @param lType Favorite List type is set to incoming lType parameter.
	 */
	public void setFavListType(String lType){
		favListType = lType;
	}
	
	/**
	 * Method for getting a Favorite List.
	 * @return Method returns a Favorite List.
	 */
	/*public List<FavoriteItem> getFavList(){
		return favList;
	}*/
	
	/**
	 * Method for setting a Favorite List.
	 * @param fList Favorite List is set to incoming fList parameter.
	 */
	/*public void setFavList(List<FavoriteItem> fList){
		favList = fList;
	}*/
	
    /**
     * Method for getting the user associated with the favorite list, for use with Hibernate.
     * @return Returns user associated with favorite list.
     */
    public TravelPlannerAppUser getUser(){
        return user;
    }    
    
    /**
     * Method for setting the user associated with favorite list, for use with Hibernate.
     * @param user Incoming parameter to set the user to.
     */        
    public void setUser(TravelPlannerAppUser user){
         this.user = user;
    }
            
    /**
     * Method for getting the listId of the favorite list for use with Hibernate.
     * @return Returns the listId of the favorite list.
     */
    public Integer getListId(){
        return listId;
    }
    
    /**
     * Method for setting the listId of the favorite list for use with Hibernate.
     * @param listId Returns the listId of the favorite list.
     */
    public void setListId(Integer listId){
        this.listId = listId;
    }    
    
	/**
	 * Method for comparing FavoriteList to accommodate sorting.
	 * Returns an integer indicating whether a list item is alphabetically less than, equal, or greater than another.
	 */
	@Override
	public int compare(FavoriteList list1, FavoriteList list2){
		return list1.getNameFavList().compareTo(list2.getNameFavList());
	}
	
	/**
	 * Method for checking the equality of FavoriteList object field values.
	 * Overrides java.lang.Object.equals(Object obj) method.
	 * @param obj Incoming Object obj parameter field values are compared to an 
	 * instance of FavoriteList and its field values.
	 * @return Method returns false if a FavoriteList field value is not equal, 
	 * if Object is not an instance of the FavoriteList class,
	 * or true if all field values are equal.
	 */
	/*@Override public boolean equals(Object obj){
		if (obj instanceof FavoriteList) {
			FavoriteList list = (FavoriteList)obj;
			if (!nameFavList.equals(list.getNameFavList())) return false;
			if (!favListType.equals(list.getFavListType())) return false;
			return true;
		}
        logger.debug("FavoriteList::equals - Incoming object is not an instance of the FavoriteList class.");
		return false;
	}*/
	
	/**
	 * Method for obtaining the hashcode of FavoriteList object field values.
	 * Overrides java.lang.Object.hashCode() method.
	 * @return Method returns a hashcode value based upon FavoriteList field values.
	 */
	/*@Override public int hashCode(){
		int fieldHashCode = nameFavList.hashCode() + favListType.hashCode() + favList.hashCode();
		return fieldHashCode;
	}*/
	
	/**
	 * Method for displaying values of the FavoriteList fields.
	 * Overrides java.lang.Object.toString() method.
	 * @return Method returns concatenated string of the class field values.
	 */
	@Override public String toString(){
		String tempString = new String();
		tempString = "Travel Planner Application Favorite List Field Values: "
					+ "\nFavorite List Name: " + nameFavList 
					+ "\nFavorite List Type: " + favListType;
		return tempString;
	}
	
	/**
	 * Method for checking the validity of FavoriteList object field values.
	 * @return Method returns false if a FavoriteList object field value has an invalid value, 
	 * or returns true if validated field values are set to valid values.
	 */
	public boolean validate(){
		if (nameFavList.isEmpty()) return false;
		if (favListType.isEmpty()) return false;
		return true;
	}
}
