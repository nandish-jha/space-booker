package com.space_booker.model;


public class Booker extends User{
    /**
     * Constructor method for end user
     * @param name
     * @param email
     * @param password
     */
    public Booker(String name, String email, String password) {
        super(name, email, password);
        this.type = User.userType.USER;
    }
}
