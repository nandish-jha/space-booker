package com.space_booker.controller;
import com.space_booker.model.CreateBookingModel;
import com.space_booker.model.SpaceBookerModel;

import java.util.ArrayList;
import java.util.HashMap;
public class CreateBookingController {
    SpaceBookerModel createBookingModel = new SpaceBookerModel();
    public void setModel(SpaceBookerModel bookingmodel) {this.createBookingModel = bookingmodel;
    }
    public void handleSelection(String table, HashMap<String,ArrayList<String>> day_time){
        //TODO: Implement this
    }




}
