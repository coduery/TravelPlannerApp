package com.travelplanner.model.domain;

import java.io.Serializable;

/**Class to define a Favorite Item for the Travel Planner Application Favorite Lists.
 * Design Pattern: Value Object - an object that defines field values, 
 * the state of the field values, and allows for the analyzing and comparison of field values.
 * @author Gavin Rouleau
 */
public class FavoriteItem  implements Serializable {
    
    /** Field to store default serial ID. */
    private static final long serialVersionUID = 1L;

    /** Field to store the Name of a Favorite Item */
    private String nameFavItem;
    
    /** Field to store the Type of a Favorite Item */
    private String favItemType;

    /** Field to store the Location Description of a Favorite Item */
    private String locationDescript;

    /** Field to store the Address of a Favorite Item. */
    private String address;

    /** Field to store the City of a Favorite Item. */
    private String city;

    /** Field to store the State or Province of a Favorite Item. */
    private String stateProvince;

    /** Field to store the Country of a Favorite Item. */
    private String country;

    /** Field to store the Zip Code of a Favorite Item. */
    private String zip;

    /** Field to store the Phone Number of a Favorite Item. */
    private String phone;

    /** Field to store the Miscellaneous Notes of a Favorite Item. */
    private String notes;
    
    /** Field to store the favorite list that is associated with favorite item, for use with hibernate. */    
    private FavoriteList list;
    
    /** Field to store the an integer id of the favorite item, for use with Hibernate */
    private Integer itemId;
    
    /**Method for getting the name of a Favorite Item.
     * @return Method returns FavoriteItem name.
     */
    public String getNameFavItem(){
        return nameFavItem;
    }
    
    /**Method for setting the name of a Favorite Item.
     * @param nItem Favorite Item name is set to incoming nItem parameter.
     */
    public void setNameFavItem(String nItem){
        nameFavItem = nItem;
    }
    
    /**Method for getting the type of a Favorite Item.
     * @return Method returns FavoriteItem type.
     */
    public String getFavItemType(){
        return favItemType;
    }
    
    /**Method for setting the type of a Favorite Item.
     * @param iType Favorite Item type is set to incoming iType parameter.
     */
    public void setFavItemType(String iType){
        favItemType = iType;
    }
    
    /**Method for getting the location description of a Favorite Item.
     * @return Method returns FavoriteItem location description.
     */
    public String getLocationDescript(){
        return locationDescript;
    }
    
    /**Method for setting the location description of a Favorite Item.
     * @param lDescript Favorite Item location description is set to incoming lDescript parameter.
     */
    public void setLocationDescript(String lDescript){
        locationDescript = lDescript;
    }
    
    /**Method for getting the street address of a Favorite Item.
     * @return Method returns FavoriteItem street address.
     */
    public String getAddress(){
        return address;
    }
    
    /**Method for setting the street address of a Favorite Item.
     * @param addr Favorite Item street address is set to incoming addr parameter.
     */
    public void setAddress(String addr){
        address = addr;
    }
    
    /**Method for getting the City of a Favorite Item.
     * @return Method returns FavoriteItem City.
     */
    public String getCity(){
        return city;
    }
    
    /**Method for setting the City of a Favorite Item.
     * @param cty Favorite Item City is set to incoming cty parameter.
     */
    public void setCity(String cty){
        city = cty;
    }
    
    /**Method for getting the State or Province of a Favorite Item.
     * @return Method returns FavoriteItem State or Province.
     */
    public String getStateProvince(){
        return stateProvince;
    }
    
    /**Method for setting the State or Province of a Favorite Item.
     * @param sProv Favorite Item State or Province is set to incoming sProv parameter.
     */
    public void setStateProvince(String sProv){
        stateProvince = sProv;
    }
    
    /**Method for getting the Country of a Favorite Item.
     * @return Method returns FavoriteItem Country.
     */
    public String getCountry(){
        return country;
    }
    
    /**Method for setting the Country of a Favorite Item.
     * @param cntry Favorite Item Country is set to incoming cntry parameter.
     */
    public void setCountry(String cntry){
        country = cntry;
    }
    
    /**Method for getting the zip code of a Favorite Item.
     * @return Method returns FavoriteItem zip code.
     */
    public String getZip(){
        return zip;
    }
    
    /**Method for setting the zip code of a Favorite Item.
     * @param zp Favorite Item zip code is set to incoming zp parameter.
     */
    public void setZip(String zp){
        zip = zp;
    }
    
