package com.space_booker.model;

import java.io.Serializable;

/*
User profile class

Abstract base for bookers and administrators
 */
public abstract class User implements Serializable {
    public enum userType {USER, ADMIN}

    protected userType type; /* DEFINED BY SUBCLASSES */
    private String name;
    private String email;
    private String password;

    protected User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public boolean verifyPassword(String password){
        return this.password.equals(password);
    }
    public boolean equals(User u){
        return u.getEmail().equals(this.email);
    }

    public boolean isAdmin(){
        return type == userType.ADMIN;
    }
}
