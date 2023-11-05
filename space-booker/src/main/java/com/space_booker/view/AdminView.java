package com.space_booker.view;

import com.space_booker.controller.SpaceBookerController;
import com.space_booker.model.*;
import com.space_booker.model.forms.AccountCreationInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/*
GUI for admin users

Used for accepting and declining requests, adding new admin users,

 */
public class AdminView extends StackPane implements ModelListener, IModelListener {

    //Model classes
    private SpaceBookerModel model;
    private InteractionModel iModel;

    private Button addNewAdminButton;

    private Button logOutButton;

    private ObservableList<Booking> requests; //Abstract model for requestListView

    //GUI Widgets
    private ListView<Booking> requestListView;
    private com.space_booker.view.RequestView requestView;

    /*
    Constructor
    */
    public AdminView() {
        //Initialize objects
        requests = FXCollections.observableArrayList();
        requestListView = new ListView<Booking>(requests);
        requestView = new RequestView();
        addNewAdminButton = new Button("Add New Admin");
        logOutButton = new Button("Log out");

        //GUI setup
        HBox root = new HBox();
        VBox requestBox = new VBox();
        requestBox.setPrefWidth(500);
        Label requestLabel = new Label("Requests");
        requestLabel.setStyle("-fx-font-size : 20;");

        //Add new admin
        VBox newAccountBox = new VBox();
        newAccountBox.setSpacing(10);
        newAccountBox.getChildren().addAll(addNewAdminButton, logOutButton);

        root.setSpacing(10);
        root.setPadding(new Insets(10));

        requestBox.getChildren().addAll(requestLabel, requestListView, requestView);
        root.getChildren().addAll(requestBox, newAccountBox);
        this.getChildren().add(root);
    }

    /*
    Sets event handlers of requestListView and requestView to handlers in controller
    */
    public void setController(SpaceBookerController controller) {
        requestListView.setOnMousePressed(e -> controller.selectRequest(requestListView.getSelectionModel()));

        requestView.setOnAccept(e -> controller.acceptRequest(iModel.getAdminSelectedRequest()));
        requestView.setOnDecline(e -> controller.declineRequest(iModel.getAdminSelectedRequest()));

        addNewAdminButton.setOnAction(Event -> {
            iModel.TriggerSwitchToCreateAdminView();
        });

        logOutButton.setOnAction(e -> {
            iModel.TriggerSwitchToLoginView();
        });
    }

    /*
    Setter for model
    */
    public void setModel(SpaceBookerModel m) {
        model = m;
        requests.setAll(model.getBookings());
    }

    /* Setter for iModel */
    public void setIModel(InteractionModel m) {iModel = m;}

    /* Updates view when model changes (called by controller) */
    @Override
    public void modelChanged() {
        requests.clear();
        requests.setAll(model.getBookings());
    }

    /* Updates view when iModel changes (called by controller) */
    @Override
    public void iModelChanged() {
        requestView.setRequest(iModel.getAdminSelectedRequest());
    }
}
