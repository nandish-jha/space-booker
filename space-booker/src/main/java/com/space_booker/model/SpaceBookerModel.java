package com.space_booker.model;

import com.space_booker.view.ModelListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/*
Main MVC model class

Contains data used by the views, edited by the controller
 */
public class SpaceBookerModel {
    public User currentUser;

    ArrayList<User> users;
    ArrayList<Booking> bookings;

    //List of views to notify when the model changes
    ArrayList<ModelListener> subscribers;

    String userDatabasePath = ".\\user_database.dat";
    String bookingDatabasePath = ".\\booking_database.dat";

    public SpaceBookerModel() {

        users = new ArrayList<User>();
        User[] userArray = SerializationModule.LoadUsers(userDatabasePath);
        users.addAll(Arrays.asList(userArray));

        // add a default admin account
        users.add(new Administrator("admin", "admin@spacebooker.ca", "admin"));

        bookings = new ArrayList<Booking>();
        Booking[] bookingArray = SerializationModule.LoadBookings(bookingDatabasePath);
        bookings.addAll(Arrays.asList(bookingArray));

        subscribers = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {return users;}
    public boolean checkIfUserExists(String email){
        return getUserByEmail(email) != null;
    }

    /**
     * Helper method for checkIfUserExists
     * @param email -> user email
     * @return -> User object
     */
    private User getUserByEmail(String email) {
        for (User user : users) {
            if (email.equals(user.getEmail()))
                return user;
        }
        return null;
    }

    public boolean verifyPassword(String email, String password){
        User user = getUserByEmail(email);
        if (user == null)
            throw new IllegalArgumentException("User does not exist in the collection, unable to verify password");
        return user.verifyPassword(password);
    }

    /**
     * Adds a new user to collection of users. Checks if email already exists
     * @param userType -> defines user type
     * @param name -> user name
     * @param email -> email
     * @param password -> password defined by the user
     * @return boolean -> true if user is added, false if it already exists
     */
    public boolean addNewUser(User.userType userType, String name, String email, String password) {

        User newUser;

        switch (userType){
            case USER -> newUser = new Booker (name, email, password);

            case ADMIN -> newUser = new Administrator(name, email, password);

            default -> throw new IllegalArgumentException();
        }

        if (checkIfUserExists(newUser.getEmail()))
            return false;

        users.add(newUser);

        User[] userArray = new User[users.size()];

        for(int i = 0; i < users.size(); i ++){
            userArray[i] = users.get(i);
        }
        SerializationModule.SaveUsers(userArray, userDatabasePath);

        notifySubscribers();
        return true;
    }

    public boolean isUserAdmin(String email){
        User user = getUserByEmail(email);

        if (user == null) return false;
        return user.isAdmin();
    }
    public ArrayList<Booking> getBookings() {return bookings;}

    public void addSubscriber(ModelListener sub) {
        subscribers.add(sub);}
    public void notifySubscribers() {
        User[] userArray = users.toArray(new User[0]);
        Booking[] bookingArray = bookings.toArray(new Booking[0]);
        SerializationModule.SaveUsers(userArray, userDatabasePath);
        SerializationModule.SaveBookings(bookingArray, bookingDatabasePath);

        subscribers.forEach(ModelListener::modelChanged);}
}
