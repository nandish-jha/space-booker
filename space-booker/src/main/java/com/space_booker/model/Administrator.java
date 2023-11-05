package com.space_booker.model;

/*
User profile for administrators
 */
public class Administrator extends User {
    /**
     * Constructor method for Administrator user
     * @param name
     * @param email
     * @param password
     */
    public Administrator(String name, String email, String password) {
        super(name, email, password);
        this.type = userType.ADMIN;
    }

}