    /**Method for getting the phone number of a Favorite Item.
     * @return Method returns FavoriteItem phone number.
     */
    public String getPhone(){
        return phone;
    }
    
    /**Method for setting the phone number of a Favorite Item.
     * @param ph Favorite Item phone number is set to incoming ph parameter.
     */
    public void setPhone(String ph){
        phone = ph;
    }
    
    /**Method for getting the notes of a Favorite Item.
     * @return Method returns FavoriteItem notes.
     */
    public String getNotes(){
        return notes;
    }
    
    /**Method for setting the notes of a Favorite Item.
     * @param nts Favorite Item notes is set to incoming nts parameter.
     */
    public void setNotes(String nts){
        notes = nts;
    }
    
    /**Method to get the favorite list associated with favorite item, for use with hibernate.
     * @return Returns the favorite list associated with the favorite item.
     */
    public FavoriteList getList(){
        return list;
    }
        
    /**Method for setting the favorite list associated with the favorite item, for use with hibernate.
     * @param list Incoming parameter that specifies the favorite list to set.
     */    
    public void setList(FavoriteList list){
        this.list = list;
    }
    
    /**
     * Method for getting the itemId of the favorite item for use with Hibernate.
     * @return Returns the itemId of the favorite item.
     */
    public Integer getItemId(){
        return itemId;
    }
    
    /**
     * Method for setting the itemId of the favorite item for use with Hibernate.
     * @param itemId Returns the itemId of the favorite item.
     */
    public void setItemId(Integer itemId){
        this.itemId = itemId;
    }     
    
    /**Method for checking the equality of FavoriteItem object field values.
     * Overrides java.lang.Object.equals(Object obj) method.
     * @param obj Incoming Object obj parameter field values are compared to an 
     * instance of FavoriteItem and its field values.
     * @return Method returns false if a FavoriteItem field value is not equal, 
     * if Object is not an instance of the FavoriteItem class, 
     * or true if all field values are equal.
     */
    @Override public boolean equals(Object obj){
        if (obj instanceof FavoriteItem) {
            FavoriteItem item = (FavoriteItem)obj;
            if (!nameFavItem.equals(item.getNameFavItem())) return false;
            if (!favItemType.equals(item.getFavItemType())) return false;
            if (!locationDescript.equals(item.getLocationDescript())) return false;
            if (!address.equals(item.getAddress())) return false;
            if (!city.equals(item.getCity())) return false;
            if (!stateProvince.equals(item.getStateProvince())) return false;
            if (!country.equals(item.getCountry())) return false;
            if (!zip.equals(item.getZip())) return false;
            if (!phone.equals(item.getPhone())) return false;
            if (!notes.equals(item.getNotes())) return false;
            return true;
        }
        return false;
    }
    
    /**Method for obtaining the hashcode of FavoriteItem object field values.
     * Overrides java.lang.Object.hashCode() method.
     * @return Method returns a hashcode value based upon FavoriteItem field values.
     */
    @Override public int hashCode(){
        int fieldHashCode = nameFavItem.hashCode() + favItemType.hashCode() + 
                            locationDescript.hashCode() + address.hashCode() + 
                            city.hashCode() + stateProvince.hashCode() +
                            country.hashCode() + zip.hashCode() +
                            phone.hashCode() + notes.hashCode();
        return fieldHashCode;
    }
    
    /**Method for displaying values of the FavoriteItem fields.  
     * Overrides java.lang.Object.toString() method.
     * @return Method returns concatenated string of the class field values.
     */
    @Override public String toString(){
        String tempString = new String();
        tempString = "Travel Planner Application Favorite Item Field Values: "
                    + "\nFavorite List Name: " + nameFavItem 
                    + "\nFavorite List Type: " + favItemType
                    + "\nFavorite List Location Description: " + locationDescript
                    + "\nFavorite List Address: " + address
                    + "\nFavorite List City: " + city
                    + "\nFavorite List State or Province: " + stateProvince
                    + "\nFavorite List Country: " + country
                    + "\nFavorite List Zip: " + zip
                    + "\nFavorite List Phone Number: " + phone
                    + "\nFavorite List Miscellaneous Notes: " + notes;
        return tempString;
    }
    
    /**Method for checking the validity of FavoriteItem object field values.
     * @return Method returns false if a FavoriteItem object field value has an invalid value, 
     * or returns true if validated field values are set to valid values.
     */
    public boolean validate(){
        if (nameFavItem == null || nameFavItem.isEmpty()) return false;
        if (favItemType == null || favItemType.isEmpty()) return false;
        return true;
    }
}
