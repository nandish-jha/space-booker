package com.space_booker.view;


import com.space_booker.controller.CreateBookingController;
import com.space_booker.model.Booking;
import com.space_booker.model.SpaceBookerModel;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CreateBookingPageView extends StackPane implements ModelListener {

    private SpaceBookerModel bookings;
//the times available
private ObservableList<String> timesbook =  FXCollections.observableArrayList(
       Arrays.asList("8:00-9:00","9:00-10:00", "10:00-11:00", "11:00-12:00", "12:00-13:00", "13:00-14:00", "14:00-15:00", "15:00-16:00", "16:00-17:00"));
    //the courts number

    private ObservableList<String> tabless = FXCollections.observableArrayList(Arrays.asList("Table 1", "Table 2", "Table 3", "Table 4", "Table 5", "Table 6","Table 7","Table 8","Table 9","Table 10"));

    //creating a list view for court
    private ListView<Label> tableView = new ListView<Label>();

    //monday
    ListView<String> mondayview = new ListView<>();
    Label mondayLabel = new Label("                                 MONDAY");
    private final VBox monday = new VBox();
    //tuesday
    ListView<String> tuesdayview = new ListView<>();
    Label tuesdayLabel = new Label("                                 TUESDAY");
    private VBox tuesday = new VBox();
    //wednesday
    ListView<String> wednesdayview = new ListView<>();
    Label wednesdayLabel = new Label("                                 WEDNESDAY");
    private VBox wednesday = new VBox();
    //thursday
    ListView<String> thursdayview = new ListView<>();
    Label thursdayLabel = new Label("                                 THURSDAY");
    private VBox thursday = new VBox();
    //friday
    ListView<String> fridayview = new ListView<>();
    Label fridayLabel = new Label("                                 FRIDAY");
    private VBox friday = new VBox();


    private VBox vBox = new VBox();

    private Button bookingbutton = new Button("     Request    ");


    private HBox hbox = new HBox();

    //an array list to keep the timings
    private ArrayList<String> time = new ArrayList<>();
    private ArrayList<String> day = new ArrayList<>();

    private HashMap<String,ArrayList<String>> time_d = new HashMap<>();

    public CreateBookingPageView(Button mainPage){
        vboxx();
       HBox daysTime = new HBox();
        daysTime.getChildren().addAll(monday,tuesday, wednesday,thursday,friday);
        daysTime.setSpacing(20);
        vBox.getChildren().add(daysTime);
        FillTable();
      //  bookingbutton.maxWidth(450);
        bookingbutton.setFont(Font.font("Arial",20));
     bookingbutton.setStyle("-fx-background-color: #14cdd3");



        vBox.setSpacing(12);
        vBox.getChildren().addAll(bookingbutton,mainPage);


        this.getChildren().addAll(vBox);

    }

    private void FillTable() {

        ObservableList<Label> tableLabelArray = FXCollections.observableArrayList();
        for (String s : tabless) {
            Label table = new Label(s);
            table.setFont(new Font("Arial", 30));
            table.setPadding(new Insets(15, 15, 15, 15));
            tableLabelArray.add(table);
        }
        tableView.setItems(tableLabelArray);
        tableView.prefHeightProperty().bind(Bindings.size(tabless).multiply(60));
        tableView.setOrientation(Orientation.HORIZONTAL);

        vBox.getChildren().add(tableView);


    }

    private void vboxx() {
        mondayview.setItems(timesbook);
        mondayview.prefHeightProperty().bind(Bindings.size(timesbook).multiply(35));
        mondayview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        monday.setSpacing(5);
        monday.getChildren().addAll(mondayLabel,mondayview);
        mondayview.setOnMousePressed(e -> this.getbooks());

        tuesdayview.setItems(timesbook);
        tuesdayview.prefHeightProperty().bind(Bindings.size(timesbook).multiply(35));
        tuesdayview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tuesday.setSpacing(5);
        tuesday.getChildren().addAll(tuesdayLabel , tuesdayview);


        wednesdayview.setItems(timesbook);
        wednesdayview.prefHeightProperty().bind(Bindings.size(timesbook).multiply(35));
        wednesdayview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        wednesday.setSpacing(5);
        wednesday.getChildren().addAll(wednesdayLabel , wednesdayview);


        thursdayview.setItems(timesbook);
        thursdayview.prefHeightProperty().bind(Bindings.size(timesbook).multiply(35));
        thursdayview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        thursday.setSpacing(5);
        thursday.getChildren().addAll( thursdayLabel,thursdayview);

        fridayview.setItems(timesbook);
        fridayview.prefHeightProperty().bind(Bindings.size(timesbook).multiply(35));
        fridayview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        friday.setSpacing(5);
        friday.getChildren().addAll(fridayLabel, fridayview);

    }

    public void setModel(SpaceBookerModel newcreateBookingModel) {this.bookings = newcreateBookingModel;}

    public void setController(CreateBookingController newcreateBookingController){
        bookingbutton.setOnAction(e -> {
            getbooks();
            Booking b= new Booking(tableView.getSelectionModel().getSelectedIndex() + 1, bookings.currentUser, day.get(0), time.get(0), Booking.BookingStatus.PENDING);
            bookings.getBookings().add(b);
            bookings.notifySubscribers();
        });
    }






private void getbooks() {
    time_d = new HashMap<>();

        if (mondayview.getSelectionModel().getSelectedItems().size()>0){
            time = new ArrayList<>();
            day = new ArrayList<>();
           time.addAll(mondayview.getSelectionModel().getSelectedItems());
           day.add("Monday");
            time_d.put("Monday",time);
        }

        if (tuesdayview.getSelectionModel().getSelectedItems().size() > 0){
            time = new ArrayList<>();
            day = new ArrayList<>();
            time.addAll(tuesdayview.getSelectionModel().getSelectedItems());
            day.add("Tuesday");
            time_d.put("Tuesday", time);
        }
        if (wednesdayview.getSelectionModel().getSelectedItems().size() > 0){
            time = new ArrayList<>();
            day = new ArrayList<>();
            time.addAll(wednesdayview.getSelectionModel().getSelectedItems());
            day.add("Wednesday");
            time_d.put("Wednesday", time);
        }

        if (thursdayview.getSelectionModel().getSelectedItems().size() > 0){
            time = new ArrayList<>();
            day = new ArrayList<>();
            time.addAll(thursdayview.getSelectionModel().getSelectedItems());
            day.add("Thursday");
            time_d.put("Thursday", time);
        }

        if (fridayview.getSelectionModel().getSelectedItems().size() > 0){
            time = new ArrayList<>();
            day = new ArrayList<>();
            time.addAll(fridayview.getSelectionModel().getSelectedItems());
            day.add("Friday");
            time_d.put("Friday", time);
        }

        else {
            System.out.println("No time slot selected");
        }

    }




    public void modelChanged() {
        /*
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (bookings.getTable().size() > 1) {
            HashMap<Integer, HashMap<String, ArrayList<String>>> hashMap = new HashMap<>();
            hashMap.put(1, this.time_d);
            bookings.setBookings(hashMap);
            ArrayList<String> tablee = new ArrayList<>();
            tablee.add(bookings.getTable().get(1));
            bookings.setTable(tablee);
        }

        StringBuilder books = new StringBuilder();
        for (int k = 0; k < bookings.getTable().size(); k++) {
            for (Map.Entry<String, ArrayList<String>> entry : bookings.getBookings().get(k+1).entrySet()) {
                books.append("//Table : ").append(bookings.getTable().get(k));
                books.append(" //Day : ").append(entry.getKey());
                books.append(" //Time :").append(entry.getValue());
            }
        }
        alert.setContentText(books.toString());
        alert.show();
        */

    }}





