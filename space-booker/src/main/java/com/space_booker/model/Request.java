package com.space_booker.model;

/*
Ben Schubert BES936 11298746

Data class representing a booking request
Requests specify a specific table, date and time and
can be accepted, declined or edited by an administrator
 */
public class Request {
    public enum requestStatus {PENDING, ACCEPTED, DECLINED}

    private Booker user;

    private Table table;
    private String date;
    private String time;
    private requestStatus status;

    /* Constructor */
    public Request(Booker user, Table table, String date, String time) {
        this.user = user;
        this.table = table;
        this.date = date;
        this.time = time;

        this.status = requestStatus.PENDING;
    }

    /* Getters + setters */
    public Booker getUser() {return user;}
    public Table getTable() {return table;}
    public String getDate() {return date;}
    public String getTime() {return time;}
    public requestStatus getStatus() {return status;}
    public void setStatus(requestStatus s) {status = s;}

    /* Displays request data as a String */
    public String toString() {
        //TODO: Display table and booker info when they are implemented
        return  date + "    " + time + "    " + status.toString();
    }
}
