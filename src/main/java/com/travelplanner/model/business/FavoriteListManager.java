package com.travelplanner.model.business;

import com.travelplanner.model.domain.FavoriteList;

/**
 * Class to handle favorite list validation.
 * @author Gavin Rouleau
 */
public class FavoriteListManager {

    /**
     * Method for validating a favorite list.
     * @param favoriteList Incoming favorite list to validate.
     * @return Returns a boolean value where true indicated favorite list is valid; false if not.
     */
    public boolean validate(FavoriteList favoriteList) {
        
        boolean isValidList = favoriteList.validate();
        return isValidList;
    }
}