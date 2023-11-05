package com.space_booker.model;

import java.io.Serializable;

public class Booking implements Serializable {
    public enum BookingStatus {APPROVED, PENDING, DECLINE};

    int tableID;
    User user;
    String date;
    String time;
    BookingStatus status;

    //Constructor

    public Booking(int tableID, User userID, String date, String time, BookingStatus status) {
        this.tableID = tableID;
        this.user = userID;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public Booking() {}

    //Getter Setter

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public String detail(){
        return "Booking:    table_id: "+tableID+"     User:"+ user.getEmail() +"     Date: "+date+"     Time:"+time+"     Status:"+status.toString();
    }

    @Override
    public String toString() {
        return detail();
    }
}
