package com.space_booker.view;

import com.space_booker.controller.SpaceBookerController;
import com.space_booker.model.User;
import com.space_booker.model.forms.AccountCreationInfo;
import com.space_booker.model.forms.AccountLoginInfo;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LoginView extends StackPane implements ModelListener, IModelListener {

    protected enum AccountActionType {
        LOGIN,
        CREATE,
    }
    protected VBox _root;

    private Label _viewLabel;

    private TextField _nameField;
    private TextField _emailField;
    private PasswordField _passwordField;
    private PasswordField _confirmPasswordField;

    private Button _actionButton;
    private Button _switchButton;

    protected boolean isAdmin = false;
    protected boolean isSwitchButtonVisible = true;
    protected AccountActionType _accountActionState = AccountActionType.LOGIN;

    public LoginView(){
        _viewLabel = new Label();
        _nameField = new TextField();
        _emailField = new TextField();
        _passwordField = new PasswordField();
        _confirmPasswordField = new PasswordField();

        _actionButton = new Button();
        _switchButton = new Button();
        _switchButton.setOnAction(event -> {
            switch(_accountActionState){
                case LOGIN -> _accountActionState = AccountActionType.CREATE;
                case CREATE -> _accountActionState = AccountActionType.LOGIN;
            }
            addElementsToView();
        });

        // _viewLabel settings
        _viewLabel.setFont(new Font("Arial", 22));

        // _nameField settings
        _nameField.setPromptText("Full name");
        _nameField.setMaxWidth(200);
        _nameField.setAlignment(Pos.CENTER);

        // _email settings
        _emailField.setPromptText("Email");
        _emailField.setMaxWidth(200);
        _emailField.setAlignment(Pos.CENTER);

        // _password settings
        _passwordField.setPromptText("Password");
        _passwordField.setMaxWidth(200);
        _passwordField.setAlignment(Pos.CENTER);

        // _confirmPasswordField settings
        _confirmPasswordField.setPromptText("Confirm password");
        _confirmPasswordField.setMaxWidth(200);
        _confirmPasswordField.setAlignment(Pos.CENTER);

        addElementsToView();
    }

    protected void addElementsToView(){
        VBox fieldsCollection = new VBox();

        switch (_accountActionState) {
            case LOGIN -> {
                _viewLabel.setText("Log into an account");
                _actionButton.setText("Login");
                _switchButton.setText("Create new account");
                fieldsCollection.getChildren().addAll(_emailField, _passwordField);
            }
            case CREATE -> {
                _viewLabel.setText("Create a new account");
                _actionButton.setText("Create account");
                _switchButton.setText("Log into an existing account");
                _switchButton.setVisible(isSwitchButtonVisible);
                fieldsCollection.getChildren().addAll(_nameField, _emailField, _passwordField, _confirmPasswordField);
            }
        }
        fieldsCollection.setAlignment(Pos.CENTER);
        fieldsCollection.setSpacing(5);

        VBox buttonsCollection = new VBox();
        buttonsCollection.getChildren().addAll(_actionButton, _switchButton);
        buttonsCollection.setAlignment(Pos.CENTER);
        buttonsCollection.setSpacing(5);

        _root = new VBox();
        _root.alignmentProperty().setValue(Pos.CENTER);
        _root.setSpacing(20);

        _root.getChildren().addAll(_viewLabel, fieldsCollection, buttonsCollection);
        this.getChildren().add(_root);
    }

    private void ClearFields(){
        _nameField.setText("");
        _emailField.setText("");
        _passwordField.setText("");
        _confirmPasswordField.setText("");
    }

    public AccountLoginInfo getLoginInfo() {
        return new AccountLoginInfo(_emailField.getText(), _passwordField.getText());
    }

    public AccountCreationInfo getCreationInfo() {
        return new AccountCreationInfo(_nameField.getText(), _emailField.getText(), _passwordField.getText(), _confirmPasswordField.getText());
    }

    public void setController(SpaceBookerController controller) {
        _actionButton.setOnAction(e -> {

            switch (_accountActionState){
                case LOGIN -> controller.verifyLogin(getLoginInfo());
                case CREATE -> controller.createNewAccount(getCreationInfo(), isAdmin);
            }
        });
    }
    @Override
    public void iModelChanged() {
        ClearFields();
    }

    @Override
    public void modelChanged() {

    }
}
