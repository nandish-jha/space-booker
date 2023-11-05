package com.space_booker.model.forms;

public class AccountLoginInfo {
    private final String _email;
    private final String _password;

    /**
     * Constructor for immutable form containing user registration data
     * @param email
     * @param password
     */
    public AccountLoginInfo(String email, String password){
        _email = email;
        _password = password;
    }

    public String getEmail(){
        return _email;
    }

    public String getPassword(){
        return _password;
    }
}
