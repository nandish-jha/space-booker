package com.space_booker.controller;

import com.space_booker.model.*;
import com.space_booker.model.forms.AccountCreationInfo;
import com.space_booker.model.forms.AccountLoginInfo;
import javafx.scene.control.Alert;
import javafx.scene.control.MultipleSelectionModel;

public class SpaceBookerController {

    private SpaceBookerModel model;
    private InteractionModel iModel;

    public SpaceBookerController() {}

    /* Set model classes modified by the controller */
    public void setModel(SpaceBookerModel m) {model = m;}
    public void setIModel(InteractionModel m) {iModel = m;}

    public void selectRequest(MultipleSelectionModel<Booking> r) {
        iModel.setAdminSelectedRequest(r.getSelectedItem());
        iModel.notifySubscribers();
    }

    public void acceptRequest(Booking r) {
        if (r.getStatus() != Booking.BookingStatus.PENDING) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Cannot accept a request that is not pending");
            a.show();
            return;
        }
        r.setStatus(Booking.BookingStatus.APPROVED);
        Mailer.sendNotification(r.getUser(), r);
        model.notifySubscribers();
    }

    public void declineRequest(Booking r) {
        if (r.getStatus() != Booking.BookingStatus.PENDING) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Cannot decline a request that is not pending");
            a.show();
            return;
        }
        r.setStatus(Booking.BookingStatus.DECLINE);
        Mailer.sendNotification(r.getUser(), r);
        model.notifySubscribers();
    }

    private static void createAndShowAlert(Alert.AlertType type, String message) {
        Alert a = new Alert(type);
        a.setContentText(message);
        a.show();
    }

    /**
     * Checks for error in information acquired from the user and creates a new account
     * @param info form containing user info
     */
    public void createNewAccount(AccountCreationInfo info, boolean isAdmin){
        if (info.getName().equals("")){
            createAndShowAlert(Alert.AlertType.WARNING, "Name not provided");
        }
        if (!validateEmail(info.getEmail())){
            createAndShowAlert(Alert.AlertType.ERROR, "Provided email is incorrect");
            return;
        }
        if (info.getPassword().equals("")){
            createAndShowAlert(Alert.AlertType.ERROR, "No password provided");
            return;
        }
        if (!info.getConfirmPassword().equals(info.getPassword())){
            createAndShowAlert(Alert.AlertType.ERROR, "Passwords do not match");
            return;
        }

        User.userType userType;

        if (isAdmin){
            userType = User.userType.ADMIN;
        }
        else{
            userType = User.userType.USER;
        }

        if (!model.addNewUser(userType, info.getName(), info.getEmail(), info.getPassword())){
            createAndShowAlert(Alert.AlertType.ERROR, "Account with provided email already exists");
        }
        else{
            createAndShowAlert(Alert.AlertType.INFORMATION, "Account creation successful");
            if (isAdmin){
                iModel.TriggerSwitchToAdminView();
            }
        }

        model.notifySubscribers();
        iModel.notifySubscribers();
    }

    /**
     * Checks for error in information acquired from the user and allows user access
     * @param info form containing user login info
     */
    public void verifyLogin(AccountLoginInfo info){
        if (!validateEmail(info.getEmail())){
            createAndShowAlert(Alert.AlertType.ERROR, "Provided email invalid");
            return;
        }

        if (info.getPassword().equals("")){
            createAndShowAlert(Alert.AlertType.ERROR, "No password provided");
            return;
        }

        if (!model.checkIfUserExists(info.getEmail())){
            createAndShowAlert(Alert.AlertType.ERROR, "No account associated with provided email");
            return;
        }
        if (!model.verifyPassword(info.getEmail(), info.getPassword())){
            createAndShowAlert(Alert.AlertType.ERROR, "Incorrect password");
            return;
        }
        createAndShowAlert(Alert.AlertType.INFORMATION, "Login successful");

        if (model.isUserAdmin(info.getEmail())){
            iModel.TriggerSwitchToAdminView();
        }
        else{
            iModel.TriggerSwitchToBookerView();
        }
    }

    private boolean validateEmail(String emailAddress) {
        if (emailAddress.equals(""))
            return false;

        String[] emailDivided = emailAddress.split("@");
        if (emailDivided.length == 1) {
            return false;
        }
        return emailDivided[1].chars().filter(x -> x == '.').count() >= 1;
    }

    public void switchToAdminView(){
        iModel.TriggerSwitchToAdminView();
    }

    public void switchToLoginView(){
        iModel.TriggerSwitchToLoginView();
    }
}

