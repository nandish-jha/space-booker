package com.space_booker.model;



import com.space_booker.model.Request;
import com.space_booker.view.ModelListener;
import javafx.scene.control.MultipleSelectionModel;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateBookingModel {

    private ArrayList<ModelListener> subscribers = new ArrayList<>();

    private ArrayList<String> table = new ArrayList<>();
    private HashMap<Integer,HashMap<String,ArrayList<String>>> bookings = new HashMap<>();

    public CreateBookingModel(){}

    public ArrayList<String> getTable() {return table;}


    public HashMap<Integer, HashMap<String,ArrayList<String>>> getBookings() {return bookings;}

    public void addSubscriber(ModelListener sub){this.subscribers.add(sub);}

    public void notifySubscribers() {this.subscribers.forEach(ModelListener::modelChanged);}

    public void setTable(ArrayList<String> table) {this.table = table;}

    public void setBookings(HashMap<Integer, HashMap<String,ArrayList<String>>> bookings) {this.bookings = bookings;}


    }