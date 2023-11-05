package com.space_booker.view;
import com.space_booker.model.Booking;
import com.space_booker.model.SpaceBookerModel;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Map;

public class CheckBookingsView extends StackPane implements ModelListener{
    //creating a list view for the bookings
    private ListView<Booking> booking = new ListView<Booking>();
    private SpaceBookerModel bookingm;
    private VBox vBox = new VBox();
    private ObservableList<Booking> requestsList = FXCollections.observableArrayList();

    public void setModel(SpaceBookerModel bookingMdel) {
        this.bookingm = bookingMdel;
        populateBookings();
    }

    public CheckBookingsView(Button main) {
        Button cancelButton = new Button("Cancel Booking");
        cancelButton.setOnAction(e -> {
            if (booking.getSelectionModel().getSelectedItem() == null) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("No request selected");
            } else {
                bookingm.getBookings().remove(booking.getSelectionModel().getSelectedItem());
                bookingm.notifySubscribers();
            }
        });

        vBox.getChildren().addAll(booking, main, cancelButton);
        this.getChildren().add(vBox);
        main.setAlignment(Pos.BOTTOM_LEFT);
    }

    private void populateBookings() {
        requestsList.clear();
        //Only show bookings for current user
        if (bookingm.currentUser == null) {
            return;
        }
        bookingm.getBookings().forEach(b -> {
            if (b.getUser().getEmail().equals(bookingm.currentUser.getEmail())) {
                requestsList.add(b);
            }
        });

        this.booking.setItems(requestsList);
    }

    @Override
    public void modelChanged() {
        populateBookings();
    }
}