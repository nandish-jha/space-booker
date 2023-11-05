package com.space_booker.view;

import com.space_booker.model.Booking;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/*
Composite widget used in AdminView

Displays info about a request and provides options to accept or decline it
 */
public class RequestView extends StackPane {
    private Booking request;

    //GUI widgets
    private Label table;
    private Label date;
    private Label time;
    private Label booker;

    private Button acceptButton;
    private Button declineButton;

    /* Constructor */
    public RequestView() {
        //Initialize objects
        table = new Label("Table:");
        date = new Label("Date:");
        time = new Label("Time:");
        booker = new Label("Requested by:");

        acceptButton = new Button("Accept");
        declineButton = new Button("Decline");

        //GUI setup
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color : rgb(200, 200, 200);");
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(20);
        buttonBox.getChildren().addAll(acceptButton, declineButton);
        root.getChildren().addAll(table, date, time, booker, buttonBox);
        this.getChildren().add(root);
    }

    public void setRequest(Booking r) {
        if (r == null) return;

        request = r;
        table.setText("Table: " + request.getTableID());
        date.setText("Date: " + request.getDate());
        time.setText("Time: " + request.getTime());
        booker.setText("Requested by: " + request.getUser().getEmail());
    }

    /* Sets event handler for acceptButton */
    public void setOnAccept(EventHandler<ActionEvent> e) {acceptButton.setOnAction(e);}

    /* Sets event handler for declineButton */
    public void setOnDecline(EventHandler<ActionEvent> e) {declineButton.setOnAction(e);}

}
