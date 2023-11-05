package com.space_booker.tests;

import com.space_booker.model.SpaceBookerModel;
import com.space_booker.model.User;

public class AccountCreationTests {
    private static void testCreation(SpaceBookerModel testModel) {//
        if (testModel.addNewUser(User.userType.USER, "Nandish Jha", "naj474@usask.ca", "iloveyou3000"))
            System.out.println("addNewUser() -> test 1 successful");
        else System.out.println("ERROR: addNewUser() -> Account should be added without any errors");

        if (!testModel.addNewUser(User.userType.USER, "Nandish", "naj474@usask.ca", "iloveyou6000"))
            System.out.println("addNewUser() -> test 2 successful");
        else System.out.println("ERROR: addNewUser() -> Account should not be added, as an account with the same email already exists");
    }

    private static void testLogin(SpaceBookerModel testModel){
        if (testModel.checkIfUserExists("naj474@usask.ca"))
            System.out.println("checkIfUserExists() -> test 1 successful");
        else System.out.println("ERROR: checkIfUserExists() -> Account exists, but method returned false");

        if (!testModel.checkIfUserExists("incorrectEmail"))
            System.out.println("checkIfUserExists() -> test 2 successful");
        else System.out.println("ERROR: checkIfUserExists() -> Account does not exist, but method returned true");

        if (testModel.verifyPassword("naj474@usask.ca", "iloveyou3000"))
            System.out.println("verifyPassword() -> test successful");
        else System.out.println("ERROR: verifyPassword() -> Provided password is correct, but method returned false");
    }

    public static void main(String[] args){
        SpaceBookerModel testObj = new SpaceBookerModel();
        testCreation(testObj);
        testLogin(testObj);
    }
}
