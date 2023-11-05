package com.space_booker.view;

import com.space_booker.controller.CreateBookingController;
import com.space_booker.model.Booking;
import com.space_booker.controller.SpaceBookerController;
import com.space_booker.model.CreateBookingModel;
import com.space_booker.model.SpaceBookerModel;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
        import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class BookerView extends StackPane {
    Button requestBooking = new Button("Request bookings");
    Button requestPending = new Button("Bookings");

    Button logOutButton = new Button("Log out");

    Button contactUsButton = new Button("Contact Us");

    Button MainPage = new Button("Main Page");
    Button MainPageFromCurr = new Button("Main Page");


    VBox vbbox = new VBox();

    SpaceBookerModel createModel;

    CheckBookingsView checkBookingsView;
    CreateBookingPageView createBookingPageView;

    CreateBookingController createBookingController;

    public void setModel(SpaceBookerModel m) {
        createModel = m;

        createModel.addSubscriber(createBookingPageView);
        createModel.addSubscriber(checkBookingsView);

        createBookingPageView.setModel(createModel);
        checkBookingsView.setModel(createModel);

        createBookingController.setModel(createModel);
    }

    public BookerView() {
        createBookingPageView = new CreateBookingPageView(MainPage);

        checkBookingsView = new CheckBookingsView(MainPageFromCurr);

        createBookingController = new CreateBookingController();
        createBookingPageView.setController(createBookingController);

        vbbox.getChildren().addAll(requestBooking, requestPending, logOutButton, contactUsButton);
        vbbox.setAlignment(Pos.CENTER);
        vbbox.setSpacing(30);


        requestBooking.setOnAction(e -> {this.getChildren().clear();this.getChildren().addAll(createBookingPageView);});
        requestBooking.setFont(Font.font("Arial",25));
        requestBooking.setStyle("-fx-background-color: #ae8ad4");

        requestPending.setOnAction(e -> {
            this.getChildren().clear();
            this.getChildren().addAll(checkBookingsView);
            createModel.notifySubscribers();
        });
        requestPending.setFont(Font.font("Arial",25));
       requestPending.setStyle("-fx-background-color: #ae8ad4");

        logOutButton.setFont(Font.font("Arial",25));
        logOutButton.setStyle("-fx-background-color: #ae8ad4");

        contactUsButton.setFont(Font.font("Arial",25));
        contactUsButton.setStyle("-fx-background-color: #ae8ad4");


       MainPage.setOnAction(e -> {goToMainPage();});
        MainPage.setFont(Font.font("Arial",25));
      MainPage.setStyle("-fx-background-color: #14cdd3");


      MainPageFromCurr.setOnAction(e -> {goToMainPage();});
      MainPageFromCurr.setFont(Font.font("Arial",25));
        MainPageFromCurr.setStyle("-fx-background-color: #14cdd3");

            this.getChildren().addAll(vbbox);

    }

    private void goToMainPage() {
        this.getChildren().clear();
        this.getChildren().addAll(vbbox);


    }

    public void setController(SpaceBookerController controller){
        logOutButton.setOnAction(e -> {
            controller.switchToLoginView();
        });
    }

}







