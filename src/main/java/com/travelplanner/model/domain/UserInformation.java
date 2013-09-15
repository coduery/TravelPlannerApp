package com.travelplanner.model.domain;

import java.io.Serializable;

/**
 * Class to define a User Information object for the Travel Planner Application.
 * Design Pattern: Value Object - an object that defines field values, the state of 
 * the field values, and allows for the analyzing and comparison of field values.
 * @author Gavin Rouleau
 */
public class UserInformation implements Serializable {

    /** Field to store default serial ID. */
    private static final long serialVersionUID = 1L;

    /** Field to store a user's first name. */
    private String firstName;

    /** Field to store a user's middle name. */
    private String middleName;

    /** Field to store a user's last name. */
    private String lastName;

    /** Field to store a user's address. */
    private String address;

    /** Field to store a user's phone number. */
    private String phone;

    /** Field to store a user's email address. */
    private String email;

    /** 
     * Method to get a user's first name.
     * @return Method returns the first name of a user.
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Method to set a user's first name.
     * @param firstname User's first name is set to incoming firstName parameter.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Method to get a user's middle name.
     * @return Method returns the middle name of a user.
     */
    public String getMiddleName() {
        return middleName;
    }
    
    /**
     * Method to set a user's middle name.
     * @param middleName User's middle name is set to incoming middleName parameter.
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    /**
     * Method to get a user's last name.
     * @return Method returns the last name of a user.
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Method to set a user's last name.
     * @param lastName User's last name is set to incoming lastName parameter.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * Method to get a user's address.
     * @return Method returns the address of a user.
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * Method to set a user's address.
     * @param address User's address is set to incoming address parameter.
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * Method to get a user's phone.
     * @return Method returns the phone of a user.
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     * Method to set a user's phone number.
     * @param phone User's phone is set to incoming phone parameter.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     * Method to get a user's email address.
     * @return Method returns the email address of a user.
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Method to set a user's email address.
     * @param email User's email address is set to incoming email parameter.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
