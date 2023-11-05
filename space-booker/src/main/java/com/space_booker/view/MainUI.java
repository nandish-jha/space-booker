package com.space_booker.view;

import com.space_booker.controller.SpaceBookerController;
import com.space_booker.model.*;
import javafx.scene.layout.StackPane;

/*
This class is used by SpaceBookerApp.
 It sets up the connections between the different parts of the application
 */
public class MainUI extends StackPane implements IModelListener{

    //Model
    private SpaceBookerModel model;
    private InteractionModel iModel;
    //View classes
    private LoginView loginView;
    private BookerView bookerView;
    private AdminView adminView;
    private CreateAdminView createAdminView;
    //Controller
    private SpaceBookerController controller;

    public MainUI() {
        //Initialize objects
        model = new SpaceBookerModel();
        iModel = new InteractionModel();

        loginView = new LoginView();
        bookerView = new BookerView();
        adminView = new AdminView();
        createAdminView = new CreateAdminView();

        controller = new SpaceBookerController();

        adminView.setModel(model);
        adminView.setIModel(iModel);
        adminView.setController(controller);

        loginView.setController(controller);
        createAdminView.setController(controller);
        bookerView.setController(controller);

        bookerView.setModel(model);

        model.addSubscriber(adminView);
        model.addSubscriber(loginView);
        model.addSubscriber(createAdminView);

        iModel.addSubscriber(adminView);
        iModel.addSubscriber(loginView);
        iModel.addSubscriber(createAdminView);
        iModel.addSubscriber(this);

        controller.setModel(model);
        controller.setIModel(iModel);

        //Set up UI
        this.getChildren().add(loginView);
    }
    @Override
    public void iModelChanged() {
        // View switch
        if (iModel.ShouldSwitchToAdminView()){
            this.getChildren().clear();
            this.getChildren().add(adminView);
        }
        else if (iModel.ShouldSwitchToBookerView()){
            this.getChildren().clear();
            this.getChildren().add(bookerView);
        }
        else if (iModel.ShouldSwitchToCreateAdminView()){
            this.getChildren().clear();
            this.getChildren().add(createAdminView);
        }
        else if (iModel.ShouldSwitchToLoginView()){
            this.getChildren().clear();
            this.getChildren().add(loginView);
        }
    }
}
