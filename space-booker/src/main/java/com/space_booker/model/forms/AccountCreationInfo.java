package com.space_booker.model.forms;

public class AccountCreationInfo extends AccountLoginInfo {
    private final String _name;
    private final String _confirmPassword;

    /**
     * @param name
     * @param email
     * @param password
     *
     */
    public AccountCreationInfo(String name, String email, String password, String confirmPassword){
        super(email, password);
        _name = name;
        _confirmPassword = confirmPassword;
    }

    public String getName(){
        return _name;
    }
    public String getConfirmPassword(){
        return _confirmPassword;
    }
}
